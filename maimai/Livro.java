/**
 Essa classe representa um livro cadastrado na biblioteca.
 Além de armazenar as informações do livro, também funciona como um nó de uma lista duplamente encadeada através das referências para o livro anterior e o seguinte.
 */
public class Livro {

    // Informações do livro
    private String titulo;
    private String autor;
    private String genero;
    private boolean disponibilidade;
    private int quantidade;
    private String ano;
    private String descricao;
    private String pathFoto;

    // Referências utilizadas pela lista duplamente encadeada
    private Livro seguinte;
    private Livro anterior;

    /**
     Cria um novo livro com os dados fornecidos.
     A disponibilidade é definida automaticamente de acordo com a quantidade disponível.
     */
    public Livro(String titulo, String genero, String autor,
                 String ano, String pathFoto, String descricao) {

        this.titulo = titulo;
        this.genero = genero;
        this.autor = autor;
        this.ano = ano;
        this.pathFoto = pathFoto;
        this.descricao = descricao;

        this.disponibilidade = (quantidade > 0);

        // Inicialmente o livro não possui vizinhos na lista
        this.seguinte = null;
        this.anterior = null;
    }

    /**
      Retornam os valores armazenados nos atributos do livro.
     */
    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public String getGenero() { return genero; }
    public String getAno() { return ano; }
    public String getDescricao() { return descricao; }
    public int getQuantidade() { return quantidade; }
    public boolean isDisponibilidade() { return disponibilidade; }
    public String getPathFoto() { return pathFoto; }

    /**
      Retornam os livros vizinhos na lista encadeada.
     */
    public Livro getSeguinte() { return seguinte; }
    public Livro getAnterior() { return anterior; }

    // ==================== SETTERS ====================

    /**
      Métodos responsáveis por atualizar os dados do livro.
     */
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public void setAutor(String autor) { this.autor = autor; }
    public void setGenero(String genero) { this.genero = genero; }
    public void setAno(String ano) { this.ano = ano; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    /**
    Atualiza a quantidade de exemplares e ajusta automaticamente a disponibilidade do livro.
     */
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
        this.disponibilidade = (this.quantidade > 0);
    }

    public void setDisponibilidade(boolean disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

    public void setPathFoto(String pathFoto) {
        this.pathFoto = pathFoto;
    }

    /**
     Atualizam as referências utilizadas pela lista duplamente encadeada.
     */
    public void setSeguinte(Livro seguinte) {
        this.seguinte = seguinte;
    }

    public void setAnterior(Livro anterior) {
        this.anterior = anterior;
    }
}
