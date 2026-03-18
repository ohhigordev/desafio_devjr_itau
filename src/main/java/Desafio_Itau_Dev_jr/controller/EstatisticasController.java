package Desafio_Itau_Dev_jr.controller;

import Desafio_Itau_Dev_jr.dto.EstatisticasResponseDTO;
import Desafio_Itau_Dev_jr.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estatistica")
@RequiredArgsConstructor
public class EstatisticasController {

    private final TransactionService service;

    @GetMapping
    @Operation(description = "Endpoint responsavel por buscar estatísticas de transações")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Buscar efetuada com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro na busca de estatísticas de transações"),
            @ApiResponse(responseCode = "500", description = "Erro de interno")
    })
    public ResponseEntity<EstatisticasResponseDTO> obterEstatisticas(){
        return ResponseEntity.ok(service.calcularEstatistica());
    }

}
