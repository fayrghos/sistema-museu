package sistema.menu;

import sistema.Sistema;

public abstract class Opcao {

    protected Sistema sistema;

    public String titulo;
    public boolean visivelAdmin = false;
    public boolean visivelCurador = false;

    public Opcao(Sistema sistema, String titulo) {
        this.sistema = sistema;
        this.titulo = titulo;
    }

    public Opcao(
        Sistema sistema,
        String titulo,
        boolean visivelAdmin,
        boolean visivelCurador
    ) {
        this.sistema = sistema;
        this.titulo = titulo;
        this.visivelAdmin = visivelAdmin;
        this.visivelCurador = visivelCurador;
    }

    public void imprimir(int indice) {
        System.out.printf("  [%d] %s\n", indice, titulo);
    }

    public abstract void interagir();
}
