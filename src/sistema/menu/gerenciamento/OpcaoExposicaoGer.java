package sistema.menu.gerenciamento;

import sistema.Sistema;
import sistema.menu.Opcao;

public final class OpcaoExposicaoGer extends Opcao {

    public OpcaoExposicaoGer(Sistema sistema) {
        super(sistema, "Exposição", false, true);
    }

    @Override
    public void interagir() {
        System.out.println("Não implementada.");
    }
}
