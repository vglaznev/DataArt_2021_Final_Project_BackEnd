package ru.glaznev.javaschool.newsportal.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.glaznev.javaschool.newsportal.controller.dto.ErrorDTO;
import ru.glaznev.javaschool.newsportal.exception.ArticleNotFoundException;

@ControllerAdvice
public class ExceptionHandlerController {
    @ExceptionHandler(ArticleNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorDTO handleArticleNotFoundException(ArticleNotFoundException exception) {
        return new ErrorDTO(exception.getMessage());
    }
}
