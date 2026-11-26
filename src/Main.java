import banco.InicializadorBanco;
import java.util.Scanner;
import sistema.Sistema;
import sistema.menu.IdMenu;
import sistema.menu.Menu;
import sistema.menu.Opcao;
import sistema.menu.autenticacao.OpcaoLogin;
import sistema.menu.item.OpcaoAtualizarItem;
import sistema.menu.item.OpcaoCadastrarItem;
import sistema.menu.item.OpcaoConsultarItem;
import sistema.menu.item.OpcaoExcluirItem;
import sistema.menu.principal.OpcaoDeslogar;
import sistema.menu.principal.OpcaoGerenciarItens;
import sistema.menu.principal.OpcaoGerenciarUsuarios;
import sistema.menu.usuario.OpcaoAtivarUsuario;
import sistema.menu.usuario.OpcaoAtualizarUsuario;
import sistema.menu.usuario.OpcaoCadastrarUsuario;
import sistema.menu.usuario.OpcaoConsultarUsuario;
import sistema.menu.usuario.OpcaoDesativarUsuario;
import sistema.menu.usuario.OpcaoListarUsuario;

public class Main {

    public static void main(String[] args) {
        InicializadorBanco.inicializar();

        Scanner leitor = new Scanner(System.in);
        Sistema sistema = new Sistema(leitor);

        sistema.registrarMenu(
            IdMenu.AUTENTICACAO,
            new Menu(
                sistema,
                "Autenticação",
                new Opcao[] { new OpcaoLogin(sistema) },
                null
            )
        );

        sistema.registrarMenu(
            IdMenu.PRINCIPAL,
            new Menu(
                sistema,
                "Menu Principal",
                new Opcao[] {
                    new OpcaoGerenciarUsuarios(sistema),
                    new OpcaoGerenciarItens(sistema),
                    new OpcaoDeslogar(sistema),
                },
                null
            )
        );

        sistema.registrarMenu(
            IdMenu.GERENCIAR_USUARIOS,
            new Menu(
                sistema,
                "Gerenciar Usuários",
                new Opcao[] {
                    new OpcaoConsultarUsuario(sistema),
                    new OpcaoCadastrarUsuario(sistema),
                    new OpcaoAtualizarUsuario(sistema),
                    new OpcaoDesativarUsuario(sistema),
                    new OpcaoAtivarUsuario(sistema),
                    new OpcaoListarUsuario(sistema),
                },
                IdMenu.PRINCIPAL
            )
        );

        sistema.registrarMenu(
            IdMenu.GERENCIAR_ITENS,
            new Menu(
                sistema,
                "Gerenciar Itens",
                new Opcao[] {
                    new OpcaoConsultarItem(sistema),
                    new OpcaoCadastrarItem(sistema),
                    new OpcaoAtualizarItem(sistema),
                    new OpcaoExcluirItem(sistema),
                },
                IdMenu.PRINCIPAL
            )
        );

        sistema.alterarMenu(IdMenu.AUTENTICACAO);
        System.out.println("Bem-vindo(a) ao gerenciador do museu!");
        System.out.println();

        while (sistema.rodando()) {
            sistema.menuAtivo().imprimir();
            sistema.menuAtivo().processar();
        }

        leitor.close();
    }
}
