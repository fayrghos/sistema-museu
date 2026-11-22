package sistema.menu.principal;

import sistema.Sistema;
import sistema.menu.Opcao;

public final class OpcaoConsultarUsu extends Opcao {

    public OpcaoConsultarUsu(Sistema sistema) {
        super(sistema, "Consultar Usuário", true, false);
    }

    @Override
    public void interagir() {
        System.out.println("Não implementado.");
    }
}
