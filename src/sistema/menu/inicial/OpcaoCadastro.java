package sistema.menu.inicial;

import sistema.Sistema;
import sistema.menu.IdMenu;
import sistema.menu.Opcao;

public final class OpcaoCadastro extends Opcao {

    public OpcaoCadastro(Sistema sistema) {
        super(sistema, "Cadastro");
    }

    @Override
    public void interagir() {
        System.out.println("Registrado com sucesso!");
        sistema.alterarMenu(IdMenu.PRINCIPAL);
        sistema.aguardarConfirmacao();
    }
}
