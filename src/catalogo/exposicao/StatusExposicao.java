package catalogo.exposicao;


public enum StatusExposicao {
    PLANEJAMENTO(1),
    EM_DESENVOLVIMENTO(2),
    CONCLUIDA(3);

    private final int id;

    StatusExposicao(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static StatusExposicao buscarPorId(int id) {
        for (StatusExposicao status : values()) {
            if (status.getId() == id) {
                return status;
            }
        }

        throw new IllegalArgumentException("Status inexistente: " + id);
    }
}
