package sistema.menu;

import sistema.Sistema;
import sistema.menu.inicial.OpcaoSaida;
import utilidades.Entrada;

public class Menu {

    private final Sistema sistema;

    public Opcao[] opcoes;
    public Opcao saida;

    public Menu(Sistema sistema, Opcao[] opcoes, Runnable executorSaida) {
        this.sistema = sistema;
        this.opcoes = opcoes;
        this.saida = new OpcaoSaida(executorSaida);
    }

    public void imprimir() {
        for (int i = 0; i < opcoes.length; i++) {
            opcoes[i].imprimir(i + 1);
        }
        saida.imprimir(0);
    }

    public void processar() {
        int escolha = Entrada.lerOpcao(sistema.leitor(), opcoes.length);

        if (escolha != 0) {
            opcoes[escolha - 1].interagir();
            return;
        }
        saida.interagir();
    }
}
