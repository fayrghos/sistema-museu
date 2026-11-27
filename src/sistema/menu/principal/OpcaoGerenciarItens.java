package sistema.menu.principal;

import sistema.Sistema;
import sistema.menu.IdMenu;
import sistema.menu.Opcao;

public final class OpcaoGerenciarItens extends Opcao {

    public OpcaoGerenciarItens(Sistema sistema) {
        super(sistema, "Gerenciar Itens", false, true);
    }

    @Override
    public void interagir() {
        sistema.alterarMenu(IdMenu.GERENCIAR_ITENS);
    }
}
