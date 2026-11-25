package banco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class BancoGenericoDAO<T> {

    // Contrato: o que cada entidade precisa informar
    public abstract String tabela();
    public abstract List<String> colunasInsercao();
    public abstract void preencherStatement(PreparedStatement ps, T obj) throws SQLException;
    public abstract T mapear(ResultSet rs) throws SQLException;
    public abstract void definirId(T obj, int id);

    // INSERT universal
    public void inserir(T obj) {
        List<String> colunas = colunasInsercao();
        String sql = "INSERT INTO " + tabela()
                   + " (" + String.join(", ", colunas) + ") VALUES ("
                   + String.join(", ", Collections.nCopies(colunas.size(), "?")) + ")";

        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            preencherStatement(ps, obj);
            ps.executeUpdate();

            try (ResultSet chaves = ps.getGeneratedKeys()) {
                if (chaves.next()) {
                    definirId(obj, chaves.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir em " + tabela() + ": " + e.getMessage(), e);
        }
    }

    public String colunaId() {
        return "id";
    }

    public void atualizar(int id, T obj) {
        List<String> colunas = colunasAtualizacao();
        List<String> atribuicoes = new ArrayList<>();

        for (String coluna : colunas) {
            atribuicoes.add(coluna + " = ?");
        }

        String sql = "UPDATE " + tabela()
                + " SET " + String.join(", ", atribuicoes)
                + " WHERE " + colunaId() + " = ?";

        try (Connection conn = ConexaoBD.getConexao();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            preencherStatement(ps, obj);
            ps.setInt(colunas.size() + 1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(
                "Erro ao atualizar em " + tabela() + ": " + e.getMessage(), e
            );
        }
    }

    public List<T> listarTodos() {
        return consultar("SELECT * FROM " + tabela());
    }

    public T buscarPorId(int id) {
        List<T> r = consultar("SELECT * FROM " + tabela() + " WHERE " + colunaId() + " = ?", id);
        return r.isEmpty() ? null : r.get(0);
    }

    // Public porque os DAOs específicos usam para criar suas próprias buscas
    public List<T> consultar(String sql, Object... parametros) {
        List<T> lista = new ArrayList<>();

        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            for (int i = 0; i < parametros.length; i++) {
                ps.setObject(i + 1, parametros[i]);
            }
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapear(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro na consulta em " + tabela() + ": " + e.getMessage(), e);
        }
        return lista;
    }

    public List<String> colunasAtualizacao() {
    return colunasInsercao();
    }

    public void excluir(int id) {
    String sql = "DELETE FROM " + tabela()
               + " WHERE " + colunaId() + " = ?";

    try (Connection conn = ConexaoBD.getConexao();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setInt(1, id);
        ps.executeUpdate();

    } catch (SQLException e) {
        throw new RuntimeException(
            "Erro ao excluir em " + tabela() + ": " + e.getMessage(), e
        );
    }
}

}