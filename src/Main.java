import java.util.Scanner;
import sistema.Sistema;
import sistema.menu.IdMenu;
import sistema.menu.Menu;
import sistema.menu.Opcao;
import sistema.menu.inicial.OpcaoCadastro;
import sistema.menu.inicial.OpcaoLogin;

public class Main {

    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);
        Sistema sistema = new Sistema(leitor);

        sistema.registrarMenu(
            IdMenu.INICIAL,
            new Menu(
                sistema,
                new Opcao[] {
                    new OpcaoLogin(sistema),
                    new OpcaoCadastro(sistema),
                },
                null
            )
        );

        sistema.alterarMenu(IdMenu.INICIAL);
        System.out.println("Bem-vindo(a) ao gerenciador do museu!");

        while (sistema.rodando()) {
            sistema.menuAtivo().imprimir();
            sistema.menuAtivo().processar();
        }

        leitor.close();
    }
}
