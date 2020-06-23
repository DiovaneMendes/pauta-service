package br.com.pauta.stub;

import br.com.pauta.model.PautaOutput;

public abstract class PautaOutputStub {
  public static PautaOutput parametroBindToPautaResponse() {
    return PautaOutput.builder()
      .nome("dividendo")
      .votoNegativos(34L)
      .votoPositivos(23L)
      .build();
  }

  public static PautaOutput parametroBindToVotoResponse() {
    return parametroBindToPautaResponse();
  }
}