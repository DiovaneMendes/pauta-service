package br.com.pauta.stub;

import br.com.pauta.model.response.PautaResponse;
import br.com.pauta.model.response.VotoResponse;

public abstract class PautaResponseStub {
  public static PautaResponse retornoBindToPautaResponse() {
    return PautaResponse.builder()
      .nome("dividendo")
      .voto(voto())
      .build();
  }

  private static VotoResponse voto() {
    return VotoResponse.builder()
      .negativo(34L)
      .positivo(23L)
      .build();
  }
}
