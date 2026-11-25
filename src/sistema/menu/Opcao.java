package sistema.menu;

import sistema.Sistema;

public abstract class Opcao {

    protected Sistema sistema;
    protected final boolean usavelDeslogado;

    public String titulo;
    public boolean requerAdmin = true;
    public boolean requerCurador = true;

    public Opcao(Sistema sistema, String titulo) {
        this.sistema = sistema;
        this.titulo = titulo;
        this.usavelDeslogado = true;
    }

    public Opcao(
        Sistema sistema,
        String titulo,
        boolean requerAdmin,
        boolean requerCurador
    ) {
        this.sistema = sistema;
        this.titulo = titulo;
        this.requerAdmin = requerAdmin;
        this.requerCurador = requerCurador;
        this.usavelDeslogado = false;
    }

    public void imprimir(int indice) {
        System.out.printf("  [%d] %s\n", indice, titulo);
    }

    public abstract void interagir();
}
