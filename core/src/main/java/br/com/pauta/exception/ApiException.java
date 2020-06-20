package br.com.pauta.exception;

import org.springframework.http.HttpStatus;

public abstract class ApiException extends RuntimeException{
  public ApiException() {
  }

  public ApiException(String mensagem) {
    super(mensagem);
  }

  public abstract Long getCodigo();

  public abstract String getMensagem();

  public abstract HttpStatus getHttpStatus();
}