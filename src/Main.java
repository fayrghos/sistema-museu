import java.util.Scanner;
import menu.opcoes.Opcao;
import menu.opcoes.OpcaoCadastro;
import menu.opcoes.OpcaoLogin;
import menu.submenus.Submenu;

public class Main {

    public static boolean sistemaRodando = true;
    public static Submenu menuAtivo = null;

    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);

        Submenu menuAutenticar = new Submenu(
            leitor,
            new Opcao[] { new OpcaoLogin(), new OpcaoCadastro() },
            () -> sistemaRodando = false
        );

        menuAtivo = menuAutenticar;

        System.out.println("Bem-vindo(a) ao gerenciador do museu!");
        while (sistemaRodando) {
            menuAtivo.imprimir();
            menuAtivo.processar();
        }

        leitor.close();
    }
}
