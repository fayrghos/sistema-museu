package sistema.menu.usuario;

import autenticacao.Usuario;
import java.util.List;
import sistema.Sistema;
import sistema.menu.Opcao;

public final class OpcaoListarUsuario extends Opcao {

    public OpcaoListarUsuario(Sistema sistema) {
        super(sistema, "Listar Usuários", true, false);
    }

    @Override
    public void interagir() {
        List<Usuario> usuarios = Usuario.listarUsuario();

        System.out.printf("Usuários cadastrados: %d\n", usuarios.size());
        for (Usuario usuario : Usuario.listarUsuario()) {
            usuario.verUsuario();
        }
        sistema.aguardarConfirmacao();
    }
}
