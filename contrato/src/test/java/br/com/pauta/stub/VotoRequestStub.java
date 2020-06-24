package br.com.pauta.stub;

import br.com.pauta.model.request.VotoRequest;

public abstract class VotoRequestStub {
  public static VotoRequest parametroBindToVotoInput() {
    return VotoRequest.builder()
      .cpfAssociado(123L)
      .nomePauta("diversidade")
      .valorVoto("SIM")
      .build();
  }

  public static VotoRequest parametroGravarVoto() {
    return VotoRequest.builder()
      .cpfAssociado(567L)
      .nomePauta("woop")
      .valorVoto("NAO")
      .build();
  }
}