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
        try (Connection conn = ConexaoBD.getConexao()) {
            conn.setAutoCommit(false);
            try {
                criarTabelas(conn);
                criarAdminPadrao(conn);
                conn.commit();
                System.out.println("[BD] Banco inicializado com sucesso.");
            } catch (Exception e) {
                conn.rollback();
                throw e;
            }
        } catch (Exception e) {
            throw new RuntimeException("Falha ao inicializar o banco de dados", e);
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
                VALUES (?, ?, ?, TRUE, TRUE, FALSE)
                ON CONFLICT (email) DO NOTHING
                """;
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "admin@admin.com");
            ps.setString(2, "Administrador");
            ps.setString(3, SenhaHash.gerarHash("admin"));
            ps.executeUpdate();
        }
    }
}
