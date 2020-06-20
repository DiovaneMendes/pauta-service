package br.com.pauta.component;

import br.com.pauta.model.PautaInput;
import br.com.pauta.service.PautaService;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PautaComponent {
  private PautaService pautaService;

  public String criarPauta(PautaInput pautaInput) {
    return pautaService.criarPauta(pautaInput);
  }
}