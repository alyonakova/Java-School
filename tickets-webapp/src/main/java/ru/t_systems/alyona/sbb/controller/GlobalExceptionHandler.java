package ru.t_systems.alyona.sbb.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {NoHandlerFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handlePageNotFoundError(Model model, NoHandlerFoundException e) {
        model.addAttribute("errorMessage", "Error 404: Requested page not found.");
        return "error";
    }

    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleOtherException(Model model, Exception exception) {
        log.error("Unexpected server error", exception);
        model.addAttribute("errorMessage", exception.getMessage());
        return "error";
    }
}
