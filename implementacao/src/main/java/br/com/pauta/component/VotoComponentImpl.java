package br.com.pauta.component;

import br.com.pauta.exception.SessaoAbertaException;
import br.com.pauta.model.VotoInput;
import br.com.pauta.service.PautaService;
import br.com.pauta.service.SessaoService;
import br.com.pauta.service.VotoService;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@AllArgsConstructor
public class VotoComponentImpl implements VotoComponent {
  private PautaService pautaService;
  private VotoService votoService;
  private SessaoService sessaoService;

  @Override
  public String gravarVoto(VotoInput votoInput) {
    return buscarIdPauta()
      .andThen(validarSessaoAberta())
      .andThen(validarVotoAssociado())
      .andThen(inserirVoto())
      .apply(votoInput);
  }

  private Function<VotoInput, VotoInput> buscarIdPauta() {
    return (VotoInput votoInput) -> {
      var idPauta = pautaService.buscarIdPauta(votoInput.getNomePauta());
      votoInput.setIdPauta(idPauta);
      return votoInput;
    };
  }

  private Function<VotoInput, VotoInput> validarSessaoAberta() {
    return (VotoInput votoInput) -> {
      var idPauta = votoInput.getIdPauta();
      if (!sessaoService.getSessaoAberta(idPauta))
        throw new SessaoAbertaException("Não há uma sessão aberta para votação.");
      return votoInput;
    };
  }

  private Function<VotoInput, VotoInput> validarVotoAssociado() {
    return (VotoInput votoInput) -> {
      votoService.validarVotoAssociado(votoInput);
      return votoInput;
    };
  }

  private Function<VotoInput, String> inserirVoto() {
    return (VotoInput votoInput) -> votoService.inserirVoto(votoInput);
  }
}