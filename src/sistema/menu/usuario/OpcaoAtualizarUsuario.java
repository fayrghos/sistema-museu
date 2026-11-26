package sistema.menu.usuario;

import autenticacao.Usuario;
import autenticacao.UsuarioDAO;
import sistema.Sistema;
import sistema.menu.Opcao;
import utilidades.Entrada;

public final class OpcaoAtualizarUsuario extends Opcao {

    public OpcaoAtualizarUsuario(Sistema sistema) {
        super(sistema, "Atualizar Usuário", true, false);
    }

    @Override
    public void interagir() {
        String emailDesejado = Entrada.lerPalavra(
            "Insira o email do usuário",
            sistema.leitor()
        );

        UsuarioDAO buscadorUsuario = new UsuarioDAO();
        Usuario usuario = buscadorUsuario.buscarPorEmail(emailDesejado);

        if (usuario == null) {
            System.out.println("Usuário não encontrado.");
            sistema.aguardarConfirmacao();
            return;
        }

        try {
            usuario.editarUsuario(
                Entrada.lerLinha("Insira o novo nome", sistema.leitor()),
                Entrada.lerPalavra("Insira o novo email", sistema.leitor()),
                Entrada.lerPalavra("Insira a nova senha", sistema.leitor())
            );
        } catch (IllegalArgumentException err) {
            System.out.println(err.getMessage());
        }

        try {
            usuario.alterarPermissoes(
                Entrada.lerBooleano("Deve ser administrador", sistema.leitor()),
                Entrada.lerBooleano("Deve ser curador", sistema.leitor())
            );
        } catch (IllegalArgumentException err) {
            System.out.println(err.getMessage());
        }

        sistema.aguardarConfirmacao();
    }
}
