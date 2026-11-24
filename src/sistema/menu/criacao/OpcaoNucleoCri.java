package sistema.menu.criacao;

import sistema.Sistema;
import sistema.menu.Opcao;

public final class OpcaoNucleoCri extends Opcao {

    public OpcaoNucleoCri(Sistema sistema) {
        super(sistema, "Núcleo", false, true);
    }

    @Override
    public void interagir() {
        System.out.println("Não implementada.");
    }
}
