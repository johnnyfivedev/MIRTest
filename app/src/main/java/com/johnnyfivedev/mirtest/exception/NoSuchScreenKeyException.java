package com.johnnyfivedev.mirtest.exception;


public class NoSuchScreenKeyException extends RuntimeException {

    public NoSuchScreenKeyException(String screenKey) {
        super("No screen with key " +
                screenKey +
                ". Have you forgot to add key " +
                screenKey +
                " to Navigator? Or forgot to switch Navigator?"
        );
    }
}
