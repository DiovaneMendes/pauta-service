package br.com.pauta.exception;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import javax.validation.UnexpectedTypeException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class ExceptionHandlers {
  @ExceptionHandler(Exception.class)
  public ApiExceptionModel handleInternalError(Exception error, HttpServletResponse response) {
    log.error("ERRO INTERNO NO SISTEMA: ".concat(error.getMessage()));
    response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
    return  ApiExceptionModel.builder()
      .codigo(500L)
      .mensagem("Erro interno no sistema. Caso o problema persista entre em contato com a central de serviços")
      .build();
  }

  @ExceptionHandler(ApiException.class)
  public ApiExceptionModel handleApiException(ApiException error, HttpServletResponse response) {
    log.error(error.getMensagem());
    response.setStatus(error.getHttpStatus().value());
    return ApiExceptionModel.builder()
      .codigo(error.getCodigo())
      .mensagem(error.getMensagem())
      .build();
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public List<ApiExceptionModel> handleConstraintViolation(ConstraintViolationException error,
                                                           HttpServletResponse response) {
    log.error(error.getMessage());
    response.setStatus(HttpStatus.BAD_REQUEST.value());
    return error.getConstraintViolations().stream()
      .map(constraintViolation -> {
        String constraintViolationMessage = constraintViolation.getMessage();
        return ApiExceptionModel.builder()
          .codigo(400L)
          .mensagem(constraintViolationMessage)
          .build();
      })
      .collect(Collectors.toList());
  }

  @ExceptionHandler(UnexpectedTypeException.class)
  public List<ApiExceptionModel> handleMethodTypeMismatchException(UnexpectedTypeException error,
                                                                   HttpServletResponse response) {
    log.error(error.getMessage());
    response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
    return Arrays.asList(ApiExceptionModel.builder()
      .mensagem(error.getMessage())
      .build());
  }

  @ExceptionHandler(BindException.class)
  public List<ApiExceptionModel> handleSpringBeanValidationException(BindException error,
                                                                     HttpServletResponse response) {
    log.error(error.getMessage());
    response.setStatus(HttpStatus.BAD_REQUEST.value());
    return error.getBindingResult().getFieldErrors().stream()
      .map(fieldError -> ApiExceptionModel.builder()
        .codigo(400L)
        .mensagem(fieldError.getDefaultMessage())
        .build()
      )
      .collect(Collectors.toList());
  }
}