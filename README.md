# Sistema de Monitoramento de Vegetação em Rodovias

## Descrição
Projeto desenvolvido para a Sprint 1 do Challenge, com o objetivo de simular um sistema de monitoramento e priorização de roçada de vegetação em rodovias.

Nesta etapa, foi realizada a modelagem inicial do domínio utilizando Programação Orientada a Objetos (POO) em Java.

O sistema permite:
- cadastrar trechos de rodovia;
- registrar crescimento da vegetação;
- validar dados do domínio;
- simular monitoramento de trechos críticos.

---

## Tecnologias Utilizadas

- Java
- Eclipse IDE
- Git e GitHub

---

## Estrutura do Projeto

```plaintext
src/
├── br/com/sistemamonitoramento/main
│   └── SistemaPrincipal.java
│
├── br/com/sistemamonitoramento/model
│   └── TrechoRodovia.java
```

---

## Conceitos Aplicados

- Classes e Objetos
- Encapsulamento
- Métodos e comportamentos
- Getters e Setters
- Validação de dados
- Clean Code
- Organização em pacotes

---

## Funcionalidades

### Classe `TrechoRodovia`

A classe representa um trecho de uma rodovia monitorada.

### Propriedades:
- `quilometroInicial`
- `quilometroFinal`
- `nivelVegetacao`
- `nome`

### Métodos:
- `registrarCrescimento(double taxa)`
- getters e validações encapsuladas

---

## Regras de Negócio

- O nível da vegetação não pode ser negativo.
- O quilômetro final não pode ser menor que o quilômetro inicial.
- O nome da rodovia não pode ser vazio.

---

## Perguntas de Refelexão

- Por que TrechoRodovia é uma classe e "BR-116 KM 10 ao 15" é um objeto?
  
**Resposta:**  Por que TrechoRodovia é um molde para os trechos de rodovias, como *BR-116 KM 10 ao 15*. 

- Como um método difere de uma função solta em programação estruturada?

**Resposta:**  O método pertence à classe e tem a capacidade de mudar as propriedades de um objeto.

- Se o nivelVegetacao fosse público, que tipo de "quebra" no sistema de previsão da Motiva um programador descuidado poderia causar?

**Resposta:**  Se o nivelVegetacao fosse público qualquer pessoa poderia modificar sem uma validação.

Raquel Oliveira  
Curso de Ciência da Computação - FIAP
