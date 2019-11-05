package com.example.cli.log.service;

public interface ILogService<T,S> {
    T selectById(S id);
}
