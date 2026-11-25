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

    public List<Obra> buscarPorTitulo(String titulo) {
        return consultar(
                "SELECT * FROM OBRA WHERE titulo ILIKE ? ORDER BY titulo",
                "%" + titulo + "%");
    }

    public List<Obra> buscarPorAutoria(String autoria) {
        return consultar(
                "SELECT * FROM OBRA WHERE autoria ILIKE ? ORDER BY autoria",
                "%" + autoria + "%");
    }

}