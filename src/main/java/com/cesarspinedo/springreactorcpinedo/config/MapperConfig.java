package com.cesarspinedo.springreactorcpinedo.config;

import com.cesarspinedo.springreactorcpinedo.model.Matricula;
import com.cesarspinedo.springreactorcpinedo.model.dto.MatriculaDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class MapperConfig {

    @Bean("defaultMapper")
    public ModelMapper defaultMapper() {
        return new ModelMapper();
    }

    @Bean("matriculaMapper")
    public ModelMapper matriculaMapper() {
        ModelMapper mapper = new ModelMapper();

        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        //Lectura
        TypeMap<Matricula, MatriculaDTO> typeMap1 = mapper.createTypeMap(Matricula.class, MatriculaDTO.class);
        typeMap1.addMapping(e -> e.getEstudiante().getId(), (dest, v) -> dest.getEstudiante().setId((String) v));

        //Escritura
        TypeMap<MatriculaDTO, Matricula> typeMap2 = mapper.createTypeMap(MatriculaDTO.class, Matricula.class);
        typeMap2.addMapping(e -> e.getEstudiante().getId(), (dest, v) -> dest.getEstudiante().setId((String) v));

        return mapper;
    }
}
