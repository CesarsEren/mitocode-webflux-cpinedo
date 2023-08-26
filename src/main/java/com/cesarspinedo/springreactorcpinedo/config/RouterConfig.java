package com.cesarspinedo.springreactorcpinedo.config;

import com.cesarspinedo.springreactorcpinedo.handler.CursoHandler;
import com.cesarspinedo.springreactorcpinedo.handler.EstudianteHandler;
import com.cesarspinedo.springreactorcpinedo.handler.MatriculaHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterConfig {

    //Functional Endpoints
    @Bean
    public RouterFunction<ServerResponse> routesCurso(CursoHandler handler){
        return route(GET("/v1/curses"), handler::findAll)
                .andRoute(GET("/v1/curses/{id}"), handler::findById)
                .andRoute(POST("/v1/curses"), handler::create)
                .andRoute(PUT("/v1/curses/{id}"), handler::update)
                .andRoute(DELETE("/v1/curses/{id}"), handler::delete);
    }

    @Bean
    public RouterFunction<ServerResponse> routesEstudiante(EstudianteHandler handler){
        return route(GET("/v1/students"), handler::findAll)
                .andRoute(GET("/v1/students/{id}"), handler::findById)
                .andRoute(POST("/v1/students"), handler::create)
                .andRoute(PUT("/v1/students/{id}"), handler::update)
                .andRoute(DELETE("/v1/students/{id}"), handler::delete);
    }

    @Bean
    public RouterFunction<ServerResponse> routesMatricula(MatriculaHandler handler){
        return route(GET("/v1/tuitions"), handler::findAll)
                .andRoute(GET("/v1/tuitions/{id}"), handler::findById)
                .andRoute(POST("/v1/tuitions"), handler::create)
                .andRoute(PUT("/v1/tuitions/{id}"), handler::update)
                .andRoute(DELETE("/v1/tuitions/{id}"), handler::delete);
    }

}
