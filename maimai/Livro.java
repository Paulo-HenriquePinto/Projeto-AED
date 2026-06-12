public class Livro {
    // Atributos privados (encapsulados)
    private String titulo;
    private String autor;
    private String genero;
    private boolean disponibilidade;
    private int quantidade;
    private String ano;
    private String descricao;
    private String pathFoto;
    
    // Ponteiros da lista encadeada
    private Livro seguinte;
    private Livro anterior;

    // Construtor completo
    public Livro(String titulo, String genero, String autor, String ano, String pathFoto, String descricao, int quantidade) {
        this.titulo = titulo;
        this.genero = genero;
        this.autor = autor;
        this.ano = ano;
        this.pathFoto = pathFoto;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.disponibilidade = (quantidade > 0);
        this.seguinte = null;
        this.anterior = null;
    }

    // --- Getters ---
    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public String getGenero() { return genero; }
    public String getAno() { return ano; }
    public String getDescricao() { return descricao; }
    public int getQuantidade() { return quantidade; }
    public boolean isDisponibilidade() { return disponibilidade; }
    public String getPathFoto() { return pathFoto; }
    public Livro getSeguinte() { return seguinte; }
    public Livro getAnterior() { return anterior; }

    // --- Setters ---
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public void setAutor(String autor) { this.autor = autor; }
    public void setGenero(String genero) { this.genero = genero; }
    public void setAno(String ano) { this.ano = ano; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    
    public void setQuantidade(int quantidade) { 
        this.quantidade = quantidade;
        this.disponibilidade = (this.quantidade > 0);
    }

    public void setDisponibilidade(boolean disponibilidade) { this.disponibilidade = disponibilidade; }
    public void setPathFoto(String pathFoto) { this.pathFoto = pathFoto; }
    public void setSeguinte(Livro seguinte) { this.seguinte = seguinte; }
    public void setAnterior(Livro anterior) { this.anterior = anterior; }
}