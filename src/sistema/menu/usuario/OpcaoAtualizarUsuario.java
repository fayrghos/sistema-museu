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
        int idDesejado = Entrada.lerInteiro(
            "Insira o ID do usuário",
            sistema.leitor()
        );

        UsuarioDAO buscadorUsuario = new UsuarioDAO();
        Usuario usuario = buscadorUsuario.buscarPorId(idDesejado);

        if (usuario == null) {
            System.out.println("Usuário não encontrado.");
            sistema.aguardarConfirmacao();
            return;
        }

        try {
            usuario.editarUsuario(
                Entrada.lerPalavra("Insira o novo nome", sistema.leitor()),
                Entrada.lerPalavra("Insira o novo email", sistema.leitor()),
                Entrada.lerPalavra("Insira a nova senha", sistema.leitor())
            );
        } catch (IllegalArgumentException err) {
            System.out.println(err.getMessage());
        }

        sistema.aguardarConfirmacao();
    }
}
