package br.com.sistemamonitoramento.model;

public class TrechoRodovia {
	// Criando as propriedades
	private double quilometroInicial;
	private double quilometroFinal;
	private double nivelVegetacao;
	private String nome;
	
	// Construtor
	public TrechoRodovia(double quilometroInicial, double quilometroFinal, double nivelVegetacao, String nome) {
		this.setNivelVegetacao(nivelVegetacao);
		this.setQuilometroInicial(quilometroInicial);
		this.setQuilometroFinal(quilometroFinal);	
		this.setNome(nome);
	}
	
	// Getters and Setters
	public double getQuilometroInicial() {
		return quilometroInicial;
	}
	private void setQuilometroInicial(double quilometroInicial) {
		this.quilometroInicial = quilometroInicial;
	}
	public double getQuilometroFinal() {
		return quilometroFinal;
	}
	private void setQuilometroFinal(double quilometroFinal) {
		if (quilometroFinal < this.getQuilometroInicial()) {
			System.out.println("Quilômetro final está menor que o quilômetro inicial");
		}else {
			this.quilometroFinal = quilometroFinal;
		}
	}
	public double getNivelVegetacao() {
		return nivelVegetacao;
	}
	private void setNivelVegetacao(double nivelVegetacao) {
		if (nivelVegetacao < 0) {
			System.out.println("O nivel de vegetação não pode ser negativo");			
		} else {
			this.nivelVegetacao = nivelVegetacao;
		}	
	}
	public String getNome() {
		return nome;
	}
	private void setNome(String nome) {
		if (nome.isBlank()) {
			System.out.println("Nome não informado, por favor informe o nome");			
		} else {
			this.nome = nome;
		}	
	}
	
	// Métodos
	public void registrarCrescimento(double taxa) {
		double crescimento = this.getNivelVegetacao();
		if (taxa > 0) {
			crescimento += taxa;
		}else {
			System.out.println("A taxa de crescimento tem que ser maior que 0");
		}
		this.setNivelVegetacao(crescimento);
	}
	

}
