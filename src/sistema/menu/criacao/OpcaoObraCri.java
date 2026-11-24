package sistema.menu.criacao;

import sistema.Sistema;
import sistema.menu.Opcao;

public final class OpcaoObraCri extends Opcao {

    public OpcaoObraCri(Sistema sistema) {
        super(sistema, "Obra", false, true);
    }

    @Override
    public void interagir() {
        System.out.println("Não implementada.");
    }
}
