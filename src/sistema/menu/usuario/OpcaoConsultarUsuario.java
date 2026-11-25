package sistema.menu.usuario;

import autenticacao.Usuario;
import autenticacao.UsuarioDAO;
import sistema.Sistema;
import sistema.menu.Opcao;
import utilidades.Entrada;

public final class OpcaoConsultarUsuario extends Opcao {

    public OpcaoConsultarUsuario(Sistema sistema) {
        super(sistema, "Consultar Usuário", true, false);
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

        usuario.verUsuario();

        sistema.aguardarConfirmacao();
    }
}
