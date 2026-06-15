import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

public class Memoria {

    private Central central;
    private final String caminho = "dados/memoria/dadosBiblioteca.json";

    public Memoria(Central central) {
        this.central = central;
    }

    public void inicializar() {
        if (!Files.exists(Paths.get(caminho))) return;
        try {
            String conteudo = Files.readString(Paths.get(caminho)).trim();
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
                String pathFoto = extrairCampo(textoLivro, "pathFoto");
                String descricao = extrairCampo(textoLivro, "descricao");

                Livro novo = new Livro(titulo, genero, autor, ano, pathFoto, descricao);
                central.biblio.add(novo);
            }
        } catch (Exception e) {
            System.out.println("Erro ao ler arquivo: " + e.getMessage());
        }
    }

    public String extrairCampo(String texto, String campo) {
        String alvo = "\"" + campo + "\":";
        int posInicio = texto.indexOf(alvo);
        if (posInicio == -1) return "";
        posInicio += alvo.length();
        while (posInicio < texto.length() && (texto.charAt(posInicio) == ' ' || texto.charAt(posInicio) == '"')) posInicio++;
        int posFim = posInicio;
        while (posFim < texto.length() && texto.charAt(posFim) != '"' && texto.charAt(posFim) != ',' && texto.charAt(posFim) != '}' && texto.charAt(posFim) != '\n') posFim++;
        return texto.substring(posInicio, posFim).trim();
    }

    public void adicionarNoJson(Livro livro) {
        try {
            String pathFotoEscapado = livro.getPathFoto().replace("\\", "\\\\");
    
            String jsonNovo = "  {\n" +
                    "    \"titulo\": \"" + livro.getTitulo() + "\",\n" +
                    "    \"genero\": \"" + livro.getGenero() + "\",\n" +
                    "    \"autor\": \"" + livro.getAutor() + "\",\n" +
                    "    \"ano\": \"" + livro.getAno() + "\",\n" +
                    "    \"pathFoto\": \"" + pathFotoEscapado + "\",\n" +
                    "    \"descricao\": \"" + livro.getDescricao() + "\"\n" +
                    "  }";
    
            Path path = Paths.get(caminho);
            if (path.getParent() != null) Files.createDirectories(path.getParent());
    
            String conteudoFinal;
            if (Files.exists(path) && Files.size(path) > 0) {
                String conteudoAntigo = Files.readString(path).trim();
                
                String conteudoSemFechamento = conteudoAntigo.substring(0, conteudoAntigo.lastIndexOf(']'));
                
                conteudoSemFechamento = conteudoSemFechamento.trim();
                if (conteudoSemFechamento.endsWith(",")) {
                    conteudoSemFechamento = conteudoSemFechamento.substring(0, conteudoSemFechamento.length() - 1);
                }

                if (conteudoSemFechamento.equals("[")) {
                    conteudoFinal = conteudoSemFechamento + "\n" + jsonNovo + "\n]";
                } else {
                    conteudoFinal = conteudoSemFechamento + ",\n" + jsonNovo + "\n]";
                }
            } else {
                conteudoFinal = "[\n" + jsonNovo + "\n]";
            }
            
            Files.writeString(path, conteudoFinal);
        } catch (Exception e) {
            System.out.println("Erro ao salvar JSON: " + e.getMessage());
        }
    }

    public void removerNoJson(Livro livroParaRemover) {
        try {
            String conteudoFinal = "[\n";
            Livro atual = central.biblio.getLivro();
            boolean primeiroInserido = false;

            while (atual != null) {
                if (saoIguais(atual, livroParaRemover)) {
                    atual = atual.getSeguinte();
                    continue;
                }
                String jsonLivro = "  {\n" +
                        "    \"titulo\": \"" + atual.getTitulo() + "\",\n" +
                        "    \"genero\": \"" + atual.getGenero() + "\",\n" +
                        "    \"autor\": \"" + atual.getAutor() + "\",\n" +
                        "    \"ano\": \"" + atual.getAno() + "\",\n" +
                        "    \"pathFoto\": \"" + atual.getPathFoto() + "\",\n" +
                        "    \"descricao\": \"" + atual.getDescricao() + "\"\n" +
                        "  }";

                if (primeiroInserido) conteudoFinal += ",\n";
                conteudoFinal += jsonLivro;
                primeiroInserido = true;
                atual = atual.getSeguinte();
            }
            conteudoFinal += "\n]";
            Files.writeString(Paths.get(caminho), conteudoFinal);
        } catch (Exception e) {
            System.out.println("Erro ao remover do JSON: " + e.getMessage());
        }
    }

    public boolean saoIguais(Livro l1, Livro l2) {
        return l1.getTitulo().equals(l2.getTitulo()) &&
               l1.getGenero().equals(l2.getGenero()) &&
               l1.getAutor().equals(l2.getAutor()) &&
               l1.getAno().equals(l2.getAno());
    }
}