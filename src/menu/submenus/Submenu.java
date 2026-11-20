package menu.submenus;

import java.util.Scanner;
import menu.opcoes.Opcao;
import menu.opcoes.OpcaoSaida;
import utilidades.Entrada;

public class Submenu {

    public Scanner leitor;

    public Opcao[] opcoes;
    public Opcao saida;

    public Submenu(Scanner leitor, Opcao[] opcoes, Runnable executorSaida) {
        this.leitor = leitor;
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
        int escolha = Entrada.ler(leitor, opcoes.length);

        if (escolha != 0) {
            opcoes[escolha - 1].interagir();
            return;
        }
        saida.interagir();
    }
}
