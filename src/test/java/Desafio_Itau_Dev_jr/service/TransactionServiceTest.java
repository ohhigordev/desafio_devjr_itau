package Desafio_Itau_Dev_jr.service;

import Desafio_Itau_Dev_jr.dto.EstatisticasResponseDTO;
import Desafio_Itau_Dev_jr.dto.TransacaoRequestDTO;
import Desafio_Itau_Dev_jr.infrastructure.UnprocessableEntityException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class TransactionServiceTest {
    private TransactionService service;

    @BeforeEach
    void setUp() {
        service = new TransactionService();
        service.limparTransacoes();
    }

    @Test
    void deveAdicionarTransacaoValida() {
        TransacaoRequestDTO transacao = new TransacaoRequestDTO(100.0, OffsetDateTime.now());
        assertDoesNotThrow(() -> service.adicionarTransacao(transacao));
    }

    @Test
    void deveLancarExcecaoParaValorNegativo() {
        TransacaoRequestDTO transacao = new TransacaoRequestDTO(-10.0, OffsetDateTime.now());
        assertThrows(UnprocessableEntityException.class, () -> service.adicionarTransacao(transacao));
    }

    @Test
    void deveLancarExcecaoParaDataNoFuturo() {
        TransacaoRequestDTO transacao = new TransacaoRequestDTO(100.0, OffsetDateTime.now().plusDays(1));
        assertThrows(UnprocessableEntityException.class, () -> service.adicionarTransacao(transacao));
    }

    @Test
    void deveCalcularEstatisticasCorretamente() {
        // Transação dentro dos 60s
        service.adicionarTransacao(new TransacaoRequestDTO(10.0, OffsetDateTime.now()));
        service.adicionarTransacao(new TransacaoRequestDTO(20.0, OffsetDateTime.now()));

        EstatisticasResponseDTO stats = service.calcularEstatistica();

        assertEquals(2, stats.count());
        assertEquals(30.0, stats.sum());
        assertEquals(15.0, stats.avg());
        assertEquals(10.0, stats.min());
        assertEquals(20.0, stats.max());
    }

    @Test
    void deveIgnorarTransacoesMaisAntigasQue60Segundos() {
        // Simulando transação antiga (fora da janela)
        TransacaoRequestDTO antiga = new TransacaoRequestDTO(100.0, OffsetDateTime.now().minusSeconds(61));
        service.getTransacoes().add(antiga); // Adicionando direto na lista para teste de filtro

        EstatisticasResponseDTO stats = service.calcularEstatistica();

        assertEquals(0, stats.count());
        assertEquals(0.0, stats.sum());
    }
}
