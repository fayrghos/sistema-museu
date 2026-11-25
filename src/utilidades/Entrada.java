package utilidades;

import java.util.Scanner;

public class Entrada {

    public static boolean lerBooleano(String rotulo, Scanner leitor) {
        for (;;) {
            try {
                System.out.printf("%s? [S/N]: ", rotulo);
                String entrada = leitor.nextLine().trim();

                if (entrada.equalsIgnoreCase("s")) {
                    return true;
                }

                if (entrada.equalsIgnoreCase("n")) {
                    return false;
                }

                throw new RuntimeException("Insira apenas 'S' ou 'N'");
            } catch (RuntimeException err) {
                System.err.println(err.getMessage());
            }
        }
    }

    public static String lerPalavra(String rotulo, Scanner leitor) {
        for (;;) {
            try {
                System.out.printf("%s: ", rotulo);
                String entrada = leitor.nextLine().trim();

                if (entrada.isEmpty()) {
                    throw new RuntimeException(
                        "A entrada não pode estar em branco"
                    );
                }

                if (entrada.contains(" ")) {
                    throw new RuntimeException("Espaços não são permitidos");
                }

                return entrada;
            } catch (RuntimeException err) {
                System.err.println(err.getMessage());
            }
        }
    }

    public static int lerInteiro(String rotulo, Scanner leitor) {
        for (;;) {
            try {
                System.out.printf("%s: ", rotulo);
                String entrada = leitor.nextLine().trim();

                return Integer.parseInt(entrada);
            } catch (NumberFormatException err) {
                System.out.println("Apenas números inteiros são permitidos");
            } catch (RuntimeException err) {
                System.err.println(err.getMessage());
            }
        }
    }

    public static int lerOpcao(Scanner leitor, int quantOpcoes) {
        for (;;) {
            try {
                System.out.println();
                System.out.print("Escolha: ");
                String entrada = leitor.nextLine().trim();
                int escolha = Integer.parseInt(entrada);

                System.out.println();

                if (escolha < 0 || escolha > quantOpcoes) {
                    throw new RuntimeException(
                        "Apenas números inteiros são permitidos"
                    );
                }

                return escolha;
            } catch (NumberFormatException err) {
                System.out.println("Apenas números inteiros são permitidos");
            } catch (RuntimeException err) {
                System.err.println(err.getMessage());
            }
        }
    }
}
