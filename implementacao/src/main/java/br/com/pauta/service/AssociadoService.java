package br.com.pauta.service;

import br.com.pauta.associado.AssociadoRestClient;
import br.com.pauta.exception.VotoException;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AssociadoService {
  private final String UNABLE_TO_VOTE = "UNABLE_TO_VOTE";
  private AssociadoRestClient associadoRestClient;

  public void validarCpf(Long cpf) {
    var associadoOutput = associadoRestClient.validaCpf(cpf);
    if (associadoOutput.getStatus().equals(UNABLE_TO_VOTE))
      throw new VotoException("Esse cpf não pode votar!");
  }
}