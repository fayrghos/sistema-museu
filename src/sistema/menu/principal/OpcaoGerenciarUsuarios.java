package sistema.menu.principal;

import sistema.Sistema;
import sistema.menu.IdMenu;
import sistema.menu.Opcao;

public final class OpcaoGerenciarUsuarios extends Opcao {

    public OpcaoGerenciarUsuarios(Sistema sistema) {
        super(sistema, "Gerenciar Usuários", true, false);
    }

    @Override
    public void interagir() {
        sistema.alterarMenu(IdMenu.GERENCIAR_USUARIOS);
    }
}
