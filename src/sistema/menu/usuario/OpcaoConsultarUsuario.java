package sistema.menu.usuario;

import sistema.Sistema;
import sistema.menu.Opcao;

public final class OpcaoConsultarUsuario extends Opcao {

    public OpcaoConsultarUsuario(Sistema sistema) {
        super(sistema, "Consultar Usuário", true, false);
    }

    @Override
    public void interagir() {
        System.out.println("Consultando usuário...");
        sistema.aguardarConfirmacao();
    }
}
