package br.com.pauta.component;

import br.com.pauta.enuns.TipoValidacaoPautaEnum;
import br.com.pauta.exception.PautaParametroException;
import br.com.pauta.model.PautaInput;
import br.com.pauta.model.PautaOutput;
import br.com.pauta.service.PautaService;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

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
    if (ObjectUtils.isEmpty(nomePauta)) throw new PautaParametroException();
    return pautaService.buscarPorNome(nomePauta);
  }
}