package br.com.pauta.service;

import br.com.pauta.exception.BancoException;
import br.com.pauta.exception.PautaCadastradaException;
import br.com.pauta.model.PautaInput;
import br.com.pauta.repository.PautaRepository;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class PautaService {
  private static final String ERRO = "Erro: metodo - ";
  @Value("${pauta.gravacao.sucesso}")
  private String mensagemSucesso;

  @Autowired
  private PautaRepository pautaRepository;

  public String criarPauta(PautaInput pautaInput) {
    try {
      pautaRepository.criarPauta(pautaInput);
      return mensagemSucesso;
    } catch (DataAccessException erro) {
      log.error(ERRO.concat("criarPauta - ").concat(erro.getMessage()));
      throw new BancoException();
    }
  }

  public void validarPauta(PautaInput pautaInput) {
    try {
      Optional.ofNullable(pautaRepository.validarPauta(pautaInput))
        .orElseThrow(() -> new PautaCadastradaException("Está pauta já foi cadastrada!"));
    } catch (DataAccessException erro) {
      log.error(ERRO.concat("validarPauta - ").concat(erro.getMessage()));
      throw new BancoException();
    }
  }
}