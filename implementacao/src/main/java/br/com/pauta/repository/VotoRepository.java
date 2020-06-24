package br.com.pauta.repository;

import br.com.pauta.model.VotoInput;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface VotoRepository {
  @Select("SELECT CASE                                                                "
    + " WHEN COUNT(*) >= 1 THEN NULL                                                  "
    + " ELSE 0 END                                                                    "
    + " FROM VOTO                                                                     "
    + " WHERE matricula_associado = #{votoInput.matriculaAssociado, jdbcType=INTEGER} "
    + " AND fk_pauta = #{votoInput.idPauta, jdbcType=INTEGER}                         ")
  Long validarVotoAssociado(@Param("votoInput") VotoInput votoInput);

  @Insert("INSERT INTO VOTO (                                "
    + "   matricula_associado,                               "
    + "   valor,                                             "
    + "   fk_pauta                                           "
    + " ) VALUES (                                           "
    + "   #{votoInput.matriculaAssociado, jdbcType=INTEGER}, "
    + "   #{votoInput.valorVoto, jdbcType=VARCHAR},          "
    + "   #{votoInput.idPauta, jdbcType=INTEGER} )           ")
  void inserirVoto(@Param("votoInput") VotoInput votoInput);
}