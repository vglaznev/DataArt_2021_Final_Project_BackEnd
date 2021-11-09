package ru.glaznev.javaschool.newsportal.controller;

import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.glaznev.javaschool.newsportal.controller.dto.ErrorDTO;
import ru.glaznev.javaschool.newsportal.exception.ArticleNotFoundException;
import ru.glaznev.javaschool.newsportal.exception.InputFileException;
import ru.glaznev.javaschool.newsportal.exception.NoSuchTopicException;

@ControllerAdvice
public class ExceptionHandlerController {
    @ExceptionHandler(ArticleNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorDTO handleArticleNotFoundException(ArticleNotFoundException exception) {
        return new ErrorDTO(exception.getMessage());
    }

    @ExceptionHandler(InputFileException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorDTO handleInputFileException(InputFileException exception) {
        return new ErrorDTO(exception.getMessage());
    }

    /*@ExceptionHandler(NoSuchTopicException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorDTO NoSuchTopicException(InputFileException exception) {
        return new ErrorDTO(exception.getMessage());
    }*/
    @ExceptionHandler(ConversionFailedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorDTO handleConversionFailedException(ConversionFailedException exception) {
        return new ErrorDTO(exception.getCause().getMessage());
    }
}
