package br.com.pauta.associado;

import br.com.pauta.associado.exception.AssociadoRestException;
import br.com.pauta.associado.model.AssociadoOutput;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
@AllArgsConstructor
public class AssociadoRestClient {
  private final static String MENSAGEM_ERRO = "Erro: classe - AssociadoRestClient; metodo - ";
  private RestTemplate restTemplate;

  public AssociadoOutput validaCpf(Long cpf) {
    try {
      return restTemplate.getForEntity(getUri(cpf), AssociadoOutput.class).getBody();
    } catch (RestClientException erro) {
      log.error(MENSAGEM_ERRO.concat(" validaCpf - ").concat(erro.getMessage()));
      throw new AssociadoRestException("CPF fornecido é inválido!");
    }
  }

  private String getUri(Long cpf) {
    return String.format("https://user-info.herokuapp.com/users/%s", cpf);
  }
}