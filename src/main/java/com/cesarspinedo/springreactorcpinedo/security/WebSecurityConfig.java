package com.cesarspinedo.springreactorcpinedo.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

//Clase S7
@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final AuthenticationManager authenticationManager;
    private final SecurityContextRepository securityContextRepository;

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http){
        return http
                .exceptionHandling(Customizer.withDefaults())
                /*.authenticationEntryPoint((swe, e) -> Mono.fromRunnable(() -> {
                    swe.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                })).accessDeniedHandler((swe, e) -> Mono.fromRunnable(() -> {
                    swe.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
                }))
                .and()*/
                //.csrf().disable()
                .csrf(ServerHttpSecurity.CsrfSpec::disable) //csrf -> csrf.disable()
                //.formLogin().disable()
                .formLogin(ServerHttpSecurity.FormLoginSpec::disable) //frm -> frm.disable()
                //.httpBasic().disable()
                .httpBasic(Customizer.withDefaults())
                .authenticationManager(authenticationManager)
                .securityContextRepository(securityContextRepository)
                .authorizeExchange( req -> {
                    req.pathMatchers("/login").permitAll();
                    //req.pathMatchers("/v2/**").authenticated()
                    req.anyExchange().authenticated();
                })
                .build();

        //Lista de cambios deprecated -> https://docs.spring.io/spring-security/site/docs/current/api/deprecated-list.html
    }
}
