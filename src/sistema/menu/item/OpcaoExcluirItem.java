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

public final class OpcaoExcluirItem extends Opcao {

    public OpcaoExcluirItem(Sistema sistema) {
        super(sistema, "Excluir Item", false, true);
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

                System.out.printf("Excluindo obra de ID %d.\n", obra.getId());
                obra.excluirObra();
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

                if (new NucleoTematicoDAO().possuiNucleos(exposicao.getId())) {
                    System.out.println(
                        "Não é possível excluir uma exposição que possui núcleos vinculados."
                    );
                    sistema.aguardarConfirmacao();
                    return;
                }

                System.out.printf(
                    "Excluindo exposição de ID %d.\n",
                    exposicao.getId()
                );
                exposicao.excluirExposicao();
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

                System.out.printf(
                    "Excluindo núcleo de ID %d.\n",
                    nucleo.getId()
                );
                nucleo.excluirNucleo();
            }
        }

        sistema.aguardarConfirmacao();
    }
}
