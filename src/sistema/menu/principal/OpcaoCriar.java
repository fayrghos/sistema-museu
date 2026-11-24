package sistema.menu.principal;

import sistema.Sistema;
import sistema.menu.Opcao;

public final class OpcaoCriar extends Opcao {

    public OpcaoCriar(Sistema sistema) {
        super(sistema, "Criar Item", false, true);
    }

    @Override
    public void interagir() {}
}
