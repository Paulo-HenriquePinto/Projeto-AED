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
      MÉTODOS ANTIGOS (SOMENTE DE TESTE)

      Esse método é responsável por inserir um novo livro no final da lista.
      Caso a biblioteca esteja vazia, o livro adicionado se torna o primeiro e o ultimo livro da lista
      Caso não, ele vai sendo inserido na biblioteca
     
     
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

    
      Esse método remove um livro da lista, reajustando a referencia aos nós vizinhos para manter a estrutura encadeada.
    
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
    */

    /**
      MÉTODOS NOVOS 

      Método para inserir o livro na ordem alfabética certa (início, meio e fim)
      recebe o objeto Livro inteiro, facilitando a vida do pessoal do Swing
     */
    public boolean inserirLivro(Livro novoLivro) {
        
        // Tratamento para evitar inserção de livros com campos principais vazios
        if (novoLivro == null || novoLivro.getTitulo() == null || novoLivro.getTitulo().trim().isEmpty() || 
            novoLivro.getAutor() == null || novoLivro.getAutor().trim().isEmpty()) {
            System.out.println("Aviso: O título e o autor são obrigatórios.");
            return false; 
        }

        // Caso 1: A lista (biblioteca) está vazia. O livro será o primeiro e o último.
        if (isEmpty()) {
            Primeiro_livro = novoLivro;
            Ultimo_livro = novoLivro;
            return true;
        }

        // Caso 2: Inserção no início da lista. 
        // Se o título for menor alfabeticamente que o primeiro atual
        if (novoLivro.getTitulo().compareToIgnoreCase(Primeiro_livro.getTitulo()) < 0) {
            novoLivro.setSeguinte(Primeiro_livro);
            Primeiro_livro.setAnterior(novoLivro);
            Primeiro_livro = novoLivro; // Atualiza o ponteiro de início
            return true;
        }

        // Caso 3: Inserção no meio ou no final da lista
        Livro atual = Primeiro_livro;
        
        // Vai percorrendo a lista pra frente enquanto o título do próximo livro ainda for menor
        while (atual.getSeguinte() != null && atual.getSeguinte().getTitulo().compareToIgnoreCase(novoLivro.getTitulo()) < 0) {
            atual = atual.getSeguinte();
        }

        // Se chegou no final da lista e não achou nenhum livro maior (inserção no fim)
        if (atual.getSeguinte() == null) {
            atual.setSeguinte(novoLivro);
            novoLivro.setAnterior(atual);
            Ultimo_livro = novoLivro; // Atualiza o ponteiro do fim
        } 
        // Se achou uma posição no meio da lista, encaixa ele entre o "atual" e o "seguinte"
        else {
            novoLivro.setSeguinte(atual.getSeguinte());
            novoLivro.setAnterior(atual);
            
            // Refaz as conexões dos livros vizinhos pra apontarem pro novo livro
            atual.getSeguinte().setAnterior(novoLivro);
            atual.setSeguinte(novoLivro);
        }

        return true; 
    }

    // Método para remover um livro pelo título e autor 
    public boolean removerLivro(String titulo, String autor) {
        
        // Se a biblioteca está vazia, não há o que remover
        if (isEmpty()) {
            return false; 
        }

        Livro atual = Primeiro_livro;

        // Loop para varrer a lista procurando o livro certo
        while (atual != null) {
            
            // Verifica se o título e o autor batem
            if (atual.getTitulo().equalsIgnoreCase(titulo) && atual.getAutor().equalsIgnoreCase(autor)) {
                
                // Se o livro a ser removido for o primeiro da lista
                if (atual == Primeiro_livro) {
                    Primeiro_livro = atual.getSeguinte(); // O segundo livro vira o primeiro
                    if (Primeiro_livro != null) {
                        Primeiro_livro.setAnterior(null); // Corta o vínculo do novo primeiro pra trás
                    } else {
                        // Se a lista só tinha esse livro e ele foi removido, a lista zerou
                        Ultimo_livro = null;
                    }
                } 
                // Se o livro a ser removido for o último da lista
                else if (atual == Ultimo_livro) {
                    Ultimo_livro = atual.getAnterior(); // O penúltimo vira o último
                    if (Ultimo_livro != null) {
                        Ultimo_livro.setSeguinte(null); // Corta o vínculo do novo último pra frente
                    }
                } 
                // Se o livro a ser removido estiver no meio da lista
                else {
                    // O vizinho de trás pula o atual e aponta pro da frente
                    atual.getAnterior().setSeguinte(atual.getSeguinte());
                    // O vizinho da frente pula o atual e aponta pro de trás
                    atual.getSeguinte().setAnterior(atual.getAnterior());
                }

                // Desconecta totalmente o livro removido da estrutura 
                atual.setSeguinte(null);
                atual.setAnterior(null);

                return true; // Remoção feita com sucesso e ponteiros reconectados
            }
            
            // Pula pro próximo se ainda não bateu os dados
            atual = atual.getSeguinte();
        }

        // Se varreu toda a lista e não achou, retorna false
        return false; 
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

