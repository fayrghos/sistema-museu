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
        return new Exposicao(usuario, titulo, tema, conceito, objetivos, descricao, periodoInicioPrevisto, periodoFimPrevisto, textoApresentacao);
    }

    public void verExposicao(){
        System.out.println("ID: " + id);
        System.out.println("Título: " + titulo);
        System.out.println("Tema: " + tema);
        System.out.println("Conceito: " + conceito);
        System.out.println("Objetivos: " + objetivos);
        System.out.println("Descrição: " + descricao);
        System.out.println("Previsão para Início: " + periodoInicioPrevisto);
        System.out.println("Previsão para término: " + periodoFimPrevisto);
        System.out.println("Apresentação: " + textoApresentacao);
        System.out.println("Status: " + status);
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
    }

    public void excluirExposicao(){
        // exclui uma exposição
    }

    public static List<Exposicao> listarExposicoes(){
        // retorna todas as exposições do banco de dados
    }

    public static List<Exposicao> buscarPorTitulo(String titulo){
        // retorna todas as exposições com o título x no banco de dados
    }

    public static List<Exposicao> buscarPorTema(String tema){
        // retorna todas as exposições com o tema x no banco de dados
    }

    public void iniciarDesenvolvimento(){
        status = StatusExposicao.EM_DESENVOLVIMENTO;
    }

    public void concluirExposicao(){
        status = StatusExposicao.CONCLUIDA;
    }

    public void reabrirExposicao(){
        status = StatusExposicao.EM_DESENVOLVIMENTO;
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
}
