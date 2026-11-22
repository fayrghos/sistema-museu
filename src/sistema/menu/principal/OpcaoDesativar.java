package sistema.menu.principal;

import sistema.Sistema;
import sistema.menu.Opcao;

public final class OpcaoDesativar extends Opcao {

    public OpcaoDesativar(Sistema sistema) {
        super(sistema, "Desativar Usuário", true, false);
    }

    @Override
    public void interagir() {
        System.out.println("Não implementado.");
    }
}
