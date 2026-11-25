package sistema;

import autenticacao.Usuario;
import java.util.HashMap;
import java.util.Scanner;
import sistema.menu.IdMenu;
import sistema.menu.Menu;

public final class Sistema {

    private Scanner leitor;
    private Usuario usuarioLogado;

    private Menu menuAtivo;
    private HashMap<IdMenu, Menu> mapaMenus = new HashMap<>();

    public Sistema(Scanner leitor) {
        this.leitor = leitor;
    }

    public void aguardarConfirmacao() {
        System.out.println();
        System.out.print(">> Pressione ENTER para continuar...");

        if (leitor.hasNextLine()) {
            leitor.nextLine();
        }

        System.out.println();
    }

    public void alterarMenu(IdMenu idMenu) {
        this.menuAtivo = obterMenu(idMenu);
    }

    public void alterarMenu(Menu novoMenu) {
        this.menuAtivo = novoMenu;
    }

    public void desligar() {
        this.menuAtivo = null;
    }

    public void deslogarUsuario() {
        alterarMenu(IdMenu.AUTENTICACAO);
        this.usuarioLogado = null;
    }

    public Scanner leitor() {
        return this.leitor;
    }

    public void logarUsuario(Usuario novoUsuario) {
        alterarMenu(IdMenu.PRINCIPAL);
        this.usuarioLogado = novoUsuario;
    }

    public Menu menuAtivo() {
        return this.menuAtivo;
    }

    public Menu obterMenu(IdMenu idMenu) {
        return mapaMenus.get(idMenu);
    }

    public void registrarMenu(IdMenu idMenu, Menu novoMenu) {
        mapaMenus.put(idMenu, novoMenu);
    }

    public boolean rodando() {
        return menuAtivo != null;
    }

    public Usuario usuario() {
        return this.usuarioLogado;
    }
}
