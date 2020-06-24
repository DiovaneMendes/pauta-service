package br.com.pauta.repository;

import br.com.pauta.repository.provider.PautaProvider;
import br.com.pauta.stub.StringStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("PautaProvider")
public class PautaProviderTest {
  private PautaProvider pautaProvider;

  @BeforeEach
  public void setup() {
    pautaProvider = new PautaProvider();
  }

  @Nested
  @DisplayName("Dado que o método [buscaPorNome] seja chamdo...")
  class ChamadaBuscaPorNome {
    private Map params;

    @BeforeEach
    public void setup() {
      params = new HashMap();
      params.put("nomePauta", "woop");
    }

    @Test
    @DisplayName("Deve retornar query")
    public void deveRetornarQuery() {
      var esperado = remocaoEspaco(StringStub.resultadoBuscaPorNomeProvide());
      var resultado = remocaoEspaco(pautaProvider.buscaPorNome(params));

      assertEquals(esperado, resultado);
    }

    private String remocaoEspaco(String stg) {
      return stg.replaceAll("\\s+", " ");
    }
  }
}