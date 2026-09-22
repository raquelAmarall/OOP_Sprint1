package br.com.sistemamonitoramento.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.sistemamonitoramento.db.ConexaoBD;
import br.com.sistemamonitoramento.model.EquipeManutencaoRecord;

public class EquipeManutencaoDAO {

    // SQLs
    private static final String SQL_INSERIR =
            "INSERT INTO equipe_manutencao (nome) VALUES (?)";

    private static final String SQL_BUSCAR_POR_ID =
            "SELECT id, nome FROM equipe_manutencao WHERE id = ?";

    private static final String SQL_LISTAR_TODAS =
            "SELECT id, nome FROM equipe_manutencao";

    private static final String SQL_ATUALIZAR =
            "UPDATE equipe_manutencao SET nome = ? WHERE id = ?";

    private static final String SQL_DELETAR =
            "DELETE FROM equipe_manutencao WHERE id = ?";

 // CRIAR TABELA
    public void criarTabela() {

        String sqlTabela =
                "CREATE TABLE equipe_manutencao (" +
                "id NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY, " +
                "nome VARCHAR2(100) NOT NULL" +
                ")";

        Connection conn = ConexaoBD.getInstancia().getConexao();

        try {
            var stmt = conn.createStatement();

            try {
                stmt.execute(sqlTabela);
                System.out.println("Tabela 'equipe_manutencao' criada com sucesso!");

            } catch (SQLException e) {

                if (e.getErrorCode() == 955) {
                    System.out.println("Tabela 'equipe_manutencao' já existe.");
                } else {
                    throw e;
                }
            }

            stmt.close();

        } catch (SQLException e) {
            System.out.println("Erro ao criar tabela: " + e.getMessage());
        }
    }    

 // INSERT
    public EquipeManutencaoRecord inserir(EquipeManutencaoRecord equipe) {

        Connection conn = ConexaoBD.getInstancia().getConexao();

        try (PreparedStatement stmt =
                conn.prepareStatement(SQL_INSERIR, new String[] {"ID"})) {

            stmt.setString(1, equipe.nome());

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {

                if (rs.next()) {
                    int idGerado = rs.getInt(1);

                    System.out.println(
                        "Equipe inserida com sucesso! ID gerado: " + idGerado
                    );

                    return new EquipeManutencaoRecord(
                        idGerado,
                        equipe.nome()
                    );
                }
            }

        } catch (SQLException e) {
            System.out.println("Erro ao inserir equipe: " + e.getMessage());
        }

        return null;
    }


    // SELECT por ID
    public EquipeManutencaoRecord buscarPorId(int id) {

        Connection conn = ConexaoBD.getInstancia().getConexao();

        try (PreparedStatement stmt = conn.prepareStatement(SQL_BUSCAR_POR_ID)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    return extrairEquipe(rs);
                }
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar equipe: " + e.getMessage());
        }

        return null;
    }


    // SELECT todas
    public List<EquipeManutencaoRecord> listarTodas() {

        List<EquipeManutencaoRecord> equipes = new ArrayList<>();

        Connection conn = ConexaoBD.getInstancia().getConexao();

        try (PreparedStatement stmt = conn.prepareStatement(SQL_LISTAR_TODAS);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                equipes.add(extrairEquipe(rs));
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar equipes: " + e.getMessage());
        }

        return equipes;
    }


    // UPDATE
    public void atualizar(EquipeManutencaoRecord equipe) {

        Connection conn = ConexaoBD.getInstancia().getConexao();

        try (PreparedStatement stmt = conn.prepareStatement(SQL_ATUALIZAR)) {

            stmt.setString(1, equipe.nome());
            stmt.setInt(2, equipe.id());

            stmt.executeUpdate();

            System.out.println("Equipe atualizada com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar equipe: " + e.getMessage());
        }
    }


    // DELETE
    public void deletar(int id) {

        Connection conn = ConexaoBD.getInstancia().getConexao();

        try (PreparedStatement stmt = conn.prepareStatement(SQL_DELETAR)) {

            stmt.setInt(1, id);

            stmt.executeUpdate();

            System.out.println("Equipe deletada com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao deletar equipe: " + e.getMessage());
        }
    }


    // Converte o resultado do banco em Record
    private EquipeManutencaoRecord extrairEquipe(ResultSet rs)
            throws SQLException {

        int id = rs.getInt("id");
        String nome = rs.getString("nome");

        return new EquipeManutencaoRecord(id, nome);
    }
}