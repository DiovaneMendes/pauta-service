package br.com.pauta.binder;

import br.com.pauta.model.VotoInput;
import br.com.pauta.model.request.VotoRequest;

import java.util.Optional;

public abstract class VotoRequestBinder {
  public static VotoInput bindToVotoInput(VotoRequest votoRequest) {
    return Optional.ofNullable(votoRequest)
      .map(votoReq -> VotoInput.builder()
        .cpfAssociado(votoReq.getCpfAssociado())
        .nomePauta(votoReq.getNomePauta())
        .valorVoto(votoReq.getValorVoto())
        .build()
      )
      .orElse(null);
  }
}