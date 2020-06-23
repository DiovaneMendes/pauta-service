package br.com.pauta.controller;

import br.com.pauta.component.SessaoComponentImpl;
import br.com.pauta.component.VotoComponent;
import br.com.pauta.stub.SessaoInputStub;
import br.com.pauta.stub.SessaoRequestStub;
import br.com.pauta.stub.VotoInputStub;
import br.com.pauta.stub.VotoRequestStub;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DisplayName("SessaoController")
@ExtendWith(SpringExtension.class)
public class SessaoControllerTest {
  @Mock
  private SessaoComponentImpl sessaoComponent;

  @Mock
  private VotoComponent votoComponent;

  @InjectMocks
  private SessaoController sessaoController;

  @Nested
  @DisplayName("Dado que o método [abrirSessao] seja chamado...")
  class ChamadaAbrirSessao {
    String esperado;
    @BeforeEach
    public void setup() {
      esperado = "Sessão aberta e deve ser finalizada: dd/MM/YYYY - HH:mm";
      when(sessaoComponent.abrirSessao(any())).thenReturn(esperado);
    }

    @Test
    @DisplayName("Deve chamar o método [abrirSessao] do SessaoComponent passando um SessaoInput")
    public void deveChamarMetodoAbrirSessao_SessaoComponent() {
      var parametroComponent = SessaoInputStub.parametroAbrirSessao();
      var parametroController = SessaoRequestStub.parametroAbrirSessao();

      sessaoController.abrirSessao(parametroController);

      verify(sessaoComponent, times(1)).abrirSessao(parametroComponent);
    }

    @Test
    @DisplayName("Deve retornar o retorno do método [abrirSessao] do SessaoComponent")
    public void deveRetornarORetornoAbrirSessao_SessaoComponent() {
      var parametroController = SessaoRequestStub.parametroAbrirSessao();

      var resultado = sessaoController.abrirSessao(parametroController);

      assertEquals(esperado, resultado);
    }
  }

  @Nested
  @DisplayName("Dado que o método [gravarVoto] seja chamado...")
  class ChamadaGravarVoto {
    String esperado;
    @BeforeEach
    public void setup() {
      esperado = "Voto gravado com sucesso.";
      when(votoComponent.gravarVoto(any())).thenReturn(esperado);
    }

    @Test
    @DisplayName("Deve chamar o método [gravarVoto] do VotoComponent passando um VotoInput")
    public void deveChamarMetodoGravarVoto_VotoComponent() {
      var parametroComponent = VotoInputStub.parametroGravarVoto();
      var parametroController = VotoRequestStub.parametroGravarVoto();

      sessaoController.gravarVoto(parametroController);

      verify(votoComponent, times(1)).gravarVoto(parametroComponent);
    }

    @Test
    @DisplayName("Deve retornar o retorno do método [gravarVoto] do SessaoComponent")
    public void deveRetornarORetornoGravarVoto_SessaoComponent() {
      var parametroController = VotoRequestStub.parametroGravarVoto();

      var resultado = sessaoController.gravarVoto(parametroController);

      assertEquals(esperado, resultado);
    }
  }
}