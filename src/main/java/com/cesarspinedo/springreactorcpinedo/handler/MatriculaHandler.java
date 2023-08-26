package com.cesarspinedo.springreactorcpinedo.handler;


import com.cesarspinedo.springreactorcpinedo.model.Matricula;
import com.cesarspinedo.springreactorcpinedo.model.dto.MatriculaDTO;
import com.cesarspinedo.springreactorcpinedo.service.cruds.IMatriculaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;

import static org.springframework.web.reactive.function.BodyInserters.fromValue;

@Component
@Slf4j
@RequiredArgsConstructor
public class MatriculaHandler {

    private final IMatriculaService service;

    @Qualifier("matriculaMapper")
    private final ModelMapper mapper;

    public Mono<ServerResponse> findAll(ServerRequest req) {
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(service.findAll().map(this::convertToDto), MatriculaDTO.class);
    }

    public Mono<ServerResponse> findById(ServerRequest req) {
        String id = req.pathVariable("id");

        return service.findById(id)
                .map(this::convertToDto)
                .flatMap(e -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(fromValue(e))
                ).switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> create(ServerRequest req) {
        Mono<MatriculaDTO> monoMatriculaDTO = req.bodyToMono(MatriculaDTO.class);
        //log.info("monoMatriculaDTO: {}", monoMatriculaDTO.subscribe());
        return monoMatriculaDTO
                .flatMap(e -> service.save(this.convertToEntity(e)))
                .map(this::convertToDto)
                .flatMap(e -> ServerResponse
                        .created(URI.create(req.uri().toString().concat("/").concat(e.getId())))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(fromValue(e))
                );
    }

    public Mono<ServerResponse> update(ServerRequest req) {
        String id = req.pathVariable("id");

        return req.bodyToMono(MatriculaDTO.class)
                .map(e -> {
                    e.setId(id);
                    return e;
                })
                .flatMap(e -> service.update(this.convertToEntity(e), id))
                .map(this::convertToDto)
                .flatMap(e -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(fromValue(e))
                )
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> delete(ServerRequest req) {
        String id = req.pathVariable("id");

        return service.delete(id)
                .flatMap(result -> {
                    if (result) {
                        return ServerResponse.noContent().build();
                    } else {
                        return ServerResponse.notFound().build();
                    }
                });
    }

    private MatriculaDTO convertToDto(Matricula model) {
        return mapper.map(model, MatriculaDTO.class);
    }

    private Matricula convertToEntity(MatriculaDTO dto) {
        return mapper.map(dto, Matricula.class);
    }
}