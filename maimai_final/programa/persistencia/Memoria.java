package programa.persistencia;

import programa.controle.Central;
import programa.modelo.Biblioteca;
import programa.modelo.Livro;

import java.nio.file.*;

/**
 * Classe responsável por salvar os dados da biblioteca.
 * Realiza a leitura e escrita dos livros em um arquivo JSON, permitindo que os dados sejam mantidos entre execuções do sistema.
 */
public class Memoria {

    // Referência ao controlador central da aplicação
    private Central central;

    // Caminho do arquivo utilizado para armazenamento dos dados
    private final String caminho = "dados/memoria/dadosBiblioteca.json";

    /**
     * Inicializa a classe de salvar.
     * @param central Controlador principal da aplicação.
     */
    public Memoria(Central central) {
        this.central = central;
    }

    /**
     * Carrega os livros armazenados no arquivo JSON e os adiciona à biblioteca em memória.
     */
    public void inicializar() {
        Path path = Paths.get(caminho);
        if (!Files.exists(path)) return;

        try {
            String conteudo = Files.readString(path).trim();

            // Limpeza básica do array JSON
            if (conteudo.startsWith("[")) conteudo = conteudo.substring(1);
            if (conteudo.endsWith("]")) conteudo = conteudo.substring(0, conteudo.length() - 1);

            String[] livrosTexto = conteudo.split("\\},");

            for (String textoLivro : livrosTexto) {
                if (textoLivro.trim().isEmpty()) continue;
                if (!textoLivro.trim().endsWith("}")) textoLivro += "}";

                String titulo = extrairCampo(textoLivro, "titulo");
                String genero = extrairCampo(textoLivro, "genero");
                String autor = extrairCampo(textoLivro, "autor");
                String ano = extrairCampo(textoLivro, "ano");
                String descricao = extrairCampo(textoLivro, "descricao");
                String dispTexto = extrairCampo(textoLivro, "disponibilidade");

                // A MÁGICA ESTÁ AQUI:
                // Pegamos o caminho salvo com '/' e convertemos para o separador do sistema local
                String pathFoto = extrairCampo(textoLivro, "pathFoto")
                                    .replace("/", java.io.File.separator);

                Livro novo = new Livro(titulo, genero, autor, ano, pathFoto, descricao);
                novo.setDisponibilidade(Boolean.parseBoolean(dispTexto));

                central.biblio.inserirLivro(novo);
            }
        } catch (Exception e) {
            System.out.println("Erro ao ler arquivo: " + e.getMessage());
        }
    }

    /**
     * Extrai o valor de um campo específico dentro de um texto JSON.
     * @param texto Conteúdo JSON.
     * @param campo Nome do campo desejado.
     * @return valor encontrado ou string vazia.
     */
    public String extrairCampo(String texto, String campo) {
        String alvo = "\"" + campo + "\":";
        int posInicio = texto.indexOf(alvo);

        if (posInicio == -1) return "";

        posInicio += alvo.length();

        // Ignora espaços e aspas iniciais
        while (posInicio < texto.length() && (texto.charAt(posInicio) == ' ' || texto.charAt(posInicio) == '"')) {
            posInicio++;
        }

        int posFim = posInicio;

        // Procura o fim do valor
        while (posFim < texto.length() && texto.charAt(posFim) != '"' && texto.charAt(posFim) != ',' && 
               texto.charAt(posFim) != '}' && texto.charAt(posFim) != '\n') {
            posFim++;
        }

        return texto.substring(posInicio, posFim).trim();
    }

    /**
     * Adiciona um novo livro ao arquivo JSON.
     * @param livro Livro que será salvo.
     */
    public void adicionarNoJson(Livro livro) {
        try {
            // Normaliza o caminho para '/' (padrão universal para JSON)
            String pathNormalizado = livro.getPathFoto().replace("\\", "/");

            String jsonNovo = String.format(
                "  {\n" +
                "    \"titulo\": \"%s\",\n" +
                "    \"genero\": \"%s\",\n" +
                "    \"autor\": \"%s\",\n" +
                "    \"ano\": \"%s\",\n" +
                "    \"pathFoto\": \"%s\",\n" +
                "    \"descricao\": \"%s\",\n" +
                "    \"disponibilidade\": %b\n" +
                "  }",
                livro.getTitulo(), livro.getGenero(), livro.getAutor(),
                livro.getAno(), pathNormalizado, livro.getDescricao(), livro.getDisponibilidade()
            );

            Path path = Paths.get(caminho);
            if (path.getParent() != null) Files.createDirectories(path.getParent());

            String conteudoFinal;
            if (Files.exists(path) && Files.size(path) > 0) {
                String conteudoAntigo = Files.readString(path).trim();
                // Remove o ']' do final para poder adicionar o novo objeto
                String semFechamento = conteudoAntigo.substring(0, conteudoAntigo.lastIndexOf(']')).trim();
                
                // Adiciona vírgula se já existirem livros
                conteudoFinal = semFechamento.endsWith("[") ? 
                                semFechamento + "\n" + jsonNovo + "\n]" : 
                                semFechamento + ",\n" + jsonNovo + "\n]";
            } else {
                conteudoFinal = "[\n" + jsonNovo + "\n]";
            }

            Files.writeString(path, conteudoFinal);

        } catch (Exception e) {
            System.out.println("Erro ao salvar JSON: " + e.getMessage());
        }
    }

    /**
     * Remove um livro do arquivo JSON.
     * O arquivo é reconstruído utilizando todos os livros da biblioteca, exceto o livro removido.
     * @param livroParaRemover Livro que será excluído.
     */
    public void removerNoJson(Livro livroParaRemover) {
        try {
            StringBuilder conteudoFinal = new StringBuilder("[\n");
            Livro atual = central.biblio.getPrimeiro_livro();
            boolean primeiroInserido = false;

            while (atual != null) {
                if (saoIguais(atual, livroParaRemover)) {
                    atual = atual.getSeguinte();
                    continue;
                }

                if (primeiroInserido) conteudoFinal.append(",\n");

                String pathNormalizado = atual.getPathFoto().replace("\\", "/");
                conteudoFinal.append(String.format(
                    "  {\n    \"titulo\": \"%s\",\n    \"genero\": \"%s\",\n    \"autor\": \"%s\",\n" +
                    "    \"ano\": \"%s\",\n    \"pathFoto\": \"%s\",\n    \"descricao\": \"%s\",\n" +
                    "    \"disponibilidade\": %b\n  }",
                    atual.getTitulo(), atual.getGenero(), atual.getAutor(), 
                    atual.getAno(), pathNormalizado, atual.getDescricao(), atual.getDisponibilidade()
                ));

                primeiroInserido = true;
                atual = atual.getSeguinte();
            }
            conteudoFinal.append("\n]");
            Files.writeString(Paths.get(caminho), conteudoFinal.toString());
        } catch (Exception e) {
            System.out.println("Erro ao remover do JSON: " + e.getMessage());
        }
    }

    /**
     * Verifica se dois livros representam o mesmo registro.
     * A comparação é feita utilizando os principais atributos de identificação do livro.
     * @param l1 Primeiro livro.
     * @param l2 Segundo livro.
     * @return true se os livros forem considerados iguais.
     */
    public boolean saoIguais(Livro l1, Livro l2) {
        return l1.getTitulo().equals(l2.getTitulo()) &&
               l1.getGenero().equals(l2.getGenero()) &&
               l1.getAutor().equals(l2.getAutor()) &&
               l1.getAno().equals(l2.getAno());
    }

    /**
     * Atualiza o estado de disponibilidade de um livro específico no arquivo JSON.
     * @param livroAlterado Livro que teve sua disponibilidade alterada.
     */
    public void atualizarDisponibilidadeNoJson(Livro livroAlterado) {
        try {
            StringBuilder conteudoFinal = new StringBuilder("[\n");
            Livro atual = central.biblio.getPrimeiro_livro();
            boolean primeiroInserido = false;

            while (atual != null) {
                if (primeiroInserido) conteudoFinal.append(",\n");

                String pathNormalizado = atual.getPathFoto().replace("\\", "/");

                conteudoFinal.append(String.format(
                    "  {\n    \"titulo\": \"%s\",\n    \"genero\": \"%s\",\n    \"autor\": \"%s\",\n" +
                    "    \"ano\": \"%s\",\n    \"pathFoto\": \"%s\",\n    \"descricao\": \"%s\",\n" +
                    "    \"disponibilidade\": %b\n  }",
                    atual.getTitulo(), atual.getGenero(), atual.getAutor(), 
                    atual.getAno(), pathNormalizado, atual.getDescricao(), atual.getDisponibilidade()
                ));

                primeiroInserido = true;
                atual = atual.getSeguinte();
            }
            conteudoFinal.append("\n]");
            Files.writeString(Paths.get(caminho), conteudoFinal.toString());
        } catch (Exception e) {
            System.out.println("Erro ao atualizar JSON: " + e.getMessage());
        }
    }
}