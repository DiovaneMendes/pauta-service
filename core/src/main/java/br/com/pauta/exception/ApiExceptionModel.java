package br.com.pauta.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiExceptionModel {
  private Long codigo;
  private String mensagem;
  private String campo;
}