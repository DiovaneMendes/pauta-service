package br.com.pauta.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SessaoInput {
  private String nomePauta;
  private Long minutos;
  private Integer idPauta;
  private LocalDateTime dataFinalizacao;
}