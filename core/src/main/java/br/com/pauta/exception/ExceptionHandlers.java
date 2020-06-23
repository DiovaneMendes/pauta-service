package br.com.pauta.exception;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import javax.validation.UnexpectedTypeException;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class ExceptionHandlers {
  @ExceptionHandler(Exception.class)
  public List<ApiExceptionModel> handleInternalError(Exception error, HttpServletResponse response) {
    log.error("ERRO INTERNO NO SISTEMA: ".concat(error.getMessage()));
    response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
    var apiExceptionModel = ApiExceptionModel.builder()
      .codigo(500L)
      .mensagem("Erro interno no sistema. Caso o problema persista entre em contato com a central de serviços")
      .build();

    return List.of(apiExceptionModel);
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

  @ExceptionHandler(ServletRequestBindingException.class)
  public ApiExceptionModel handleSpringBindValidationException(ServletRequestBindingException error,
                                                                     HttpServletResponse response) {
    response.setStatus(HttpStatus.BAD_REQUEST.value());
    return ApiExceptionModel.builder()
        .codigo(400L)
        .mensagem(error.getMessage())
        .build();
  }

  @ExceptionHandler(MissingServletRequestPartException.class)
  public ApiExceptionModel handleMissingMultipartFieldException(MissingServletRequestPartException error,
                                                                      HttpServletResponse response) {
    response.setStatus(HttpStatus.BAD_REQUEST.value());
    return ApiExceptionModel.builder()
      .codigo(400L)
      .mensagem(error.getMessage())
      .campo(error.getRequestPartName())
      .build();
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public List<ApiExceptionModel> handleBeanValidationException(MethodArgumentNotValidException error,
                                                               HttpServletResponse response) {
    response.setStatus(HttpStatus.BAD_REQUEST.value());
    return error.getBindingResult().getFieldErrors().stream()
      .map(fieldError -> ApiExceptionModel.builder()
        .codigo(400L)
        .mensagem(fieldError.getDefaultMessage())
        .campo(fieldError.getField())
        .build()
      )
      .collect(Collectors.toList());
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public List<ApiExceptionModel> handleInvalidBodyException(HttpMessageNotReadableException error,
                                                            HttpServletResponse response) {
    log.error(error.getMessage());
    response.setStatus(HttpStatus.BAD_REQUEST.value());
    Throwable rootCause = error.getRootCause();
    if (rootCause instanceof ApiException) {
      var apiException = (ApiException) rootCause;
      return List.of(ApiExceptionModel.builder()
          .codigo(apiException.getCodigo())
          .mensagem(apiException.getMensagem())
          .build());
    }
    return List.of(ApiExceptionModel.builder()
        .codigo(400L)
        .mensagem("Verifique o corpo da requisição de acordo com o contrato da operação e refaça a operação")
        .build());
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

  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  public ApiExceptionModel handleMethodTypeMismatchException(MethodArgumentTypeMismatchException error,
                                                                   HttpServletResponse response) {
    log.error(error.getMessage());
    response.setStatus(HttpStatus.BAD_REQUEST.value());
    return ApiExceptionModel.builder()
        .codigo(400L)
        .mensagem(error.getMessage())
        .campo(error.getName())
        .build();
  }

  @ExceptionHandler(UnexpectedTypeException.class)
  public ApiExceptionModel handleMethodTypeMismatchException(UnexpectedTypeException error,
                                                                   HttpServletResponse response) {
    log.error(error.getMessage());
    response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
    return ApiExceptionModel.builder()
      .mensagem(error.getMessage())
      .build();
  }
}