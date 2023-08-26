package com.cesarspinedo.springreactorcpinedo.handler;

import com.cesarspinedo.springreactorcpinedo.model.Estudiante;
import com.cesarspinedo.springreactorcpinedo.model.dto.EstudianteDTO;
import com.cesarspinedo.springreactorcpinedo.service.cruds.IEstudianteService;
import com.cesarspinedo.springreactorcpinedo.validator.RequestValidator;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.validation.Validator;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;

import static org.springframework.web.reactive.function.BodyInserters.fromValue;

@Component
@RequiredArgsConstructor
public class EstudianteHandler {

    private final IEstudianteService service;
    private final Validator validator;
    private final RequestValidator requestValidator;

    @Qualifier("defaultMapper")
    private final ModelMapper mapper;


    public Mono<ServerResponse> findAll(ServerRequest req){
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(service.findAll().map(this::convertToDto), EstudianteDTO.class);
    }

    public Mono<ServerResponse> findById(ServerRequest req){
        String id = req.pathVariable("id");

        return service.findById(id)
                .map(this::convertToDto)
                .flatMap(e -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(fromValue(e))
                ).switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> create(ServerRequest req){
        Mono<EstudianteDTO> monoEstudianteDTO = req.bodyToMono(EstudianteDTO.class);

        return monoEstudianteDTO
                .flatMap(requestValidator::validate)
                .flatMap(e -> service.save(this.convertToEntity(e)))
                .map(this::convertToDto)
                .flatMap(e -> ServerResponse
                        .created(URI.create(req.uri().toString().concat("/").concat(e.getId())))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(fromValue(e))
                );
    }

    public Mono<ServerResponse> update(ServerRequest req){
        String id = req.pathVariable("id");

        return req.bodyToMono(EstudianteDTO.class)
                .map(e -> {
                    e.setId(id);
                    return e;
                })
                .flatMap(requestValidator::validate)
                .flatMap(e -> service.update(this.convertToEntity(e), id))
                .map(this::convertToDto)
                .flatMap(e -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(fromValue(e))
                )
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> delete(ServerRequest req){
        String id = req.pathVariable("id");

        return service.delete(id)
                .flatMap(result -> {
                    if(result){
                        return ServerResponse.noContent().build();
                    }else{
                        return ServerResponse.notFound().build();
                    }
                });
    }

    private EstudianteDTO convertToDto(Estudiante model) {
        return mapper.map(model, EstudianteDTO.class);
    }

    private Estudiante convertToEntity(EstudianteDTO dto) {
        return mapper.map(dto, Estudiante.class);
    }
}
