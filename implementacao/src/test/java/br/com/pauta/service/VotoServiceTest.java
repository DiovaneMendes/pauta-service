package br.com.pauta.service;

import br.com.pauta.exception.BancoException;
import br.com.pauta.exception.VotoException;
import br.com.pauta.model.VotoInput;
import br.com.pauta.repository.VotoRepository;
import br.com.pauta.stub.VotoInputStub;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.dao.DataAccessException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DisplayName("VotoService")
@ExtendWith(SpringExtension.class)
public class VotoServiceTest {
  @Mock
  private VotoRepository votoRepository;
  @InjectMocks
  private VotoService votoService;

  VotoInput parametro = VotoInputStub.parametroService();

  @Nested
  @DisplayName("Dado que o método [validarVotoAssociado] seja chamado...")
  class ChamadaValidarVotoAssociado {
    @Test
    @DisplayName("Deve chamar o método [validarVotoAssociado] do VotoRepository"
      + " passando o VotoInput passado acima")
    public void deveChamarMetodoValidarVotoAssociado_VotoRepository() {
      when(votoRepository.validarVotoAssociado(any())).thenReturn(0L);

      votoService.validarVotoAssociado(parametro);

      verify(votoRepository, times(1)).validarVotoAssociado(parametro);
    }

    @Nested
    @DisplayName("Dado que o retorno do método [validarVotoAssociado] do VotoRepository seja indefinido...")
    class ValidarVotoAssociadoRepositoryIndefinido {
      @Test
      @DisplayName("Deve gerar exceção VotoException com a mensagem: Associado já votou nessa pauta.")
      public void deveGerarExcecaoVotoException() {
        when(votoRepository.validarVotoAssociado(any())).thenReturn(null);

        var erro = assertThrows(VotoException.class, () -> votoService.validarVotoAssociado(parametro));

        assertEquals("Associado já votou nessa pauta.", erro.getMensagem());
      }
    }

    @Nested
    @DisplayName("Dado que o retorno do método [validarVotoAssociado] do VotoRepository seja definido...")
    class ValidarVotoAssociadoRepositoryDefinido {
      @Test
      @DisplayName("Não deve gerar exceção")
      public void naoDeveGerarExcecaoVotoException() {
        when(votoRepository.validarVotoAssociado(any())).thenReturn(0L);

        assertDoesNotThrow(() -> votoService.validarVotoAssociado(parametro));
      }
    }

    @Nested
    @DisplayName("Dado que o método [validarVotoAssociado] do VotoRepository gere um erro...")
    class ValidarVotoAssociadoRepositoryErro {
      @Test
      @DisplayName("Deve gerar exceção: BancoException")
      public void deveGerarExcecaoBancoException() {
        when(votoRepository.validarVotoAssociado(any()))
          .thenThrow(new DataAccessException("ERRO") {
            @Override
            public String getMessage() {
              return super.getMessage();
            }
          });

        assertThrows(BancoException.class, () -> votoService.validarVotoAssociado(parametro));
      }
    }
  }

  @Nested
  @DisplayName("Dado que o método [inserirVoto] seja chamado...")
  class ChamadaInserirVoto {
    @Test
    @DisplayName("Deve chamar método [inserirVoto] do VotoRepository passando o VotoInput passado acima")
    public void deveChamarMetodoInserirVoto_VotoRepository() {
      votoService.inserirVoto(parametro);

      verify(votoRepository, times(1)).inserirVoto(parametro);
    }

    @Test
    @DisplayName("Deve retonar mensagem: Voto gravado com sucesso.")
    public void deveRetornarMensagem() {
      var resultado = votoService.inserirVoto(parametro);

      assertEquals("Voto gravado com sucesso.", resultado);
    }

    @Nested
    @DisplayName("Dado que o método [inserirVoto] do VotoRepository gere algum erro...")
    class InserirVotoRepositoryErro {
      @Test
      @DisplayName("Deve gerar exceção BancoException com a mensagem: Houve um erro ao gravar voto.")
      public void deveGerarExcecaoBancoException() {
        doThrow(new DataAccessException("ERRO") {
          @Override
          public String getMessage() {
            return super.getMessage();
          }
        }).when(votoRepository).inserirVoto(parametro);

        var erro = assertThrows(BancoException.class, () -> votoService.inserirVoto(parametro));

        assertEquals("Houve um erro ao gravar voto.", erro.getMensagem());
      }
    }
  }
}