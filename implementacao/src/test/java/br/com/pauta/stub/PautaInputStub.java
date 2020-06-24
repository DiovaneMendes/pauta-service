package br.com.pauta.stub;

import br.com.pauta.model.PautaInput;

public abstract class PautaInputStub {
  public static PautaInput parametroCriarPautaComponent() {
    return PautaInput.builder()
      .nomePauta("woop")
      .build();
  }

  public static PautaInput parametroCriarPautaService() {
    return PautaInput.builder()
      .nomePauta("woop")
      .build();
  }
}