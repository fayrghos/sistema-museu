package sistema.menu.gerenciamento;

import sistema.Sistema;
import sistema.menu.Opcao;

public final class OpcaoGerenciarExposicao extends Opcao {

    public OpcaoGerenciarExposicao(Sistema sistema) {
        super(sistema, "Exposição", false, true);
    }

    @Override
    public void interagir() {
        System.out.println("Gerenciando exposição...");
    }
}
