package br.com.pauta.repository;

import br.com.pauta.model.PautaInput;
import br.com.pauta.model.PautaOutput;
import br.com.pauta.repository.provider.PautaProvider;

import org.apache.ibatis.annotations.*;

@Mapper
public interface PautaRepository {
  @Insert("INSERT INTO PAUTA (NOME)                        "
    + " VALUES( #{pautaInput.nomePauta, jdbcType=VARCHAR} )")
  void criarPauta(@Param("pautaInput") PautaInput pautaInput);

  @Select("SELECT CASE                                  "
    + " WHEN COUNT(*) >= 1 THEN NULL                    "
    + " ELSE 0 END                                      "
    + " FROM pauta                                      "
    + " WHERE NOME ILIKE #{nomePauta, jdbcType=VARCHAR} ")
  Integer validarPauta(@Param("nomePauta") String nomePauta);

  @Select("SELECT id_pauta FROM PAUTA                   "
    + " WHERE NOME ILIKE #{nomePauta, jdbcType=VARCHAR} ")
  Integer buscarIdPauta(@Param("nomePauta") String nomePauta);

  @SelectProvider(type = PautaProvider.class, method = "buscaPorNome")
  @Results({
    @Result(column = "nome", property = "nome"),
    @Result(column = "voto_positivo", property = "votoPositivos"),
    @Result(column = "voto_negativo", property = "votoNegativos")
  })
  PautaOutput buscaPorNome(@Param("nomePauta") String nomePauta);
}