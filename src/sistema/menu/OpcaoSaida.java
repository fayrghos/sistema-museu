package sistema.menu;

import sistema.Sistema;

public final class OpcaoSaida extends Opcao {

    private final Menu menuPai;

    public OpcaoSaida(Sistema sistema, Menu menuPai) {
        super(sistema, "Sair");
        this.menuPai = menuPai;
    }

    @Override
    public void interagir() {
        if (menuPai != null) {
            sistema.alterarMenu(menuPai);
            return;
        }
        sistema.desligar();
    }
}
