package sistema.menu.principal;

import sistema.Sistema;
import sistema.menu.Opcao;

public final class OpcaoDesativarUsuario extends Opcao {

    public OpcaoDesativarUsuario(Sistema sistema) {
        super(sistema, "Desativar Usuário", true, false);
    }

    @Override
    public void interagir() {
        System.out.println("Desativando usuário...");
        sistema.aguardarConfirmacao();
    }
}
