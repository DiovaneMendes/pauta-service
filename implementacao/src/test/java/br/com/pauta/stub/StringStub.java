package br.com.pauta.stub;

public abstract class StringStub {
  public static String resultadoBuscaPorNomeProvide() {
    return "SELECT p.nome,"
      + "   (SELECT COUNT(*) FROM voto "
      + "     WHERE fk_pauta=p.id_pauta "
      + "     AND valor ILIKE 'SIM') AS voto_positivo, "
      + "   (SELECT COUNT(*) FROM voto "
      + "     WHERE fk_pauta=p.id_pauta "
      + "     AND valor ILIKE 'NÃO') AS voto_negativo "
      + " FROM pauta p "
      + " WHERE p.nome ILIKE 'woop'";
  }
}