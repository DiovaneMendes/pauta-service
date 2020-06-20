package br.com.pauta.exception;

import org.springframework.http.HttpStatus;

public class BancoException extends ApiException{
  public BancoException() {
    super("Houve um erro com a conexão com o banco.");
  }

  @Override
  public Long getCodigo() {
    return 500L;
  }

  @Override
  public String getMensagem() {
    return "Houve um erro com a conexão com o banco.";
  }

  @Override
  public HttpStatus getHttpStatus() {
    return HttpStatus.INTERNAL_SERVER_ERROR;
  }
}
