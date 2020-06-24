package br.com.pauta.associado.exception;

import br.com.pauta.exception.ApiException;
import org.springframework.http.HttpStatus;

public class AssociadoRestException extends ApiException {
  private static String mensagem;

  public AssociadoRestException(String mensagem) {
    super(mensagem);
    AssociadoRestException.mensagem = mensagem;
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