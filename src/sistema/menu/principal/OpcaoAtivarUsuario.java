package sistema.menu.principal;

import sistema.Sistema;
import sistema.menu.Opcao;

public final class OpcaoAtivarUsuario extends Opcao {

    public OpcaoAtivarUsuario(Sistema sistema) {
        super(sistema, "Ativar Usuário", true, false);
    }

    @Override
    public void interagir() {
        System.out.println("Ativando usuário...");
        sistema.aguardarConfirmacao();
    }
}
