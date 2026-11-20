package sistema.menu.inicial;

import sistema.Sistema;
import sistema.menu.Menu;
import sistema.menu.Opcao;

public final class OpcaoSaida extends Opcao {

    private final Menu menuPai;

    public OpcaoSaida(Sistema sistema, Menu menuPai) {
        super(sistema, "Sair");
        this.menuPai = menuPai;
    }

    @Override
    public void interagir() {
        if (menuPai != null) {
            sistema.definirMenu(menuPai);
            return;
        }
        sistema.desligar();
    }
}
