package sistema.menu.item;

import catalogo.exposicao.Exposicao;
import catalogo.exposicao.ExposicaoDAO;
import catalogo.nucleo.NucleoTematico;
import catalogo.obra.Obra;
import sistema.Sistema;
import sistema.menu.Opcao;
import utilidades.Entrada;

public final class OpcaoCadastrarItem extends Opcao {

    public OpcaoCadastrarItem(Sistema sistema) {
        super(sistema, "Cadastrar Item", false, true);
    }

    @Override
    public void interagir() {
        TipoItem tipoItem = Entrada.lerItem(sistema.leitor());

        switch (tipoItem) {
            case OBRA -> {
                Obra obra = Obra.criarObra(
                    Entrada.lerPalavra("Insira o nome", sistema.leitor()),
                    Entrada.lerPalavra("Insira a autoria", sistema.leitor()),
                    Entrada.lerInteiro("Insira o ano", sistema.leitor()),
                    Entrada.lerPalavra("Insira a técnica", sistema.leitor()),
                    Entrada.lerPalavra("Insira a descrição", sistema.leitor()),
                    Entrada.lerCategoria(sistema.leitor()),
                    Entrada.lerPalavra("Insira a legenda", sistema.leitor())
                );
                System.out.printf(
                    "Obra cadastrada com sucesso. ID: %d\n",
                    obra.getId()
                );
            }
            case EXPOSICAO -> {
                Exposicao exposicao = Exposicao.criarExposicao(
                    sistema.usuario(),
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
                    "Exposição cadastrada com sucesso. ID: %d\n",
                    exposicao.getId()
                );
            }
            case NUCLEO -> {
                int idExposicao = Entrada.lerInteiro(
                    "Insira o ID da exposição vinculada",
                    sistema.leitor()
                );
                Exposicao exposicao = new ExposicaoDAO().buscarPorId(
                    idExposicao
                );

                if (exposicao == null) {
                    System.out.println(
                        "Nenhuma exposição com esse ID foi encontrada."
                    );
                    sistema.aguardarConfirmacao();
                    return;
                }

                NucleoTematico nucleo = NucleoTematico.criarNucleo(
                    exposicao,
                    Entrada.lerPalavra("Insira o título", sistema.leitor()),
                    Entrada.lerPalavra("Insira a descrição", sistema.leitor()),
                    Entrada.lerPalavra(
                        "Insira o texto de contextualização",
                        sistema.leitor()
                    ),
                    Entrada.lerInteiro("Insira a ordem", sistema.leitor())
                );
                System.out.printf(
                    "Núcleo cadastrado com sucesso. ID: %d\n",
                    nucleo.getId()
                );
            }
        }

        sistema.aguardarConfirmacao();
    }
}
