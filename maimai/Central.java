public class Central {
    TelaPrimaria TelaInicial;
    TelaPosAdicionarLivro tela;
    menu TelaMenu;
    addLivro adicionarLivro;
    listarAcervo listaAcervo;
    acervoScroll AcervoScroll;
    TelaPosRemoverLivro TelaRemovido;
    Operacoes TelaOperacoes;
    LivroOperavel LivroOp;
    Biblioteca biblio;
    Memoria mem;

    public Central(){
        biblio = new Biblioteca();
        tela = new TelaPosAdicionarLivro(this);
        TelaMenu = new menu(this); // tela persistente
        adicionarLivro = new addLivro(this);
        listaAcervo = new listarAcervo(this);
        AcervoScroll = new acervoScroll(this);
        TelaRemovido = new TelaPosRemoverLivro(this);
        TelaOperacoes = new Operacoes(this);
        LivroOp = new LivroOperavel(this);
        TelaInicial = new TelaPrimaria(this);
        mem = new Memoria(this);
    }

    public void CarregarMemoria(){
        mem.inicializar();
    }

    public void VerTelaPrimaria(Boolean ver){
        TelaInicial.VerTela(ver);
    }

    public void VerAddLivro(Boolean ver){
        adicionarLivro.VerTela(ver);
    }

    public void VerMenu(Boolean ver){
        TelaMenu.VerTela(ver);
    }

    public void VerTelaAdicionado(Boolean ver){
        tela.VerTela(ver);
    }

    public void VerListarAcervo(Boolean ver){
        listaAcervo.VerTela(ver);
    }

    public void VerAcervoScroll(Boolean ver){
        AcervoScroll.VerTela(ver);
    }

    public void VerTelaRemovido(Boolean ver){
        TelaRemovido.VerTela(ver);
    }

    public void VerTelaOperacoes(Boolean ver){
        TelaOperacoes.VerTela(ver);
    }

    public void VerLivroOperavel(Boolean ver){
        LivroOp.VerTela(ver);
    }


}
