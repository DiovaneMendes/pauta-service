package br.com.pauta.stub;

import br.com.pauta.model.request.VotoRequest;

public abstract class VotoRequestStub {
  public static VotoRequest parametroBindToVotoInput() {
    return VotoRequest.builder()
      .matriculaAssociado(123L)
      .nomePauta("diversidade")
      .valorVoto("SIM")
      .build();
  }
}