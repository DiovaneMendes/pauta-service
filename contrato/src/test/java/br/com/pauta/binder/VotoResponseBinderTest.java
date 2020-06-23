package br.com.pauta.binder;

import br.com.pauta.stub.PautaOutputStub;
import br.com.pauta.stub.VotoResponseStub;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@DisplayName("VotoResponseBinder")
public class VotoResponseBinderTest {
  @Nested
  @DisplayName("Dado que o método [bindToVotoResponse] seja chamado...")
  class ChamadaBindToVotoResponse {
    @Nested
    @DisplayName("Dado que o PautaOutput passado seja indefinido...")
    class PautaOutputIndefinida {
      @Test
      @DisplayName("Deve retornar null")
      public void deveRetornarNull() {
        assertNull(VotoResponseBinder.bindToVotoResponse(null));
      }
    }

    @Nested
    @DisplayName("Dado que o PautaOutput passado seja definido...")
    class PautaOutputDefinido{
      @Test
      @DisplayName("Deve retornar um PautaOutput")
      public void deveRetornarVotoInput() {
        var parametro = PautaOutputStub.parametroBindToVotoResponse();
        var resultado = VotoResponseBinder.bindToVotoResponse(parametro);
        var esperado = VotoResponseStub.retornoBindToVotoResponse();

        assertEquals(esperado, resultado);
      }
    }
  }
}