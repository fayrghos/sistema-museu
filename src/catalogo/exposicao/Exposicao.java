package catalogo.exposicao;

import java.util.Date;
import java.util.List;
import autenticacao.Usuario;

public class Exposicao {
    private int id;
    private String titulo;
    private String tema;
    private String conceito;
    private String objetivos;
    private String descricao;
    private Date periodoInicioPrevisto;
    private Date periodoFimPrevisto;
    private String textoApresentacao;
    private StatusExposicao status;


    public Exposicao(int id, String titulo, String tema,
        String conceito, String objetivos, String descricao,
        Date periodoInicioPrevisto, Date periodoFimPrevisto,
        String textoApresentacao, StatusExposicao status) {

        this.id = id;
        this.titulo = titulo;
        this.tema = tema;
        this.conceito = conceito;
        this.objetivos = objetivos;
        this.descricao = descricao;
        this.periodoInicioPrevisto = periodoInicioPrevisto;
        this.periodoFimPrevisto = periodoFimPrevisto;
        this.textoApresentacao = textoApresentacao;
        this.status = status;
    }


    private Exposicao(Usuario usuario, String titulo, String tema, String conceito, String objetivos, String descricao, Date periodoInicioPrevisto, Date periodoFimPrevisto, String textoApresentacao){
        this.titulo = titulo;
        this.tema = tema;
        this.conceito = conceito;
        this.objetivos = objetivos;
        this.descricao = descricao;
        this.periodoInicioPrevisto = periodoInicioPrevisto;
        this.periodoFimPrevisto = periodoFimPrevisto;
        this.textoApresentacao = textoApresentacao;
        status = StatusExposicao.PLANEJAMENTO;
    }

    public static Exposicao criarExposicao(Usuario usuario, String titulo, String tema, String conceito, String objetivos, String descricao, Date periodoInicioPrevisto, Date periodoFimPrevisto, String textoApresentacao){
        Exposicao exposicao = new Exposicao(usuario, titulo, tema, conceito, objetivos, descricao, periodoInicioPrevisto, periodoFimPrevisto, textoApresentacao);

        ExposicaoDAO dao = new ExposicaoDAO();

        dao.inserir(exposicao, usuario);
        // criar exposição: passa -> id exposicao, id usuario, id status_exposicao, titulo, tema, conceito, objetivos, descricao, periodo_inicio_previsto, periodo_fim_previsto, texto_apresentacao

        return exposicao;
    }

    public void verExposicao() {
        System.out.printf("ID: %d%n", id);
        System.out.printf("Título: %s%n", titulo);
        System.out.printf("Tema: %s%n", tema);
        System.out.printf("Conceito: %s%n", conceito);
        System.out.printf("Objetivos: %s%n", objetivos);
        System.out.printf("Descrição: %s%n", descricao);
        System.out.printf("Data inicial prevista: %s%n", periodoInicioPrevisto);
        System.out.printf("Data final prevista: %s%n", periodoFimPrevisto);
        System.out.printf("Texto de apresentação: %s%n", textoApresentacao);
        System.out.printf("Status: %s%n", status);
    }

    public void editarExposicao(String titulo, String tema, String conceito, String objetivos, String descricao, Date periodoInicioPrevisto, Date periodoFimPrevisto, String textoApresentacao){
        this.titulo = titulo;
        this.tema = tema;
        this.conceito = conceito;
        this.objetivos = objetivos;
        this.descricao = descricao;
        this.periodoInicioPrevisto = periodoInicioPrevisto;
        this.periodoFimPrevisto = periodoFimPrevisto;
        this.textoApresentacao = textoApresentacao;

        ExposicaoDAO dao = new ExposicaoDAO();
        dao.atualizar(id, this);
    }

    public void excluirExposicao(){
        ExposicaoDAO dao = new ExposicaoDAO();
        dao.excluirExposicao(id);
    }

    public static List<Exposicao> listarExposicoes(){
        ExposicaoDAO dao = new ExposicaoDAO();
        List<Exposicao> exposicoes;
        exposicoes = dao.listarTodos();
        return exposicoes;
    }

    public static List<Exposicao> buscarPorTitulo(String titulo){
        ExposicaoDAO dao = new ExposicaoDAO();
        return dao.buscarPorTitulo(titulo);
    }

    public static List<Exposicao> buscarPorTema(String tema){
        ExposicaoDAO dao = new ExposicaoDAO();
        return dao.buscarPorTema(tema);
    }

    public void iniciarDesenvolvimento() {
    status = StatusExposicao.EM_DESENVOLVIMENTO;

    ExposicaoDAO dao = new ExposicaoDAO();
    dao.atualizar(id, this);
    }

    public void concluirExposicao(){
        status = StatusExposicao.CONCLUIDA;

        ExposicaoDAO dao = new ExposicaoDAO();
        dao.atualizar(id, this);
    }

    public void reabrirExposicao(){
        status = StatusExposicao.EM_DESENVOLVIMENTO;

        ExposicaoDAO dao = new ExposicaoDAO();
        dao.atualizar(id, this);
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getTema() {
        return tema;
    }

    public String getConceito() {
        return conceito;
    }

    public String getObjetivos() {
        return objetivos;
    }

    public String getDescricao() {
        return descricao;
    }

    public Date getPeriodoInicioPrevisto() {
        return periodoInicioPrevisto;
    }

    public Date getPeriodoFimPrevisto() {
        return periodoFimPrevisto;
    }

    public String getTextoApresentacao() {
        return textoApresentacao;
    }

    public StatusExposicao getStatus() {
        return status;
    }

    public void setId(int id){
        this.id = id;
    }
}
