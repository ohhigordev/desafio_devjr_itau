package Desafio_Itau_Dev_jr.controller;

import Desafio_Itau_Dev_jr.dto.EstatisticasResponseDTO;
import Desafio_Itau_Dev_jr.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estatisticas")
@RequiredArgsConstructor
public class EstatisticasController {

    private final TransactionService service;

    @GetMapping
    public ResponseEntity<EstatisticasResponseDTO> obterEstatisticas(){
        return ResponseEntity.ok(service.calcularEstatistica());
    }

}
