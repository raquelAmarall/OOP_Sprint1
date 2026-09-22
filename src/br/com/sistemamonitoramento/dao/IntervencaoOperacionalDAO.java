package br.com.sistemamonitoramento.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.sistemamonitoramento.db.ConexaoBD;
import br.com.sistemamonitoramento.model.IntervencaoOperacionalRecord;

public class IntervencaoOperacionalDAO {

    private static final String SQL_INSERIR =
            "INSERT INTO intervencao_operacional " +
            "(tipo, descricao, id_trecho) VALUES (?, ?, ?)";

    private static final String SQL_BUSCAR_POR_ID =
            "SELECT id, tipo, descricao, id_trecho " +
            "FROM intervencao_operacional WHERE id = ?";

    private static final String SQL_LISTAR_TODAS =
            "SELECT id, tipo, descricao, id_trecho " +
            "FROM intervencao_operacional";

    private static final String SQL_ATUALIZAR =
            "UPDATE intervencao_operacional SET " +
            "tipo = ?, descricao = ?, id_trecho = ? WHERE id = ?";

    private static final String SQL_DELETAR =
            "DELETE FROM intervencao_operacional WHERE id = ?";


    public void criarTabela() {

        String sqlTabela =
                "CREATE TABLE intervencao_operacional (" +
                "id NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY, " +
                "tipo VARCHAR2(100) NOT NULL, " +
                "descricao VARCHAR2(255), " +
                "id_trecho NUMBER NOT NULL, " +
                "CONSTRAINT fk_intervencao_trecho " +
                "FOREIGN KEY (id_trecho) REFERENCES trecho_rodovia(id)" +
                ")";

        Connection conn = ConexaoBD.getInstancia().getConexao();

        try {
            var stmt = conn.createStatement();

            try {
                stmt.execute(sqlTabela);
                System.out.println(
                    "Tabela 'intervencao_operacional' criada com sucesso!"
                );
            } catch (SQLException e) {
                if (e.getErrorCode() == 955) {
                    System.out.println(
                        "Tabela 'intervencao_operacional' já existe."
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


    public IntervencaoOperacionalRecord inserir(
            IntervencaoOperacionalRecord intervencao) {

        Connection conn = ConexaoBD.getInstancia().getConexao();

        try (PreparedStatement stmt =
                conn.prepareStatement(SQL_INSERIR, new String[] {"ID"})) {

            stmt.setString(1, intervencao.tipo());
            stmt.setString(2, intervencao.descricao());
            stmt.setInt(3, intervencao.idTrecho());

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {

                if (rs.next()) {

                    int id = rs.getInt(1);

                    System.out.println(
                        "Intervenção inserida com sucesso! ID gerado: " + id
                    );

                    return new IntervencaoOperacionalRecord(
                        id,
                        intervencao.tipo(),
                        intervencao.descricao(),
                        intervencao.idTrecho()
                    );
                }
            }

        } catch (SQLException e) {
            System.out.println(
                "Erro ao inserir intervenção: " + e.getMessage()
            );
        }

        return null;
    }


    public IntervencaoOperacionalRecord buscarPorId(int id) {

        Connection conn = ConexaoBD.getInstancia().getConexao();

        try (PreparedStatement stmt =
                conn.prepareStatement(SQL_BUSCAR_POR_ID)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    return extrairIntervencao(rs);
                }
            }

        } catch (SQLException e) {
            System.out.println(
                "Erro ao buscar intervenção: " + e.getMessage()
            );
        }

        return null;
    }


    public List<IntervencaoOperacionalRecord> listarTodas() {

        List<IntervencaoOperacionalRecord> lista = new ArrayList<>();

        Connection conn = ConexaoBD.getInstancia().getConexao();

        try (PreparedStatement stmt =
                conn.prepareStatement(SQL_LISTAR_TODAS);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(extrairIntervencao(rs));
            }

        } catch (SQLException e) {
            System.out.println(
                "Erro ao listar intervenções: " + e.getMessage()
            );
        }

        return lista;
    }


    public void atualizar(IntervencaoOperacionalRecord intervencao) {

        Connection conn = ConexaoBD.getInstancia().getConexao();

        try (PreparedStatement stmt =
                conn.prepareStatement(SQL_ATUALIZAR)) {

            stmt.setString(1, intervencao.tipo());
            stmt.setString(2, intervencao.descricao());
            stmt.setInt(3, intervencao.idTrecho());
            stmt.setInt(4, intervencao.id());

            stmt.executeUpdate();

            System.out.println("Intervenção atualizada com sucesso!");

        } catch (SQLException e) {
            System.out.println(
                "Erro ao atualizar intervenção: " + e.getMessage()
            );
        }
    }


    public void deletar(int id) {

        Connection conn = ConexaoBD.getInstancia().getConexao();

        try (PreparedStatement stmt =
                conn.prepareStatement(SQL_DELETAR)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

            System.out.println("Intervenção deletada com sucesso!");

        } catch (SQLException e) {
            System.out.println(
                "Erro ao deletar intervenção: " + e.getMessage()
            );
        }
    }


    private IntervencaoOperacionalRecord extrairIntervencao(
            ResultSet rs) throws SQLException {

        return new IntervencaoOperacionalRecord(
            rs.getInt("id"),
            rs.getString("tipo"),
            rs.getString("descricao"),
            rs.getInt("id_trecho")
        );
    }
}