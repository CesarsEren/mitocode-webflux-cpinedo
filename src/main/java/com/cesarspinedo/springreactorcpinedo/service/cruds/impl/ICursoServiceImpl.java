package com.cesarspinedo.springreactorcpinedo.service.cruds.impl;

import com.cesarspinedo.springreactorcpinedo.model.Curso;
import com.cesarspinedo.springreactorcpinedo.repo.ICursoRepo;
import com.cesarspinedo.springreactorcpinedo.repo.IGenericRepo;
import com.cesarspinedo.springreactorcpinedo.service.cruds.CRUDImpl;
import com.cesarspinedo.springreactorcpinedo.service.cruds.ICursoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ICursoServiceImpl extends CRUDImpl<Curso, String> implements ICursoService {
    private final ICursoRepo repo;

    @Override
    protected IGenericRepo<Curso, String> getRepo() {
        return repo;
    }
}
