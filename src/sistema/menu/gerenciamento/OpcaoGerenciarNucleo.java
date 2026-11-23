package sistema.menu.gerenciamento;

import sistema.Sistema;
import sistema.menu.Opcao;

public final class OpcaoGerenciarNucleo extends Opcao {

    public OpcaoGerenciarNucleo(Sistema sistema) {
        super(sistema, "Núcleo", false, true);
    }

    @Override
    public void interagir() {
        System.out.println("Gerenciando núcleo...");
    }
}
