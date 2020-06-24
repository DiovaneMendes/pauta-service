package br.com.pauta.stub;

import br.com.pauta.associado.model.AssociadoOutput;

public abstract class AssociadoOutputStub {
  public static AssociadoOutput retornoValidarCpf() {
    return AssociadoOutput.builder()
      .status("ABLE_TO_VOTE")
      .build();
  }
}