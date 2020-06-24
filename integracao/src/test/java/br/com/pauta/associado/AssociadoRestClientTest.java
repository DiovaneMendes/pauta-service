package br.com.pauta.associado;

import br.com.pauta.associado.model.AssociadoOutput;
import br.com.pauta.stub.AssociadoOutputStub;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DisplayName("AssociadoRestClient")
@ExtendWith(SpringExtension.class)
public class AssociadoRestClientTest {
  @Autowired
  private RestTemplate restTemplate;
  @InjectMocks
  private AssociadoRestClient associadoRestClient;

  @Nested
  @DisplayName("Dado que o método [validaCpf] seja chamado...")
  class ChamadaValidaCpf {

  }
}
