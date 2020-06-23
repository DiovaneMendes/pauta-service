package br.com.pauta.controller;

import br.com.pauta.binder.PautaRequestBinder;
import br.com.pauta.binder.PautaResponseBinder;
import br.com.pauta.component.PautaComponent;
import br.com.pauta.model.request.PautaRequest;
import br.com.pauta.model.response.PautaResponse;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("v1/pauta")
public class PautaController {
  private PautaComponent pautaComponent;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public String criarPauta(@Valid @RequestBody PautaRequest pautaRequest) {
    var pautaInput = PautaRequestBinder.bindToPautaModel(pautaRequest);
    return pautaComponent.criarPauta(pautaInput);
  }

  @GetMapping("/busca")
  @ResponseStatus(HttpStatus.OK)
  public PautaResponse buscarPorNome(@RequestParam String nomePauta) {
    var pautaOutput = pautaComponent.buscarPorNome(nomePauta);
    return PautaResponseBinder.bindToPautaResponse(pautaOutput);
  }
}