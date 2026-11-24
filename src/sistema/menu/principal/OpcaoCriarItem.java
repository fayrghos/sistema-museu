package sistema.menu.principal;

import sistema.Sistema;
import sistema.menu.Opcao;

public final class OpcaoCriarItem extends Opcao {

    public OpcaoCriarItem(Sistema sistema) {
        super(sistema, "Criar Item", false, true);
    }

    @Override
    public void interagir() {
        System.out.println("Criando item...");
        sistema.aguardarConfirmacao();
    }
}
