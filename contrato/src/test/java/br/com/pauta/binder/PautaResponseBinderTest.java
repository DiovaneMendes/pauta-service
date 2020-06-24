package br.com.pauta.binder;

import br.com.pauta.stub.PautaOutputStub;
import br.com.pauta.stub.PautaResponseStub;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@DisplayName("PautaResponseBinder")
public class PautaResponseBinderTest {
  @Nested
  @DisplayName("Dado que o método [bindToPautaResponse] seja chamado...")
  class ChamadaBindToPautaResponse {
    @Nested
    @DisplayName("Dado que o PautaOutput passado seja indefinido...")
    class PautaOutputIndefinido{
      @Test
      @DisplayName("Deve retornar null")
      public void deveRetornarNull() {
        assertNull(PautaResponseBinder.bindToPautaResponse(null));
      }
    }

    @Nested
    @DisplayName("Dado que o PautaOutput passado seja definido...")
    class PautaOutputDefinido{
      @Test
      @DisplayName("Deve retornar um PautaResponse")
      public void deveRetornarPautaResponse() {
        var parametro = PautaOutputStub.parametroBindToPautaResponse();
        var resultado = PautaResponseBinder.bindToPautaResponse(parametro);
        var esperado = PautaResponseStub.retornoBindToPautaResponse();
        assertEquals(esperado, resultado);
      }
    }
  }
}
