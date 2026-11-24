package sistema.menu.criacao;

import sistema.Sistema;
import sistema.menu.Opcao;

public final class OpcaoCriarNucleo extends Opcao {

    public OpcaoCriarNucleo(Sistema sistema) {
        super(sistema, "Núcleo", false, true);
    }

    @Override
    public void interagir() {
        System.out.println("Criando núcleo...");
        sistema.aguardarConfirmacao();
    }
}
