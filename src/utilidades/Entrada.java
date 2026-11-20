package utilidades;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Entrada {

    public static int lerOpcao(Scanner leitor, int quantOpcoes) {
        for (;;) {
            try {
                System.out.println();
                System.out.print("Escolha: ");
                int escolha = leitor.nextInt();

                if (escolha < 0 || escolha > quantOpcoes) {
                    throw new ArrayIndexOutOfBoundsException();
                }

                return escolha;
            } catch (InputMismatchException err) {
                System.out.println("Apenas números inteiros são permitidos.");
                leitor.next();
            } catch (ArrayIndexOutOfBoundsException err) {
                System.out.println("Selecione uma escolha válida.");
            }
        }
    }
}
