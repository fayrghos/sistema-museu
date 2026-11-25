package sistema.menu.principal;

import sistema.Sistema;
import sistema.menu.Opcao;

public final class OpcaoDeslogar extends Opcao {

    public OpcaoDeslogar(Sistema sistema) {
        super(sistema, "Deslogar");
    }

    @Override
    public void interagir() {
        sistema.deslogarUsuario();
        System.out.println("Deslogado com sucesso!");
        sistema.aguardarConfirmacao();
    }
}
