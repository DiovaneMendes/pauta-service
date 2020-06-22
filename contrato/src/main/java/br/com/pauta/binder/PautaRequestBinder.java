package br.com.pauta.binder;

import br.com.pauta.model.PautaInput;
import br.com.pauta.model.PautaRequest;

import java.util.Optional;

public abstract class PautaRequestBinder {
  public static PautaInput bindToPautaModel(PautaRequest pautaRequest) {
    return Optional.ofNullable(pautaRequest)
      .map(pautaReq -> PautaInput.builder()
        .nomePauta(pautaReq.getNomePauta())
        .build())
      .orElse(null);
  }
}