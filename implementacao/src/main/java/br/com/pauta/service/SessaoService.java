package br.com.pauta.service;

import br.com.pauta.exception.BancoException;
import br.com.pauta.model.SessaoInput;
import br.com.pauta.repository.SessaoRepository;
import br.com.pauta.util.PautaUtil;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class SessaoService {
  private final static String MENSAGEM_ERRO = "Erro: classe - SessaoService; metodo - ";
  private final static String MENSAGEM_SUCESSO = "Sessãoo aberta e deve ser finalizada: ";

  @Autowired
  private SessaoRepository sessaoRepository;

  public String abrirSessao(SessaoInput sessaoInput) {
    try {
      sessaoRepository.abrirSessao(sessaoInput);
      var dataFinalizacao = PautaUtil.formatarData(sessaoInput.getDataFinalizacao());
      return MENSAGEM_SUCESSO.concat(dataFinalizacao);
    } catch (DataAccessException erro) {
      log.error(MENSAGEM_ERRO.concat(" abrirSessao - ").concat(erro.getMessage()));
      throw new BancoException("Houve um erro ao abrir a sessão.");
    }
  }

  public boolean getSessaoAberta(Integer idPauta) {
    try {
      return Optional.ofNullable(sessaoRepository.getSessaoAberta(idPauta))
        .isPresent();
    } catch (DataAccessException erro) {
      log.error(MENSAGEM_ERRO.concat(" getSessaoAberta - ").concat(erro.getMessage()));
      throw new BancoException();
    }
  }
}