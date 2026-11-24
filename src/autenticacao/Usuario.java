package autenticacao;

import utilidades.hash.SenhaHash;
import utilidades.validacao.EmailValidacao;

import java.util.ArrayList;

public class Usuario {

    private int id;
    private String nome;
    private String email;
    private String senha;
    private boolean ativo;
    private boolean administrador;
    private boolean curador;

    private Usuario(
        int id, 
        String nome,
        String email,
        String senha,
        boolean ativo,
        boolean administrador,
        boolean curador
    ){
        this.id=id;
        this.nome=nome;
        this.email=email;
        this.senha=senha;
        this.ativo=ativo;
        this.administrador=administrador;
        this.curador=curador;
    }
    public static Usuario criarUsuario(
        int id, 
        String nome,
        String email,
        String senha,
        boolean ativo,
        boolean administrador,
        boolean curador
    ){
        if (!EmailValidacao.validarEmail(email)) {
            throw new IllegalArgumentException("E-mail inválido!");
        }

        String senhaHash = SenhaHash.gerarHash(senha);
        Usuario novoUsuario = new Usuario(id, nome,email,senhaHash,ativo,administrador,curador);

        // Chamar função e salva no banco
        return novoUsuario;
    }
    
    public int getId(){
        return id;
    }

    public String getNome(){
        return nome;
    }

    public String getEmail(){
        return email;
    }

    public String getSenha(){
        return senha;
    }

    public boolean getAtivo(){
        return ativo;
    }

    public boolean getAdministrador(){
        return administrador;
    }

    public boolean getCurador(){
        return curador;
    }

    private void setNome(String nome){
        this.nome = nome;
    }

    private void setEmail(String email){
        this.email = email;
    }

    private void setSenha(String senha){
        this.senha = senha;
    }

    private void setAtivo(boolean ativo){
        this.ativo = ativo;
    }

    private void setAdministrador(boolean administrador){
        this.administrador = administrador;
    }

    private void setCurador(boolean curador){
        this.curador = curador;
    }

    public void verUsuario() {
        System.out.println("-------------Dados Usuário-------------");
        System.out.println("Nome: "+ nome + "\nE-mail: "+email);
        System.out.println(
            "Conta: " + (ativo?"Ativa":"Desativada") + 
            "\nAdministrador: " + (administrador?"Sim":"Não")+
            "\nCurador: " + (curador?"Sim":"Não")
        );
    }

    public void editarUsuario(String nome, String email, String senha){

        boolean nomeValido = nome != null && !nome.isBlank();
        boolean emailValido = email != null && !email.isBlank();
        boolean senhaValida = senha != null && !senha.isBlank();

        if (!nomeValido && !emailValido && !senhaValida) {
            System.out.println("Perfil do Usuário não foi atualizado!");
            return;
        }

        if (emailValido) {
            // Consulta no banco: se o e-mail já existir em outro usuário, avisa e dá return;
            setEmail(email);
        }

        if (nomeValido) {
            setNome(nome);
        }

        if (senhaValida) {
            setSenha(senha);
        }

        System.out.println("Perfil do Usuário atualizado!");
    }

    public void alterarPermissoes(boolean administrador, boolean curador){

        if (!curador && !administrador) {
            System.out.println("Não é permitido ficar sem cargo!");
            return;
        }
        

        // TODO: Bloquear ação de ZERAR OS ADMINISTRADORES DO SISTEMA USO DO BANCO DE DADOS
        setAdministrador(administrador);
        setCurador(curador);
        System.out.println("As permissões do Usuário foram atualizadas!");
    }

    public void desativar(){
        setAtivo(false);
        System.out.println("A conta do usuário: " + nome+" foi desativada");
    }

    public void ativar(){
        setAtivo(true);
        System.out.println("A conta do usuário: " + nome+" foi ativada");
    }
    
    public static ArrayList<Usuario> listarUsuario() {

        ArrayList<Usuario> lista = new ArrayList<>();

        if (lista.isEmpty()) {
            System.out.println("Lista Vazia");
            return null;
        }

        // Lógica da captura dos usuario do banco de dados
        return lista;
    }
}
