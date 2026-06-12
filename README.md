# Sistema de Monitoramento de Vegetação em Rodovias

## Descrição
Projeto desenvolvido para o Challenge 2026, com o objetivo de simular um sistema de monitoramento e priorização de roçada de vegetação em rodovias.

## Tecnologias Utilizadas

- Java
- Eclipse IDE
- Git e GitHub

---

## Perguntas de Refelexão - Sprint 1

- Por que TrechoRodovia é uma classe e "BR-116 KM 10 ao 15" é um objeto?
  
**Resposta:**  Por que TrechoRodovia é um molde para os trechos de rodovias, como *BR-116 KM 10 ao 15*. 

- Como um método difere de uma função solta em programação estruturada?

**Resposta:**  O método pertence à classe e tem a capacidade de mudar as propriedades de um objeto.

- Se o nivelVegetacao fosse público, que tipo de "quebra" no sistema de previsão da Motiva um programador descuidado poderia causar?

**Resposta:**  Se o nivelVegetacao fosse público qualquer pessoa poderia modificar sem uma validação.

## Perguntas de Refelexão - Sprint 2

- Por que não faz sentido para a Motiva que uma equipe execute apenas uma "Intervenção Operacional" genérica sem especificar qual é?
  
**Resposta:**  Porque uma intervenção operacional é apenas um conceito genérico. Na prática, a equipe precisa saber qual serviço será realizado, como roçada mecanizada ou pulverização, pois cada intervenção possui procedimentos e objetivos diferentes.

- Qual a diferença arquitetural entre fazer um Trecho herdar de uma classe abstrata vs. implementar uma Interface?

**Resposta:** Uma Interface é como um Menu, ela não "prepara" nada ela apenas avisa aos métodos o que deverá ser implementado. Já uma classe abstrada é quase uma classe normal, pois tem atributos e métodos implementados, servindo como base para outras classes, mas ela não pode ser instanciada na main.

Raquel Oliveira  
Curso de Ciência da Computação - FIAP
