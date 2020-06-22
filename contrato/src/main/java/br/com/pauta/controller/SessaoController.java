package br.com.pauta.controller;

import br.com.pauta.binder.SessaoRequestBinder;
import br.com.pauta.component.SessaoComponent;
import br.com.pauta.model.SessaoRequest;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("sessao/v1")
public class SessaoController {
  private SessaoComponent sessaoComponent;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public String abrirSessao(@Valid @RequestBody SessaoRequest sessaoRequest) {
    var sessaoInput = SessaoRequestBinder.bindToSessaoInput(sessaoRequest);
    return sessaoComponent.abrirSessao(sessaoInput);
  }
}