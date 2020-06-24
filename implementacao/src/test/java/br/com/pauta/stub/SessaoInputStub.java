package br.com.pauta.stub;

import br.com.pauta.model.SessaoInput;

import java.time.LocalDateTime;

public abstract class SessaoInputStub {
  public static SessaoInput parametroAbrirSessaoComponent() {
    return SessaoInput.builder()
      .nomePauta("woop")
      .minutos(15L)
      .build();
  }

  public static SessaoInput parametroAbrirSessaoService() {
    return SessaoInput.builder()
      .nomePauta("woop")
      .minutos(15L)
      .dataFinalizacao(LocalDateTime.of(2020, 11, 11, 23,15, 34))
      .build();
  }
}