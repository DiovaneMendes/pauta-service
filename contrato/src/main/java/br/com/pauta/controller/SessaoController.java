package br.com.pauta.controller;

import br.com.pauta.binder.SessaoRequestBinder;
import br.com.pauta.binder.VotoRequestBinder;
import br.com.pauta.component.SessaoComponent;
import br.com.pauta.component.VotoComponent;
import br.com.pauta.exception.BancoException;
import br.com.pauta.model.request.SessaoRequest;
import br.com.pauta.model.request.VotoRequest;

import io.swagger.annotations.*;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("v1/sessao")
@Api("Endpoint para operações de sessões")
public class SessaoController {
  private SessaoComponent sessaoComponent;
  private VotoComponent votoComponent;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @ApiOperation(value = "Abre e grava uma sessão", response = String.class)
  @ApiResponses({
    @ApiResponse(code = 201, message = "Sessão aberta e deve ser finalizada: dd/MM/YYYY - HH:mm",
      response = String.class),
    @ApiResponse(code = 500, message = "Houve um erro ao abrir a sessão.", response = BancoException.class)
  })
  public String abrirSessao(@ApiParam(value = "Informações para abrir sessão")
                              @Valid @RequestBody SessaoRequest sessaoRequest) {
    var sessaoInput = SessaoRequestBinder.bindToSessaoInput(sessaoRequest);
    return sessaoComponent.abrirSessao(sessaoInput);
  }

  @PostMapping("/voto")
  @ResponseStatus(HttpStatus.CREATED)
  @ApiOperation(value = "Registra voto", response = String.class)
  @ApiResponses({
    @ApiResponse(code = 201, message = "Voto gravado com sucesso.", response = String.class),
    @ApiResponse(code = 500, message = "Houve um erro ao gravar voto.", response = BancoException.class)
  })
  public String gravarVoto(@ApiParam(value = "Informações para registrar voto")
                             @Valid @RequestBody VotoRequest votoRequest) {
    var votoInput = VotoRequestBinder.bindToVotoInput(votoRequest);
    return votoComponent.gravarVoto(votoInput);
  }
}