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
            usuario.desativar();
        } catch (IllegalArgumentException err) {
            System.out.println(err.getMessage());
        }

        sistema.aguardarConfirmacao();
    }
}
