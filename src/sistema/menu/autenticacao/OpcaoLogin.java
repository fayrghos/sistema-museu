package sistema.menu.autenticacao;

import autenticacao.Usuario;
import autenticacao.UsuarioDAO;
import sistema.Sistema;
import sistema.menu.Opcao;
import utilidades.Entrada;
import utilidades.hash.SenhaHash;
import utilidades.validacao.EmailValidacao;

public final class OpcaoLogin extends Opcao {

    public OpcaoLogin(Sistema sistema) {
        super(sistema, "Login");
    }

    @Override
    public void interagir() {
        String email = Entrada.lerPalavra("Insira seu email", sistema.leitor());

        if (!EmailValidacao.validarEmail(email)) {
            System.out.println("O email inserido não é válido.");
            sistema.aguardarConfirmacao();
            return;
        }

        String senha = Entrada.lerPalavra("Insira sua senha", sistema.leitor());

        UsuarioDAO buscadorUsuario = new UsuarioDAO();
        Usuario usuario = buscadorUsuario.buscarPorEmail(email);

        if (usuario == null) {
            System.out.println("Usuário não encontrado.");
            sistema.aguardarConfirmacao();
            return;
        }

        if (!usuario.getAtivo()) {
            System.out.println("Usuário não disponível.");
            sistema.aguardarConfirmacao();
            return;
        }

        if (!SenhaHash.verificarSenha(senha, usuario.getSenha())) {
            System.out.println("Credenciais inválidas.");
            sistema.aguardarConfirmacao();
            return;
        }

        System.out.printf("Bem-vindo(a), %s!\n", usuario.getNome());
        sistema.aguardarConfirmacao();

        sistema.logarUsuario(usuario);
    }
}
