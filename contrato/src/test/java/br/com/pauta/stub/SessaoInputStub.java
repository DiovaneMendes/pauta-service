package br.com.pauta.stub;

import br.com.pauta.model.SessaoInput;

public abstract class SessaoInputStub {
  public static SessaoInput retornoBindToSessaoInput() {
    return SessaoInput.builder()
      .nomePauta("lucro")
      .minutos(45L)
      .build();
  }
}