package com.cesarspinedo.springreactorcpinedo.repo;

import com.cesarspinedo.springreactorcpinedo.model.User;

import reactor.core.publisher.Mono;

public interface IUserRepo extends IGenericRepo<User, String> {

    //@Query("{username: ?}")
    Mono<User> findOneByUsername(String username);
}
