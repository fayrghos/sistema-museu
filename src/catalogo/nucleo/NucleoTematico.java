package catalogo.nucleo;

import java.util.ArrayList;
import java.util.List;

import catalogo.exposicao.Exposicao;
import catalogo.obra.Obra;

public class NucleoTematico {

    private int id;
    private String titulo;
    private String descricao;
    private String textoContextualizacao;
    private int ordem;

    private Exposicao exposicao;

    private static int proximoId = 1;
    private static List<NucleoTematico> nucleos = new ArrayList<>();

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

    private NucleoTematico(Exposicao exposicao, String titulo, String descricao, String textoContextualizacao, int ordem) {

        this.id = proximoId++;
        this.exposicao = exposicao;
        this.titulo = titulo;
        this.descricao = descricao;
        this.textoContextualizacao = textoContextualizacao;
        this.ordem = ordem;
    }

    public static NucleoTematico criarNucleo(Exposicao exposicao, String titulo, String descricao,
            String textoContextualizacao, int ordem) {

        if (exposicao == null) {
            return null;
        }

        NucleoTematico novoNucleo = new NucleoTematico(exposicao, titulo, descricao, textoContextualizacao, ordem);

        nucleos.add(novoNucleo);

        return novoNucleo;
    }

    public static List<NucleoTematico> listarNucleo() {
        return new ArrayList<>(nucleos);
    }

    public static List<NucleoTematico> buscarPorTitulo(String titulo) {

        List<NucleoTematico> resultado = new ArrayList<>();

        if (titulo == null || titulo.isBlank()) {
            return resultado;
        }

        for (NucleoTematico nucleo : nucleos) {

            if (nucleo.getTitulo().toLowerCase()
                    .contains(titulo.toLowerCase())) {

                resultado.add(nucleo);
            }
        }

        return resultado;
    }

    public void verNucleo() {

        System.out.println("ID: " + id);
        System.out.println("Título: " + titulo);
        System.out.println("Descrição: " + descricao);
        System.out.println("Contextualização: " + textoContextualizacao);
        System.out.println("Ordem: " + ordem);
    }

    public void editarNucleo(String titulo, String descricao, String textoContextualizacao, int ordem) {

        this.titulo = titulo;
        this.descricao = descricao;
        this.textoContextualizacao = textoContextualizacao;
        this.ordem = ordem;
    }

    public void excluirNucleo() {

        for (Obra obra : Obra.listarObras()) {
            if (obra.getNucleoTematico() == this) {
                System.out.println(
                        "Não é possível excluir: o núcleo possui obras vinculadas.");
                return;
            }
        }
        nucleos.remove(this);
    }

}