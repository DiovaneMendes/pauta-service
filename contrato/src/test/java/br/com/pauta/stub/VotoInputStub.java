package br.com.pauta.stub;

import br.com.pauta.model.VotoInput;

public abstract class VotoInputStub {
  public static VotoInput retornoBindToVotoInput() {
    return VotoInput.builder()
      .matriculaAssociado(123L)
      .nomePauta("diversidade")
      .valorVoto("SIM")
      .build();
  }
}