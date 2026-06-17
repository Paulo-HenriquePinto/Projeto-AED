/*
 * Universidade Federal de Sergipe - Departamento de Computação
 * Algoritmo e Estrutura de Dados I - 2026.1 - T01
 * Trabalho da Segunda Unidade - Grupo G8
 *
 * Sistema de gerenciamento de biblioteca pública
 * Estrutura usada: Lista Duplamente Encadeada
 *
 * Integrantes:
 *   - Lauren Victoria Ferreira Roma
 *   - Paulo Henrique Sa de Oliveira Pinto
 *   - Matheus Nascimento dos Santos
 *   - Gabriel Felipe Vilela Marti
 *   - Jose Antonio Rodrigues Santos
 */

package programa.modelo;

public class Livro {
    private String Titulo;
    private String Autor;
    private String Genero;
    private boolean Disponibilidade;
    private String descricao;
    private String Ano;
    private String caminho_imagem;

    private Livro Seguinte;
    private Livro Anterior;

    public Livro(String titulo, String genero, String autor, String ano, String caminho, String descricao){
        this.Titulo = titulo;
        this.Genero = genero;
        this.Autor = autor;
        this.Ano = ano;
        this.descricao = descricao;
        this.caminho_imagem = caminho;
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

    public String getAno() {
        return Ano;
    }

    public String getPathFoto() {
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

    // public void setAno(String ano) {
    //     this.Ano = ano;
    // }

    public void setPathFoto(String caminho_imagem) {
        this.caminho_imagem = caminho_imagem;
    }
}