package br.com.pauta.stub;

import br.com.pauta.model.response.VotoResponse;

public abstract class VotoResponseStub {
  public static VotoResponse retornoBindToVotoResponse() {
    return VotoResponse.builder()
      .negativo(34L)
      .positivo(23L)
      .build();
  }
}