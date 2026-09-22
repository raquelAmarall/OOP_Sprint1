package br.com.sistemamonitoramento.service;

import br.com.sistemamonitoramento.dao.RelatorioPrioridadeDAO;
import br.com.sistemamonitoramento.model.TrechoRodovia;

public class GeradorRelatorio {

	public void gerarRelatorio(TrechoRodovia[] trechos) {

	    int qtUrgente = 0;
	    int qtCritico = 0;
	    int qtAtencao = 0;
	    int qtNormal = 0;

	    System.out.println("\n=== RELATÓRIO DE PRIORIDADE ===");

	    for (TrechoRodovia trecho : trechos) {

	        double nivel = trecho.getNivelVegetacao();

	        if (nivel >= 15) {

	            qtUrgente++;

	            System.out.println(
	                trecho.getNome() + " - Prioridade: URGENTE"
	            );

	        } else if (nivel >= 10) {

	            qtCritico++;

	            System.out.println(
	                trecho.getNome() + " - Prioridade: CRÍTICO"
	            );

	        } else if (nivel >= 5) {

	            qtAtencao++;

	            System.out.println(
	                trecho.getNome() + " - Prioridade: ATENÇÃO"
	            );

	        } else {

	            qtNormal++;

	            System.out.println(
	                trecho.getNome() + " - Prioridade: NORMAL"
	            );
	        }
	    }

	    System.out.println("\n=== RESUMO ===");

	    System.out.println("Urgente: " + qtUrgente);
	    System.out.println("Crítico: " + qtCritico);
	    System.out.println("Atenção: " + qtAtencao);
	    System.out.println("Normal: " + qtNormal);

	    String resumo =
	            "Relatório de prioridade gerado pelo sistema MOTIVA.";

	    RelatorioPrioridadeDAO dao =
	            new RelatorioPrioridadeDAO();

	    dao.salvarRelatorio(
	            qtUrgente,
	            qtCritico,
	            qtAtencao,
	            qtNormal,
	            resumo
	    );

	    System.out.println(
	            "Relatório salvo no banco de dados!"
	    );
	}
}