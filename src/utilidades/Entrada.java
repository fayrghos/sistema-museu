package utilidades;

import catalogo.obra.CategoriaObra;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import sistema.menu.item.TipoItem;

public class Entrada {

    public static TipoItem lerItem(Scanner leitor) {
        System.out.println("-- Seleção de item");
        System.out.println("  [1] Obra");
        System.out.println("  [2] Núcleo");
        System.out.println("  [3] Exposição");

        int opcao = lerOpcao(leitor, 3, false);

        return switch (opcao) {
            case 1 -> TipoItem.OBRA;
            case 2 -> TipoItem.NUCLEO;
            case 3 -> TipoItem.EXPOSICAO;
            default -> null;
        };
    }

    public static CategoriaObra lerCategoria(Scanner leitor) {
        System.out.println("-- Seleção de categoria");
        System.out.println("  [1] Pintura");
        System.out.println("  [2] Escultura");
        System.out.println("  [3] Fotografia");
        System.out.println("  [4] Instalação");
        System.out.println("  [5] Documento");
        System.out.println("  [6] Outra");

        for (;;) {
            int opcao = lerOpcao(leitor, 6, false);

            return switch (opcao) {
                case 1 -> CategoriaObra.PINTURA;
                case 2 -> CategoriaObra.ESCULTURA;
                case 3 -> CategoriaObra.FOTOGRAFIA;
                case 4 -> CategoriaObra.INSTALACAO;
                case 5 -> CategoriaObra.DOCUMENTO;
                case 6 -> CategoriaObra.OUTRA;
                default -> null;
            };
        }
    }

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

    public static Date lerData(String rotulo, Scanner leitor) {
        for (;;) {
            try {
                System.out.printf("%s (AAAA-MM-DD): ", rotulo);
                String entrada = leitor.nextLine().trim();
                return Date.valueOf(LocalDate.parse(entrada));
            } catch (DateTimeParseException err) {
                System.out.println("Data inválida. Use o formato AAAA-MM-DD.");
            }
        }
    }

    public static int lerOpcao(
        Scanner leitor,
        int quantOpcoes,
        boolean permitirZero
    ) {
        int menorOpcao = permitirZero ? 0 : 1;

        for (;;) {
            System.out.println();
            System.out.print("Escolha: ");

            int escolha;
            try {
                String entrada = leitor.nextLine().trim();
                System.out.println();
                escolha = Integer.parseInt(entrada);
            } catch (NumberFormatException err) {
                System.out.println(
                    "Apenas números inteiros são permitidos."
                );
                continue;
            }

            if (escolha < menorOpcao || escolha > quantOpcoes) {
                System.out.printf(
                    "Opção inválida. Escolha um número entre %d e %d.\n",
                    menorOpcao,
                    quantOpcoes
                );
                continue;
            }

            return escolha;
        }
    }
}
