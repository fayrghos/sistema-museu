package sistema.menu.inicial;

import sistema.menu.Opcao;

public final class OpcaoSaida extends Opcao {

    private Runnable executor;

    public OpcaoSaida(Runnable executor) {
        super("Sair");
        this.executor = executor;
    }

    @Override
    public void interagir() {
        executor.run();
    }
}
