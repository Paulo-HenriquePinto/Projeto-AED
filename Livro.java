public class Livro {
    private String Titulo;
    private String Autor;
    private String Genero;
    private boolean Disponibilidade;
    private String descricao;
    private String caminho_imagem;

    private Livro Seguinte;
    private Livro Anterior;

    public Livro(String titulo, String genero, String autor){
        this.Titulo = titulo;
        this.Genero = genero;
        this.Autor = autor;
        this.Disponibilidade = true; //por padrao comeca como disponivel assim que adicionado
        this.Seguinte = this.Anterior = null;
    }

    public String getTitulo () {
        return Titulo;
    }   

    public String getGenero() {
        return Genero;
    }

    public boolean getDisponibilidade() {
        return Disponibilidade;
    }

    public Livro getSeguinte() {
        return Seguinte;
    }

    public Livro getAnterior() {
        return Anterior;
    }

    public String getAutor() {
        return Autor;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getCaminho_imagem() {
        return caminho_imagem;
    }

    
    public void setDisponibilidade(boolean estado) {
        this.Disponibilidade = estado;
    }

    public void setSeguinte(Livro seguinte) {
        this.Seguinte = seguinte;
    }

    public void setAnterior(Livro anterior) {
        this.Anterior = anterior;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setCaminho_imagem(String caminho_imagem) {
        this.caminho_imagem = caminho_imagem;
    }
}
