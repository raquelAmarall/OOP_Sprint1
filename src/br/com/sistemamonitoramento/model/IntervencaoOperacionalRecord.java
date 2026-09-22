package br.com.sistemamonitoramento.model;

public record IntervencaoOperacionalRecord(
        int id,
        String tipo,
        String descricao,
        int idTrecho
) {
}