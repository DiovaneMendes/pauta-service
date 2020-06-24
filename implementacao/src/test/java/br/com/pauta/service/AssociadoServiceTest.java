package br.com.pauta.service;

import br.com.pauta.associado.AssociadoRestClient;
import br.com.pauta.associado.model.AssociadoOutput;
import br.com.pauta.exception.VotoException;
import br.com.pauta.stub.AssociadoOutputStub;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@DisplayName("AssociadoService")
@ExtendWith(SpringExtension.class)
public class AssociadoServiceTest {
  @Mock
  private AssociadoRestClient associadoRestClient;
  @InjectMocks
  private AssociadoService associadoService;

  private AssociadoOutput associadoOutput;

  @BeforeEach
  public void setup() {
    associadoOutput = AssociadoOutputStub.retornoValidarCpf();
    when(associadoRestClient.validaCpf(anyLong())).thenReturn(associadoOutput);
  }

  @Nested
  @DisplayName("Dado que o método [validarCpf] seja chamado...")
  class ChamadaValidarCpf {
    @Test
    @DisplayName("Deve chamar o método [validarCpf] do AssociadoRestClient passando o cpf passado acima")
    public void deveChamarMetodoValidarCpf_AssociadoService() {
      var cpf = 3560695812L;
      associadoService.validarCpf(cpf);

      verify(associadoRestClient, times(1)).validaCpf(cpf);
    }

    @Nested
    @DisplayName("Dado que o retorno do método [validarCpf] AssociadoRestClient seja UNABLE_TO_VOTE")
    class ValidarCpfRestUnable {
      @Test
      @DisplayName("Deve gerar exceção VotoException com a mensagem: Esse cpf não pode votar!")
      public void deveGerarExcecaoVotoException() {
        associadoOutput.setStatus("UNABLE_TO_VOTE");
        when(associadoRestClient.validaCpf(anyLong())).thenReturn(associadoOutput);

        var erro = assertThrows(VotoException.class, () -> associadoService.validarCpf(3560695812L));

        assertEquals("Esse cpf não pode votar!", erro.getMensagem());
      }
    }

    @Nested
    @DisplayName("Dado que o retorno do método [validarCpf] AssociadoRestClient seja ABLE_TO_VOTE")
    class ValidarCpfRestEnable {
      @Test
      @DisplayName("Não deve gerar exceção")
      public void naoDeveGerarExcecao() {
        assertDoesNotThrow(() -> associadoService.validarCpf(3560695812L));
      }
    }
  }
}