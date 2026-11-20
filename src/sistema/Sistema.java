package sistema;

import java.util.Scanner;
import sistema.menu.Menu;

public final class Sistema {

    private Scanner leitor;
    private Menu menuAtivo;

    public Sistema(Scanner leitor) {
        this.leitor = leitor;
    }

    public void definirMenu(Menu novoMenu) {
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

    public boolean rodando() {
        return menuAtivo != null;
    }
}
