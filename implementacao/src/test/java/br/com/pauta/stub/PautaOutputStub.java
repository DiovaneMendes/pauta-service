package br.com.pauta.stub;

import br.com.pauta.model.PautaOutput;

public abstract class PautaOutputStub {
  public static PautaOutput parametroBuscaPorNomeComponent() {
    return PautaOutput.builder()
      .nome("digital")
      .votoPositivos(12L)
      .votoNegativos(4L)
      .build();
  }

  public static PautaOutput retornoBuscaPorNomeService() {
    return PautaOutput.builder()
      .nome("woop")
      .votoPositivos(34L)
      .votoNegativos(9L)
      .build();
  }
}