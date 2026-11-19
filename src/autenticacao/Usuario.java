package autenticacao;
// teste

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

        return new Usuario(id, nome,email,senha,ativo,administrador,curador);
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
            this.email = email;
        }

        if (nomeValido) {
            this.nome = nome;
        }

        if (senhaValida) {
            this.senha = senha;
        }

        System.out.println("Perfil do Usuário atualizado!");
    }

    public void alterarPermissoes(boolean administrador, boolean curador){

        if (!curador && !administrador) {
            System.out.println("Não é permitido ficar sem cargo!");
            return;
        }
        

        // TODO: Bloquear ação de ZERAR OS ADMINISTRADORES DO SISTEMA USO DO BANCO DE DADOS
        this.administrador = administrador;
        this.curador = curador;
        System.out.println("As permissões do Usuário foram atualizadas!");
    }

    public void desativar(){
        this.ativo = false;
        System.out.println("A conta do usuário: " + nome+" foi desativada");
    }
    public void ativar(){
        this.ativo = true;
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
