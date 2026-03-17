package Desafio_Itau_Dev_jr.service;

import Desafio_Itau_Dev_jr.dto.TransacaoRequestDTO;
import Desafio_Itau_Dev_jr.infrastructure.UnprocessableEntityException;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class TransactionService {

    // Lista thread-safe para armazenamento em memória
    private final List<TransacaoRequestDTO> transacoes = Collections.synchronizedList(new ArrayList<>());

    public void adicionarTransacao(TransacaoRequestDTO transacao){
        // Validação: Campos obrigatórios ( caso o JSON venha incompleto)
        if(transacao.valor() == null || transacao.dataHora() == null){
            throw new UnprocessableEntityException("Campos obrigatórios não preenchidos");
        }

        // Validação: Valor não pode ser negativo
        if (transacao.valor() < 0){
            throw new UnprocessableEntityException("O valor da transação não pode ser negativo");
        }

        // Validação: não pode ser no futuro
        if(transacao.dataHora().isAfter(OffsetDateTime.now())){
            throw new UnprocessableEntityException("O valor da transação não pode ser no futuro");
        }

        transacoes.add(transacao);
    }

}
