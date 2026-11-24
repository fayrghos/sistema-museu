package banco;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBD {

    private static final Dotenv ENV = Dotenv.configure()
            .ignoreIfMissing()
            .load();

    private static final String URL = ENV.get("DB_URL", "jdbc:postgresql://localhost:5433/museu");
    private static final String USUARIO = ENV.get("POSTGRES_USER", "postgres");
    private static final String SENHA = obrigatoria("POSTGRES_PASSWORD");

    private ConexaoBD() {}

    private static String obrigatoria(String chave) {
        String valor = ENV.get(chave);
        if (valor == null || valor.isBlank()) {
            throw new IllegalStateException(
                    "Variável " + chave + " não encontrada. Verifique o arquivo .env");
        }
        return valor;
    }

    public static Connection getConexao() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }
}