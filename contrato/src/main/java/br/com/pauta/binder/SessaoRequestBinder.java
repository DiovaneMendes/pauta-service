package br.com.pauta.binder;

import br.com.pauta.model.SessaoInput;
import br.com.pauta.model.request.SessaoRequest;

import java.util.Optional;

public abstract class SessaoRequestBinder {
  public static SessaoInput bindToSessaoInput(SessaoRequest sessaoRequest) {
    return Optional.ofNullable(sessaoRequest)
      .map(sessaoReq -> SessaoInput.builder()
        .nomePauta(sessaoReq.getNomePauta())
        .minutos(validarMinutos(sessaoReq.getMinutos()))
        .build())
      .orElse(null);
  }

  private static Long validarMinutos(Long minutos) {
    return Optional.ofNullable(minutos)
      .orElse(1L);
  }
}
