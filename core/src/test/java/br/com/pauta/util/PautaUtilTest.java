package br.com.pauta.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@DisplayName("PautaUtil")
public class PautaUtilTest {
  @Nested
  @DisplayName("Dado que o método [formatarData] seja chamado...")
  class ChamadaFormatarData {
    @Nested
    @DisplayName("Dado que a data passada seja indefinida...")
    class DataIndefinida {
      @Test
      @DisplayName("Deve retornar null")
      public void deveRetornarNull() {
        assertNull(PautaUtil.formatarData(null));
      }
    }

    @Nested
    @DisplayName("Dado que a data passada seja definida...")
    class DataDefinida {
      @Test
      @DisplayName("Deve retornar dd/MM/yyyy - HH:mm")
      public void deveRetornarDataFormatada() {
        var data = LocalDateTime.of(2020, 11, 11, 23, 15, 59);
        var resultado = PautaUtil.formatarData(data);

        assertEquals("11/11/2020 - 23:15", resultado);
      }
    }
  }
}