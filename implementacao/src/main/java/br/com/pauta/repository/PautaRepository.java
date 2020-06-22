package br.com.pauta.repository;

import br.com.pauta.model.PautaInput;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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
}