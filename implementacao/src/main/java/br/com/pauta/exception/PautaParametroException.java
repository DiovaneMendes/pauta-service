package br.com.pauta.exception;

import org.springframework.http.HttpStatus;

public class PautaParametroException extends ApiException {
  @Override
  public Long getCodigo() {
    return 400L;
  }

  @Override
  public String getMensagem() {
    return "Parametro 'nomePauta' é obrigatório";
  }

  @Override
  public HttpStatus getHttpStatus() {
    return HttpStatus.BAD_REQUEST;
  }
}