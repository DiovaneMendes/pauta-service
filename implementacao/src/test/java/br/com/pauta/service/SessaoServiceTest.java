package br.com.pauta.service;

import br.com.pauta.exception.BancoException;
import br.com.pauta.model.SessaoInput;
import br.com.pauta.repository.SessaoRepository;
import br.com.pauta.stub.SessaoInputStub;

import org.junit.jupiter.api.BeforeEach;
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

@DisplayName("SessaoService")
@ExtendWith(SpringExtension.class)
public class SessaoServiceTest {
  @Mock
  private SessaoRepository sessaoRepository;
  @InjectMocks
  private SessaoService sessaoService;


  @Nested
  @DisplayName("Dado que o método [abrirSessao] seja chamado...")
  class ChamadaAbrirSessao {
    SessaoInput parametro = SessaoInputStub.parametroAbrirSessaoService();

    @Test
    @DisplayName("Deve chamar o método [abrirSessao] do SessaoRepository passando o SessaoInput passado acima")
    public void deveChamarMetodoAbrirSessao_SessaRepository() {
      sessaoService.abrirSessao(parametro);

      verify(sessaoRepository, times(1)).abrirSessao(parametro);
    }

    @Test
    @DisplayName("Deve retornar mensagem: "
      + "Sessãoo aberta e deve ser finalizada: 11/11/2020 - 23:15")
    public void deveRetornarMensagem() {
      var resultado = sessaoService.abrirSessao(parametro);

      assertEquals("Sessãoo aberta e deve ser finalizada: 11/11/2020 - 23:15", resultado);
    }

    @Nested
    @DisplayName("Dado que o método [abrirSessao] do SessaoRepository gere algum erro...")
    class AbrirSessaoRepositoryErro {
      @Test
      @DisplayName("Deve gerar exceção: BancoException com a mensagem: "
        + "Houve um erro ao abrir a sessão.")
      public void deveGerarExcecaoBancoException() {
        doThrow(new DataAccessException("ERRO") {
          @Override
          public String getMessage() {
            return super.getMessage();
          }
        }).when(sessaoRepository).abrirSessao(any());

        var erro = assertThrows(BancoException.class,
          () -> sessaoService.abrirSessao(parametro));

        assertEquals("Houve um erro ao abrir a sessão.", erro.getMensagem());
      }
    }
  }

  @Nested
  @DisplayName("Dado que o método [getSessaoAberta] seja chamado...")
  class ChamadaGetSessaoAberta {
    @BeforeEach
    public void setup() {
      when(sessaoRepository.getSessaoAberta(anyInt())).thenReturn("Aberta");
    }

    @Test
    @DisplayName("Deve chamar o método [getSessaoAberta] do SessaoRepository passando o idPauta")
    public void deveChamarMetodoGetSessaoAberta() {
      sessaoService.getSessaoAberta(3);

      verify(sessaoRepository, times(1)).getSessaoAberta(3);
    }

    @Nested
    @DisplayName("Dado que o retorno do método [getSessaoAberta] do SessaoRepository seja definido...")
    class RetornoDefinido {
      @Test
      @DisplayName("Deve retornar true")
      public void deveRetornarTrue() {
        assertTrue(sessaoService.getSessaoAberta(3));
      }

      @Test
      @DisplayName("Deve retornar false")
      public void deveRetornarFalse() {
        when(sessaoRepository.getSessaoAberta(anyInt())).thenReturn(null);
        assertFalse(sessaoService.getSessaoAberta(3));
      }
    }

    @Nested
    @DisplayName("Dado que o método [getSessaoAberta] do SessaoRepository tenha gerado um erro...")
    class GetSessaoAbertaRepositoryErro {
      @Test
      @DisplayName("Deve gerar execeção: BancoException")
      public void deveGerarExcecaoBancoException() {
        when(sessaoRepository.getSessaoAberta(anyInt()))
          .thenThrow(new DataAccessException("ERRO") {
            @Override
            public String getMessage() {
              return super.getMessage();
            }
          });

        assertThrows(BancoException.class, () -> sessaoService.getSessaoAberta(3));
      }
    }
  }
}