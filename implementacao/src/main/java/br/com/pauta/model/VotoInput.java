package br.com.pauta.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VotoInput {
  private Long matriculaAssociado;
  private String nomePauta;
  private String valorVoto;
  private Integer idPauta;
}