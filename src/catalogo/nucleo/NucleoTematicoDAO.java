package catalogo.nucleo;

import banco.BancoGenericoDAO;
import banco.ConexaoBD;

import catalogo.exposicao.Exposicao;
import catalogo.exposicao.ExposicaoDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class NucleoTematicoDAO
        extends BancoGenericoDAO<NucleoTematico> {

    @Override
    public String tabela() {
        return "NUCLEO_TEMATICO";
    }

    @Override
    public String colunaId() {
        return "id_nucleo_tematico";
    }

    @Override
    public List<String> colunasInsercao() {
        return List.of(
                "id_exposicao",
                "titulo",
                "descricao",
                "texto_contextualizacao",
                "ordem");
    }

    @Override
    public void preencherStatement(PreparedStatement ps, NucleoTematico nucleo) throws SQLException {

        ps.setInt(1, nucleo.getExposicao().getId());
        ps.setString(2, nucleo.getTitulo());
        ps.setString(3, nucleo.getDescricao());
        ps.setString(4, nucleo.getTextoContextualizacao());
        ps.setInt(5, nucleo.getOrdem());
    }

    @Override
    public NucleoTematico mapear(ResultSet rs)
            throws SQLException {

        Integer idExposicao = rs.getObject(
                "id_exposicao", Integer.class);

        if (idExposicao == null) {
            throw new SQLException(
                    "Núcleo sem exposição associada.");
        }

        Exposicao exposicao = new ExposicaoDAO()
                .buscarPorId(idExposicao);

        if (exposicao == null) {
            throw new SQLException(
                    "Exposição associada não encontrada.");
        }

        return NucleoTematico.recuperarDoBanco(
                rs.getInt("id_nucleo_tematico"),
                exposicao,
                rs.getString("titulo"),
                rs.getString("descricao"),
                rs.getString("texto_contextualizacao"),
                rs.getInt("ordem"));
    }

    @Override
    public void definirId(NucleoTematico nucleo, int id) {
        nucleo.setId(id);
    }

    public void atualizar(NucleoTematico nucleo) {
        atualizar(nucleo.getId(), nucleo);
    }

    public List<NucleoTematico> buscarPorTitulo(String titulo) {

        if (titulo == null || titulo.isBlank()) {
            return List.of();
        }

        String termo = titulo.trim()
                .replace("!", "!!")
                .replace("%", "!%")
                .replace("_", "!_");

        return consultar(
                """
                        SELECT * FROM NUCLEO_TEMATICO
                        WHERE titulo ILIKE ? ESCAPE '!'
                        ORDER BY titulo
                        """,
                "%" + termo + "%");
    }

    public boolean possuiObras(int idNucleo) {

        String sql = """
                SELECT EXISTS (
                    SELECT 1
                    FROM OBRA
                    WHERE id_nucleo_tematico = ?
                )
                """;

        try (
                Connection conn = ConexaoBD.getConexao();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idNucleo);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() && rs.getBoolean(1);
            }

        } catch (SQLException e) {
            throw new RuntimeException(
                    "Erro ao verificar obras vinculadas.", e);
        }
    }
}