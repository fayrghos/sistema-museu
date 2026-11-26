package utilidades;

import org.mindrot.jbcrypt.BCrypt;

public class SenhaHash {

    public static String gerarHash(String senha) {
        if (senha == null || senha.isBlank()) {
            return null;
        }

        return BCrypt.hashpw(senha, BCrypt.gensalt(12));
    }

    public static boolean verificarSenha(
        String senhaOriginal,
        String senhaHashBanco
    ) {
        if (
            senhaOriginal == null ||
            senhaHashBanco == null ||
            senhaOriginal.isBlank() ||
            senhaHashBanco.isBlank()
        ) {
            return false;
        }

        try {
            return BCrypt.checkpw(senhaOriginal, senhaHashBanco);
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
