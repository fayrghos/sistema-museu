package autenticacao;

import utilidades.hash.SenhaHash;
import utilidades.validacao.EmailValidacao;

import java.util.List;

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

        UsuarioDAO bancoUsuario = new UsuarioDAO();

        if (bancoUsuario.buscarPorEmail(email) != null) {
            throw new IllegalArgumentException("E-mail já existe no sistema!");
        }

        if (!administrador && !curador) {
            throw new IllegalArgumentException("O usuário não pode ficar sem cargo!");
        }
        
        String senhaHash = SenhaHash.gerarHash(senha);
        Usuario novoUsuario = new Usuario(0, nome, email, senhaHash, ativo, administrador, curador);

        bancoUsuario.inserir(novoUsuario);
        return novoUsuario;
    }

    public static Usuario recuperarDoBanco(
        int id,
        String nome,
        String email,
        String senhaHash,
        boolean ativo,
        boolean administrador,
        boolean curador
    ) {
        return new Usuario(id, nome, email, senhaHash, ativo, administrador, curador);
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

    public void setId(int id) {
        if (this.id != 0) {
            throw new IllegalStateException("O id do usuário já foi definido e não pode ser alterado.");
        }
        this.id = id;
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
        System.out.println();
        System.out.println("ID: "+ id + "\nNome: "+ nome + "\nE-mail: "+email);
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

        UsuarioDAO banco = new UsuarioDAO();

        // Valida tudo antes de alterar qualquer campo
        if (emailValido) {
            if (!EmailValidacao.validarEmail(email)) {
                throw new IllegalArgumentException("E-mail inválido!");
            }

            Usuario existente = banco.buscarPorEmail(email);
            if (existente != null && existente.getId() != this.id) {
                throw new IllegalArgumentException("E-mail já existe no sistema!");
            }
            setEmail(email);
        }

        if (nomeValido) {
            setNome(nome);
        }

        if (senhaValida) {
            setSenha(SenhaHash.gerarHash(senha));
        }

        banco.atualizar(this);
        System.out.println("Perfil do Usuário atualizado!");
    }

    public void alterarPermissoes(boolean administrador, boolean curador){

        if (!curador && !administrador) {
            throw new IllegalArgumentException("Não é permitido ficar sem cargo!");
        }

        UsuarioDAO banco = new UsuarioDAO();

        // Só bloqueia se é admin ativo agora e está deixando de ser
        boolean deixandoDeSerAdmin = this.administrador && !administrador;
        if (deixandoDeSerAdmin && this.ativo && banco.contarAdministradoresAtivos() <= 1) {
            throw new IllegalArgumentException("Você é o único administrador, não é possível alterar permissão!");
        }

        setAdministrador(administrador);
        setCurador(curador);
        banco.atualizar(this);
        System.out.println("As permissões do Usuário foram atualizadas!");
    }

    public void desativar(){

        UsuarioDAO banco = new UsuarioDAO();

        // Só bloqueia se é admin ativo (o último)
        if (this.administrador && this.ativo && banco.contarAdministradoresAtivos() <= 1) {
            throw new IllegalArgumentException("Você é o único administrador, não é possível desativar a conta!");
        }

        setAtivo(false);
        banco.atualizar(this);
        System.out.println("A conta do usuário '" + nome + "' foi desativada");
    }

    public void ativar(){
        setAtivo(true);
        new UsuarioDAO().atualizar(this);
        System.out.println("A conta do usuário '" + nome + "' foi ativada");
    }

    public static List<Usuario> listarUsuario() {
        return new UsuarioDAO().listarTodos();
    }

    public static boolean autenticar(String email, String senha) {
        if (email == null || email.isBlank() || senha == null || senha.isBlank()) {
            return false;
        }

        Usuario usuario = new UsuarioDAO().buscarPorEmail(email);

        if (usuario == null || !usuario.getAtivo()) {
            return false;
        }

        return SenhaHash.verificarSenha(senha, usuario.getSenha());
    }
}