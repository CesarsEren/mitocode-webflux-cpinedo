package com.cesarspinedo.springreactorcpinedo.service.cruds.impl;

import com.cesarspinedo.springreactorcpinedo.model.Estudiante;
import com.cesarspinedo.springreactorcpinedo.repo.IEstudianteRepo;
import com.cesarspinedo.springreactorcpinedo.repo.IGenericRepo;
import com.cesarspinedo.springreactorcpinedo.service.cruds.CRUDImpl;
import com.cesarspinedo.springreactorcpinedo.service.cruds.IEstudianteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IEstudianteServiceImpl extends CRUDImpl<Estudiante, String> implements IEstudianteService {
    private final IEstudianteRepo repo;

    @Override
    protected IGenericRepo<Estudiante, String> getRepo() {
        return repo;
    }
}
