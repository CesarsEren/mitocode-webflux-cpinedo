package com.cesarspinedo.springreactorcpinedo.model.dto;

import com.cesarspinedo.springreactorcpinedo.model.Curso;
import com.cesarspinedo.springreactorcpinedo.model.Estudiante;
import com.cesarspinedo.springreactorcpinedo.service.ICRUD;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EstudianteDTO {

    private String id;
    private String nombres;
    private String apellidos;
    private String DNI;
    private Integer Edad;
}
