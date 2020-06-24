package br.com.pauta.component;

import br.com.pauta.exception.SessaoAbertaException;
import br.com.pauta.model.VotoInput;
import br.com.pauta.service.PautaService;
import br.com.pauta.service.SessaoService;
import br.com.pauta.service.VotoService;
import br.com.pauta.stub.VotoInputStub;

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

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@DisplayName("VotoComponentImpl")
@ExtendWith(SpringExtension.class)
public class VotoComponentImplTest {
  @Mock
  private PautaService pautaService;
  @Mock
  private VotoService votoService;
  @Mock
  private SessaoService sessaoService;
  @InjectMocks
  private VotoComponentImpl votoComponent;

  @Nested
  @DisplayName("Dado que o método [gravarVoto] seja chamado...")
  class ChamadaGravarVoto {
    String esperado;
    VotoInput parametro;

    @BeforeEach
    public void setup() {
      esperado = "Voto gravado com sucesso.";
      parametro = VotoInputStub.parametroGravarVotoComponent();

      when(pautaService.buscarIdPauta(anyString())).thenReturn(56);
      when(sessaoService.getSessaoAberta(anyInt())).thenReturn(true);
      when(votoService.inserirVoto(any(VotoInput.class))).thenReturn(esperado);
    }

    @Test
    @DisplayName("Deve chamar o método [buscarIdPauta] do PautaService passando VotoInput.nomePauta")
    public void deveChamarMetodoBuscarIdPauta_PautaService() {
      votoComponent.gravarVoto(parametro);

      verify(pautaService, times(1)).buscarIdPauta(parametro.getNomePauta());
    }

    @Test
    @DisplayName("Deve chamar o método [getSessaoAberta] do SessaoService passando VotoInput.idPauta")
    public void deveChamarMetodoGetSessaoAberta_SessaoService() {
      votoComponent.gravarVoto(parametro);

      verify(sessaoService, times(1)).getSessaoAberta(parametro.getIdPauta());
    }

    @Nested
    @DisplayName("Dado que o retorno do método [getSessaoAberta] do SessaoService seja false...")
    class GetSessaoAbertaFalse {
      @Test
      @DisplayName("Deve gerar exceção: SessaoAbertaException")
      public void deveGerarExcecaoSessaoAbertaException() {
        when(sessaoService.getSessaoAberta(anyInt())).thenReturn(false);

        assertThrows(SessaoAbertaException.class,
          () -> votoComponent.gravarVoto(parametro));
      }
    }

    @Test
    @DisplayName("Deve chamar o método [validarVotoAssociado] do "
      + "VotoService passando VotoInput já com [idPauta] definido")
    public void deveChamarMetodoValidarVotoAssociado() {
      votoComponent.gravarVoto(parametro);
      var parametroTeste = VotoInputStub.parametroInserirVotoComponent();

      verify(votoService).validarVotoAssociado(parametroTeste);
    }

    @Test
    @DisplayName("Deve chamar método [inserirVoto] do VotoService "
      + "passando VotoInput já com [idPauta] definido")
    public void deveChamarMetodoInserirVoto_VotoService() {
      votoComponent.gravarVoto(parametro);
      var parametroTeste = VotoInputStub.parametroInserirVotoComponent();

      verify(votoService).inserirVoto(parametroTeste);
    }

    @Test
    @DisplayName("Deve retornar o retorno do método [inserirVoto] do VotoService")
    public void deveRetornarRetornoInserirVoto() {
      var resultado = votoComponent.gravarVoto(parametro);

      assertEquals(esperado, resultado);
    }
  }
}