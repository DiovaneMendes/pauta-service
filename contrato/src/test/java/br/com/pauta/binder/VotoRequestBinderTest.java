package br.com.pauta.binder;

import br.com.pauta.stub.VotoInputStub;
import br.com.pauta.stub.VotoRequestStub;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@DisplayName("VotoRequestBinder")
public class VotoRequestBinderTest {
  @Nested
  @DisplayName("Dado que o método [bindToVotoInput] seja chamado...")
  class ChamadaBindToVotoInput {
    @Nested
    @DisplayName("Dado que o VotoRequest passado seja indefinido...")
    class VotoRequestIndefinida {
      @Test
      @DisplayName("Deve retornar null")
      public void deveRetornarNull() {
        assertNull(VotoRequestBinder.bindToVotoInput(null));
      }
    }

    @Nested
    @DisplayName("Dado que o VotoRequest passado seja definido...")
    class VotoRequestDefinido{
      @Test
      @DisplayName("Deve retornar um VotoInput")
      public void deveRetornarVotoInput() {
        var parametro = VotoRequestStub.parametroBindToVotoInput();
        var resultado = VotoRequestBinder.bindToVotoInput(parametro);
        var esperado = VotoInputStub.retornoBindToVotoInput();
        assertEquals(esperado, resultado);
      }
    }
  }
}