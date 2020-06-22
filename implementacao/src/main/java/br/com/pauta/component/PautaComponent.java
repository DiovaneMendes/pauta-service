package br.com.pauta.component;

import br.com.pauta.model.PautaInput;
import br.com.pauta.model.PautaOutput;

public interface PautaComponent {
  String criarPauta(PautaInput pautaInput);
  PautaOutput buscarPorNome(String nomePauta);
}