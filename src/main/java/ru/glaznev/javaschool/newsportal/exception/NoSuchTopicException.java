package ru.glaznev.javaschool.newsportal.exception;

public class NoSuchTopicException extends RuntimeException {
    public NoSuchTopicException(String message) {
        super(message);
    }
}
