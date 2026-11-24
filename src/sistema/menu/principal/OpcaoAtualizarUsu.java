package sistema.menu.principal;

import sistema.Sistema;
import sistema.menu.Opcao;

public final class OpcaoAtualizarUsu extends Opcao {

    public OpcaoAtualizarUsu(Sistema sistema) {
        super(sistema, "Atualizar Usuário", true, false);
    }

    @Override
    public void interagir() {
        System.out.println("Não implementado.");
    }
}
