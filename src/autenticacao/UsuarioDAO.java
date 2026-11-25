package autenticacao;

import banco.BancoGenericoDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UsuarioDAO extends BancoGenericoDAO<Usuario> {

    @Override
    public String tabela() {
        return "USUARIO";
    }

    @Override
    public List<String> colunasInsercao() {
        return List.of("nome", "email", "senha", "ativo", "administrador", "curador");
    }

    @Override
    public void preencherStatement(PreparedStatement ps, Usuario u) throws SQLException {
        ps.setString(1, u.getNome());
        ps.setString(2, u.getEmail());
        ps.setString(3, u.getSenha());
        ps.setBoolean(4, u.getAtivo());
        ps.setBoolean(5, u.getAdministrador());
        ps.setBoolean(6, u.getCurador());
    }

    @Override
    public String colunaId() {
        return "id_usuario";
    }

    @Override
    public Usuario mapear(ResultSet rs) throws SQLException {
        return Usuario.recuperarDoBanco(
            rs.getInt(colunaId()),
            rs.getString("nome"),
            rs.getString("email"),
            rs.getString("senha"),
            rs.getBoolean("ativo"),
            rs.getBoolean("administrador"),
            rs.getBoolean("curador")
        );
    }
    
    public void atualizar(Usuario u) {
        atualizar(u.getId(), u);
    }

    @Override
    public void definirId(Usuario u, int id) {
        u.setId(id);
    }

    // Buscas específicas
    public Usuario buscarPorEmail(String email) {
        List<Usuario> r = consultar("SELECT * FROM USUARIO WHERE LOWER(email) = LOWER(?)", email);
        return r.isEmpty() ? null : r.get(0);
    }

    public List<Usuario> buscarPorNome(String nome) {
        return consultar("SELECT * FROM USUARIO WHERE nome ILIKE ? ORDER BY nome", "%" + nome + "%");
    }

    public List<Usuario> buscarAdministradoresAtivos() {
        return consultar("SELECT * FROM USUARIO WHERE administrador = TRUE AND ativo = TRUE");
    }

    public int contarAdministradoresAtivos() {
        return buscarAdministradoresAtivos().size();
    }
}