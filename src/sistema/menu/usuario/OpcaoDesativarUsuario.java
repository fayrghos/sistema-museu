package sistema.menu.usuario;

import autenticacao.Usuario;
import autenticacao.UsuarioDAO;
import sistema.Sistema;
import sistema.menu.Opcao;
import utilidades.Entrada;

public final class OpcaoDesativarUsuario extends Opcao {

    public OpcaoDesativarUsuario(Sistema sistema) {
        super(sistema, "Desativar Usuário", true, false);
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
            usuario.desativar();

            if (sistema.usuario().getId() == usuario.getId()) {
                System.out.println(
                    "Você desativou a própria conta e será deslogado."
                );
                sistema.deslogarUsuario();
            }
        } catch (IllegalArgumentException err) {
            System.out.println(err.getMessage());
        }

        sistema.aguardarConfirmacao();
    }
}
