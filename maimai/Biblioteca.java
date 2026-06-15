/**
  Essa classe responsável por armazenar os livros cadastrados.
  Os livros são organizados seguindo o modelo de uma lista duplamente encadeada, permitindo buscar, adicionar e remover livros
 */
public class Biblioteca {

    // Primeiro livro da lista
    private Livro Primeiro_livro;

    // Último livro da lista
    private Livro Ultimo_livro;

    /**
      Inicializa uma biblioteca vazia.
     */
    public Biblioteca() {
        this.Ultimo_livro = this.Primeiro_livro = null;
    }

    /**
      Verifica se a lista de livros está vazia.
     */
    public boolean isEmpty() {
        return Primeiro_livro == null;
    }

    /**
      Esse método é responsável por inserir um novo livro no final da lista.
      Caso a biblioteca esteja vazia, o livro adicionado se torna o primeiro e o ultimo livro da lista
      Caso não, ele vai sendo inserido na biblioteca
     
     */
    public void add(Livro novoLivro) {
        if (isEmpty()) {
            Primeiro_livro = novoLivro;
            Ultimo_livro = novoLivro;
        } else {
            Ultimo_livro.setSeguinte(novoLivro);
            novoLivro.setAnterior(Ultimo_livro);
            Ultimo_livro = novoLivro;
        }
    }

    /**
     Esse método remove um livro da lista, reajustando a referencia aos nós vizinhos para manter a estrutura encadeada.
     */
    public void removerLivro(Livro livroRemover) {
        if (livroRemover == null || isEmpty()) return;

        // Caso exista apenas um livro na biblioteca, o primeiro livro, que também é o último, é removido e a lista fica vazia
        if (livroRemover == Primeiro_livro && livroRemover == Ultimo_livro) {
            Primeiro_livro = null;
            Ultimo_livro = null;

        // Remoção do primeiro livro da lista
        } else if (livroRemover == Primeiro_livro) {
            Primeiro_livro = livroRemover.getSeguinte();
            Primeiro_livro.setAnterior(null);

        // Remoção do último livro da lista
        } else if (livroRemover == Ultimo_livro) {
            Ultimo_livro = livroRemover.getAnterior();
            Ultimo_livro.setSeguinte(null);

        // Remoção de livro que não esta nos extremos da lista
        } else {
            livroRemover.getAnterior().setSeguinte(livroRemover.getSeguinte());
            livroRemover.getSeguinte().setAnterior(livroRemover.getAnterior());
        }

        // Remove as ligações do nó removido
        livroRemover.setSeguinte(null);
        livroRemover.setAnterior(null);
    }

    /**
      O método realiza uma busca sequencial pelo título informado.
      A comparação ignora diferenças entre letras maiúsculas e minúsculas.
      retorna o livro encontrado, ou null caso não exista.
     */
    public Livro buscarLivro(String titulo) {
        Livro atual = Primeiro_livro;

        while (atual != null) {
            if (atual.getTitulo().equalsIgnoreCase(titulo)) {
                return atual;
            }
            atual = atual.getSeguinte();
        }

        return null;
    }

    /**
      Percorre toda a lista exibindo os títulos dos livros cadastrados no catálogo.
     */
    public void listarCatalogo() {
        Livro atual = Primeiro_livro;

        while (atual != null) {
            System.out.println(atual.getTitulo());
            atual = atual.getSeguinte();
        }
    }

    /**
      Retorna o primeiro livro da lista.
      Utilizado como ponto inicial para percorrer o catálogo.
     */
    public Livro getLivro() {
        return Primeiro_livro;
    }
}

