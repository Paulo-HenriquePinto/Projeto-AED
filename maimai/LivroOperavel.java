import javax.swing.*;
import java.awt.*;

/**
 Essa classe é responsável pela tela de visualização e manipulação de um livro.
 Nela são exibidas as informações detalhadas do livro selecionado, permitindo ao usuário realizar operações como aluguel, devolução e remoção de livros do acervo.
 */
public class LivroOperavel {

    // Classe auxiliar utilizada para criação e manipulação dos componentes gráficos
    private funcoes_essenciais func = new funcoes_essenciais();

    // Referência ao controlador central da aplicação
    private Central controle;

    // Componentes principais da janela
    private JFrame tela;
    private JPanel obj;

    // Componentes utilizados para exibir os dados do livro
    JTextArea txtDescricao;
    JLabel descricao;
    JButton img;
    JTextField[] camposTexto = new JTextField[4];

    /**
     Construtor da tela.
     Inicializa todos os componentes visuais necessários.
     */
    public LivroOperavel(Central controle){
        this.controle = controle;

        Tela();
        PaineisSuperiores();
        PaineisInferiores();
        BotoesNavegaveis();

        tela.revalidate();
        tela.repaint();
    }

    /**
     Exibe ou oculta a janela.
     ver true para exibir e false para ocultar.
     */
    public void VerTela(Boolean ver){
        tela = func.VerTela(tela, ver);
    }

    /**
     Cria a estrutura principal da tela.
     Contém o painel lateral, o cabeçalho e a área do logotipo.
     */
    private void Tela() {

        // Criação da janela principal
        tela = func.criaFrame(
                400, 200,
                800, 500,
                Color.BLACK,
                "Livro Operavel",
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
        JPanel obj2 = func.criaJPanel(
                250, 0,
                550, 80,
                new Color(227, 163, 36),
                null
        );
        tela.add(obj2);

        // Título da tela
        JLabel addLivro = func.criarLabel(
                20, 20,
                550, 50,
                null,
                "Operações",
                false,
                new Font("Arial", Font.BOLD, 30)
        );
        obj2.add(addLivro);

        // Área reservada para o logotipo
        JPanel logo = func.criaJPanel(
                0, 0,
                250, 140,
                Color.white,
                null
        );
        obj.add(logo);

        // Renderização da imagem do brasão/logo
        renderizador imagemCapa = new renderizador(
                func.brasao,
                0, 0,
                250, 140
        );
        logo.add(imagemCapa);
    }

    /**
     Cria a região superior da tela onde serão exibidos a capa e a descrição do livro selecionado.
     */
    private void PaineisSuperiores(){

        // Painel principal das informações superiores
        JPanel p1 = func.criaJPanel(
                300, 80,
                440, 210,
                Color.white,
                null
        );
        tela.add(p1);

        // Área destinada à exibição da capa do livro
        img = func.botao(
                20, 20,
                160, 160,
                Color.LIGHT_GRAY,
                "",
                null,
                null
        );
        p1.add(img);

        // Rótulo da descrição
        descricao = func.criarLabel(
                200, 15,
                100, 20,
                null,
                "Descrição:",
                false,
                new Font("Arial", Font.BOLD, 15)
        );
        p1.add(descricao);

        // Campo onde será exibida a descrição do livro
        txtDescricao = func.areaTexto(
                200, 40,
                235, 140,
                Color.LIGHT_GRAY,
                false
        );
        p1.add(txtDescricao);
    }

    /**
     Atualiza os componentes da tela com os dados do livro selecionado.
     livroVisto Livro que será exibido.
     */
    public void AtualizarTela(Livro livroVisto){

        // Carrega a imagem armazenada no caminho do livro
        Image imagem = new ImageIcon(
                livroVisto.getPathFoto()
        ).getImage();

        // Redimensiona a imagem para caber no espaço disponível
        ImageIcon imagemFinal = new ImageIcon(
                imagem.getScaledInstance(
                        175,
                        160,
                        Image.SCALE_SMOOTH
                )
        );

        // Atualiza a capa exibida
        img.setIcon(imagemFinal);

        // Atualiza os campos de texto com os dados do livro
        func.SetCampos(
                camposTexto,
                txtDescricao,
                livroVisto
        );

        tela.revalidate();
        tela.repaint();
    }

    /**
     Cria a região inferior da tela responsável por exibir informações como título, autor, gênero e ano.
     */
    private void PaineisInferiores(){

        GridBagConstraints lay = new GridBagConstraints();
        lay.insets = new Insets(5, 5, 5, 5);

        // Painel que organiza os campos informativos em grade
        JPanel p2 = func.criaJPanel(
                300, 290,
                440, 170,
                Color.white,
                new GridBagLayout()
        );

        p2.setBorder(
                BorderFactory.createEmptyBorder(
                        0, 15, 60, 15
                )
        );

        tela.add(p2);

        // Criação dos campos de exibição dos atributos do livro
        func.rotulosGradeados(
                p2,
                camposTexto
        );
    }

    /**
     Cria os botões responsáveis pelas operações disponíveis para o livro selecionado.
     */
    private void BotoesNavegaveis(){

        // Barras laterais decorativas
        JPanel botaoDireito = func.criaJPanel(
                250, 80,
                50, 420,
                Color.LIGHT_GRAY,
                null
        );

        JPanel botaoEsquerdo = func.criaJPanel(
                740, 80,
                50, 420,
                Color.LIGHT_GRAY,
                null
        );

        tela.add(botaoDireito);
        tela.add(botaoEsquerdo);

        /**
         * Botão responsável pela operação de aluguel.
         */
        JButton botaoAlugar = func.botao(
                50, 170,
                140, 40,
                new Color(227,163,36),
                "Alugar Livro",
                null,
                null
        );

        botaoAlugar.setForeground(
                new Color(24,58,89)
        );

        botaoAlugar.addActionListener(a -> {
            // Futuramente realizará a lógica de aluguel
        });

        obj.add(botaoAlugar);

        /**
         * Botão responsável pela remoção do livro.
         */
        JButton botaoRemover = func.botao(
                50, 240,
                140, 40,
                new Color(227,163,36),
                "Remover Livro",
                null,
                null
        );

        botaoRemover.setForeground(
                new Color(24,58,89)
        );

        botaoRemover.addActionListener(a -> {

            // Exibe a tela de confirmação de remoção
            controle.VerTelaRemovido(true);

            // Fecha a tela atual
            controle.VerLivroOperavel(false);
        });

        obj.add(botaoRemover);

        /**
         * Botão responsável pela devolução do livro.
         */
        JButton botaoDevolver = func.botao(
                50, 310,
                140, 40,
                new Color(227,163,36),
                "Devolver Livro",
                null,
                null
        );

        botaoDevolver.setForeground(
                new Color(24,58,89)
        );

        botaoDevolver.addActionListener(a -> {
            // Futuramente realizará a lógica de devolução
        });

        obj.add(botaoDevolver);

        /**
        Retorna para a tela anterior de operações.
         */
        JButton voltar = func.botao(
                50, 390,
                140, 40,
                new Color(227,163,36),
                "Voltar",
                null,
                null
        );

        voltar.addActionListener(ev -> {

            // Reabre a tela de operações
            controle.VerTelaOperacoes(true);

            // Fecha a tela atual
            controle.VerLivroOperavel(false);
        });

        obj.add(voltar);
    }
}
