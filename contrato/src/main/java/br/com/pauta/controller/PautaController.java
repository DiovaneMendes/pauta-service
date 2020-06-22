package br.com.pauta.controller;

import br.com.pauta.binder.PautaRequestBinder;
import br.com.pauta.component.PautaComponent;
import br.com.pauta.model.PautaRequest;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("pauta/v1")
public class PautaController {
  private PautaComponent pautaComponent;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public String criarPauta(@Valid @RequestBody PautaRequest pautaRequest) {
    var pautaInput = PautaRequestBinder.bindToPautaModel(pautaRequest);
    return pautaComponent.criarPauta(pautaInput);
  }
}