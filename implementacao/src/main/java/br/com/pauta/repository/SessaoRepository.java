package br.com.pauta.repository;

import br.com.pauta.model.SessaoInput;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SessaoRepository {
  @Insert("INSERT INTO SESSAO (                               "
    + "   data_inicio,                                        "
    + "   data_finalizacao,                                   "
    + "   fk_pauta                                            "
    + " ) VALUES (                                            "
    + "   current_timestamp,                                  "
    + "   #{sessaoInput.dataFinalizacao, jdbcType=TIMESTAMP}, "
    + "   #{sessaoInput.idPauta, jdbcType=VARCHAR} )          ")
  void abrirSessao(@Param("sessaoInput") SessaoInput sessaoInput);

  @Select("SELECT CASE                                  "
    + " WHEN MAX(data_finalizacao) >= current_timestamp "
    + " THEN 'ABERTA' ELSE NULL END                     "
    + " FROM SESSAO                                     "
    + " WHERE fk_pauta= #{idPauta, jdbcType=INTEGER}    ")
  String getSessaoAberta(@Param("idPauta") Integer idPauta);
}