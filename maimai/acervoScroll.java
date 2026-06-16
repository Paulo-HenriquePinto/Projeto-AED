import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.*;

/**
 Tela responsável por exibir o acervo da biblioteca em formato de cards com rolagem.
 Cada livro é apresentado através de sua capa e título, permitindo uma visualização mais organizada do catálogo.
 */
public class acervoScroll {

    // Classe auxiliar utilizada para criação dos componentes gráficos
    private funcoes_essenciais func = new funcoes_essenciais();

    // Referência ao controlador principal da aplicação
    private Central controle;

    // Componentes principais da interface
    private JFrame tela;
    private JPanel obj;
    private JPanel obj2;

    // Painel que contém os cards dos livros
    private JPanel painelExibicao;

    // Área de rolagem dos cards
    private JScrollPane scroll;

    GridBagConstraints lay = new GridBagConstraints();

    /**
     Construtor da tela.
     Inicializa a interface e exibe os livros cadastrados.
     */
    public acervoScroll(Central controle){
        this.controle = controle;

        Tela();

        // Exibe inicialmente todos os livros do acervo
        CriarCardsRolaveis(controle.biblio.getLivro());

        BotoesNavegaveis();
    }

    /**
     Exibe ou oculta a janela.
     ver true para exibir e false para ocultar.
     */
    public void VerTela(Boolean ver){
        tela = func.VerTela(tela, ver);
    }

    /**
     Atualiza a área de exibição dos livros.
     livro Primeiro livro da lista a ser exibida.
     */
    public void AtualizarTela(Livro livro){
        CriarCardsRolaveis(livro);
    }

    /**
     Cria a estrutura principal da janela.
     Contém painel lateral, logotipo e cabeçalho.
     */
    public void Tela() {

        tela = func.criaFrame(
                400,
                200,
                800,
                500,
                Color.BLACK,
                "Tela Principal",
                null
        );

        // Painel lateral
        obj = func.criaJPanel(
                0,
                0,
                250,
                500,
                new Color(24, 58, 89),
                null
        );

        tela.add(obj);

        // Área reservada ao logotipo
        JPanel Logo = func.criaJPanel(
                0,
                0,
                250,
                140,
                Color.white,
                null
        );

        obj.add(Logo);

        renderizador imagemCapa = new renderizador(
                func.brasao,
                0,
                0,
                250,
                140
        );

        imagemCapa.setBounds(0, 0, 250, 140);

        Logo.add(imagemCapa);

        // Cabeçalho superior
        obj2 = func.criaJPanel(
                250,
                0,
                550,
                80,
                new Color(227,163,36),
                null
        );

        tela.add(obj2);

        JLabel addLivro = func.criarLabel(
                20,
                20,
                550,
                50,
                null,
                "Acervo:",
                false,
                new Font("Arial", Font.BOLD, 30)
        );

        obj2.add(addLivro);
    }

    /**
      Cria dinamicamente os cards dos livros.
     Cada card contém:
     - imagem da capa;
     - título do livro.
     Os cards são inseridos em um painel rolável, permitindo visualizar grandes quantidades de livros.
     PrimeiroLivro Primeiro elemento da lista encadeada.
     */
    public void CriarCardsRolaveis(Livro PrimeiroLivro){

        // Remove a área anterior para reconstrução
        if(painelExibicao != null){
            tela.remove(scroll);
        }

        painelExibicao = func.criaJPanel(
                0,
                0,
                0,
                0,
                Color.white,
                func.gradeCards
        );

        scroll = func.Scroll(painelExibicao);

        scroll.setBounds(
                250,
                130,
                550,
                290
        );

        Livro Iteraveis = PrimeiroLivro;

        int contador_de_cards = 0;

        // Percorre toda a lista de livros
        while(Iteraveis != null){

            JButton imagem = new JButton();

            imagem.setIcon(
                    func.SetImageIcon(
                            Iteraveis.getPathFoto()
                    )
            );

            JLabel nomeLivro = new JLabel(
                    Iteraveis.getTitulo(),
                    JLabel.CENTER
            );

            JPanel cards = func.Cards(
                    imagem,
                    nomeLivro
            );

            painelExibicao.add(cards);

            contador_de_cards++;

            Iteraveis = Iteraveis.getSeguinte();
        }

        /**
         Adiciona painéis vazios para completar a última linha do GridLayout.
         Isso mantém todos os cards alinhados visualmente.
         */
        int extras = (3 - (contador_de_cards % 3)) % 3;

        for(int i = 0; i < extras; i++){

            JPanel cards = new JPanel(new BorderLayout());

            cards.setBorder(
                    javax.swing.BorderFactory.createEmptyBorder(
                            10,
                            15,
                            10,
                            15
                    )
            );

            cards.setOpaque(true);
            cards.setBorder(null);

            painelExibicao.add(cards);
        }

        // Atualiza a interface
        painelExibicao.revalidate();
        painelExibicao.repaint();

        tela.add(scroll);

        tela.revalidate();
        tela.repaint();
    }

    /**
     Cria os botões de navegação da tela.
     Permite retornar ao menu principal ou alternar para o modo de exibição encadeado.
     */
    public void BotoesNavegaveis(){

        // Retorna ao menu principal
        JButton voltar = func.botao(50,330,140,40,new Color(227,163,36),"Voltar Menu",null,null);

        voltar.addActionListener(ev -> {
            controle.VerMenu(true);
            controle.VerAcervoScroll(false);
        });

        obj.add(voltar);

        // Retorna para a visualização em lista encadeada
        JButton ModoEncadeado = func.botao(50,270,140,40,new Color(227,163,36),"Voltar",null,null);

        ModoEncadeado.addActionListener(ev -> {
            controle.VerListarAcervo(true);
            controle.VerAcervoScroll(false);
        });

        obj.add(ModoEncadeado);
    }
}
