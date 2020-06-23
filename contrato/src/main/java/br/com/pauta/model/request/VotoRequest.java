package br.com.pauta.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@Builder
@ApiModel
@NoArgsConstructor
@AllArgsConstructor
public class VotoRequest {
  @ApiModelProperty(value = "matriculaAssociado", example = "48053", required = true)
  @NotNull(message = "Matricula do associado é obrigatória.")
  private Long matriculaAssociado;
  @ApiModelProperty(value = "nomePauta", example = "woop", required = true)
  @NotNull(message = "Nome da pauta é obrigatória.")
  private String nomePauta;
  @ApiModelProperty(value = "valorVoto", example = "SIM ou NÃO", required = true)
  @NotNull(message = "Valor do voto é obrigatório.")
  @Pattern(regexp = "SIM|NÃO", message = "Formato inválido utilize SIM ou NÃO")
  private String valorVoto;
}