package br.com.pauta.binder;

import br.com.pauta.stub.SessaoInputStub;
import br.com.pauta.stub.SessaoRequestStub;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@DisplayName("SessaoRequestBinder")
public class SessaoRequestBinderTest {
  @Nested
  @DisplayName("Dado que o método [bindToSessaoInput] seja chamado...")
  class ChamadaBindToSessaoInput{
    @Nested
    @DisplayName("Dado que o SessaoRequest passado seja indefinido...")
    class SessaoRequestIndefinido{
      @Test
      @DisplayName("Deve retornar null")
      public void deveRetornarNull() {
        assertNull(SessaoRequestBinder.bindToSessaoInput(null));
      }
    }

    @Nested
    @DisplayName("Dado que o SessaoRequest passado seja definido...")
    class SessaoRequestDefinido{
      @Nested
      @DisplayName("Dado que SessaoRequest seja definido e tenha [minutos] definido...")
      class SessaoRequestMinutosDefinido{
        @Test
        @DisplayName("Deve retornar SessaoInput")
        public void deveRetornarSessaoInputComMinutos() {
          var parametro = SessaoRequestStub.parametroBindToSessaoInput();
          var resultado = SessaoRequestBinder.bindToSessaoInput(parametro);
          var esperado = SessaoInputStub.retornoBindToSessaoInput();
          assertEquals(esperado, resultado);
        }
      }

      @Nested
      @DisplayName("Dado que SessaoRequest seja definido e tenha [minutos] indefinido...")
      class SessaoRequestMinutosIndefinido{
        @Test
        @DisplayName("Deve retornar SessaoInput com minutos com valor de 1")
        public void deveRetornarSessaoInput() {
          var parametro = SessaoRequestStub.parametroBindToSessaoInput();
          parametro.setMinutos(null);

          var resultado = SessaoRequestBinder.bindToSessaoInput(parametro);
          var esperado = SessaoInputStub.retornoBindToSessaoInput();
          esperado.setMinutos(1L);

          assertEquals(esperado, resultado);
        }
      }
    }
  }
}