package br.com.sistemamonitoramento.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBD {

    private static final String HOST = "oracle.fiap.com.br";
    private static final String PORT = "1521";
    private static final String SID = "ORCL";
    private static final String USER = "RM566491";
    private static final String PASSWORD = "090207";

    private static ConexaoBD instancia;

    private Connection conexao;

    private ConexaoBD() {
    }

    public static ConexaoBD getInstancia() {
        if (instancia == null) {
            instancia = new ConexaoBD();
        }

        return instancia;
    }

    public void conectar() {
        String url = "jdbc:oracle:thin:@//" + HOST + ":" + PORT + "/" + SID;

        try {
            conexao = DriverManager.getConnection(url, USER, PASSWORD);
            System.out.println("Conexão com o Oracle realizada com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao conectar com o Oracle: " + e.getMessage());
        }
    }

    public void desconectar() {
        try {
            if (conexao != null && !conexao.isClosed()) {
                conexao.close();
                System.out.println("Conexão encerrada.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao fechar a conexão: " + e.getMessage());
        }
    }

    public Connection getConexao() {
        return conexao;
    }
}