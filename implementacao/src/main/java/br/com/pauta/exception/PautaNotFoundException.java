package br.com.pauta.exception;

import org.springframework.http.HttpStatus;

public class PautaNotFoundException extends ApiException{
  private String mensagem;

  public PautaNotFoundException(String mensagem) {
    super(mensagem);
    this.mensagem = mensagem;
  }

  @Override
  public Long getCodigo() {
    return 404L;
  }

  @Override
  public String getMensagem() {
    return mensagem;
  }

  @Override
  public HttpStatus getHttpStatus() {
    return HttpStatus.NOT_FOUND;
  }
}