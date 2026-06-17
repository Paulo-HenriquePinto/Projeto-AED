package programa.ui.componente;

import programa.modelo.Livro;

import javax.swing.JButton;

/**
  Essa classe que representa um botão associado a um livro específico.
  Ela é utilizada na interface gráfica para permitir que cada botão
  carregue uma referência ao livro que representa.
 */
public class BotaoLivro extends JButton {

    // Livro vinculado ao botão
    private Livro livroAssociado;

    /**
      Cria um botão associado ao livro informado.
     */
    public BotaoLivro(Livro livro) {
        super();
        this.livroAssociado = livro;
    }

    /**
      Esse método permite identificar qual livro foi selecionado pelo usuário na interface.
      Retorna o livro associado ao botão. 
     *
      Livro vinculado ao botão.
     */
    public Livro getLivroAssociado() {
        return livroAssociado;
    }
}
