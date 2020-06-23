package br.com.pauta.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@ApiModel
@NoArgsConstructor
@AllArgsConstructor
public class PautaRequest {
  @ApiModelProperty(value = "nomePauta", example = "Lucro", required = true)
  @NotNull(message = "Nome da pauta é obrigatória")
  private String nomePauta;
}
