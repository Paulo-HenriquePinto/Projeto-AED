public class Livro {
    private String Titulo;
    private String Autor;
    private String Genero;
    private boolean Disponibilidade;
    String pathFoto;
    private Livro Seguinte;
    private Livro Anterior;
    String ano;

    public Livro(String titulo, String genero, String autor, String no, String pathFoto){
        this.Titulo = titulo;
        this.Genero = genero;
        this.Autor = autor;
        ano = no;
        this.Seguinte = this.Anterior = null;
        this.pathFoto = pathFoto;
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
    
    public void setDisponibilidade(boolean estado) {
        this.Disponibilidade = estado;
    }

    public void setSeguinte(Livro seguinte) {
        this.Seguinte = seguinte;
    }

    public void setAnterior(Livro anterior) {
        this.Anterior = anterior;
    }
}