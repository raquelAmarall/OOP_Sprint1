# Sistema de Monitoramento de Vegetação em Rodovias

## Descrição

Projeto desenvolvido para o Challenge MOTIVA da FIAP, com o objetivo de simular um sistema de monitoramento e priorização de manutenção da vegetação em trechos de rodovias.

O projeto foi desenvolvido utilizando Programação Orientada a Objetos (POO) em Java e, na Sprint 3, recebeu persistência de dados utilizando banco de dados Oracle e JDBC puro.

O sistema permite:

- Cadastrar e consultar trechos de rodovia;
- Registrar informações sobre o nível de vegetação;
- Simular o monitoramento dos trechos;
- Registrar intervenções operacionais;
- Gerar relatórios de prioridade;
- Armazenar os dados no banco de dados Oracle;
- Consultar o histórico de relatórios.

---

## Tecnologias Utilizadas

- Java 21
- Oracle Database
- JDBC
- Eclipse IDE
- Git e GitHub
- ojdbc17.jar

---

## Estrutura do Projeto

```plaintext
src/
├── br/com/sistemamonitoramento/dao
│   ├── EquipeManutencaoDAO.java
│   ├── IntervencaoOperacionalDAO.java
│   ├── RelatorioPrioridadeDAO.java
│   └── TrechoRodoviaDAO.java
│
├── br/com/sistemamonitoramento/db
│   └── ConexaoBD.java
│
├── br/com/sistemamonitoramento/main
│   └── SistemaPrincipal.java
│
├── br/com/sistemamonitoramento/model
│   ├── EquipeManutencaoRecord.java
│   ├── IntervencaoOperacional.java
│   ├── IntervencaoOperacionalRecord.java
│   ├── MonitoravelViaIoT.java
│   ├── MotorPriorizacao.java
│   ├── Pulverizacao.java
│   ├── RelatorioPrioridadeRecord.java
│   ├── RocadaMecanizada.java
│   ├── TrechoRodovia.java
│   └── TrechoRodoviaRecord.java
│
├── br/com/sistemamonitoramento/service
│   └── GeradorRelatorio.java
│
└── module-info.java

lib/
└── ojdbc17.jar

sql/
├── script-criacao.sql
└── script-dados.sql
```
---

## Conceitos Aplicados

- Programação Orientada a Objetos (POO)
- Encapsulamento
- Classes e objetos
- Herança
- Abstração
- Polimorfismo
- Interfaces
- Records
- Validação de dados
- DAO (Data Access Object)
- JDBC
- PreparedStatement
- Persistência de dados
- Organização em pacotes

---

## Banco de Dados

O projeto utiliza o Oracle Database para armazenar os dados do sistema.

Foram criadas as seguintes tabelas:

- `equipe_manutencao`
- `trecho_rodovia`
- `intervencao_operacional`
- `relatorio_prioridade`

Os scripts SQL utilizados no projeto estão disponíveis na pasta `sql`.

---

## DAOs

O projeto utiliza o padrão DAO para separar o acesso ao banco de dados das demais partes do sistema.

### EquipeManutencaoDAO

Responsável pelas operações de persistência das equipes de manutenção.

- `inserir()`
- `buscarPorId()`
- `listarTodas()`
- `atualizar()`
- `deletar()`

### TrechoRodoviaDAO

Responsável pelas operações de persistência dos trechos de rodovia.

- `inserir()`
- `buscarPorId()`
- `listarTodas()`
- `atualizar()`
- `deletar()`

### IntervencaoOperacionalDAO

Responsável pelas operações de persistência das intervenções operacionais.

- `inserir()`
- `buscarPorId()`
- `listarTodas()`
- `atualizar()`
- `deletar()`

### RelatorioPrioridadeDAO

Responsável pelo armazenamento e consulta dos relatórios de prioridade.

- `inserir()`
- `buscarPorId()`
- `listarTodas()`
- `atualizar()`
- `deletar()`
- `salvarRelatorio()`

---

## Gerador de Relatório

A classe `GeradorRelatorio` analisa o nível de vegetação dos trechos e classifica cada trecho de acordo com sua prioridade.

As categorias utilizadas são:

- **Normal:** nível abaixo de 5;
- **Atenção:** nível de 5 a 9,99;
- **Crítico:** nível de 10 a 14,99;
- **Urgente:** nível igual ou superior a 15.

Após gerar o relatório no console, os resultados são armazenados no banco de dados por meio do `RelatorioPrioridadeDAO`.

---

## Conexão com o Banco

A classe `ConexaoBD` é responsável por estabelecer e encerrar a conexão com o Oracle.

O projeto utiliza o driver `ojdbc17.jar`, localizado na pasta `lib/` e configurado no projeto.

---

## Como Executar

1. Abra o projeto no Eclipse.
2. Verifique se o `ojdbc17.jar` está configurado no projeto.
3. Configure as credenciais de acesso ao Oracle na classe `ConexaoBD`.
4. Execute a classe `SistemaPrincipal.java`.
5. O programa realizará os testes de conexão, criação das tabelas, operações CRUD, geração do relatório, persistência e consulta do histórico.

---

## Testes Realizados

Foram realizados testes de:

- Conexão com o Oracle;
- Criação das tabelas;
- Inserção de registros;
- Busca por ID;
- Listagem de registros;
- Atualização de registros;
- Exclusão de registros;
- Geração de relatório;
- Persistência do relatório;
- Consulta do histórico;
- Encerramento da conexão.

Os testes principais foram executados com sucesso no ambiente de desenvolvimento.

---

## Integrantes

Allan de Souza Cardoso RM 561721

Eduardo Bacelar Rudner RM 564925

Giovana Dias Valentini RM  562390

Júlia Borges Paschoalinoto RM 564725

Raquel Amaral de Oliveira RM 566491

#### Curso de Ciência da Computação - FIAP
