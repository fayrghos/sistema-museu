package catalogo.obra;

import java.util.ArrayList;
import java.util.List;

import catalogo.nucleo.NucleoTematico;

public class Obra {
    private int id;
    private String titulo;
    private String autoria;
    private int ano;
    private String tecnica;
    private String descricao;
    private CategoriaObra categoria;
    private String legenda;

    private NucleoTematico nucleoTematico;

    private static int proximoId = 1;
    private static List<Obra> obras = new ArrayList<>();

    private Obra(String titulo, String autoria, int ano, String tecnica, String descricao, CategoriaObra categoria,
            String legenda) {

        this.id = proximoId++;
        this.titulo = titulo;
        this.autoria = autoria;
        this.ano = ano;
        this.tecnica = tecnica;
        this.descricao = descricao;
        this.categoria = categoria;
        this.legenda = legenda;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutoria() {
        return autoria;
    }

    public int getAno() {
        return ano;
    }

    public String getTecnica() {
        return tecnica;
    }

    public String getDescricao() {
        return descricao;
    }

    public CategoriaObra getCategoria() {
        return categoria;
    }

    public String getLegenda() {
        return legenda;
    }

    public NucleoTematico getNucleoTematico() {
        return nucleoTematico;
    }

    public static Obra criarObra(String titulo, String autoria, int ano, String tecnica, String descricao,
            CategoriaObra categoria, String legenda) {

        Obra novaObra = new Obra(
                titulo, autoria, ano, tecnica,
                descricao, categoria, legenda);

        obras.add(novaObra);

        return novaObra;
    }

    public static List<Obra> listarObras() {
        return new ArrayList<>(obras);
    }

    public static List<Obra> buscarPorTitulo(String titulo) {

        List<Obra> resultado = new ArrayList<>();

        if (titulo == null || titulo.isBlank()) {
            return resultado;
        }

        for (Obra obra : obras) {

            if (obra.getTitulo().toLowerCase()
                    .contains(titulo.toLowerCase())) {

                resultado.add(obra);
            }
        }

        return resultado;
    }

    public static List<Obra> buscarPorAutoria(String autoria) {

        List<Obra> resultado = new ArrayList<>();

        if (autoria == null || autoria.isBlank()) {
            return resultado;
        }

        for (Obra obra : obras) {

            if (obra.getAutoria().toLowerCase()
                    .contains(autoria.toLowerCase())) {

                resultado.add(obra);
            }
        }

        return resultado;
    }

    public void verObra() {
        System.out.println("ID: " + id);
        System.out.println("Título: " + titulo);
        System.out.println("Autoria: " + autoria);
        System.out.println("Ano: " + ano);
        System.out.println("Técnica: " + tecnica);
        System.out.println("Descrição: " + descricao);
        System.out.println("Categoria: " + categoria);
        System.out.println("Legenda: " + legenda);
    }

    public void editarObra(String titulo, String autoria,
            int ano, String tecnica, String descricao,
            CategoriaObra categoria, String legenda) {

        this.titulo = titulo;
        this.autoria = autoria;
        this.ano = ano;
        this.tecnica = tecnica;
        this.descricao = descricao;
        this.categoria = categoria;
        this.legenda = legenda;
    }

    public boolean vincularNucleo(NucleoTematico nucleo) {

        if (nucleo == null || this.nucleoTematico != null) {
            return false;
        }

        this.nucleoTematico = nucleo;

        return true;
    }

    public boolean desvincularNucleo() {

        if (this.nucleoTematico == null) {
            return false;
        }

        this.nucleoTematico = null;

        return true;
    }

    public void excluirObra() {

        if (this.nucleoTematico != null) {
            System.out.println(
                    "Não é possível excluir: a obra está vinculada a um núcleo.");
            return;
        }

        obras.remove(this);
    }

}
