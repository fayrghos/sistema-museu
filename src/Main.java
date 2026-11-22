import java.util.Scanner;
import sistema.Sistema;
import sistema.menu.IdMenu;
import sistema.menu.Menu;
import sistema.menu.Opcao;
import sistema.menu.criacao.OpcaoExposicaoCri;
import sistema.menu.criacao.OpcaoNucleoCri;
import sistema.menu.criacao.OpcaoObraCri;
import sistema.menu.gerenciamento.OpcaoExposicaoGer;
import sistema.menu.gerenciamento.OpcaoNucleoGer;
import sistema.menu.gerenciamento.OpcaoObraGer;
import sistema.menu.inicial.OpcaoCadastro;
import sistema.menu.inicial.OpcaoLogin;
import sistema.menu.principal.OpcaoAtivar;
import sistema.menu.principal.OpcaoAtualizarUsu;
import sistema.menu.principal.OpcaoConsultarIte;
import sistema.menu.principal.OpcaoConsultarUsu;
import sistema.menu.principal.OpcaoCriar;
import sistema.menu.principal.OpcaoDesativar;
import sistema.menu.principal.OpcaoExcluir;

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
                    new OpcaoAtivar(sistema),
                    new OpcaoDesativar(sistema),
                    new OpcaoConsultarUsu(sistema),
                    new OpcaoAtualizarUsu(sistema),
                    new OpcaoCriar(sistema),
                    new OpcaoConsultarIte(sistema),
                    new OpcaoExcluir(sistema),
                },
                IdMenu.INICIAL
            )
        );

        sistema.registrarMenu(
            IdMenu.CRIACAO,
            new Menu(
                sistema,
                new Opcao[] {
                    new OpcaoExposicaoCri(sistema),
                    new OpcaoNucleoCri(sistema),
                    new OpcaoObraCri(sistema),
                },
                IdMenu.INICIAL
            )
        );

        sistema.registrarMenu(
            IdMenu.GERENCIAMENTO,
            new Menu(
                sistema,
                new Opcao[] {
                    new OpcaoExposicaoGer(sistema),
                    new OpcaoNucleoGer(sistema),
                    new OpcaoObraGer(sistema),
                },
                IdMenu.INICIAL
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
