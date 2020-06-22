package br.com.pauta.exception;

import org.springframework.http.HttpStatus;

public class VotoException extends ApiException {
  private String mensagem;

  public VotoException(String mensagem) {
    super(mensagem);
    this.mensagem = mensagem;
  }

  @Override
  public Long getCodigo() {
    return 400L;
  }

  @Override
  public String getMensagem() {
    return mensagem;
  }

  @Override
  public HttpStatus getHttpStatus() {
    return HttpStatus.BAD_REQUEST;
  }
}
