package br.com.pauta.exception;

import org.springframework.http.HttpStatus;

public class PautaCadastradaException extends ApiException {
  private String mensagem;

  public PautaCadastradaException(String mensagem){
    super(mensagem);
    this.mensagem = mensagem;
  }

  @Override
  public Long getCodigo() {
    return 409L;
  }

  @Override
  public String getMensagem() {
    return this.mensagem;
  }

  @Override
  public HttpStatus getHttpStatus() {
    return HttpStatus.CONFLICT;
  }
}