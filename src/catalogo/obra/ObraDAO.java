package catalogo.obra;

import banco.BancoGenericoDAO;
import banco.ConexaoBD;

import catalogo.nucleo.NucleoTematico;
import catalogo.nucleo.NucleoTematicoDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

public class ObraDAO extends BancoGenericoDAO<Obra> {

    @Override
    public String tabela() {
        return "OBRA";
    }

    @Override
    public String colunaId() {
        return "id_obra";
    }

    @Override
    public List<String> colunasInsercao() {
        return List.of(
                "id_categoria_obra",
                "id_nucleo_tematico",
                "titulo",
                "autoria",
                "ano",
                "tecnica",
                "descricao",
                "legenda");
    }

    private int buscarIdCategoria(CategoriaObra categoria)
            throws SQLException {

        if (categoria == null) {
            throw new IllegalArgumentException(
                    "A categoria da obra deve ser informada.");
        }

        String sql = """
                SELECT id_categoria_obra
                FROM CATEGORIA_OBRA
                WHERE nome = ?
                """;

        try (Connection conn = ConexaoBD.getConexao(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, categoria.name());

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id_categoria_obra");
                }
            }
        }

        throw new SQLException("Categoria da obra não encontrada.");
    }

    private CategoriaObra buscarCategoriaPorId(int id)
            throws SQLException {

        String sql = """
                SELECT nome
                FROM CATEGORIA_OBRA
                WHERE id_categoria_obra = ?
                """;

        try (Connection conn = ConexaoBD.getConexao(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return CategoriaObra.valueOf(
                            rs.getString("nome"));
                }
            }
        }

        throw new SQLException("Categoria da obra não encontrada.");
    }

    @Override
    public void preencherStatement(PreparedStatement ps, Obra obra) throws SQLException {

        ps.setInt(1, buscarIdCategoria(obra.getCategoria()));

        if (obra.getNucleoTematico() != null) {
            ps.setInt(2, obra.getNucleoTematico().getId());
        } else {
            ps.setNull(2, Types.INTEGER);
        }

        ps.setString(3, obra.getTitulo());
        ps.setString(4, obra.getAutoria());
        ps.setInt(5, obra.getAno());
        ps.setString(6, obra.getTecnica());
        ps.setString(7, obra.getDescricao());
        ps.setString(8, obra.getLegenda());
    }

    @Override
    public Obra mapear(ResultSet rs) throws SQLException {

        CategoriaObra categoria = buscarCategoriaPorId(rs.getInt("id_categoria_obra"));

        Integer idNucleo = rs.getObject("id_nucleo_tematico", Integer.class);

        NucleoTematico nucleo = null;

        if (idNucleo != null) {
            nucleo = new NucleoTematicoDAO().buscarPorId(idNucleo);
        }

        return Obra.recuperarDoBanco(
                rs.getInt("id_obra"),
                rs.getString("titulo"),
                rs.getString("autoria"),
                rs.getInt("ano"),
                rs.getString("tecnica"),
                rs.getString("descricao"),
                categoria,
                rs.getString("legenda"),
                nucleo);
    }

    @Override
    public void definirId(Obra obra, int id) {
        obra.setId(id);
    }

    public void atualizar(Obra obra) {
        atualizar(obra.getId(), obra);
    }

    @Override
    public void atualizar(int id, Obra obra) {

        String sql = """
                UPDATE OBRA
                SET titulo = ?,
                    autoria = ?,
                    ano = ?,
                    tecnica = ?,
                    descricao = ?,
                    id_categoria_obra = ?,
                    legenda = ?
                WHERE id_obra = ?
                """;

        try (
                Connection conn = ConexaoBD.getConexao();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, obra.getTitulo());
            ps.setString(2, obra.getAutoria());
            ps.setInt(3, obra.getAno());
            ps.setString(4, obra.getTecnica());
            ps.setString(5, obra.getDescricao());
            ps.setInt(6, buscarIdCategoria(obra.getCategoria()));
            ps.setString(7, obra.getLegenda());
            ps.setInt(8, id);

            if (ps.executeUpdate() != 1) {
                throw new IllegalStateException(
                        "Obra não encontrada para atualização.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(
                    "Erro ao atualizar obra.", e);
        }
    }

    public boolean vincularNucleo(int idObra, int idNucleo) {

        String sql = """
                UPDATE OBRA
                SET id_nucleo_tematico = ?
                WHERE id_obra = ?
                AND id_nucleo_tematico IS NULL
                """;

        try (
                Connection conn = ConexaoBD.getConexao();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idNucleo);
            ps.setInt(2, idObra);

            return ps.executeUpdate() == 1;

        } catch (SQLException e) {
            throw new RuntimeException(
                    "Erro ao vincular obra ao núcleo.", e);
        }
    }

    public boolean desvincularNucleo(int idObra, int idNucleo) {

        String sql = """
                UPDATE OBRA
                SET id_nucleo_tematico = NULL
                WHERE id_obra = ?
                AND id_nucleo_tematico = ?
                """;

        try (
                Connection conn = ConexaoBD.getConexao();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idObra);
            ps.setInt(2, idNucleo);

            return ps.executeUpdate() == 1;

        } catch (SQLException e) {
            throw new RuntimeException(
                    "Erro ao desvincular obra do núcleo.", e);
        }
    }

    @Override
    public void excluir(int id) {

        String sql = """
                DELETE FROM OBRA
                WHERE id_obra = ?
                AND id_nucleo_tematico IS NULL
                """;

        try (
                Connection conn = ConexaoBD.getConexao();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            if (ps.executeUpdate() != 1) {
                throw new IllegalStateException(
                        "A obra não existe ou possui um núcleo vinculado.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(
                    "Erro ao excluir obra.", e);
        }
    }

    private String criarPadraoBusca(String texto) {

        String termo = texto.trim()
                .replace("!", "!!")
                .replace("%", "!%")
                .replace("_", "!_");

        return "%" + termo + "%";
    }

    public List<Obra> buscarPorTitulo(String titulo) {

        if (titulo == null || titulo.isBlank()) {
            return List.of();
        }

        return consultar(
                """
                        SELECT * FROM OBRA
                        WHERE titulo ILIKE ? ESCAPE '!'
                        ORDER BY titulo
                        """,
                criarPadraoBusca(titulo));
    }

    public List<Obra> buscarPorAutoria(String autoria) {

        if (autoria == null || autoria.isBlank()) {
            return List.of();
        }

        return consultar(
                """
                        SELECT * FROM OBRA
                        WHERE autoria ILIKE ? ESCAPE '!'
                        ORDER BY autoria
                        """,
                criarPadraoBusca(autoria));
    }

}