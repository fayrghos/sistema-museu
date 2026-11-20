package sistema.menu;

public abstract class Opcao {

    public String titulo;
    public boolean visivelAdmin = false;
    public boolean visivelCurador = false;

    public Opcao(String titulo) {
        this.titulo = titulo;
    }

    public Opcao(String titulo, boolean visivelAdmin, boolean visivelCurador) {
        this.titulo = titulo;
        this.visivelAdmin = visivelAdmin;
        this.visivelCurador = visivelCurador;
    }

    public void imprimir(int indice) {
        System.out.printf("  [%d] %s\n", indice, titulo);
    }

    public abstract void interagir();
}
