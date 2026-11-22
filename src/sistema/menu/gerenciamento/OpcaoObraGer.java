package sistema.menu.gerenciamento;

import sistema.Sistema;
import sistema.menu.Opcao;

public final class OpcaoObraGer extends Opcao {

    public OpcaoObraGer(Sistema sistema) {
        super(sistema, "Obra", false, true);
    }

    @Override
    public void interagir() {
        System.out.println("Não implementada.");
    }
}
