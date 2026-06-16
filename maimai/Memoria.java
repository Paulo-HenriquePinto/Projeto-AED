import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

/**
 Classe responsável por salvar os dados da biblioteca.
 Realiza a leitura e escrita dos livros em um arquivo JSON, permitindo que os dados sejam mantidos entre execuções do sistema.
 */
public class Memoria {

    // Referência ao controlador central da aplicação
    private Central central;

    // Caminho do arquivo utilizado para armazenamento dos dados
    private final String caminho = "dados/memoria/dadosBiblioteca.json";

    /**
     Inicializa a classe de salvar.
     central Controlador principal da aplicação.
     */
    public Memoria(Central central) {
        this.central = central;
    }

    /**
     Carrega os livros armazenados no arquivo JSON e os adiciona à biblioteca em memória.
     */
    public void inicializar() {

        // Caso o arquivo não exista, não há dados para carregar
        if (!Files.exists(Paths.get(caminho))) return;

        try {

            // Lê todo o conteúdo do arquivo
            String conteudo = Files.readString(Paths.get(caminho)).trim();

            // Remove os colchetes externos do vetor JSON
            if (conteudo.startsWith("[")) conteudo = conteudo.substring(1);
            if (conteudo.endsWith("]")) conteudo = conteudo.substring(0, conteudo.length() - 1);

            // Separa os objetos JSON individualmente
            String[] livrosTexto = conteudo.split("\\},");

            for (String textoLivro : livrosTexto) {

                if (textoLivro.trim().isEmpty()) continue;

                // Garante que cada objeto termine corretamente
                if (!textoLivro.trim().endsWith("}")) textoLivro += "}";

                // Extrai os campos armazenados no JSON
                String titulo = extrairCampo(textoLivro, "titulo");
                String genero = extrairCampo(textoLivro, "genero");
                String autor = extrairCampo(textoLivro, "autor");
                String ano = extrairCampo(textoLivro, "ano");
                String pathFoto = extrairCampo(textoLivro, "pathFoto");
                String descricao = extrairCampo(textoLivro, "descricao");

                // Cria o objeto Livro e adiciona ao catálogo
                Livro novo = new Livro(
                        titulo,
                        genero,
                        autor,
                        ano,
                        pathFoto,
                        descricao
                );

                central.biblio.inserirLivro(novo);
            }

        } catch (Exception e) {
            System.out.println("Erro ao ler arquivo: " + e.getMessage());
        }
    }

    /**
      Extrai o valor de um campo específico dentro de um texto JSON.
     texto Conteúdo JSON.
     campo Nome do campo desejado.
     Retorna valor encontrado ou string vazia.
     */
    public String extrairCampo(String texto, String campo) {

        String alvo = "\"" + campo + "\":";

        int posInicio = texto.indexOf(alvo);

        if (posInicio == -1) return "";

        posInicio += alvo.length();

        // Ignora espaços e aspas iniciais
        while (
                posInicio < texto.length() &&
                (texto.charAt(posInicio) == ' ' ||
                 texto.charAt(posInicio) == '"')
        ) {
            posInicio++;
        }

        int posFim = posInicio;

        // Procura o fim do valor
        while (
                posFim < texto.length() &&
                texto.charAt(posFim) != '"' &&
                texto.charAt(posFim) != ',' &&
                texto.charAt(posFim) != '}' &&
                texto.charAt(posFim) != '\n'
        ) {
            posFim++;
        }

        return texto.substring(posInicio, posFim).trim();
    }

    /**
     Adiciona um novo livro ao arquivo JSON.
     livro Livro que será salvo.
     */
    public void adicionarNoJson(Livro livro) {

        try {

            // Escapa barras invertidas presentes no caminho da imagem
            String pathFotoEscapado = livro.getPathFoto().replace("\\", "\\\\");

            // Monta o objeto JSON correspondente ao livro
            String jsonNovo =
                    "  {\n" +
                    "    \"titulo\": \"" + livro.getTitulo() + "\",\n" +
                    "    \"genero\": \"" + livro.getGenero() + "\",\n" +
                    "    \"autor\": \"" + livro.getAutor() + "\",\n" +
                    "    \"ano\": \"" + livro.getAno() + "\",\n" +
                    "    \"pathFoto\": \"" + pathFotoEscapado + "\",\n" +
                    "    \"descricao\": \"" + livro.getDescricao() + "\"\n" +
                    "  }";

            Path path = Paths.get(caminho);

            // Cria a estrutura de diretórios caso não exista
            if (path.getParent() != null) {
                Files.createDirectories(path.getParent());
            }

            String conteudoFinal;

            // Caso o arquivo já exista, adiciona o novo livro ao final
            if (Files.exists(path) && Files.size(path) > 0) {

                String conteudoAntigo = Files.readString(path).trim();

                // Remove o colchete de fechamento
                String conteudoSemFechamento =
                        conteudoAntigo.substring(
                                0,
                                conteudoAntigo.lastIndexOf(']')
                        );

                conteudoSemFechamento = conteudoSemFechamento.trim();

                // Remove vírgula final, se existir
                if (conteudoSemFechamento.endsWith(",")) {
                    conteudoSemFechamento =
                            conteudoSemFechamento.substring(
                                    0,
                                    conteudoSemFechamento.length() - 1
                            );
                }

                // Trata o caso do primeiro livro do arquivo
                if (conteudoSemFechamento.equals("[")) {
                    conteudoFinal =
                            conteudoSemFechamento +
                            "\n" +
                            jsonNovo +
                            "\n]";
                } else {
                    conteudoFinal =
                            conteudoSemFechamento +
                            ",\n" +
                            jsonNovo +
                            "\n]";
                }

            } else {

                // Cria um novo arquivo JSON
                conteudoFinal =
                        "[\n" +
                        jsonNovo +
                        "\n]";
            }

            Files.writeString(path, conteudoFinal);

        } catch (Exception e) {
            System.out.println("Erro ao salvar JSON: " + e.getMessage());
        }
    }

    /**
     Remove um livro do arquivo JSON.
     O arquivo é reconstruído utilizando todos os livros da biblioteca, exceto o livro removido.
     livroParaRemover Livro que será excluído.
     */
    public void removerNoJson(Livro livroParaRemover) {

        try {

            String conteudoFinal = "[\n";

            Livro atual = central.biblio.getLivro();

            boolean primeiroInserido = false;

            // Percorre toda a biblioteca
            while (atual != null) {

                // Ignora o livro que será removido
                if (saoIguais(atual, livroParaRemover)) {
                    atual = atual.getSeguinte();
                    continue;
                }

                String jsonLivro =
                        "  {\n" +
                        "    \"titulo\": \"" + atual.getTitulo() + "\",\n" +
                        "    \"genero\": \"" + atual.getGenero() + "\",\n" +
                        "    \"autor\": \"" + atual.getAutor() + "\",\n" +
                        "    \"ano\": \"" + atual.getAno() + "\",\n" +
                        "    \"pathFoto\": \"" + atual.getPathFoto() + "\",\n" +
                        "    \"descricao\": \"" + atual.getDescricao() + "\"\n" +
                        "  }";

                if (primeiroInserido) {
                    conteudoFinal += ",\n";
                }

                conteudoFinal += jsonLivro;

                primeiroInserido = true;

                atual = atual.getSeguinte();
            }

            conteudoFinal += "\n]";

            // Sobrescreve o arquivo com o novo conteúdo
            Files.writeString(
                    Paths.get(caminho),
                    conteudoFinal
            );

        } catch (Exception e) {
            System.out.println("Erro ao remover do JSON: " + e.getMessage());
        }
    }

    /**
     Verifica se dois livros representam o mesmo registro.
     A comparação é feita utilizando os principais atributos de identificação do livro.
     l1 Primeiro livro.
     l2 Segundo livro.
     Retorna true se os livros forem considerados iguais.
     */
    public boolean saoIguais(Livro l1, Livro l2) {

        return l1.getTitulo().equals(l2.getTitulo()) &&
               l1.getGenero().equals(l2.getGenero()) &&
               l1.getAutor().equals(l2.getAutor()) &&
               l1.getAno().equals(l2.getAno());
    }
}
