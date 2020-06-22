package br.com.pauta.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PautaRequest {
  @NotNull(message = "Nome da pauta é obrigatória")
  private String nomePauta;
}
