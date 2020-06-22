package br.com.pauta.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PautaOutput {
  private String nome;
  private Long votoPositivos;
  private Long votoNegativos;
}