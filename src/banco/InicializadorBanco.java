package banco;

import utilidades.hash.SenhaHash;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class InicializadorBanco {

    public static void inicializar() {
        if (ConexaoBD.USUARIO.isBlank() || ConexaoBD.SENHA.isBlank()) {
            System.err.println(
                "Por favor, configure as credenciais do banco de dados no arquivo .env."
            );
            System.exit(1);
        }
        
        try (Connection conn = ConexaoBD.getConexao()) {
            conn.setAutoCommit(false);
            try {
                criarTabelas(conn);
                criarAdminPadrao(conn);
                conn.commit();
            } catch (Exception e) {
                conn.rollback();
                throw e;
            }
        } catch (SQLException err) {
            String estado = err.getSQLState();

            if (estado.equals("08001")) {
                System.err.println(
                    "Não foi possível conectar ao banco de dados. Ele está rodando?"
                );
            } else if (estado.equals("28P01")) {
                System.err.println(
                    "O banco de dados está rodando, mas rejeitou as credenciais."
                );
            } else {
                System.err.println(err);
            }

            System.exit(1);
        } catch (Exception err) {
            System.err.println("Um erro desconhecido ocorreu no banco de dados.");
            System.exit(1);
        }
    }

    private static void criarTabelas(Connection conn) throws SQLException, IOException {
        String sql;
        try (InputStream in = InicializadorBanco.class.getResourceAsStream("/schema.sql")) {
            if (in == null) {
                throw new IOException("schema.sql não encontrado em src/main/resources");
            }
            sql = new String(in.readAllBytes(), StandardCharsets.UTF_8);
        }
        try (Statement st = conn.createStatement()) {
            st.execute(sql); // o driver do Postgres aceita vários comandos separados por ;
        }
    }

    private static void criarAdminPadrao(Connection conn) throws SQLException {
        String sql = """
                INSERT INTO USUARIO (email, nome, senha, ativo, administrador, curador)
                SELECT ?, ?, ?, TRUE, TRUE, FALSE
                WHERE NOT EXISTS (SELECT 1 FROM USUARIO WHERE email = ?)
                ON CONFLICT (email) DO NOTHING
                """;
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "admin@admin.com");
            ps.setString(2, "Administrador");
            ps.setString(3, SenhaHash.gerarHash("admin"));
            ps.setString(4, "admin@admin.com");
            ps.executeUpdate();
        }
    }
}
