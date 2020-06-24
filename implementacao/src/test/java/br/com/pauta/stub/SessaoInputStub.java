package br.com.pauta.stub;

import br.com.pauta.model.SessaoInput;

public abstract class SessaoInputStub {
  public static SessaoInput parametroAbrirSessaoComponent() {
    return SessaoInput.builder()
      .nomePauta("woop")
      .minutos(15L)
      .build();
  }
}