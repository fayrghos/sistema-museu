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

public final class OpcaoAtualizarItem extends Opcao {

    public OpcaoAtualizarItem(Sistema sistema) {
        super(sistema, "Atualizar Item", false, true);
    }

    @Override
    public void interagir() {
        TipoItem tipoItem = Entrada.lerItem(sistema.leitor());

        switch (tipoItem) {
            case OBRA -> {
                ObraDAO buscadorObra = new ObraDAO();
                Obra obra = buscadorObra.buscarPorId(
                    Entrada.lerInteiro("Insira o ID da obra", sistema.leitor())
                );

                if (obra == null) {
                    System.out.println(
                        "Nenhuma obra com esse ID foi encontrada."
                    );
                    sistema.aguardarConfirmacao();
                    return;
                }

                obra.editarObra(
                    Entrada.lerPalavra("Insira o nome", sistema.leitor()),
                    Entrada.lerPalavra("Insira a autoria", sistema.leitor()),
                    Entrada.lerInteiro("Insira o ano", sistema.leitor()),
                    Entrada.lerPalavra("Insira a técnica", sistema.leitor()),
                    Entrada.lerPalavra("Insira a descrição", sistema.leitor()),
                    Entrada.lerCategoria(sistema.leitor()),
                    Entrada.lerPalavra("Insira a legenda", sistema.leitor())
                );
                System.out.printf(
                    "Obra de ID %d atualizada com sucesso.\n",
                    obra.getId()
                );
            }
            case EXPOSICAO -> {
                ExposicaoDAO buscadorExposicao = new ExposicaoDAO();
                Exposicao exposicao = buscadorExposicao.buscarPorId(
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

                exposicao.editarExposicao(
                    Entrada.lerPalavra("Insira o título", sistema.leitor()),
                    Entrada.lerPalavra("Insira o tema", sistema.leitor()),
                    Entrada.lerPalavra("Insira o conceito", sistema.leitor()),
                    Entrada.lerPalavra("Insira os objetivos", sistema.leitor()),
                    Entrada.lerPalavra("Insira a descrição", sistema.leitor()),
                    Entrada.lerData(
                        "Insira a data inicial prevista",
                        sistema.leitor()
                    ),
                    Entrada.lerData(
                        "Insira a data final prevista",
                        sistema.leitor()
                    ),
                    Entrada.lerPalavra(
                        "Insira o texto de apresentação",
                        sistema.leitor()
                    )
                );
                System.out.printf(
                    "Exposição de ID %d atualizada com sucesso.\n",
                    exposicao.getId()
                );
            }
            case NUCLEO -> {
                NucleoTematicoDAO buscadorNucleo = new NucleoTematicoDAO();
                NucleoTematico nucleo = buscadorNucleo.buscarPorId(
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

                nucleo.editarNucleo(
                    Entrada.lerPalavra("Insira o título", sistema.leitor()),
                    Entrada.lerPalavra("Insira a descrição", sistema.leitor()),
                    Entrada.lerPalavra(
                        "Insira o texto de contextualização",
                        sistema.leitor()
                    ),
                    Entrada.lerInteiro("Insira a ordem", sistema.leitor())
                );
                System.out.printf(
                    "Núcleo de ID %d atualizado com sucesso.\n",
                    nucleo.getId()
                );
            }
        }

        sistema.aguardarConfirmacao();
    }
}
