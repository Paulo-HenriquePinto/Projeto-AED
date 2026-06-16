import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.*;

/**
 Tela responsável por exibir os livros disponíveis para operações.
 Permite pesquisar livros no acervo e selecionar um deles para visualizar detalhes ou executar ações específicas.
 */
public class Operacoes{

    // Componentes principais da interface
    private JFrame tela;
    private JPanel obj;
    private JPanel obj2;

    // Painel onde os cards dos livros são exibidos
    private JPanel painelExibicao;

    // Campo utilizado para pesquisa de livros
    private JTextField txtPesquisa;

    // Área de rolagem dos cards
    private JScrollPane scroll;

    // Livro atualmente exibido após uma pesquisa
    private Livro exibicao;

    // Classe auxiliar responsável pela criação dos componentes gráficos
    private funcoes_essenciais func = new funcoes_essenciais();

    // Referência ao controlador principal da aplicação
    private Central controle;

    /**
     Construtor da tela.
     Inicializa todos os componentes visuais e exibe os livros cadastrados na biblioteca.
     */
    public Operacoes(Central controle){
        this.controle = controle;

        Tela();
        PainelPesquisa();

        // Exibe inicialmente todos os livros cadastrados
        CriarCardsRolaveis(controle.biblio.getLivro());

        exibicao = null;
    }

    /**
     Exibe ou oculta a tela.
     ver true para exibir e false para ocultar.
     */
    public void VerTela(Boolean ver){
        tela = func.VerTela(tela, ver);
    }

    /**
     Cria a estrutura principal da janela. 
     Contém o painel lateral, cabeçalho e botão de retorno.
     */
    private void Tela() {

        tela = func.criaFrame(
                400, 200,
                800, 500,
                Color.black,
                "Tela Operações",
                null
        );

        // Painel lateral esquerdo
        obj = func.criaJPanel(
                0, 0,
                250, 500,
                new Color(24,58,89),
                null
        );
        tela.add(obj);

        // Cabeçalho superior
        obj2 = func.criaJPanel(
                250, 0,
                550, 80,
                new Color(227, 163, 36),
                null
        );
        tela.add(obj2);

        // Título da tela
        JLabel textoTitulo = func.criarLabel(
                20, 15,
                210, 40,
                null,
                "Operações",
                false,
                new Font("Arial", Font.BOLD, 24)
        );

        textoTitulo.setForeground(new Color(24,58,89));
        obj2.add(textoTitulo);

        // Área destinada ao logotipo da aplicação
        JPanel Logo = func.criaJPanel(
                0, 0,
                250, 140,
                Color.white,
                null
        );

        obj.add(Logo);

        renderizador imagemCapa = new renderizador(
                func.brasao,
                0, 0,
                250, 140
        );

        Logo.add(imagemCapa);

        /**
         Botão responsável por retornar ao menu principal.
         */
        JButton btnVoltar = func.botao(
                50, 245,
                140, 40,
                new Color(227,163,36),
                "Voltar",
                null,
                null
        );

        btnVoltar.setForeground(new Color(24,58,89));

        btnVoltar.addActionListener(ev -> {
            controle.VerMenu(true);
            controle.VerTelaOperacoes(false);
        });

        obj.add(btnVoltar);
    }

    /**
     Cria o painel de pesquisa utilizado para localizar livros cadastrados no acervo.
     */
    private void PainelPesquisa(){

        JPanel painelPesquisa = func.criaJPanel(
                250, 80,
                550, 50,
                Color.WHITE,
                null
        );

        tela.add(painelPesquisa);

        // Campo de texto para entrada da pesquisa
        txtPesquisa = func.caixaTexto(
                Color.LIGHT_GRAY,
                true
        );

        txtPesquisa.setBounds(20, 10, 360, 30);

        painelPesquisa.add(txtPesquisa);

        /**
         Botão responsável por realizar a busca de um livro pelo título.
         */
        JButton btnPesquisa = func.botao(
                390, 10,
                140, 30,
                Color.LIGHT_GRAY,
                "Pesquisa",
                null,
                null
        );

        btnPesquisa.addActionListener(e -> {

            String Busca = txtPesquisa.getText();

            exibicao = controle.biblio.buscarLivro(Busca);

            CriarCardsRolaveis(exibicao);
        });

        painelPesquisa.add(btnPesquisa);
    }

    /**
     Atualiza a área de exibição dos livros e limpa o campo de pesquisa.
     livro Livro que será exibido.
     */
    public void AtualizarTela(Livro livro){
        CriarCardsRolaveis(livro);
        txtPesquisa.setText("");
    }

    /**
     Cria dinamicamente os cards dos livros exibidos na área central da tela.
     Cada card contém:
     - imagem da capa;
     - título do livro;
     - acesso à tela de detalhes.
     
     PrimeiroLivro Primeiro livro da lista a ser exibida.
     */
    private void CriarCardsRolaveis(Livro PrimeiroLivro){

        // Remove a área de exibição anterior para reconstruí-la
        if(painelExibicao != null){
            tela.remove(scroll);
        }

        painelExibicao = func.criaJPanel(
                0, 0,
                0, 0,
                Color.white,
                func.gradeCards
        );

        scroll = func.Scroll(painelExibicao);

        scroll.setBounds(
                250, 130,
                550, 290
        );

        Livro Iteraveis = PrimeiroLivro;

        int contador_de_cards = 0;

        // Percorre os livros e cria um card para cada um
        while(Iteraveis != null){

            BotaoLivro imagem = new BotaoLivro(Iteraveis);

            /**
             Ao clicar na capa do livro, a tela de detalhes é aberta.
             */
            imagem.addActionListener(e -> {

                controle.LivroOp.AtualizarTela(
                        imagem.getLivroAssociado()
                );

                controle.VerLivroOperavel(true);
                controle.VerTelaOperacoes(false);
            });

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
         Adiciona painéis vazios para completar a última linha do GridLayout e manter o alinhamento visual dos cards.
         */
        int extras = (3 - (contador_de_cards % 3)) % 3;

        for(int i = 0; i < extras; i++){

            JPanel cards = new JPanel(new BorderLayout());

            cards.setBorder(
                    javax.swing.BorderFactory.createEmptyBorder(
                            10, 15, 10, 15
                    )
            );

            cards.setOpaque(true);
            cards.setBorder(null);

            painelExibicao.add(cards);
        }

        // Atualiza a interface gráfica
        painelExibicao.revalidate();
        painelExibicao.repaint();

        tela.add(scroll);

        tela.revalidate();
        tela.repaint();
    }
}
