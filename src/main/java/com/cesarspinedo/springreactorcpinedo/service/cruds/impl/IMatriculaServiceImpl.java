package com.cesarspinedo.springreactorcpinedo.service.cruds.impl;

import com.cesarspinedo.springreactorcpinedo.model.Curso;
import com.cesarspinedo.springreactorcpinedo.model.Matricula;
import com.cesarspinedo.springreactorcpinedo.model.dto.MatriculaDTO;
import com.cesarspinedo.springreactorcpinedo.repo.ICursoRepo;
import com.cesarspinedo.springreactorcpinedo.repo.IEstudianteRepo;
import com.cesarspinedo.springreactorcpinedo.repo.IGenericRepo;
import com.cesarspinedo.springreactorcpinedo.repo.IMatriculaRepo;
import com.cesarspinedo.springreactorcpinedo.service.cruds.CRUDImpl;
import com.cesarspinedo.springreactorcpinedo.service.cruds.IMatriculaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IMatriculaServiceImpl extends CRUDImpl<Matricula, String> implements IMatriculaService {
    private final IMatriculaRepo iMatriculaRepo;
    private final IEstudianteRepo iEstudianteRepo;
    private final ICursoRepo iCursoRepo;

    @Override
    protected IGenericRepo<Matricula, String> getRepo() {
        return iMatriculaRepo;
    }

    private Mono<Matricula> populateEstudiante(Matricula matricula) {
        return iEstudianteRepo.findById(matricula.getEstudiante().getId())
                .map(estudent -> {
                    matricula.setEstudiante(estudent);
                    return matricula;
                });
    }


    private Mono<Matricula> populateCursos(Matricula matricula) {
        List<Mono<Curso>> lst = matricula.getCursos().stream()
                .map(item -> iCursoRepo.findById(item.getId())
                        .map(dish -> {
                            item.setId(dish.getId());
                            item.setNombre(dish.getNombre());
                            item.setSiglas(dish.getSiglas());
                            item.setEstado(dish.getEstado());
                            return item;
                        })
                ).collect(Collectors.toList());
        return Mono.when(lst).then(Mono.just(matricula));
    }
}
