package br.com.pauta.stub;

import br.com.pauta.model.request.PautaRequest;

public abstract class PautaRequestStub {
  public static PautaRequest parametroBindToPautaModel() {
    return PautaRequest.builder()
      .nomePauta("dividendo")
      .build();
  }
}