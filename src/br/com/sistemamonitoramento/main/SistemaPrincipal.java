package br.com.sistemamonitoramento.main;

import br.com.sistemamonitoramento.model.Pulverizacao;
import br.com.sistemamonitoramento.model.RocadaMecanizada;
import br.com.sistemamonitoramento.model.TrechoRodovia;

public class SistemaPrincipal {

	public static void main(String[] args) {
		
		Pulverizacao pulverizacao = new Pulverizacao(); 
		RocadaMecanizada rocada = new RocadaMecanizada();
		
		TrechoRodovia [] trechos = new TrechoRodovia [] {
			new TrechoRodovia(110, 330, 5, "BR-116", true, true),
			new TrechoRodovia(500, 900, 10, "BR-230", true, false),
			new TrechoRodovia(123, 456, 15, "BR-123", false, true),
			new TrechoRodovia(700, 800, 1, "BR-500", false, false)

		};
		
		for (TrechoRodovia trecho : trechos) {
			trecho.registrarCrescimento(3);
			System.out.println(trecho.transmitirDadosSensor());
		}

		
		System.out.println("\n=== Relatório de Prioridade ===");

		for (TrechoRodovia trecho : trechos) {

		    double nivel = trecho.getNivelVegetacao();

		    System.out.println("\nTrecho: " + trecho.getQuilometroInicial() + "KM ao " + trecho.getQuilometroFinal() + "KM.");
		    System.out.println("Vegetação: " + nivel);

		    if (nivel >= 15) {
		        System.out.println("Prioridade: ALTA");
		    }
		    else if (nivel >= 10) {
		        System.out.println("Prioridade: Média");
		    }
		    else {
		        System.out.println("Prioridade: Baixa");
		    }
		}
		
		System.out.println("Intervenções");
		for (TrechoRodovia trecho : trechos) {
			
			double nivel = trecho.getNivelVegetacao();
			
			 System.out.println("\nTrecho: " + trecho.getNome());

		    if (nivel >= 15) {
		    	System.out.println("Executando a Roçada Mecanizada");
		        double novoNivel = rocada.executarServico(nivel);
		        trecho.setNivelVegetacao(novoNivel);
		        System.out.println("Nível de Vegetação: " + trecho.getNivelVegetacao());
		    }
		    else if (nivel >= 10) {
		    	System.out.println("Executando a Pulverização");
		    	double novoNivel = pulverizacao.executarServico(nivel);
		        trecho.setNivelVegetacao(novoNivel);
		        System.out.println("Nível de Vegetação: " + trecho.getNivelVegetacao());
		    }
		    else {
		    	System.out.println("Não precisa de manutenção ainda, nível =  " + trecho.getNivelVegetacao());
		    }
			
			}
	}

}
