package br.com.pauta.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VotoRequest {
  @NotNull(message = "Matricula do associado é obrigatória.")
  private Long matriculaAssociado;
  @NotNull(message = "Nome da pauta é obrigatória.")
  private String nomePauta;
  @NotNull(message = "Valor do voto é obrigatório.")
  @Pattern(regexp = "SIM|NÃO", message = "Formato inválido utilize SIM ou NÃO")
  private String valorVoto;
}