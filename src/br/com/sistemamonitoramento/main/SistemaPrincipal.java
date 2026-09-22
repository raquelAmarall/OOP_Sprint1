package br.com.sistemamonitoramento.main;

import br.com.sistemamonitoramento.dao.EquipeManutencaoDAO;
import br.com.sistemamonitoramento.dao.TrechoRodoviaDAO;
import br.com.sistemamonitoramento.dao.IntervencaoOperacionalDAO;
import br.com.sistemamonitoramento.dao.RelatorioPrioridadeDAO;
import br.com.sistemamonitoramento.db.ConexaoBD;
import br.com.sistemamonitoramento.model.EquipeManutencaoRecord;
import br.com.sistemamonitoramento.model.TrechoRodoviaRecord;
import br.com.sistemamonitoramento.model.IntervencaoOperacionalRecord;
import br.com.sistemamonitoramento.service.GeradorRelatorio;
import br.com.sistemamonitoramento.model.TrechoRodovia;
import br.com.sistemamonitoramento.model.RelatorioPrioridadeRecord;

public class SistemaPrincipal {

    public static void main(String[] args) {

        // ==========================================
        // 1. CONECTAR COM O ORACLE
        // ==========================================

        ConexaoBD conexao = ConexaoBD.getInstancia();
        conexao.conectar();


        // ==========================================
        // 2. CRIAR OS DAOs
        // ==========================================

        EquipeManutencaoDAO dao = new EquipeManutencaoDAO();

        TrechoRodoviaDAO trechoDAO = new TrechoRodoviaDAO();

        IntervencaoOperacionalDAO intervencaoDAO =
                new IntervencaoOperacionalDAO();

        RelatorioPrioridadeDAO relatorioDAO =
                new RelatorioPrioridadeDAO();


        // ==========================================
        // 3. CRIAR AS TABELAS
        // ==========================================

        dao.criarTabela();

        trechoDAO.criarTabela();

        intervencaoDAO.criarTabela();

        relatorioDAO.criarTabela();


        // ==========================================
        // 4. TESTAR CRUD DA EQUIPE
        // ==========================================

        EquipeManutencaoRecord equipe =
                new EquipeManutencaoRecord(
                        0,
                        "Equipe de Teste"
                );

        // ---------- INSERIR ----------

        EquipeManutencaoRecord equipeInserida =
                dao.inserir(equipe);

        int id = equipeInserida.id();


        // ---------- BUSCAR ----------

        System.out.println("\n=== BUSCAR POR ID ===");

        EquipeManutencaoRecord encontrada =
                dao.buscarPorId(id);

        if (encontrada != null) {

            System.out.println(
                "ID: " + encontrada.id() +
                " | Nome: " + encontrada.nome()
            );
        }


        // ---------- ATUALIZAR ----------

        System.out.println("\n=== ATUALIZAR ===");

        EquipeManutencaoRecord equipeAtualizada =
                new EquipeManutencaoRecord(
                        id,
                        "Equipe Norte"
                );

        dao.atualizar(equipeAtualizada);


        System.out.println("\n=== EQUIPE APÓS ATUALIZAÇÃO ===");

        EquipeManutencaoRecord atualizada =
                dao.buscarPorId(id);

        if (atualizada != null) {

            System.out.println(
                "ID: " + atualizada.id() +
                " | Nome: " + atualizada.nome()
            );
        }


        // ---------- DELETAR ----------

        System.out.println("\n=== DELETAR ===");

        dao.deletar(id);


        System.out.println("\n=== BUSCAR EQUIPE DELETADA ===");

        EquipeManutencaoRecord deletada =
                dao.buscarPorId(id);

        if (deletada == null) {

            System.out.println(
                "Equipe não encontrada. Exclusão realizada!"
            );
        }


        // ---------- LISTAR ----------

        System.out.println("\n=== EQUIPES CADASTRADAS ===");

        for (EquipeManutencaoRecord e :
                dao.listarTodas()) {

            System.out.println(
                "ID: " + e.id() +
                " | Nome: " + e.nome()
            );
        }


        // ==========================================
        // 5. TESTAR CRUD DO TRECHO
        // ==========================================

        TrechoRodoviaRecord trecho =
                new TrechoRodoviaRecord(
                        0,
                        110,
                        330,
                        15,
                        "BR-116",
                        true,
                        true
                );


        // ---------- INSERIR ----------

        trecho = trechoDAO.inserir(trecho);


        // ---------- BUSCAR ----------

        System.out.println("\n=== BUSCAR TRECHO POR ID ===");

        TrechoRodoviaRecord trechoBuscado =
                trechoDAO.buscarPorId(trecho.id());

        if (trechoBuscado != null) {

            System.out.println(
                "ID: " + trechoBuscado.id() +
                " | Nome: " + trechoBuscado.nome() +
                " | Vegetação: " +
                trechoBuscado.nivelVegetacao()
            );
        }


        // ---------- ATUALIZAR ----------

        System.out.println("\n=== ATUALIZAR TRECHO ===");

        TrechoRodoviaRecord trechoAtualizado =
                new TrechoRodoviaRecord(
                        trecho.id(),
                        110,
                        330,
                        20,
                        "BR-116",
                        true,
                        true
                );

        trechoDAO.atualizar(trechoAtualizado);


        System.out.println("\n=== TRECHO APÓS ATUALIZAÇÃO ===");

        TrechoRodoviaRecord trechoDepois =
                trechoDAO.buscarPorId(trecho.id());

        if (trechoDepois != null) {

            System.out.println(
                "ID: " + trechoDepois.id() +
                " | Nome: " + trechoDepois.nome() +
                " | Vegetação: " +
                trechoDepois.nivelVegetacao()
            );
        }


        // ---------- LISTAR ----------

        System.out.println("\n=== LISTAR TRECHOS ===");

        for (TrechoRodoviaRecord t :
                trechoDAO.listarTodas()) {

            System.out.println(
                "ID: " + t.id() +
                " | Nome: " + t.nome() +
                " | Vegetação: " +
                t.nivelVegetacao()
            );
        }


        // ==========================================
        // 6. TESTAR CRUD DA INTERVENÇÃO
        // ==========================================

        IntervencaoOperacionalRecord intervencao =
                new IntervencaoOperacionalRecord(
                        0,
                        "Roçada Mecanizada",
                        "Intervenção de roçada mecanizada",
                        trecho.id()
                );


        // ---------- INSERIR ----------

        intervencao = intervencaoDAO.inserir(intervencao);


        // ---------- BUSCAR ----------

        System.out.println(
                "\n=== BUSCAR INTERVENÇÃO POR ID ==="
        );

        IntervencaoOperacionalRecord intervencaoBuscada =
                intervencaoDAO.buscarPorId(intervencao.id());

        if (intervencaoBuscada != null) {

            System.out.println(
                "ID: " + intervencaoBuscada.id() +
                " | Tipo: " + intervencaoBuscada.tipo() +
                " | Trecho: " + intervencaoBuscada.idTrecho()
            );
        }


        // ---------- ATUALIZAR ----------

        System.out.println(
                "\n=== ATUALIZAR INTERVENÇÃO ==="
        );

        IntervencaoOperacionalRecord intervencaoAtualizada =
                new IntervencaoOperacionalRecord(
                        intervencao.id(),
                        "Pulverização",
                        "Intervenção de pulverização",
                        trecho.id()
                );

        intervencaoDAO.atualizar(intervencaoAtualizada);


        System.out.println(
                "\n=== INTERVENÇÃO APÓS ATUALIZAÇÃO ==="
        );

        IntervencaoOperacionalRecord intervencaoDepois =
                intervencaoDAO.buscarPorId(intervencao.id());

        if (intervencaoDepois != null) {

            System.out.println(
                "ID: " + intervencaoDepois.id() +
                " | Tipo: " + intervencaoDepois.tipo() +
                " | Trecho: " + intervencaoDepois.idTrecho()
            );
        }


        // ---------- LISTAR ----------

        System.out.println(
                "\n=== LISTAR INTERVENÇÕES ==="
        );

        for (IntervencaoOperacionalRecord i :
                intervencaoDAO.listarTodas()) {

            System.out.println(
                "ID: " + i.id() +
                " | Tipo: " + i.tipo() +
                " | Trecho: " + i.idTrecho()
            );
        }


        // ---------- DELETAR INTERVENÇÃO ----------

        System.out.println(
                "\n=== DELETAR INTERVENÇÃO ==="
        );

        intervencaoDAO.deletar(intervencao.id());

        IntervencaoOperacionalRecord intervencaoDeletada =
                intervencaoDAO.buscarPorId(intervencao.id());

        if (intervencaoDeletada == null) {

            System.out.println(
                "Intervenção não encontrada. Exclusão realizada!"
            );
        }


        // ---------- DELETAR TRECHO ----------

        System.out.println(
                "\n=== DELETAR TRECHO ==="
        );

        trechoDAO.deletar(trecho.id());

        TrechoRodoviaRecord trechoDeletado =
                trechoDAO.buscarPorId(trecho.id());

        if (trechoDeletado == null) {

            System.out.println(
                "Trecho não encontrado. Exclusão realizada!"
            );
        }


        // ==========================================
        // 7. GERAR RELATÓRIO
        // ==========================================

        TrechoRodovia[] trechos = {

            new TrechoRodovia(
                    110, 330, 5,
                    "BR-116",
                    true, true
            ),

            new TrechoRodovia(
                    500, 900, 10,
                    "BR-230",
                    true, false
            ),

            new TrechoRodovia(
                    123, 456, 15,
                    "BR-123",
                    false, true
            ),

            new TrechoRodovia(
                    700, 800, 1,
                    "BR-500",
                    false, false
            )
        };


        GeradorRelatorio gerador =
                new GeradorRelatorio();

        gerador.gerarRelatorio(trechos);


        // ==========================================
        // 8. HISTÓRICO DE RELATÓRIOS
        // ==========================================

        System.out.println(
                "\n=== HISTÓRICO DE RELATÓRIOS ==="
        );

        for (RelatorioPrioridadeRecord r :
                relatorioDAO.listarTodas()) {

            System.out.println(
                "ID: " + r.id() +
                " | Urgente: " + r.qtUrgente() +
                " | Crítico: " + r.qtCritico() +
                " | Atenção: " + r.qtAtencao() +
                " | Normal: " + r.qtNormal()
            );
        }


        // ==========================================
        // 9. DESCONECTAR
        // ==========================================

        conexao.desconectar();
    }
}