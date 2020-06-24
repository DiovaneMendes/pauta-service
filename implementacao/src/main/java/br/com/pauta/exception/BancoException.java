package br.com.pauta.exception;

import org.springframework.http.HttpStatus;

public class BancoException extends ApiException{
  private static String mensagem = "Houve um erro com a conexão com o banco.";
  public BancoException() {
    super(mensagem);
  }

  public BancoException(String mensagem) {
    super(mensagem);
    BancoException.mensagem = mensagem;
  }

  @Override
  public Long getCodigo() {
    return 500L;
  }

  @Override
  public String getMensagem() {
    return mensagem;
  }

  @Override
  public HttpStatus getHttpStatus() {
    return HttpStatus.INTERNAL_SERVER_ERROR;
  }
}
