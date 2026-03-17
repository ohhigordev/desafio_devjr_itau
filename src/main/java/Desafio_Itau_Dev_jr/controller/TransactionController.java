package Desafio_Itau_Dev_jr.controller;

import Desafio_Itau_Dev_jr.dto.TransacaoRequestDTO;
import Desafio_Itau_Dev_jr.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transacao")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService service;

    @PostMapping
    public ResponseEntity<Void> receberTransacao(@RequestBody TransacaoRequestDTO transacao){
        service.adicionarTransacao(transacao);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping
    public ResponseEntity<Void> limparTransacoes(){
        service.limparTransacoes();
        return ResponseEntity.ok().build();
    }
}
