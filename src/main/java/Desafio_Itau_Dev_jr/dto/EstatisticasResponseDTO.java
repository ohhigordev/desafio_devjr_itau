package Desafio_Itau_Dev_jr.dto;

public record EstatisticasResponseDTO(
        long count,
        Double sum,
        Double avg,
        Double main,
        Double max
) {
}
