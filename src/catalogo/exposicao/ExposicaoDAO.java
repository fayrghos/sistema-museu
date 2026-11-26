package catalogo.exposicao;

import autenticacao.Usuario;
import banco.BancoGenericoDAO;
import banco.ConexaoBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.List;

public class ExposicaoDAO extends BancoGenericoDAO<Exposicao> {

    @Override
    public String colunaId() {
        return "id_exposicao";
    }

    @Override
    public Exposicao mapear(ResultSet rs) throws SQLException {
        return new Exposicao(
            rs.getInt("id_exposicao"),
            rs.getString("titulo"),
            rs.getString("tema"),
            rs.getString("conceito"),
            rs.getString("objetivos"),
            rs.getString("descricao"),
            rs.getDate("periodo_inicio_previsto"),
            rs.getDate("periodo_fim_previsto"),
            rs.getString("texto_apresentacao"),
            StatusExposicao.buscarPorId(rs.getInt("id_status_exposicao"))
        );
    }

    public void inserir(Exposicao obj, Usuario usuario) {
        List<String> colunas = colunasInsercao();

        String sql =
            "INSERT INTO " +
            tabela() +
            " (" +
            String.join(", ", colunas) +
            ") VALUES (" +
            String.join(", ", Collections.nCopies(colunas.size(), "?")) +
            ")";

        try (
            Connection conn = ConexaoBD.getConexao();
            PreparedStatement ps = conn.prepareStatement(
                sql,
                Statement.RETURN_GENERATED_KEYS
            )
        ) {
            ps.setInt(1, usuario.getId());
            ps.setInt(2, obj.getStatus().getId());

            preencherDadosExposicao(ps, obj, 3);

            ps.executeUpdate();

            try (ResultSet chaves = ps.getGeneratedKeys()) {
                if (chaves.next()) {
                    definirId(obj, chaves.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(
                "Erro ao inserir em " + tabela() + ": " + e.getMessage(),
                e
            );
        }
    }

    @Override
    public String tabela() {
        return "EXPOSICAO";
    }

    @Override
    public List<String> colunasInsercao() {
        return List.of(
            "id_usuario",
            "id_status_exposicao",
            "titulo",
            "tema",
            "conceito",
            "objetivos",
            "descricao",
            "periodo_inicio_previsto",
            "periodo_fim_previsto",
            "texto_apresentacao"
        );
    }

    @Override
    public void preencherStatement(
        PreparedStatement ps,
        Exposicao e
    ) throws SQLException {
        ps.setInt(1, e.getStatus().getId());
        preencherDadosExposicao(ps, e, 2);
    }

    @Override
    public void definirId(Exposicao e, int id) {
        e.setId(id);
    }

    public void excluirExposicao(int id) {
        excluir(id);
    }

    public List<Exposicao> buscarPorTema(String tema) {
        return consultar(
            "SELECT * FROM " + tabela() + " WHERE LOWER(tema) LIKE LOWER(?)",
            "%" + tema + "%"
        );
    }

    public List<Exposicao> buscarPorTitulo(String titulo) {
        return consultar(
            "SELECT * FROM " + tabela() + " WHERE LOWER(titulo) LIKE LOWER(?)",
            "%" + titulo + "%"
        );
    }

    @Override
    public List<String> colunasAtualizacao() {
        return List.of(
            "id_status_exposicao",
            "titulo",
            "tema",
            "conceito",
            "objetivos",
            "descricao",
            "periodo_inicio_previsto",
            "periodo_fim_previsto",
            "texto_apresentacao"
        );
    }

    private void preencherDadosExposicao(
        PreparedStatement ps,
        Exposicao e,
        int inicio
    ) throws SQLException {
        ps.setString(inicio, e.getTitulo());
        ps.setString(inicio + 1, e.getTema());
        ps.setString(inicio + 2, e.getConceito());
        ps.setString(inicio + 3, e.getObjetivos());
        ps.setString(inicio + 4, e.getDescricao());

        ps.setDate(
            inicio + 5,
            new java.sql.Date(e.getPeriodoInicioPrevisto().getTime())
        );

        ps.setDate(
            inicio + 6,
            new java.sql.Date(e.getPeriodoFimPrevisto().getTime())
        );

        ps.setString(inicio + 7, e.getTextoApresentacao());
    }
}
