package com.example.cli.utils;


import com.example.cli.entity.User;

public class RequestUserHolder {

    private final static ThreadLocal<User> requestHolder = new ThreadLocal<>();

    public static void add(User info) {
        requestHolder.set(info);
    }

    public static User getUser() {
        return requestHolder.get();
    }

    public static void remove() {
        requestHolder.remove();
    }


}
