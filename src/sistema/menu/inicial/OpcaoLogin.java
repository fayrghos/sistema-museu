package sistema.menu.inicial;

import sistema.Sistema;
import sistema.menu.IdMenu;
import sistema.menu.Opcao;

public final class OpcaoLogin extends Opcao {

    public OpcaoLogin(Sistema sistema) {
        super(sistema, "Login");
    }

    @Override
    public void interagir() {
        System.out.println("Logado com sucesso!");
        sistema.alterarMenu(IdMenu.PRINCIPAL);
        sistema.aguardarConfirmacao();
    }
}
