package sistema.menu.usuario;

import autenticacao.Usuario;
import sistema.Sistema;
import sistema.menu.Opcao;
import utilidades.Entrada;

public final class OpcaoCadastrarUsuario extends Opcao {

    public OpcaoCadastrarUsuario(Sistema sistema) {
        super(sistema, "Cadastrar Usuário", true, false);
    }

    @Override
    public void interagir() {
        try {
            Usuario.criarUsuario(
                Entrada.lerPalavra("Insira o nome", sistema.leitor()),
                Entrada.lerPalavra("Insira o email", sistema.leitor()),
                Entrada.lerPalavra("Insira a senha", sistema.leitor()),
                Entrada.lerBooleano("Deve estar ativo", sistema.leitor()),
                Entrada.lerBooleano("Deve ser administrador", sistema.leitor()),
                Entrada.lerBooleano("Deve ser curador", sistema.leitor())
            );
        } catch (IllegalArgumentException err) {
            System.out.println(err.getMessage());
        }

        System.out.println("Usuário criado com sucesso!");
        sistema.aguardarConfirmacao();
    }
}
