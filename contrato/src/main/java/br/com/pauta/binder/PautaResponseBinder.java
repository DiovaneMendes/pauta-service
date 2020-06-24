package br.com.pauta.binder;

import br.com.pauta.model.PautaOutput;
import br.com.pauta.model.response.PautaResponse;

import java.util.Optional;

public abstract class PautaResponseBinder {
  public static PautaResponse bindToPautaResponse(PautaOutput pautaOutput) {
    return Optional.ofNullable(pautaOutput)
      .map(pautaOut -> PautaResponse.builder()
        .nome(pautaOut.getNome())
        .voto(VotoResponseBinder.bindToVotoResponse(pautaOut))
        .build()
      )
      .orElse(null);
  }
}