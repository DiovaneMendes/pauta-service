package br.com.pauta.service;

import br.com.pauta.exception.BancoException;
import br.com.pauta.exception.PautaException;
import br.com.pauta.model.PautaInput;
import br.com.pauta.repository.PautaRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class PautaService {
  private static final String ERRO = "Erro: metodo - ";
  private PautaRepository pautaRepository;

  public String criarPauta(PautaInput pautaInput) {
    try {
      return Optional.ofNullable(pautaRepository.criarPauta(pautaInput))
        .orElseThrow(() -> new PautaException("Houve um erro ao gravar pauta."));
    } catch (DataAccessException erro) {
      log.error(ERRO.concat("criarPauta - ").concat(erro.getMessage()));
      throw new BancoException();
    }
  }
}