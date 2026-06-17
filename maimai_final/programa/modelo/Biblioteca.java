package programa.modelo;

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
    public Livro buscarLivro(String tituloBusca) {
        Livro original = Primeiro_livro;
        Livro inicioExibicao = null;
        Livro ultimoExibicao = null;
    
        while (original != null) {
            // Verifica se o título do livro atual começa com a string buscada (case-insensitive)
            if (original.getTitulo().toLowerCase().startsWith(tituloBusca.toLowerCase())) {
                
                // cópia segura do nó para exibição
                Livro copiaExibicao = new Livro(
                    original.getTitulo(), 
                    original.getGenero(), 
                    original.getAutor(), 
                    original.getAno(), 
                    original.getPathFoto(), 
                    original.getDescricao()
                );
                
                // Lógica de encadeamento da "Lista Fantasma"
                if (inicioExibicao == null) {
                    inicioExibicao = copiaExibicao;
                    ultimoExibicao = copiaExibicao;
                } else {
                    ultimoExibicao.setSeguinte(copiaExibicao);
                    ultimoExibicao = copiaExibicao;
                }
            }
            original = original.getSeguinte();
        }
        
        // Retorna o início da lista filtrada (pode ser null se nada for encontrado)
        return inicioExibicao;
    }

    //Por enqanto, o sistema de listar o catalogo da biblioteca estruturada em lista duplamente encadeada é feito principalmente pela interface grafica
    //assim a funcao para iniciar a listagem da qual a interface parte precisa retornar o primeiro elemento. 
    public Livro listarAcervo() {
        return Primeiro_livro;
    }


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
    public boolean removerLivro(String titulo, String autor, String ano, String genero){
        
        // Se a biblioteca está vazia, não há o que remover
        if (isEmpty()) {
            return false; 
        }

        Livro atual = Primeiro_livro;

        // Loop para varrer a lista procurando o livro certo
        while (atual != null) {
            
            // Verifica se o título e o autor batem
            if (atual.getTitulo().equalsIgnoreCase(titulo) && atual.getAutor().equalsIgnoreCase(autor) &&
                atual.getAno().equalsIgnoreCase(ano) && atual.getGenero().equalsIgnoreCase(genero)) {
                
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

    public boolean retirarLivro(Livro livroRetirar) {
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
    public boolean devolverLivro(Livro livroDevolver) {
        if (livroDevolver == null) {
            return false;
        }
        
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
                    atual.getPathFoto(),
                    atual.getDescricao()
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
                    atual.getPathFoto(),
                    atual.getDescricao()
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
                    atual.getPathFoto(),
                    atual.getDescricao()
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

