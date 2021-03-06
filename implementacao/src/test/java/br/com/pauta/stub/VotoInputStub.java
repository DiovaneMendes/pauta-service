package br.com.pauta.stub;

import br.com.pauta.model.VotoInput;

public abstract class VotoInputStub {
  public static VotoInput parametroGravarVotoComponent() {
    return VotoInput.builder()
      .cpfAssociado(345L)
      .nomePauta("woop")
      .valorVoto("SIM")
      .build();
  }

  public static VotoInput parametroInserirVotoComponent() {
    return VotoInput.builder()
      .cpfAssociado(345L)
      .nomePauta("woop")
      .valorVoto("SIM")
      .idPauta(56)
      .build();
  }

  public static VotoInput parametroService() {
    return VotoInput.builder()
      .cpfAssociado(890L)
      .nomePauta("digital")
      .valorVoto("NAO")
      .idPauta(98)
      .build();
  }
}