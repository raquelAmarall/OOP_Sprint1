package br.com.sistemamonitoramento.main;

import br.com.sistemamonitoramento.model.TrechoRodovia;

public class SistemaPrincipal {

	public static void main(String[] args) {
		TrechoRodovia trecho1 = new TrechoRodovia(110, 330, 5, "BR-116");
		trecho1.registrarCrescimento(10);
		TrechoRodovia trecho2 = new TrechoRodovia(500, 900, 10, "BR-230");
		

		// Exibição
		System.out.println("Rodovia " + trecho1.getNome() + " com o começo no KM " + trecho1.getQuilometroInicial() + " e término no KM " + trecho1.getQuilometroFinal() + " Tem vegetação de " + trecho1.getNivelVegetacao() + "cm.");
		System.out.println("Rodovia " + trecho2.getNome() + " com o começo no KM " + trecho2.getQuilometroInicial() + " e término no KM " + trecho2.getQuilometroFinal() + " Tem vegetação de " + trecho2.getNivelVegetacao() + "cm.");
	}

}
