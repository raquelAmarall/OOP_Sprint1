package br.com.sistemamonitoramento.model;

public record RelatorioPrioridadeRecord(
        int id,
        int qtUrgente,
        int qtCritico,
        int qtAtencao,
        int qtNormal,
        String resumo
) {
}