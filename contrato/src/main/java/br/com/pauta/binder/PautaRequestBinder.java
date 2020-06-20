package br.com.pauta.binder;

import br.com.pauta.model.PautaInput;
import br.com.pauta.model.PautaRequest;

import java.util.Objects;

public abstract class PautaRequestBinder {
  public static PautaInput bindToPautaModel(PautaRequest pautaRequest) {
    if (Objects.isNull(pautaRequest)) return null;
    return PautaInput.builder()
      .nomePauta(pautaRequest.getNomePauta())
      .build();
  }
}