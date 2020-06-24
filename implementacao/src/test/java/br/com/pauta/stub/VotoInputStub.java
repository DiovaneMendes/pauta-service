package br.com.pauta.stub;

import br.com.pauta.model.VotoInput;

public abstract class VotoInputStub {
  public static VotoInput parametroGravarVotoComponent() {
    return VotoInput.builder()
      .matriculaAssociado(345L)
      .nomePauta("woop")
      .valorVoto("SIM")
      .build();
  }

  public static VotoInput parametroInserirVotoComponent() {
    return VotoInput.builder()
      .matriculaAssociado(345L)
      .nomePauta("woop")
      .valorVoto("SIM")
      .idPauta(56)
      .build();
  }
}