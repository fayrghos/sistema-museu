package sistema.menu.item;

import catalogo.exposicao.Exposicao;
import catalogo.exposicao.ExposicaoDAO;
import catalogo.nucleo.NucleoTematico;
import catalogo.nucleo.NucleoTematicoDAO;
import catalogo.obra.Obra;
import catalogo.obra.ObraDAO;
import sistema.Sistema;
import sistema.menu.Opcao;
import utilidades.Entrada;

public final class OpcaoConsultarItem extends Opcao {

    public OpcaoConsultarItem(Sistema sistema) {
        super(sistema, "Consultar Item");
    }

    @Override
    public void interagir() {
        TipoItem tipoItem = Entrada.lerItem(sistema.leitor());

        switch (tipoItem) {
            case OBRA -> {
                Obra obra = new ObraDAO().buscarPorId(
                    Entrada.lerInteiro("Insira o ID da obra", sistema.leitor())
                );

                if (obra == null) {
                    System.out.println(
                        "Nenhuma obra com esse ID foi encontrada."
                    );
                    sistema.aguardarConfirmacao();
                    return;
                }

                System.out.println("Obra encontrada:");
                obra.verObra();
            }
            case EXPOSICAO -> {
                Exposicao exposicao = new ExposicaoDAO().buscarPorId(
                    Entrada.lerInteiro(
                        "Insira o ID da exposição",
                        sistema.leitor()
                    )
                );

                if (exposicao == null) {
                    System.out.println(
                        "Nenhuma exposição com esse ID foi encontrada."
                    );
                    sistema.aguardarConfirmacao();
                    return;
                }

                System.out.println("Exposição encontrada:");
                exposicao.verExposicao();
            }
            case NUCLEO -> {
                NucleoTematico nucleo = new NucleoTematicoDAO().buscarPorId(
                    Entrada.lerInteiro(
                        "Insira o ID do núcleo",
                        sistema.leitor()
                    )
                );

                if (nucleo == null) {
                    System.out.println(
                        "Nenhum núcleo com esse ID foi encontrado."
                    );
                    sistema.aguardarConfirmacao();
                    return;
                }

                System.out.println("Núcleo encontrado:");
                nucleo.verNucleo();
            }
        }

        sistema.aguardarConfirmacao();
    }
}
