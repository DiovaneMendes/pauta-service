package br.com.pauta.component;

import br.com.pauta.enuns.TipoValidacaoPautaEnum;
import br.com.pauta.model.PautaInput;
import br.com.pauta.model.PautaOutput;
import br.com.pauta.service.PautaService;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PautaComponentImpl implements PautaComponent{
  private PautaService pautaService;

  @Override
  public String criarPauta(PautaInput pautaInput) {
    pautaService.validarPauta(pautaInput.getNomePauta(), TipoValidacaoPautaEnum.CADASTRAR);
    return pautaService.criarPauta(pautaInput);
  }

  @Override
  public PautaOutput buscarPorNome(String nomePauta) {
    return pautaService.buscarPorNome(nomePauta);
  }
}