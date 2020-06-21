package br.com.pauta.exception;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@Slf4j
@ControllerAdvice
public class ExceptionHandlers {

  @ResponseBody
  @ExceptionHandler(Exception.class)
  public ApiExceptionModel handleInternalError(Exception error, HttpServletResponse response) {
    log.error("ERRO INTERNO NO SISTEMA: ".concat(error.getMessage()));
    response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
    return  ApiExceptionModel.builder()
      .codigo(500L)
      .mensagem("Erro interno no sistema. Caso o problema persista entre em contato com a central de serviços")
      .build();
  }

  @ResponseBody
  @ExceptionHandler(ApiException.class)
  public ApiExceptionModel handleApiException(ApiException error, HttpServletResponse response) {
    log.error("ERRO: ".concat(error.getMensagem()));
    response.setStatus(error.getHttpStatus().value());
    return ApiExceptionModel.builder()
      .codigo(error.getCodigo())
      .mensagem(error.getMensagem())
      .build();
  }
}
