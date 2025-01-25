package com.paulotech.trasaction_api.controllers;

import com.paulotech.trasaction_api.business.services.TransationService;
import com.paulotech.trasaction_api.controllers.dtos.TransacaoRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/transacao")
public class TransacaoController {

    private final TransationService service;

    @PostMapping
    @Operation(description = "Endpoint resposável por add transações")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "transações gravadas com, sucesso"),
            @ApiResponse(responseCode = "422", description = "Campos não atendem requisitos da transação"),
            @ApiResponse(responseCode = "400", description = "Erro de requisição"),
            @ApiResponse(responseCode = "500", description = "Error interno no servidor")
    })
    public ResponseEntity<Void> addTransacao(@RequestBody TransacaoRequestDTO dto){
        service.addTransacao(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping
    @Operation(description = "Endpoint resposável por add transações")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "transações gravadas com, sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de requisição"),
            @ApiResponse(responseCode = "500", description = "Error interno no servidor")
    })
    public  ResponseEntity<Void> deletarTransacoes(){
        service.limparTransacoes();
        return ResponseEntity.ok().build();
    }


}
