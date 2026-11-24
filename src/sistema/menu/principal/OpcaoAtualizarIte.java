package sistema.menu.principal;

import sistema.Sistema;
import sistema.menu.Opcao;

public final class OpcaoAtualizarIte extends Opcao {

    public OpcaoAtualizarIte(Sistema sistema) {
        super(sistema, "Atualizar Item", true, false);
    }

    @Override
    public void interagir() {
        System.out.println("Não implementado.");
    }
}
