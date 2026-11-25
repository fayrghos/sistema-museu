package sistema.menu.usuario;

import autenticacao.Usuario;
import autenticacao.UsuarioDAO;
import sistema.Sistema;
import sistema.menu.Opcao;
import utilidades.Entrada;

public final class OpcaoAtivarUsuario extends Opcao {

    public OpcaoAtivarUsuario(Sistema sistema) {
        super(sistema, "Ativar Usuário", true, false);
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

        usuario.ativar();

        sistema.aguardarConfirmacao();
    }
}
