package sistema.menu.item;

import sistema.Sistema;
import sistema.menu.Opcao;

public final class OpcaoConsultarItem extends Opcao {

    public OpcaoConsultarItem(Sistema sistema) {
        super(sistema, "Consultar Item", true, false);
    }

    @Override
    public void interagir() {
        System.out.println("Consultando item...");
        sistema.aguardarConfirmacao();
    }
}
