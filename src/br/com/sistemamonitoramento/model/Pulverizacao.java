package br.com.sistemamonitoramento.model;

public class Pulverizacao extends IntervencaoOperacional{
	
	@Override
	public double executarServico(double tamanhoVegetacao) {
		return tamanhoVegetacao * 0.5;	
	}
	

}
