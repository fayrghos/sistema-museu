package sistema.menu;

import sistema.Sistema;
import utilidades.Entrada;

public class Menu {

    protected final IdMenu pai;
    protected final Sistema sistema;

    public String titulo;
    public Opcao[] opcoes;
    public Opcao saida;

    public Menu(Sistema sistema, String titulo, Opcao[] opcoes, IdMenu pai) {
        this.sistema = sistema;
        this.titulo = titulo;
        this.opcoes = opcoes;
        this.pai = pai;
        this.saida = new OpcaoSaida(sistema, sistema.obterMenu(pai));
    }

    public void imprimir() {
        System.out.printf("-- %s\n", titulo);
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
