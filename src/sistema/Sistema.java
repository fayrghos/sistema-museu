package sistema;

import java.util.HashMap;
import java.util.Scanner;
import sistema.menu.IdMenu;
import sistema.menu.Menu;

public final class Sistema {

    private Scanner leitor;

    private Menu menuAtivo;
    private HashMap<IdMenu, Menu> mapaMenus = new HashMap<>();

    public Sistema(Scanner leitor) {
        this.leitor = leitor;
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

    public Scanner leitor() {
        return this.leitor;
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

    public void aguardarConfirmacao() {
        leitor.nextLine();
        System.out.println("Pressione ENTER para continuar...");
        leitor.nextLine();
    }
}
