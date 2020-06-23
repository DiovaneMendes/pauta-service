package br.com.pauta.stub;

import br.com.pauta.model.PautaInput;

public abstract class PautaInputStub {
  public static PautaInput retornoBindToPautaModel() {
    return PautaInput.builder()
      .nomePauta("dividendo")
      .build();
  }
}