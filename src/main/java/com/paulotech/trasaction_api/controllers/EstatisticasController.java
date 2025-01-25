package com.paulotech.trasaction_api.controllers;

import com.paulotech.trasaction_api.business.services.EstatisticasService;
import com.paulotech.trasaction_api.controllers.dtos.EstatisticasResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estatistica")
@RequiredArgsConstructor
public class EstatisticasController {
    private final EstatisticasService service;

    @GetMapping
    @Operation(description = "Endpoint resposável por buscar estatisticas de transações")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "busca efetuada com, sucesso"),
            @ApiResponse(responseCode = "400", description = "error de buscar estatisticas de transação"),
            @ApiResponse(responseCode = "500", description = "Error interno no servidor")
    })
    public ResponseEntity<EstatisticasResponseDTO> buscarEstatisticas(@RequestParam(value = "intervaloBusca",
            required = false, defaultValue = "60") Integer intervaloBusca){
        return ResponseEntity.ok(service.calcularEstatisticasTrans(intervaloBusca));
    }
}
