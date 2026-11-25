package catalogo.obra;

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

    private Obra(int id, String titulo, String autoria, int ano, String tecnica, String descricao,
            CategoriaObra categoria, String legenda) {
        this.id = id;
        this.titulo = titulo;
        this.autoria = autoria;
        this.ano = ano;
        this.tecnica = tecnica;
        this.descricao = descricao;
        this.categoria = categoria;
        this.legenda = legenda;
    }

    public void setId(int id) {
        if (this.id != 0) {
            throw new IllegalStateException("O ID da obra já foi definido.");
        }

        if (id <= 0) {
            throw new IllegalArgumentException("O ID deve ser positivo.");
        }

        this.id = id;
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

    public static Obra recuperarDoBanco(int id, String titulo, String autoria, int ano, String tecnica,
            String descricao, CategoriaObra categoria, String legenda, NucleoTematico nucleoTematico) {
        Obra obra = new Obra(id, titulo, autoria, ano, tecnica, descricao, categoria, legenda);

        obra.nucleoTematico = nucleoTematico;

        return obra;
    }

    public static Obra criarObra(String titulo, String autoria, int ano, String tecnica, String descricao,
            CategoriaObra categoria, String legenda) {

        Obra novaObra = new Obra(
                0,
                titulo,
                autoria,
                ano,
                tecnica,
                descricao,
                categoria,
                legenda);

        new ObraDAO().inserir(novaObra);

        return novaObra;
    }

    public static List<Obra> listarObras() {
        return new ObraDAO().listarTodos();
    }

    public static List<Obra> buscarPorTitulo(String titulo) {

        if (titulo == null || titulo.isBlank()) {
            return List.of();
        }

        return new ObraDAO().buscarPorTitulo(titulo.trim());
    }

    public static List<Obra> buscarPorAutoria(String autoria) {

        if (autoria == null || autoria.isBlank()) {
            return List.of();
        }

        return new ObraDAO().buscarPorAutoria(autoria.trim());
    }

    public void editarObra(String titulo, String autoria, int ano, String tecnica, String descricao,
            CategoriaObra categoria, String legenda) {

        Obra obraAtualizada = recuperarDoBanco(this.id, titulo, autoria, ano, tecnica, descricao, categoria, legenda,
                this.nucleoTematico);

        new ObraDAO().atualizar(obraAtualizada);

        this.titulo = titulo;
        this.autoria = autoria;
        this.ano = ano;
        this.tecnica = tecnica;
        this.descricao = descricao;
        this.categoria = categoria;
        this.legenda = legenda;
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

    public boolean vincularNucleo(NucleoTematico nucleo) {

        if (nucleo == null || nucleo.getId() <= 0) {
            return false;
        }

        boolean vinculado = new ObraDAO().vincularNucleo(
                this.id,
                nucleo.getId());

        if (vinculado) {
            this.nucleoTematico = nucleo;
        }

        return vinculado;
    }

    public boolean desvincularNucleo() {

        if (this.nucleoTematico == null) {
            return false;
        }

        boolean desvinculado = new ObraDAO().desvincularNucleo(
                this.id,
                this.nucleoTematico.getId());

        if (desvinculado) {
            this.nucleoTematico = null;
        }

        return desvinculado;
    }

    public void excluirObra() {
        try {
            new ObraDAO().excluir(this.id);

        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }

}
