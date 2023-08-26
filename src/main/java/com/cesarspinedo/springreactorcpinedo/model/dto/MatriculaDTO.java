package com.cesarspinedo.springreactorcpinedo.model.dto;

import com.cesarspinedo.springreactorcpinedo.model.Curso;
import com.cesarspinedo.springreactorcpinedo.model.Estudiante;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MatriculaDTO {

    String id;
    LocalDate fechaMatricula;
    EstudianteDTO estudiante;
    List<CursoDTO> cursos;
    Boolean estado;

}
