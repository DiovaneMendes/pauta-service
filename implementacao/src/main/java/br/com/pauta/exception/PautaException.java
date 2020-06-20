package br.com.pauta.exception;

import org.springframework.http.HttpStatus;

public class PautaException extends ApiException {
  private String mensagem;

  public PautaException(String mensagem){
    super(mensagem);
    this.mensagem = mensagem;
  }

  @Override
  public Long getCodigo() {
    return 404L;
  }

  @Override
  public String getMensagem() {
    return this.mensagem;
  }

  @Override
  public HttpStatus getHttpStatus() {
    return HttpStatus.BAD_REQUEST;
  }
}