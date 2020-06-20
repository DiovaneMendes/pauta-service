package br.com.pauta.repository;

import br.com.pauta.model.PautaInput;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectKey;

@Mapper
public interface PautaRepository {
//  @SelectKey(
//    keyProperty = "pautaInput.nomePauta",
//    before = true,
//    resultType = String.class,
//    statement = {"SELECT ID_PAUTA.NEXTVAL FROM DUAL"})
  @Insert("INSERT INTO PAUTA (NOME)             "
    + "VALUES( #{pautaInput.nomePauta, jdbcType=NVARCHAR} )")
  String criarPauta(@Param("pautaInput") PautaInput pautaInput);
}
