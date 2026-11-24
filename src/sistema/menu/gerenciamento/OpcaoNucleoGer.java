package sistema.menu.gerenciamento;

import sistema.Sistema;
import sistema.menu.Opcao;

public final class OpcaoNucleoGer extends Opcao {

    public OpcaoNucleoGer(Sistema sistema) {
        super(sistema, "Núcleo", false, true);
    }

    @Override
    public void interagir() {
        System.out.println("Não implementada.");
    }
}
