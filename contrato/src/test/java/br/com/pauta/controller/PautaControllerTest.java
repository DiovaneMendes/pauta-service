package br.com.pauta.controller;

import br.com.pauta.component.PautaComponentImpl;
import br.com.pauta.model.PautaInput;
import br.com.pauta.model.request.PautaRequest;
import br.com.pauta.stub.PautaOutputStub;
import br.com.pauta.stub.PautaResponseStub;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DisplayName("PautaController")
@ExtendWith(SpringExtension.class)
public class PautaControllerTest {
  @Mock
  private PautaComponentImpl pautaComponent;

  @InjectMocks
  private PautaController pautaController;

  @Nested
  @DisplayName("Dado que o método [criarPauta] seja chamado...")
  class ChamadaCriarPauta {
    String esperado;
    @BeforeEach
    public void setup(){
      esperado = "Pauta gravada com sucesso.";
      when(pautaComponent.criarPauta(any())).thenReturn(esperado);
    }

    @Test
    @DisplayName("Deve chamar método [criarPauta] do PautaComponent passando um PautaInput")
    public void deveChamarMetodoCriarPauta_PautaComponent() {
      var parametroEsperado = PautaInput.builder().nomePauta("woop").build();
      var request = PautaRequest.builder().nomePauta("woop").build();

      pautaController.criarPauta(request);

      verify(pautaComponent, times(1)).criarPauta(parametroEsperado);
    }

    @Test
    @DisplayName("Deve ter resultado igual do método [criarPauta] do PautaComponent")
    public void deveRetornarIgual_criarPauta_PautaComponent() {
      var request = PautaRequest.builder().nomePauta("woop").build();
      var resultado = pautaController.criarPauta(request);

      assertEquals(esperado, resultado);
    }
  }

  @Nested
  @DisplayName("Dado que o método [buscaPorNome] seja chamado...")
  class ChamadaBuscaPorNome{
    @BeforeEach
    public void setup() {
      var pautaOutput = PautaOutputStub.retornoBuscaPorNome();
      when(pautaComponent.buscaPorNome(any())).thenReturn(pautaOutput);
    }

    @Test
    @DisplayName("Deve chamar o método [buscaPorNome] do PautaComponent passando o nome da pauta passada acima")
    public void deveChamarMetodoBuscaPorNome_PautaComponent() {
      pautaController.buscaPorNome("digital");

      verify(pautaComponent, times(1)).buscaPorNome("digital");
    }

    @Test
    @DisplayName("Deve retornar um PautaResponse")
    public void deveRetornarUmPautaResponse() {
      var esperado = PautaResponseStub.retornoBuscaPorNome();
      var resultado = pautaController.buscaPorNome("digital");

      assertEquals(esperado, resultado);
    }
  }
}