package sistema.menu.usuario;

import sistema.Sistema;
import sistema.menu.Opcao;

public final class OpcaoCadastrarUsuario extends Opcao {

    public OpcaoCadastrarUsuario(Sistema sistema) {
        super(sistema, "Cadastrar Usuário", true, false);
    }

    @Override
    public void interagir() {
        System.out.println("Cadastrando usuário...");
    }
}
