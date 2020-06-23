package br.com.pauta.binder;

import br.com.pauta.stub.PautaInputStub;
import br.com.pauta.stub.PautaRequestStub;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@DisplayName("PautaRequestBinder")
public class PautaRequestBinderTest {
  @Nested
  @DisplayName("Dado que o método [bindToPautaModel] seja chamado...")
  class ChamadaBindToPautaModel{
    @Nested
    @DisplayName("Dado que o PautaRequest passado seja indefinida...")
    class PautaRequestIndefinida {
      @Test
      @DisplayName("Deve retornar null")
      public void deveRetornarNull() {
        assertNull(PautaRequestBinder.bindToPautaModel(null));
      }
    }

    @Nested
    @DisplayName("Dado que o PautaRequest passado seja definida...")
    class PautaRequestDefinida {
      @Test
      @DisplayName("Deve retornar um PautaInput")
      public void deveRetornarNull() {
        var parametro = PautaRequestStub.parametroBindToPautaModel();
        var resultado = PautaRequestBinder.bindToPautaModel(parametro);
        var esperado = PautaInputStub.retornoBindToPautaModel();
        assertEquals(esperado, resultado);
      }
    }
  }
}