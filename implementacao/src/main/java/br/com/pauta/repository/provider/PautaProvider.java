package br.com.pauta.repository.provider;

import java.util.Map;

public class PautaProvider {
  public String buscaPorNome(Map params) {
    var nome = params.get("nomePauta");

    return new StringBuilder().append("SELECT p.nome,")
      .append(contarVotos("SIM", "voto_positivo"))
      .append(", ")
      .append(contarVotos("NAO", "voto_negativo"))
      .append(" FROM pauta p ")
      .append(" WHERE p.nome ILIKE '")
      .append(nome)
      .append("'")
      .toString();
  }

  private String contarVotos(String tipo, String nomeColuna) {
    return "(SELECT COUNT(*) FROM voto       "
      + " WHERE fk_pauta=p.id_pauta          "
      + " AND valor ILIKE '" + tipo + "') AS "
      + nomeColuna;
  }
}