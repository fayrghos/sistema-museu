package catalogo.nucleo;

import catalogo.exposicao.Exposicao;
import java.util.List;

public class NucleoTematico {

    private int id;
    private String titulo;
    private String descricao;
    private String textoContextualizacao;
    private int ordem;

    private Exposicao exposicao;

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getTextoContextualizacao() {
        return textoContextualizacao;
    }

    public int getOrdem() {
        return ordem;
    }

    public Exposicao getExposicao() {
        return exposicao;
    }

    public void setId(int id) {
        if (this.id != 0) {
            throw new IllegalStateException("O ID do núcleo já foi definido.");
        }

        if (id <= 0) {
            throw new IllegalArgumentException("O ID deve ser positivo.");
        }

        this.id = id;
    }

    private NucleoTematico(
        int id,
        Exposicao exposicao,
        String titulo,
        String descricao,
        String textoContextualizacao,
        int ordem
    ) {
        this.id = id;
        this.exposicao = exposicao;
        this.titulo = titulo;
        this.descricao = descricao;
        this.textoContextualizacao = textoContextualizacao;
        this.ordem = ordem;
    }

    public static NucleoTematico recuperarDoBanco(
        int id,
        Exposicao exposicao,
        String titulo,
        String descricao,
        String textoContextualizacao,
        int ordem
    ) {
        return new NucleoTematico(
            id,
            exposicao,
            titulo,
            descricao,
            textoContextualizacao,
            ordem
        );
    }

    public static NucleoTematico criarNucleo(
        Exposicao exposicao,
        String titulo,
        String descricao,
        String textoContextualizacao,
        int ordem
    ) {
        if (exposicao == null || exposicao.getId() <= 0) {
            return null;
        }

        NucleoTematico novoNucleo = new NucleoTematico(
            0,
            exposicao,
            titulo,
            descricao,
            textoContextualizacao,
            ordem
        );

        new NucleoTematicoDAO().inserir(novoNucleo);

        return novoNucleo;
    }

    public static List<NucleoTematico> listarNucleo() {
        return new NucleoTematicoDAO().listarTodos();
    }

    public static List<NucleoTematico> buscarPorTitulo(String titulo) {
        if (titulo == null || titulo.isBlank()) {
            return List.of();
        }

        return new NucleoTematicoDAO().buscarPorTitulo(titulo.trim());
    }

    public void verNucleo() {
        System.out.println("ID: " + id);
        System.out.println("Título: " + titulo);
        System.out.println("Descrição: " + descricao);
        System.out.println("Contextualização: " + textoContextualizacao);
        System.out.println("Ordem: " + ordem);
    }

    public void editarNucleo(
        String titulo,
        String descricao,
        String textoContextualizacao,
        int ordem
    ) {
        NucleoTematico nucleoAtualizado = recuperarDoBanco(
            this.id,
            this.exposicao,
            titulo,
            descricao,
            textoContextualizacao,
            ordem
        );

        new NucleoTematicoDAO().atualizar(nucleoAtualizado);

        this.titulo = titulo;
        this.descricao = descricao;
        this.textoContextualizacao = textoContextualizacao;
        this.ordem = ordem;
    }

    public void excluirNucleo() {
        NucleoTematicoDAO banco = new NucleoTematicoDAO();

        if (banco.possuiObras(this.id)) {
            System.out.println(
                "Não é possível excluir: o núcleo possui obras vinculadas."
            );
            return;
        }

        try {
            banco.excluir(this.id);
            System.out.println("Núcleo excluído com sucesso.");
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }
}
