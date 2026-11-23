import java.util.Scanner;
import sistema.Sistema;
import sistema.menu.IdMenu;
import sistema.menu.Menu;
import sistema.menu.Opcao;
import sistema.menu.criacao.OpcaoCriarExposicao;
import sistema.menu.criacao.OpcaoCriarNucleo;
import sistema.menu.criacao.OpcaoCriarObra;
import sistema.menu.gerenciamento.OpcaoGerenciarExposicao;
import sistema.menu.gerenciamento.OpcaoGerenciarNucleo;
import sistema.menu.gerenciamento.OpcaoGerenciarObra;
import sistema.menu.inicial.OpcaoCadastro;
import sistema.menu.inicial.OpcaoLogin;
import sistema.menu.principal.OpcaoAtivarUsuario;
import sistema.menu.principal.OpcaoAtualizarUsuario;
import sistema.menu.principal.OpcaoConsultarItem;
import sistema.menu.principal.OpcaoConsultarUsuario;
import sistema.menu.principal.OpcaoCriarItem;
import sistema.menu.principal.OpcaoDesativarUsuario;
import sistema.menu.principal.OpcaoExcluirItem;

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

        sistema.registrarMenu(
            IdMenu.PRINCIPAL,
            new Menu(
                sistema,
                new Opcao[] {
                    new OpcaoConsultarUsuario(sistema),
                    new OpcaoAtualizarUsuario(sistema),
                    new OpcaoDesativarUsuario(sistema),
                    new OpcaoAtivarUsuario(sistema),
                    new OpcaoConsultarItem(sistema),
                    new OpcaoCriarItem(sistema),
                    new OpcaoExcluirItem(sistema),
                },
                null
            )
        );

        sistema.registrarMenu(
            IdMenu.CRIACAO,
            new Menu(
                sistema,
                new Opcao[] {
                    new OpcaoCriarExposicao(sistema),
                    new OpcaoCriarNucleo(sistema),
                    new OpcaoCriarObra(sistema),
                },
                IdMenu.PRINCIPAL
            )
        );

        sistema.registrarMenu(
            IdMenu.GERENCIAMENTO,
            new Menu(
                sistema,
                new Opcao[] {
                    new OpcaoGerenciarExposicao(sistema),
                    new OpcaoGerenciarNucleo(sistema),
                    new OpcaoGerenciarObra(sistema),
                },
                IdMenu.PRINCIPAL
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
