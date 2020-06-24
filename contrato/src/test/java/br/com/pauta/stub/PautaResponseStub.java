package br.com.pauta.stub;

import br.com.pauta.model.response.PautaResponse;
import br.com.pauta.model.response.VotoResponse;

public abstract class PautaResponseStub {
  public static PautaResponse retornoBindToPautaResponse() {
    return PautaResponse.builder()
      .nome("dividendo")
      .voto(voto(23L, 34L))
      .build();
  }

  public static PautaResponse retornoBuscaPorNome() {
    return PautaResponse.builder()
      .nome("digital")
      .voto(voto(31L, 5L))
      .build();
  }

  private static VotoResponse voto(Long positivo, Long negativo) {
    return VotoResponse.builder()
      .positivo(positivo)
      .negativo(negativo)
      .build();
  }
}
