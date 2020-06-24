package br.com.pauta.service;

import br.com.pauta.enuns.TipoValidacaoPautaEnum;
import br.com.pauta.exception.BancoException;
import br.com.pauta.exception.PautaCadastradaException;
import br.com.pauta.exception.PautaNotFoundException;
import br.com.pauta.model.PautaInput;
import br.com.pauta.model.PautaOutput;
import br.com.pauta.repository.PautaRepository;
import br.com.pauta.stub.PautaInputStub;
import br.com.pauta.stub.PautaOutputStub;

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

@DisplayName("PautaService")
@ExtendWith(SpringExtension.class)
public class PautaServiceTest {
  @Mock
  private PautaRepository pautaRepository;
  @InjectMocks
  private PautaService pautaService;

  @Nested
  @DisplayName("Dado que o método [criarPauta] seja chamado...")
  class ChamarCriarPauta {
    PautaInput parametro = PautaInputStub.parametroCriarPautaService();

    @Test
    @DisplayName("Deve chamar método [criarPauta] do PautaRepository passando o PautaInput passado acima")
    public void deveChamarCriarPauta_PautaRepository() {
      pautaService.criarPauta(parametro);

      verify(pautaRepository, times(1)).criarPauta(parametro);
    }

    @Test
    @DisplayName("Deve retornar mensagem: Pauta gravada com sucesso.")
    public void deveRetornarMensagem() {
      var resultado = pautaService.criarPauta(parametro);

      assertEquals("Pauta gravada com sucesso.", resultado);
    }

    @Nested
    @DisplayName("Dado que o método [criarPauta] do PautaRepository gere uma erro...")
    class CriarPautaErro {
      @Test
      @DisplayName("Deve gerar exceção: BancoException")
      public void deveGerarBancoException() {
        doThrow(new DataAccessException("ERRO") {
          @Override
          public String getMessage() {
            return super.getMessage();
          }
        }).when(pautaRepository).criarPauta(any());

        assertThrows(BancoException.class, () -> pautaService.criarPauta(parametro));
      }
    }
  }

  @Nested
  @DisplayName("Dado que o método [validarPauta] seja chamado...")
  class ChamadaValidarPauta {
    @Nested
    @DisplayName("Dado que o TipoValidacaoPautaEnum passado seja CADASTRAR...")
    class TipoValidacaoPautaEnumSejaCadastrar {
      @Nested
      @DisplayName("Dado que o retorno do método [validarPauta] do PautaRepository seja null...")
      class ValidarPautaRepositoryRetornarNull {
        @Test
        @DisplayName("Deve gerar exceção: PautaCadastradaException")
        public void gerarExcecaoPautaCadastradaException() {
          when(pautaRepository.validarPauta(anyString())).thenReturn(null);

          assertThrows(PautaCadastradaException.class,
            () -> pautaService.validarPauta("woop", TipoValidacaoPautaEnum.CADASTRAR));
        }
      }

      @Nested
      @DisplayName("Dado que o retorno do método [validarPauta] do PautaRepository não seja null...")
      class ValidarPautaRepositoryRetornarZero {
        @Test
        @DisplayName("Não deve gerar exceção: PautaCadastradaException")
        public void naoGerarExcecaoPautaCadastradaException() {
          when(pautaRepository.validarPauta(anyString())).thenReturn(0);

          assertDoesNotThrow(() -> pautaService.validarPauta("woop", TipoValidacaoPautaEnum.CADASTRAR));
        }
      }
    }

    @Nested
    @DisplayName("Dado que o TipoValidacaoPautaEnum passado seja BUSCAR...")
    class TipoValidacaoPautaEnumSejaBuscar {
      @Nested
      @DisplayName("Dado que o retorno do método [validarPauta] do PautaRepository seja definido...")
      class ValidarPautaRepositoryRetornarZero {
        @Test
        @DisplayName("Deve gerar exceção: PautaNotFoundException")
        public void gerarExcecaoPautaNotFoundException() {
          when(pautaRepository.validarPauta(anyString())).thenReturn(0);

          assertThrows(PautaNotFoundException.class,
            () -> pautaService.validarPauta("woop", TipoValidacaoPautaEnum.BUSCAR));
        }
      }

      @Nested
      @DisplayName("Dado que o retorno do método [validarPauta] do PautaRepository seja indefinido...")
      class ValidarPautaRepositoryRetornarNull {
        @Test
        @DisplayName("Não deve gerar exceção: PautaNotFoundException")
        public void naoGerarExcecaoPautaNotFoundException() {
          when(pautaRepository.validarPauta(anyString())).thenReturn(null);

          assertDoesNotThrow(() -> pautaService.validarPauta("woop", TipoValidacaoPautaEnum.BUSCAR));
        }
      }
    }

    @Nested
    @DisplayName("Dado que o método [validarPauta] do PautaRepository gere uma erro...")
    class CriarPautaErro {
      @Test
      @DisplayName("Deve gerar exceção: BancoException")
      public void deveGerarBancoException() {
        when(pautaRepository.validarPauta(any()))
          .thenThrow(new DataAccessException("ERRO") {
            @Override
            public String getMessage() {
              return super.getMessage();
            }
          });

        assertThrows(BancoException.class,
          () -> pautaService.validarPauta("woop", TipoValidacaoPautaEnum.CADASTRAR));
      }
    }
  }

  @Nested
  @DisplayName("Dado que o método [buscarIdPauta] seja chamado...")
  class ChamadaBuscarIdPauta {
    @Nested
    @DisplayName("Dado que o retorno do método [buscarIdPauta] do PautaRepository seja definido...")
    class BuscarIdPautaDefinido {
      @Test
      @DisplayName("Deve retornar o valor do mesmo")
      public void deveRetornarValorBuscarIdPauta() {
        when(pautaRepository.buscarIdPauta(any())).thenReturn(5);

        var resultado = pautaService.buscarIdPauta("woop");

        assertEquals(5, resultado);
      }
    }

    @Nested
    @DisplayName("Dado que o retorno do método [buscarIdPauta] do PautaRepository seja indefinido...")
    class BuscarIdPautaIndefinido {
      @Test
      @DisplayName("Deve gerar exceção: PautaNotFoundException")
      public void deveGerarExcecaoPautaNotFoundException() {
        when(pautaRepository.buscarIdPauta(any())).thenReturn(null);

        var erro = assertThrows(PautaNotFoundException.class,
          () -> pautaService.buscarIdPauta("woop"));

        assertEquals("Pauta woop não foi cadastrada!", erro.getMensagem());
      }
    }

    @Nested
    @DisplayName("Dado que o retorno do método [buscarIdPauta] do PautaRepository seja um erro...")
    class BuscarIdPautaErro {
      @Test
      @DisplayName("Deve gerar exceção: BancoException")
      public void deveGerarExcecaoBancoException() {
        when(pautaRepository.buscarIdPauta(anyString()))
          .thenThrow(new DataAccessException("ERRO") {
            @Override
            public String getMessage() {
              return super.getMessage();
            }
          });

        assertThrows(BancoException.class,
          () -> pautaService.buscarIdPauta("woop"));
      }
    }
  }

  @Nested
  @DisplayName("Dado que o método [buscaPorNome] seja chamado...")
  class ChamadaBuscaPorNome {
    PautaOutput esperado = PautaOutputStub.retornoBuscaPorNomeService();

    @Nested
    @DisplayName("Dado que o retorno do método [buscaPorNome] do PautaRepository seja definido...")
    class BuscarIdPautaDefinido {
      @Test
      @DisplayName("Deve retornar o valor do mesmo")
      public void deveRetornarValorBuscarIdPauta() {
        when(pautaRepository.buscaPorNome(any())).thenReturn(esperado);

        var resultado = pautaService.buscaPorNome("woop");

        assertEquals(esperado, resultado);
      }
    }

    @Nested
    @DisplayName("Dado que o retorno do método [buscaPorNome] do PautaRepository seja indefinido...")
    class BuscarIdPautaIndefinido {
      @Test
      @DisplayName("Deve gerar exceção: PautaNotFoundException")
      public void deveGerarExcecaoPautaNotFoundException() {
        when(pautaRepository.buscaPorNome(any())).thenReturn(null);

        var erro = assertThrows(PautaNotFoundException.class,
          () -> pautaService.buscaPorNome("woop"));

        assertEquals("Pauta woop não foi cadastrada!", erro.getMensagem());
      }
    }

    @Nested
    @DisplayName("Dado que o retorno do método [buscaPorNome] do PautaRepository seja um erro...")
    class BuscarIdPautaErro {
      @Test
      @DisplayName("Deve gerar exceção: BancoException")
      public void deveGerarExcecaoBancoException() {
        when(pautaRepository.buscaPorNome(anyString()))
          .thenThrow(new DataAccessException("ERRO") {
            @Override
            public String getMessage() {
              return super.getMessage();
            }
          });

        assertThrows(BancoException.class,
          () -> pautaService.buscaPorNome("woop"));
      }
    }
  }
}