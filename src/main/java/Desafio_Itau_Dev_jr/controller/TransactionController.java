package Desafio_Itau_Dev_jr.controller;

import Desafio_Itau_Dev_jr.dto.TransacaoRequestDTO;
import Desafio_Itau_Dev_jr.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
