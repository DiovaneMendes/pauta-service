package br.com.pauta.component;

import br.com.pauta.enuns.TipoValidacaoPautaEnum;
import br.com.pauta.exception.PautaParametroException;
import br.com.pauta.model.PautaInput;
import br.com.pauta.model.PautaOutput;
import br.com.pauta.service.PautaService;
import br.com.pauta.stub.PautaInputStub;
import br.com.pauta.stub.PautaOutputStub;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DisplayName("PautaComponentImpl")
@ExtendWith(SpringExtension.class)
public class PautaComponentImplTest {
  @Mock
  private PautaService pautaService;
  @InjectMocks
  private PautaComponentImpl pautaComponent;

  @Nested
  @DisplayName("Dado que o método [criarPauta] seja chamado...")
  class ChamadaCriarPauta {
    String esperado;
    PautaInput parametro;
    @BeforeEach
    public void setup() {
      esperado = "Pauta gravada com sucesso.";
      parametro = PautaInputStub.parametroCriarPautaComponent();
      when(pautaService.criarPauta(any())).thenReturn(esperado);
    }

    @Test
    @DisplayName("Deve chamar o método [validarPauta] do PautaService "
      + "passando PautaInput.nomePauta e TipoValidacaoPautaEnum.CADASTRAR")
    public void deveChamarValidarPauta_PautaService() {
      pautaComponent.criarPauta(parametro);

      verify(pautaService, times(1))
        .validarPauta("woop", TipoValidacaoPautaEnum.CADASTRAR);
    }

    @Test
    @DisplayName("Deve chamar o método [criarPauta] do PautaService passando o PautaInput passado acima")
    public void deveChamarMetodoCriarPauta_PautaService() {
      pautaComponent.criarPauta(parametro);

      verify(pautaService, times(1)).criarPauta(parametro);
    }

    @Test
    @DisplayName("Deve retornar o retorno do método [criarPauta] do PautaService")
    public void deveRetornarRetornoCriarPauta_PautaService() {
      var resultado = pautaComponent.criarPauta(parametro);

      assertEquals(esperado, resultado);
    }
  }

  @Nested
  @DisplayName("Dado que o método [buscaPorNome] seja chamado...")
  class ChamadaBuscaPorNome {
    PautaOutput esperado;
    @BeforeEach
    public void setup() {
      esperado = PautaOutputStub.parametroBuscaPorNomeComponent();
      when(pautaService.buscaPorNome(any())).thenReturn(esperado);
    }

    @Nested
    @DisplayName("Dado que o [nomePauta] passado seja indefinido...")
    class NomePautaIndefinido {
      @Test
      @DisplayName("Deve gerar exceção: PautaParametroException")
      public void deveGerarPautaParametroException() {
        assertThrows(PautaParametroException.class, () ->
          pautaComponent.buscaPorNome(null)
        );
      }
    }

    @Nested
    @DisplayName("Dado que o [nomePauta] passado seja definido...")
    class NomePautaDefinido {
      @Test
      @DisplayName("Deve chamar o método [buscaPorNome] do PautaService passando o nome passado acima")
      public void deveChamarMetodoBuscarPorNome_PautaService() {
        var parametro = "digital";
        pautaComponent.buscaPorNome(parametro);

        verify(pautaService, times(1)).buscaPorNome(parametro);
      }

      @Test
      @DisplayName("Deve retornar o retorno do método [buscaPorNome] do PautaService")
      public void deveRetornarRetornoBuscaPorNome_PautaService() {
        var resultado = pautaComponent.buscaPorNome("digital");

        assertEquals(esperado, resultado);
      }
    }
  }
}