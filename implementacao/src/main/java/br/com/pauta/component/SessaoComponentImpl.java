package br.com.pauta.component;

import br.com.pauta.enuns.TipoValidacaoPautaEnum;
import br.com.pauta.exception.SessaoAbertaException;
import br.com.pauta.model.SessaoInput;
import br.com.pauta.service.PautaService;
import br.com.pauta.service.SessaoService;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.function.Function;

@Component
@AllArgsConstructor
public class SessaoComponentImpl implements SessaoComponent{
  private SessaoService sessaoService;
  private PautaService pautaService;

  @Override
  public String abrirSessao(SessaoInput sessaoInput) {
    return validarPautaExistente()
      .andThen(buscarIdPauta())
      .andThen(ajustarTempoSessao())
      .andThen(validarSessaoAberta())
      .andThen(gravarSessao())
      .apply(sessaoInput);
  }

  private Function<SessaoInput, SessaoInput> validarPautaExistente() {
    return (SessaoInput sessaoInput) -> {
      pautaService.validarPauta(sessaoInput.getNomePauta(), TipoValidacaoPautaEnum.BUSCAR);
      return sessaoInput;
    };
  }

  private Function<SessaoInput, SessaoInput> buscarIdPauta() {
    return (SessaoInput sessaoInput) -> {
      var idPauta = pautaService.buscarIdPauta(sessaoInput.getNomePauta());
      sessaoInput.setIdPauta(idPauta);
      return sessaoInput;
    };
  }

  private Function<SessaoInput, SessaoInput> validarSessaoAberta() {
    return (SessaoInput sessaoInput) -> {
      var ultimaDataFinalizacao = sessaoService.getUltimaDataFinalizacao(sessaoInput);
      var dataAtual = LocalDateTime.now();
      if (dataAtual.isBefore(ultimaDataFinalizacao))
        throw new SessaoAbertaException("Já há uma sessão aberta para está pauta!");
      return sessaoInput;
    };
  }

  private Function<SessaoInput, SessaoInput> ajustarTempoSessao() {
    return (SessaoInput sessaoInput) -> {
      var dataFinalizacao = LocalDateTime.now().plusMinutes(sessaoInput.getMinutos());
      sessaoInput.setDataFinalizacao(dataFinalizacao);
      return sessaoInput;
    };
  }

  private Function<SessaoInput, String> gravarSessao() {
    return (SessaoInput sessaoInput) -> sessaoService.abrirSessao(sessaoInput);
  }
}
