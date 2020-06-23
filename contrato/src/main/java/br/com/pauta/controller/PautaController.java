package br.com.pauta.controller;

import br.com.pauta.binder.PautaRequestBinder;
import br.com.pauta.binder.PautaResponseBinder;
import br.com.pauta.component.PautaComponent;
import br.com.pauta.exception.BancoException;
import br.com.pauta.exception.PautaNotFoundException;
import br.com.pauta.model.request.PautaRequest;
import br.com.pauta.model.response.PautaResponse;

import io.swagger.annotations.*;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("v1/pauta")
@Api("Endpoint para operações de pauta")
public class PautaController {
  private PautaComponent pautaComponent;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @ApiOperation(value = "Registra uma nova pauta", response = String.class)
  @ApiResponses({
    @ApiResponse(code = 201, message = "Pauta gravada com sucesso.", response = String.class),
    @ApiResponse(code = 500, message = "Houve um erro ao criar a pauta.", response = BancoException.class)
  })
  public String criarPauta(@ApiParam(value = "Informação a ser registrada")
                             @Valid @RequestBody PautaRequest pautaRequest) {
    var pautaInput = PautaRequestBinder.bindToPautaModel(pautaRequest);
    return pautaComponent.criarPauta(pautaInput);
  }

  @GetMapping("/busca")
  @ResponseStatus(HttpStatus.OK)
  @ApiOperation(value = "Busca pauta pelo o nome", response = PautaResponse.class)
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "Pauta gravada com sucesso.", response = String.class),
    @ApiResponse(code = 404, message = "Pauta %s não foi cadastrada!", response = PautaNotFoundException.class),
    @ApiResponse(code = 500, message = "Houve um erro com a conexão com o banco.", response = BancoException.class)
  })
  public PautaResponse buscaPorNome(@ApiParam(value = "Nome da pauta", example = "Dividendos")
                                       @RequestParam String nomePauta) {
    var pautaOutput = pautaComponent.buscaPorNome(nomePauta);
    return PautaResponseBinder.bindToPautaResponse(pautaOutput);
  }
}