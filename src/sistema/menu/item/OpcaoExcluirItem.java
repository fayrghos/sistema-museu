package sistema.menu.item;

import sistema.Sistema;
import sistema.menu.Opcao;

public final class OpcaoExcluirItem extends Opcao {

    public OpcaoExcluirItem(Sistema sistema) {
        super(sistema, "Excluir Item", false, true);
    }

    @Override
    public void interagir() {
        System.out.println("Excluindo item...");
        sistema.aguardarConfirmacao();
    }
}
