package br.com.pauta.service;

import br.com.pauta.exception.BancoException;
import br.com.pauta.exception.VotoException;
import br.com.pauta.model.VotoInput;
import br.com.pauta.repository.VotoRepository;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class VotoService {
  private final static String MENSAGEM_ERRO = "Erro: classe - VotoService; metodo - ";
  private final static String MENSAGEM_SUCESSO = "Voto gravado com sucesso.";

  @Autowired
  private VotoRepository votoRepository;

  public void validarVotoAssociado(VotoInput votoInput) {
    try {
      Optional.ofNullable(votoRepository.validarVotoAssociado(votoInput))
        .orElseThrow(() -> new VotoException("Associado já votou nessa pauta."));
    } catch (DataAccessException erro) {
      log.error(MENSAGEM_ERRO.concat(" validarVotoAssociado - ").concat(erro.getMessage()));
      throw new BancoException();
    }
  }

  public String inserirVoto(VotoInput votoInput) {
    try {
      votoRepository.inserirVoto(votoInput);
      return MENSAGEM_SUCESSO;
    } catch (DataAccessException erro) {
      log.error(MENSAGEM_ERRO.concat(" inserirVoto - ").concat(erro.getMessage()));
      throw new BancoException("Houve um erro ao gravar voto.");
    }
  }
}