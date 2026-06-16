public class Biblioteca {
    private Livro Primeiro_livro;
    private Livro Ultimo_livro;

    public Biblioteca() {
        this.Ultimo_livro = this.Primeiro_livro = null; //comeca vazia
    }

    public Livro getPrimeiro_livro() {
        return Primeiro_livro;
    }

    public Livro getUltimo_livro() {
        return Ultimo_livro;
    }

    public void setPrimeiro_livro(Livro primeiro_livro) {
        this.Primeiro_livro = primeiro_livro;
    }

    public void setUltimo_livro(Livro ultimo_livro) {
        this.Ultimo_livro = ultimo_livro;
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

    //Por enqanto, o sistema de listar o catalogo da biblioteca estruturada em lista duplamente encadeada é feito principalmente pela interface grafica
    //assim a funcao para iniciar a listagem da qual a interface parte precisa retornar o primeiro elemento. 
    public Livro listarAcervo() {
        return Primeiro_livro;
    }

    //METODO TEMPORARIAMENTE DESATIVADO (TALVEZ SEJA REPAROVEITADO DEPOIS)
    //uma nota importante é que para listar os livros da biblioteca eu não preciso
    //referenciar o objeto livro inteiramente, ja que nao sao necesssarios todos seus atributos
    //basta listar os títulos de cada livro
    //public void listarAcervo() {
   //     Livro atual = Primeiro_livro;
   //     //percorre cada elemento da lista do primeiro ao ultimo e vai listando
    //    while (atual != null) {
   //         System.out.println(atual.getTitulo());
   //          //System.out.println(LIvro.getAutor);
   //         atual = atual.getSeguinte();
   //     }
   // }
   /**
     * Realiza a retirada de um livro do acervo
     * @param: O título do livro a ser retirado
     * @return: true se o livro foi retirado com sucesso, false caso contrário
     */
    public boolean retirarLivro(String titulo) {
        Livro livroRetirar = buscarLivro(titulo);
        
        // Verifica se o livro existe no acervo
        if (livroRetirar == null) {
            System.out.println("O livro não está no acervo");
            return false;
        }
        
        // Verifica se o livro já está emprestado
        if (livroRetirar.getDisponibilidade() == false) {
            System.out.println("Livro não disponivel para alocação");
            return false;
        }
        
        // Atualiza o status do livro para alocado
        livroRetirar.setDisponibilidade(false);
        System.out.println("Livro alocado com sucesso");
        return true;
    }

    /**
     * Realiza a devolução de um livro ao acervo
     * @param: O título do livro a ser retirado
     * @return: true se o livro foi encontrado e devolvido, false caso não exista no acervo
     */
    public boolean devolverLivro(String titulo) {
        Livro livroDevolver = buscarLivro(titulo);
        
     // Verifica se o livro existe no acervo
        if (livroDevolver == null) {
            System.out.println("O livro não está no acervo");
            return false;
        }
        
     // Atualiza o status do livro para disponivel
        livroDevolver.setDisponibilidade(true);
        return true;
    }
    
    /**
     * Gera uma nova lista duplamente encadeada contendo apenas os livros que estão disponíveis
     * @return: O primeiro nó (livro) da nova lista de livros disponíveis
     */
    public Livro listarLivrosDisponiveis() {
        Livro primLivroDisponivel = null;
        Livro ultLivroDisponivel = null; 
        Livro atual = Primeiro_livro;
        
        while (atual != null) {
            // Filtra apenas os livros disponíveis
            if (atual.getDisponibilidade() == true) {
                // Cria uma cópia do livro para não modificar as conexões da lista original
                Livro copiaLivro = new Livro(
                    atual.getTitulo(), 
                    atual.getGenero(), 
                    atual.getAutor(), 
                    atual.getAno(), 
                    atual.getDescricao(), 
                    atual.getCaminho_imagem()
                );
                copiaLivro.setDisponibilidade(atual.getDisponibilidade());
                if (primLivroDisponivel == null) {
                    // Se a lista estiver vazia, a cópia é o primeiro e o último elemento
                    primLivroDisponivel = copiaLivro;
                    ultLivroDisponivel = copiaLivro;
                } else {
                    // Adiciona a cópia após o último elemento atual e atualiza os ponteiros
                    ultLivroDisponivel.setSeguinte(copiaLivro);
                    copiaLivro.setAnterior(ultLivroDisponivel);
                    ultLivroDisponivel = copiaLivro;
                }
            }
            atual = atual.getSeguinte();
        }
        return primLivroDisponivel; 
    }

    /**
     * Gera uma nova lista duplamente encadeada contendo apenas os livros que estão alocados
     * @return: O primeiro nó (livro) da nova lista de livros alocados
     */
    public Livro listarLivrosAlocados() {
        Livro primLivroAlocado = null;
        Livro ultLivroAlocado = null; 
        Livro atual = Primeiro_livro;
        
        while (atual != null) {
            // Filtra apenas os livros alocados
            if (atual.getDisponibilidade() == false) {
            	// Cria uma cópia do livro para não modificar as conexões da lista original
                Livro copiaLivro = new Livro(
                    atual.getTitulo(), 
                    atual.getGenero(), 
                    atual.getAutor(), 
                    atual.getAno(), 
                    atual.getDescricao(), 
                    atual.getCaminho_imagem()
                );
                copiaLivro.setDisponibilidade(atual.getDisponibilidade());
                if (primLivroAlocado == null) {
                    primLivroAlocado = copiaLivro;
                    ultLivroAlocado = copiaLivro;
                } else {
                    ultLivroAlocado.setSeguinte(copiaLivro);
                    copiaLivro.setAnterior(ultLivroAlocado);
                    ultLivroAlocado = copiaLivro;
                }
            }
            atual = atual.getSeguinte();
        }
        return primLivroAlocado; 
    }

    /**
     * Busca no acervo e gera uma nova lista com os livros que pertencem a um gênero específico
     * @param: generoBuscado representando o gênero alvo da busca
     * @return: O primeiro nó (livro) da nova lista filtrada pelo gênero
     */
    public Livro listarLivrosPorGenero(String generoBuscado) {
        Livro primLivroDoGenero = null;
        Livro ultLivroDoGenero = null;
        Livro atual = Primeiro_livro;

        while (atual != null) {
        	// Cria uma cópia  do livro para não modificar as conexões da lista original
            if (atual.getGenero() != null && atual.getGenero().equalsIgnoreCase(generoBuscado)) {
            	// Cria uma cópia do objeto livro
                Livro copiaLivro = new Livro(
                    atual.getTitulo(), 
                    atual.getGenero(), 
                    atual.getAutor(), 
                    atual.getAno(), 
                    atual.getDescricao(), 
                    atual.getCaminho_imagem()
                );
                copiaLivro.setDisponibilidade(atual.getDisponibilidade());
                if (primLivroDoGenero == null) {
                    primLivroDoGenero = copiaLivro;
                    ultLivroDoGenero = copiaLivro;
                } else {
                    ultLivroDoGenero.setSeguinte(copiaLivro);
                    copiaLivro.setAnterior(ultLivroDoGenero);
                    ultLivroDoGenero = copiaLivro; 
                }
            }
            atual = atual.getSeguinte();
        }
        return primLivroDoGenero;
    }
}
