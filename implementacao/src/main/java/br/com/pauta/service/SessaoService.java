package br.com.pauta.service;

import br.com.pauta.exception.BancoException;
import br.com.pauta.model.SessaoInput;
import br.com.pauta.repository.SessaoRepository;
import br.com.pauta.util.PautaUtil;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class SessaoService {
  @Autowired
  private SessaoRepository sessaoRepository;
  @Value("${mensagem.erro.service}")
  private String mensagemErro;
  @Value("${sessao.aberta.sucesso}")
  private String mensagemSucesso;

  public String abrirSessao(SessaoInput sessaoInput) {
    try {
      sessaoRepository.abrirSessao(sessaoInput);
      var dataFinalizacao = PautaUtil.formatarData(sessaoInput.getDataFinalizacao());
      return mensagemSucesso.concat(dataFinalizacao);
    } catch (DataAccessException erro) {
      log.error(mensagemErro.concat(" abrirSessao - ").concat(erro.getMessage()));
      throw new BancoException();
    }
  }

  public boolean getSessaoAberta(Integer idPauta) {
    try {
      return Optional.ofNullable(sessaoRepository.getSessaoAberta(idPauta))
        .isPresent();
    } catch (DataAccessException erro) {
      log.error(mensagemErro.concat(" getSessaoAberta - ").concat(erro.getMessage()));
      throw new BancoException();
    }
  }
}