package sistema.menu.principal;

import sistema.Sistema;
import sistema.menu.Opcao;

public final class OpcaoAtivar extends Opcao {

    public OpcaoAtivar(Sistema sistema) {
        super(sistema, "Ativar Usuário", true, false);
    }

    @Override
    public void interagir() {
        System.out.println("Não implementado.");
    }
}
