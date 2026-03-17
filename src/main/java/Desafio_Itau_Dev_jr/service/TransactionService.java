package Desafio_Itau_Dev_jr.service;

import Desafio_Itau_Dev_jr.dto.EstatisticasResponseDTO;
import Desafio_Itau_Dev_jr.dto.TransacaoRequestDTO;
import Desafio_Itau_Dev_jr.infrastructure.UnprocessableEntityException;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.DoubleSummaryStatistics;
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

    public EstatisticasResponseDTO calcularEstatistca(){
        OffsetDateTime limiteTempo = OffsetDateTime.now().minusSeconds(60);

        // Filtramos e calculamos tudo em uma única passagem (O(n))
        DoubleSummaryStatistics stats = transacoes.stream()
                .filter(t -> t.dataHora().isAfter(limiteTempo))
                .mapToDouble(TransacaoRequestDTO::valor)
                .summaryStatistics();

        // Se não houver transações, stats.getCount() será 0 e os valores serão Infinity ou 0.
        // Precisamos tratar para retornar exatamente 0.0 conforme o enunciado.
        if (stats.getCount() == 0){
            return new EstatisticasResponseDTO(0L, 0.0, 0.0, 0.0, 0.0);
        }

        return new EstatisticasResponseDTO(
                stats.getCount(),
                stats.getSum(),
                stats.getAverage(),
                stats.getMin(),
                stats.getMax()
        );
    }

    public void limparTransacoes(){
        transacoes.clear();
        // Dica de ouro: Logar a limpeza ajuda muito no debug durante os testes do Itaú
        System.out.println("Histórico de transações limpo com sucesso.");
    }

}
