package sistema.menu.usuario;

import sistema.Sistema;
import sistema.menu.Opcao;

public final class OpcaoAtualizarUsuario extends Opcao {

    public OpcaoAtualizarUsuario(Sistema sistema) {
        super(sistema, "Atualizar Usuário", true, false);
    }

    @Override
    public void interagir() {
        System.out.println("Atualizando usuário...");
        sistema.aguardarConfirmacao();
    }
}
