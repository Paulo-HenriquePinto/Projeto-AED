public class Biblioteca {
    private Livro Primeiro_livro;
    private Livro Ultimo_livro;


    public Biblioteca() {
        this.Ultimo_livro = this.Primeiro_livro = null; //comeca vazia
    }

    //verifica se a lista esta vazia
    public boolean isEmpty() {
        if (Primeiro_livro == null) {
            return true;
        } else {
            return false;
        }
    }


    //seleciona o primeiro elemento da lista como atual em seguida percorre 
    //indo para o seguinte ate achar o elemento desejado
    //se nao foi encontrado entao nao esta na lista  
    public Livro buscarLivro(String titulo) {
        Livro atual = Primeiro_livro;
        while (atual != null) {
            if (atual.getTitulo().compareTo(titulo) == 0) {
                //se for igual o objetivo foi encontrado
                return atual;
            }
            //se nao caiu na condicao o atual vira o seguinte e repete o loop
            atual = atual.getSeguinte();
        }
        //se saiu do loop então o livro não está presente na lista então retorna null
        return null;
    }

    //uma nota importante é que para listar os livros da biblioteca eu não preciso
    //referenciar o objeto livro inteiramente, ja que nao sao necesssarios todos seus atributos
    //basta listar os títulos de cada livro
    public void listarCatalogo() {
        Livro atual = Primeiro_livro;
        //percorre cada elemento da lista do primeiro ao ultimo e vai listando
        while (atual != null) {
            System.out.println(atual.getTitulo());
            //System.out.println(LIvro.getAutor);
            atual = atual.getSeguinte();
        }
    }

}
