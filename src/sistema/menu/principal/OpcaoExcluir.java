package sistema.menu.principal;

import sistema.Sistema;
import sistema.menu.Opcao;

public final class OpcaoExcluir extends Opcao {

    public OpcaoExcluir(Sistema sistema) {
        super(sistema, "Excluir Item", false, true);
    }

    @Override
    public void interagir() {}
}
