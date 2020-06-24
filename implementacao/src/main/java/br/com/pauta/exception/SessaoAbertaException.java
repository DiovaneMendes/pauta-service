package br.com.pauta.exception;

import org.springframework.http.HttpStatus;

public class SessaoAbertaException extends ApiException{
  private String mensagem;
  public SessaoAbertaException(String mensagem){
    super(mensagem);
    this.mensagem = mensagem;
  }

  @Override
  public Long getCodigo() {
    return 409L;
  }

  @Override
  public String getMensagem() {
    return mensagem;
  }

  @Override
  public HttpStatus getHttpStatus() {
    return HttpStatus.CONFLICT;
  }
}
