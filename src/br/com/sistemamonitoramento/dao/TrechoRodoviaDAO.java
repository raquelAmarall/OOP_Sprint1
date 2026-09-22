package br.com.sistemamonitoramento.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.sistemamonitoramento.db.ConexaoBD;
import br.com.sistemamonitoramento.model.TrechoRodoviaRecord;

public class TrechoRodoviaDAO {

    private static final String SQL_INSERIR =
            "INSERT INTO trecho_rodovia " +
            "(quilometro_inicial, quilometro_final, nivel_vegetacao, nome, umido, monitorado) " +
            "VALUES (?, ?, ?, ?, ?, ?)";

    private static final String SQL_BUSCAR_POR_ID =
            "SELECT id, quilometro_inicial, quilometro_final, " +
            "nivel_vegetacao, nome, umido, monitorado " +
            "FROM trecho_rodovia WHERE id = ?";

    private static final String SQL_LISTAR_TODAS =
            "SELECT id, quilometro_inicial, quilometro_final, " +
            "nivel_vegetacao, nome, umido, monitorado " +
            "FROM trecho_rodovia";

    private static final String SQL_ATUALIZAR =
            "UPDATE trecho_rodovia SET " +
            "quilometro_inicial = ?, quilometro_final = ?, " +
            "nivel_vegetacao = ?, nome = ?, umido = ?, monitorado = ? " +
            "WHERE id = ?";

    private static final String SQL_DELETAR =
            "DELETE FROM trecho_rodovia WHERE id = ?";


    public void criarTabela() {

        String sqlTabela =
                "CREATE TABLE trecho_rodovia (" +
                "id NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY, " +
                "quilometro_inicial NUMBER(10,2) NOT NULL, " +
                "quilometro_final NUMBER(10,2) NOT NULL, " +
                "nivel_vegetacao NUMBER(10,2) NOT NULL, " +
                "nome VARCHAR2(100) NOT NULL, " +
                "umido NUMBER(1) NOT NULL, " +
                "monitorado NUMBER(1) NOT NULL" +
                ")";

        Connection conn = ConexaoBD.getInstancia().getConexao();

        try {
            var stmt = conn.createStatement();

            try {
                stmt.execute(sqlTabela);
                System.out.println("Tabela 'trecho_rodovia' criada com sucesso!");
            } catch (SQLException e) {
                if (e.getErrorCode() == 955) {
                    System.out.println("Tabela 'trecho_rodovia' já existe.");
                } else {
                    throw e;
                }
            }

            stmt.close();

        } catch (SQLException e) {
            System.out.println("Erro ao criar tabela: " + e.getMessage());
        }
    }


    public TrechoRodoviaRecord inserir(TrechoRodoviaRecord trecho) {

        Connection conn = ConexaoBD.getInstancia().getConexao();

        try (PreparedStatement stmt =
                conn.prepareStatement(SQL_INSERIR, new String[] {"ID"})) {

            stmt.setDouble(1, trecho.quilometroInicial());
            stmt.setDouble(2, trecho.quilometroFinal());
            stmt.setDouble(3, trecho.nivelVegetacao());
            stmt.setString(4, trecho.nome());
            stmt.setInt(5, trecho.umido() ? 1 : 0);
            stmt.setInt(6, trecho.monitorado() ? 1 : 0);

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {

                if (rs.next()) {
                    int id = rs.getInt(1);

                    System.out.println(
                        "Trecho inserido com sucesso! ID gerado: " + id
                    );

                    return new TrechoRodoviaRecord(
                        id,
                        trecho.quilometroInicial(),
                        trecho.quilometroFinal(),
                        trecho.nivelVegetacao(),
                        trecho.nome(),
                        trecho.umido(),
                        trecho.monitorado()
                    );
                }
            }

        } catch (SQLException e) {
            System.out.println("Erro ao inserir trecho: " + e.getMessage());
        }

        return null;
    }


    public TrechoRodoviaRecord buscarPorId(int id) {

        Connection conn = ConexaoBD.getInstancia().getConexao();

        try (PreparedStatement stmt =
                conn.prepareStatement(SQL_BUSCAR_POR_ID)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    return extrairTrecho(rs);
                }
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar trecho: " + e.getMessage());
        }

        return null;
    }


    public List<TrechoRodoviaRecord> listarTodas() {

        List<TrechoRodoviaRecord> trechos = new ArrayList<>();

        Connection conn = ConexaoBD.getInstancia().getConexao();

        try (PreparedStatement stmt =
                conn.prepareStatement(SQL_LISTAR_TODAS);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                trechos.add(extrairTrecho(rs));
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar trechos: " + e.getMessage());
        }

        return trechos;
    }


    public void atualizar(TrechoRodoviaRecord trecho) {

        Connection conn = ConexaoBD.getInstancia().getConexao();

        try (PreparedStatement stmt =
                conn.prepareStatement(SQL_ATUALIZAR)) {

            stmt.setDouble(1, trecho.quilometroInicial());
            stmt.setDouble(2, trecho.quilometroFinal());
            stmt.setDouble(3, trecho.nivelVegetacao());
            stmt.setString(4, trecho.nome());
            stmt.setInt(5, trecho.umido() ? 1 : 0);
            stmt.setInt(6, trecho.monitorado() ? 1 : 0);
            stmt.setInt(7, trecho.id());

            stmt.executeUpdate();

            System.out.println("Trecho atualizado com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar trecho: " + e.getMessage());
        }
    }


    public void deletar(int id) {

        Connection conn = ConexaoBD.getInstancia().getConexao();

        try (PreparedStatement stmt =
                conn.prepareStatement(SQL_DELETAR)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

            System.out.println("Trecho deletado com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao deletar trecho: " + e.getMessage());
        }
    }


    private TrechoRodoviaRecord extrairTrecho(ResultSet rs)
            throws SQLException {

        return new TrechoRodoviaRecord(
            rs.getInt("id"),
            rs.getDouble("quilometro_inicial"),
            rs.getDouble("quilometro_final"),
            rs.getDouble("nivel_vegetacao"),
            rs.getString("nome"),
            rs.getInt("umido") == 1,
            rs.getInt("monitorado") == 1
        );
    }
}