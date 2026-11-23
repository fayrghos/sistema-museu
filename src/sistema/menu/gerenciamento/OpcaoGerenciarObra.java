package sistema.menu.gerenciamento;

import sistema.Sistema;
import sistema.menu.Opcao;

public final class OpcaoGerenciarObra extends Opcao {

    public OpcaoGerenciarObra(Sistema sistema) {
        super(sistema, "Obra", false, true);
    }

    @Override
    public void interagir() {
        System.out.println("Gerenciando obra...");
    }
}
