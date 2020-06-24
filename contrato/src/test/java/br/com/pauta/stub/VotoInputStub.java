package br.com.pauta.stub;

import br.com.pauta.model.VotoInput;

public abstract class VotoInputStub {
  public static VotoInput retornoBindToVotoInput() {
    return VotoInput.builder()
      .cpfAssociado(123L)
      .nomePauta("diversidade")
      .valorVoto("SIM")
      .build();
  }

  public static VotoInput parametroGravarVoto() {
    return VotoInput.builder()
      .cpfAssociado(567L)
      .nomePauta("woop")
      .valorVoto("NAO")
      .build();
  }
}