package sistema.menu.criacao;

import sistema.Sistema;
import sistema.menu.Opcao;

public final class OpcaoCriarObra extends Opcao {

    public OpcaoCriarObra(Sistema sistema) {
        super(sistema, "Obra", false, true);
    }

    @Override
    public void interagir() {
        System.out.println("Criando obra...");
    }
}
