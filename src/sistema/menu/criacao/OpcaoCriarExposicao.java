package sistema.menu.criacao;

import sistema.Sistema;
import sistema.menu.Opcao;

public final class OpcaoCriarExposicao extends Opcao {

    public OpcaoCriarExposicao(Sistema sistema) {
        super(sistema, "Exposição", false, true);
    }

    @Override
    public void interagir() {
        System.out.println("Criando exposição...");
    }
}
