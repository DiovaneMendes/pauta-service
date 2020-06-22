package br.com.pauta.service;

import br.com.pauta.exception.BancoException;
import br.com.pauta.exception.VotoException;
import br.com.pauta.model.VotoInput;
import br.com.pauta.repository.VotoRepository;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class VotoService {
  @Autowired
  private VotoRepository votoRepository;
  @Value("${mensagem.erro.service}")
  private String mensagemErro;
  @Value("${voto.mensagem.sucesso}")
  private String mensagemSucesso;

  public void validarVotoAssociado(VotoInput votoInput) {
    try {
      Optional.ofNullable(votoRepository.validarVotoAssociado(votoInput))
        .orElseThrow(() -> new VotoException("Associado já votou nessa pauta."));
    } catch (DataAccessException erro) {
      log.error(mensagemErro.concat(" validarVotoAssociado - ").concat(erro.getMessage()));
      throw new BancoException();
    }
  }

  public String inserirVoto(VotoInput votoInput) {
    try {
      votoRepository.inserirVoto(votoInput);
      return mensagemSucesso;
    } catch (DataAccessException erro) {
      log.error(mensagemErro.concat(" inserirVoto - ").concat(erro.getMessage()));
      throw new BancoException();
    }
  }
}