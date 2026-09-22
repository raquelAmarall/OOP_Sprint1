package br.com.sistemamonitoramento.model;

public class MotorPriorizacao {

    public void analisarTrecho(TrechoRodovia trecho) {

        double nivel = trecho.getNivelVegetacao();

        IntervencaoOperacional intervencao;

        if (nivel >= 15) {

            System.out.println("Prioridade: ALTA");
            System.out.println("Executando a Roçada Mecanizada");

            intervencao = new RocadaMecanizada();

        } else if (nivel >= 10) {

            System.out.println("Prioridade: Média");
            System.out.println("Executando a Pulverização");

            intervencao = new Pulverizacao();

        } else {

            System.out.println("Prioridade: Baixa");
            System.out.println("Não precisa de manutenção ainda.");

            return;
        }

        double novoNivel = intervencao.executarServico(nivel);

        trecho.setNivelVegetacao(novoNivel);
    }
}