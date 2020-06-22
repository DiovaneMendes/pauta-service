package br.com.pauta.service;

import br.com.pauta.enuns.TipoValidacaoPautaEnum;
import br.com.pauta.exception.BancoException;
import br.com.pauta.exception.PautaCadastradaException;
import br.com.pauta.exception.PautaNotFoundException;
import br.com.pauta.model.PautaInput;
import br.com.pauta.model.PautaOutput;
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
  @Value("${mensagem.erro.service}")
  private String mensagemErro;
  @Value("${pauta.gravacao.sucesso}")
  private String mensagemSucesso;

  @Autowired
  private PautaRepository pautaRepository;

  public String criarPauta(PautaInput pautaInput) {
    try {
      pautaRepository.criarPauta(pautaInput);
      return mensagemSucesso;
    } catch (DataAccessException erro) {
      log.error(mensagemErro.concat(" criarPauta - ").concat(erro.getMessage()));
      throw new BancoException();
    }
  }

  public void validarPauta(String nomePauta, TipoValidacaoPautaEnum tipoValidacaoPautaEnum) {
    try {
      if (TipoValidacaoPautaEnum.CADASTRAR.equals(tipoValidacaoPautaEnum)) {
        Optional.ofNullable(pautaRepository.validarPauta(nomePauta))
          .orElseThrow(() ->
            new PautaCadastradaException("Pauta " + nomePauta + " já foi cadastrada!")
          );
      } else {
        Optional.ofNullable(pautaRepository.validarPauta(nomePauta))
          .ifPresent(e -> {
            throw new PautaNotFoundException(formatarMensagemNaoCadastro(nomePauta));
          });
      }
    } catch (DataAccessException erro) {
      log.error(mensagemErro.concat(" validarPauta - ").concat(erro.getMessage()));
      throw new BancoException();
    }
  }

  public Integer buscarIdPauta(String nomePauta) {
    try {
      return Optional.ofNullable(pautaRepository.buscarIdPauta(nomePauta))
        .orElseThrow(() -> new PautaNotFoundException(formatarMensagemNaoCadastro(nomePauta)));
    } catch (DataAccessException erro) {
      log.error(mensagemErro.concat(" buscarIdPauta - ").concat(erro.getMessage()));
      throw new BancoException();
    }
  }

  public PautaOutput buscarPorNome(String nomePauta) {
    try {
      return Optional.ofNullable(pautaRepository.buscarPorNome(nomePauta))
        .orElseThrow(() -> new PautaNotFoundException(formatarMensagemNaoCadastro(nomePauta)));
    } catch (DataAccessException erro) {
      log.error(mensagemErro.concat(" buscarPorNome - ").concat(erro.getMessage()));
      throw new BancoException();
    }
  }

  private String formatarMensagemNaoCadastro(String nomePauta) {
    return String.format("Pauta %s não foi cadastrada!", nomePauta);
  }
}