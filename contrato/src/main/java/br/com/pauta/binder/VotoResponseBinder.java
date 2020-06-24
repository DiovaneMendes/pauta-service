package br.com.pauta.binder;

import br.com.pauta.model.PautaOutput;
import br.com.pauta.model.response.VotoResponse;

import java.util.Optional;

public abstract class VotoResponseBinder {
  public static VotoResponse bindToVotoResponse(PautaOutput pautaOutput) {
    return Optional.ofNullable(pautaOutput)
      .map(pautaOut -> VotoResponse.builder()
        .positivo(pautaOut.getVotoPositivos())
        .negativo(pautaOut.getVotoNegativos())
        .build()
      )
      .orElse(null);
  }
}