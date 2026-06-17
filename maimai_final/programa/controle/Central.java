package programa.controle;

import programa.modelo.Biblioteca;
import programa.persistencia.Memoria;
import programa.ui.tela.*;

/**
  Essa classe é responsável por centralizar o controle da aplicação.
  Ela instancia todas as telas do sistema, mantém a biblioteca em memória e coordena a exibição das interfaces gráficas.
 */
public class Central {

    // Telas do sistema
    TelaPrimaria TelaInicial;
    TelaPosAdicionarLivro tela;
    menu TelaMenu;
    addLivro adicionarLivro;
    listarAcervo listaAcervo;
    public acervoScroll AcervoScroll;
    TelaPosRemoverLivro TelaRemovido;
    public Operacoes TelaOperacoes;
    public LivroOperavel LivroOp;

    // Estrutura que armazena os livros cadastrados
    public Biblioteca biblio;

    // Estrutura responsável por salvar dos dados
    public Memoria mem;

    /**
     Construtor principal.
     Inicializa a biblioteca e cria todas as telas da aplicação, permitindo que elas compartilhem a mesma instância de controle.
     */
    public Central() {
        biblio = new Biblioteca();

        tela = new TelaPosAdicionarLivro(this);
        TelaMenu = new menu(this);
        adicionarLivro = new addLivro(this);
        listaAcervo = new listarAcervo(this);
        AcervoScroll = new acervoScroll(this);
        TelaRemovido = new TelaPosRemoverLivro(this);
        TelaOperacoes = new Operacoes(this);
        LivroOp = new LivroOperavel(this);
        TelaInicial = new TelaPrimaria(this);

        mem = new Memoria(this);
    }

    /**
     Carrega os dados salvos anteriormente.
     */
    public void CarregarMemoria() {
        mem.inicializar();
    }

    /**
    Exibe ou oculta a tela inicial.
     */
    public void VerTelaPrimaria(Boolean ver) {
        TelaInicial.VerTela(ver);
    }

    /**
    Exibe ou oculta a tela de cadastro de livros.
     */
    public void VerAddLivro(Boolean ver) {
        adicionarLivro.VerTela(ver);
    }

    /**
     Exibe ou oculta o menu principal.
     */
    public void VerMenu(Boolean ver) {
        TelaMenu.VerTela(ver);
    }

    /**
     Exibe ou oculta a tela de confirmação de adição.
     */
    public void VerTelaAdicionado(Boolean ver) {
        tela.VerTela(ver);
    }

    /**
     Exibe ou oculta a tela de listagem do acervo.
     */
    public void VerListarAcervo(Boolean ver) {
        listaAcervo.VerTela(ver);
    }

    /**
     Exibe ou oculta a tela de visualização do acervo com rolagem.
     */
    public void VerAcervoScroll(Boolean ver) {
        AcervoScroll.VerTela(ver);
    }

    /**
     Exibe ou oculta a tela de confirmação de remoção.
     */
    public void VerTelaRemovido(Boolean ver) {
        TelaRemovido.VerTela(ver);
    }

    /**
     Exibe ou oculta a tela de operações sobre um livro.
     */
    public void VerTelaOperacoes(Boolean ver) {
        TelaOperacoes.VerTela(ver);
    }

    /**
     Exibe ou oculta a tela de detalhes/operações do livro selecionado.
     */
    public void VerLivroOperavel(Boolean ver) {
        LivroOp.VerTela(ver);
    }
}
