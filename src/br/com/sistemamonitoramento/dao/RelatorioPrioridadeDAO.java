package br.com.sistemamonitoramento.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.sistemamonitoramento.db.ConexaoBD;
import br.com.sistemamonitoramento.model.RelatorioPrioridadeRecord;

public class RelatorioPrioridadeDAO {

    private static final String SQL_INSERIR =
            "INSERT INTO relatorio_prioridade " +
            "(qt_urgente, qt_critico, qt_atencao, qt_normal, resumo) " +
            "VALUES (?, ?, ?, ?, ?)";

    private static final String SQL_BUSCAR_POR_ID =
            "SELECT id, qt_urgente, qt_critico, qt_atencao, " +
            "qt_normal, resumo FROM relatorio_prioridade WHERE id = ?";

    private static final String SQL_LISTAR_TODAS =
            "SELECT id, qt_urgente, qt_critico, qt_atencao, " +
            "qt_normal, resumo FROM relatorio_prioridade";

    private static final String SQL_ATUALIZAR =
            "UPDATE relatorio_prioridade SET " +
            "qt_urgente = ?, qt_critico = ?, qt_atencao = ?, " +
            "qt_normal = ?, resumo = ? WHERE id = ?";

    private static final String SQL_DELETAR =
            "DELETE FROM relatorio_prioridade WHERE id = ?";


    public void criarTabela() {

        String sqlTabela =
                "CREATE TABLE relatorio_prioridade (" +
                "id NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY, " +
                "qt_urgente NUMBER NOT NULL, " +
                "qt_critico NUMBER NOT NULL, " +
                "qt_atencao NUMBER NOT NULL, " +
                "qt_normal NUMBER NOT NULL, " +
                "resumo VARCHAR2(1000)" +
                ")";

        Connection conn = ConexaoBD.getInstancia().getConexao();

        try {
            var stmt = conn.createStatement();

            try {
                stmt.execute(sqlTabela);
                System.out.println(
                    "Tabela 'relatorio_prioridade' criada com sucesso!"
                );
            } catch (SQLException e) {
                if (e.getErrorCode() == 955) {
                    System.out.println(
                        "Tabela 'relatorio_prioridade' já existe."
                    );
                } else {
                    throw e;
                }
            }

            stmt.close();

        } catch (SQLException e) {
            System.out.println("Erro ao criar tabela: " + e.getMessage());
        }
    }


    public RelatorioPrioridadeRecord inserir(
            RelatorioPrioridadeRecord relatorio) {

        Connection conn = ConexaoBD.getInstancia().getConexao();

        try (PreparedStatement stmt =
                conn.prepareStatement(SQL_INSERIR, new String[] {"ID"})) {

            stmt.setInt(1, relatorio.qtUrgente());
            stmt.setInt(2, relatorio.qtCritico());
            stmt.setInt(3, relatorio.qtAtencao());
            stmt.setInt(4, relatorio.qtNormal());
            stmt.setString(5, relatorio.resumo());

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {

                if (rs.next()) {

                    int id = rs.getInt(1);

                    System.out.println(
                        "Relatório inserido com sucesso! ID gerado: " + id
                    );

                    return new RelatorioPrioridadeRecord(
                        id,
                        relatorio.qtUrgente(),
                        relatorio.qtCritico(),
                        relatorio.qtAtencao(),
                        relatorio.qtNormal(),
                        relatorio.resumo()
                    );
                }
            }

        } catch (SQLException e) {
            System.out.println(
                "Erro ao inserir relatório: " + e.getMessage()
            );
        }

        return null;
    }


    public RelatorioPrioridadeRecord buscarPorId(int id) {

        Connection conn = ConexaoBD.getInstancia().getConexao();

        try (PreparedStatement stmt =
                conn.prepareStatement(SQL_BUSCAR_POR_ID)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    return extrairRelatorio(rs);
                }
            }

        } catch (SQLException e) {
            System.out.println(
                "Erro ao buscar relatório: " + e.getMessage()
            );
        }

        return null;
    }


    public List<RelatorioPrioridadeRecord> listarTodas() {

        List<RelatorioPrioridadeRecord> lista = new ArrayList<>();

        Connection conn = ConexaoBD.getInstancia().getConexao();

        try (PreparedStatement stmt =
                conn.prepareStatement(SQL_LISTAR_TODAS);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(extrairRelatorio(rs));
            }

        } catch (SQLException e) {
            System.out.println(
                "Erro ao listar relatórios: " + e.getMessage()
            );
        }

        return lista;
    }


    public void atualizar(RelatorioPrioridadeRecord relatorio) {

        Connection conn = ConexaoBD.getInstancia().getConexao();

        try (PreparedStatement stmt =
                conn.prepareStatement(SQL_ATUALIZAR)) {

            stmt.setInt(1, relatorio.qtUrgente());
            stmt.setInt(2, relatorio.qtCritico());
            stmt.setInt(3, relatorio.qtAtencao());
            stmt.setInt(4, relatorio.qtNormal());
            stmt.setString(5, relatorio.resumo());
            stmt.setInt(6, relatorio.id());

            stmt.executeUpdate();

            System.out.println("Relatório atualizado com sucesso!");

        } catch (SQLException e) {
            System.out.println(
                "Erro ao atualizar relatório: " + e.getMessage()
            );
        }
    }


    public void deletar(int id) {

        Connection conn = ConexaoBD.getInstancia().getConexao();

        try (PreparedStatement stmt =
                conn.prepareStatement(SQL_DELETAR)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

            System.out.println("Relatório deletado com sucesso!");

        } catch (SQLException e) {
            System.out.println(
                "Erro ao deletar relatório: " + e.getMessage()
            );
        }
    }


    private RelatorioPrioridadeRecord extrairRelatorio(
            ResultSet rs) throws SQLException {

        return new RelatorioPrioridadeRecord(
            rs.getInt("id"),
            rs.getInt("qt_urgente"),
            rs.getInt("qt_critico"),
            rs.getInt("qt_atencao"),
            rs.getInt("qt_normal"),
            rs.getString("resumo")
        );
    }
    
    public void salvarRelatorio(
            int qtUrgente,
            int qtCritico,
            int qtAtencao,
            int qtNormal,
            String resumo) {

        RelatorioPrioridadeRecord relatorio =
                new RelatorioPrioridadeRecord(
                        0,
                        qtUrgente,
                        qtCritico,
                        qtAtencao,
                        qtNormal,
                        resumo
                );

        inserir(relatorio);
    }
}