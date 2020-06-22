package br.com.pauta.repository;

import br.com.pauta.model.SessaoInput;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;

@Mapper
public interface SessaoRepository {
  @Insert("INSERT INTO SESSAO (data_finalizacao, fk_pauta)  "
    + " VALUES (                                            "
    + " #{sessaoInput.dataFinalizacao, jdbcType=TIMESTAMP}, "
    + " #{sessaoInput.idPauta, jdbcType=VARCHAR} )          ")
  void abrirSessao(@Param("sessaoInput") SessaoInput sessaoInput);

  @Select("SELECT MAX(data_finalizacao) FROM SESSAO              "
    + " WHERE fk_pauta= #{sessaoInput.idPauta, jdbcType=VARCHAR} ")
  LocalDateTime getUltimaDataFinalizacao(@Param("sessaoInput") SessaoInput sessaoInput);
}