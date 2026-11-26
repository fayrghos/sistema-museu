package banco;

import io.github.cdimascio.dotenv.Dotenv;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBD {

    private static final Dotenv ENV = Dotenv.configure()
        .ignoreIfMissing()
        .load();
    private static final String URL = ENV.get(
        "DB_URL",
        "jdbc:postgresql://localhost:5435/museu"
    );

    public static final String USUARIO = ENV.get("POSTGRES_USER", "");
    public static final String SENHA = ENV.get("POSTGRES_PASSWORD", "");

    public static Connection getConexao() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }
}
