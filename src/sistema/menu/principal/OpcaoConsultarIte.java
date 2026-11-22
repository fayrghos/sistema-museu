package sistema.menu.principal;

import sistema.Sistema;
import sistema.menu.Opcao;

public final class OpcaoConsultarIte extends Opcao {

    public OpcaoConsultarIte(Sistema sistema) {
        super(sistema, "Consultar Item", true, false);
    }

    @Override
    public void interagir() {
        System.out.println("Não implementado.");
    }
}
