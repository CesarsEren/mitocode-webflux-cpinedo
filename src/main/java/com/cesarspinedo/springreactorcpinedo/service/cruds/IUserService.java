package com.cesarspinedo.springreactorcpinedo.service.cruds;

import com.cesarspinedo.springreactorcpinedo.model.User;
import com.cesarspinedo.springreactorcpinedo.service.ICRUD;
import reactor.core.publisher.Mono;

public interface IUserService extends ICRUD<User,String> {
    Mono<User> saveHash(User user);
    Mono<com.cesarspinedo.springreactorcpinedo.security.User> searchByUser(String username);
}