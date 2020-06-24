package br.com.pauta.component;

import br.com.pauta.enuns.TipoValidacaoPautaEnum;
import br.com.pauta.exception.SessaoAbertaException;
import br.com.pauta.model.SessaoInput;
import br.com.pauta.service.PautaService;
import br.com.pauta.service.SessaoService;
import br.com.pauta.stub.SessaoInputStub;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@DisplayName("SessaoComponentImpl")
public class SessaoComponentImplTest {
  @Mock
  private SessaoService sessaoService;
  @Mock
  private PautaService pautaService;
  @InjectMocks
  private SessaoComponentImpl sessaoComponent;

  @Nested
  @DisplayName("Dado que o método [abrirSessao] seja chamado...")
  class ChamadaAbrirSessao {
    SessaoInput parametro;
    String esperado;

    @BeforeEach
    public void setup() {
      parametro = SessaoInputStub.parametroAbrirSessaoComponent();
      esperado = "Sessão aberta e deve ser finalizada: 11/11/2020 - 23:15";

      when(pautaService.buscarIdPauta(any())).thenReturn(67);
      when(sessaoService.getSessaoAberta(any())).thenReturn(false);
      when(sessaoService.abrirSessao(any())).thenReturn(esperado);
    }

    @Test
    @DisplayName("Deve chamar método [validarPauta] do PautaService "
      + "passando SessaoInput.nomePauta e TipoValidacaoPautaEnum.BUSCAR")
    public void deveChamarMetodoValidarPauta_PautaService() {
      sessaoComponent.abrirSessao(parametro);

      verify(pautaService, times(1))
        .validarPauta(parametro.getNomePauta(), TipoValidacaoPautaEnum.BUSCAR);
    }

    @Test
    @DisplayName("Deve chamar método [buscaIdPauta] do PautaService "
      + "passando o SessaoInput.nomePauta")
    public void deveChamarMetodoBuscaIdPauta_PautaService() {
      sessaoComponent.abrirSessao(parametro);

      verify(pautaService, times(1))
        .buscarIdPauta(parametro.getNomePauta());
    }

    @Test
    @DisplayName("Deve chamar método [getSessaoAberta] do SessaoService "
      + "passando o SessaoInput.idPauta gerado pelo o método [buscarIdPauta] "
      + "do PautaService")
    public void deveChamarMetodoGetSessaoAberta_SessaoService() {
      sessaoComponent.abrirSessao(parametro);

      verify(sessaoService, times(1)).getSessaoAberta(67);
    }

    @Nested
    @DisplayName("Dado que o retorno do método [getSessaoAberta] do SessaoService seja true...")
    class RetornoGetSessaoAbertaTrue {
      @Test
      @DisplayName("Deve gerar exceção: SessaoAbertaException")
      public void deveGerarExcecaoSessaoAbertaException() {
        when(sessaoService.getSessaoAberta(any())).thenReturn(true);

        assertThrows(SessaoAbertaException.class,
          () -> sessaoComponent.abrirSessao(parametro));
      }
    }

    @Test
    @DisplayName("Deve chamar o método [gravarSessao] do SessaoService "
       + "passando SessaoInput com dataFinalizacao e IdPauta definidos")
    public void deveChamarMetodoGravarSessao_SessaoService() {
      var dataFinalizacao = LocalDateTime.now().plusMinutes(parametro.getMinutos());
      parametro.setDataFinalizacao(dataFinalizacao);
      parametro.setIdPauta(67);

      sessaoComponent.abrirSessao(parametro);

      verify(sessaoService, times(1)).abrirSessao(parametro);
    }

    @Test
    @DisplayName("Deve retornar mensagem: "
      + "Sessão aberta e deve ser finalizada: dd/MM/YYYY - HH:mm")
    public void deveRetornarMensagem() {
      var resultado = sessaoComponent.abrirSessao(parametro);

      assertEquals(esperado, resultado);
    }
  }
}