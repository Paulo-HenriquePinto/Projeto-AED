public class Biblioteca {
    private Livro Primeiro_livro;
    private Livro Ultimo_livro;

    public Biblioteca() {
        this.Ultimo_livro = this.Primeiro_livro = null;
    }

    public boolean isEmpty() {
        return Primeiro_livro == null;
    }

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

    public void removerLivro(Livro livroRemover) {
        if (livroRemover == null || isEmpty()) return;

        if (livroRemover == Primeiro_livro && livroRemover == Ultimo_livro) {
            Primeiro_livro = null;
            Ultimo_livro = null;
        } else if (livroRemover == Primeiro_livro) {
            Primeiro_livro = livroRemover.getSeguinte();
            Primeiro_livro.setAnterior(null);
        } else if (livroRemover == Ultimo_livro) {
            Ultimo_livro = livroRemover.getAnterior();
            Ultimo_livro.setSeguinte(null);
        } else {
            livroRemover.getAnterior().setSeguinte(livroRemover.getSeguinte());
            livroRemover.getSeguinte().setAnterior(livroRemover.getAnterior());
        }
        
        livroRemover.setSeguinte(null);
        livroRemover.setAnterior(null);
    }

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

    public void listarCatalogo() {
        Livro atual = Primeiro_livro;
        while (atual != null) {
            System.out.println(atual.getTitulo());
            atual = atual.getSeguinte();
        }
    }

    public Livro getLivro() {
        return Primeiro_livro;
    }
}