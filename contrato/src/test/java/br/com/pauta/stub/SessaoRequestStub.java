package br.com.pauta.stub;

import br.com.pauta.model.request.SessaoRequest;

public abstract class SessaoRequestStub {
  public static SessaoRequest parametroBindToSessaoInput() {
    return SessaoRequest.builder()
      .nomePauta("lucro")
      .minutos(45L)
      .build();
  }

  public static SessaoRequest parametroAbrirSessao() {
    return SessaoRequest.builder()
      .nomePauta("woop")
      .minutos(23L)
      .build();
  }
}