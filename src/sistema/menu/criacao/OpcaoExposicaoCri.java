package sistema.menu.criacao;

import sistema.Sistema;
import sistema.menu.Opcao;

public final class OpcaoExposicaoCri extends Opcao {

    public OpcaoExposicaoCri(Sistema sistema) {
        super(sistema, "Exposição", false, true);
    }

    @Override
    public void interagir() {
        System.out.println("Não implementada.");
    }
}
