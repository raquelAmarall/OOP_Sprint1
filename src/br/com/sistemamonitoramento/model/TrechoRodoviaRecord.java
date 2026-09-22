package br.com.sistemamonitoramento.model;

public record TrechoRodoviaRecord(
        int id,
        double quilometroInicial,
        double quilometroFinal,
        double nivelVegetacao,
        String nome,
        boolean umido,
        boolean monitorado
) {
}