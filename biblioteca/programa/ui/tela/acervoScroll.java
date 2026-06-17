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

package programa.ui.tela;

import programa.controle.Central;
import programa.modelo.Livro;
import programa.ui.componente.funcoes_essenciais;
import programa.ui.componente.renderizador;
import programa.ui.componente.BotaoLivro;

import javax.swing.*;
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
        CriarCardsRolaveis(controle.biblio.getPrimeiro_livro());
        chamarListagem();
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
    public void chamarListagem(){

        JComboBox<String> cbPrimeiro;
        JComboBox<String> cbSegundo;
        JButton btnListar;

        JPanel painelFiltros = func.criaJPanel(250, 80, 550, 50, Color.WHITE, null);
        tela.add(painelFiltros);

        String[] opcPrincipais = { "Alfabética (A-Z)", "Gênero", "Disponibilidade"};
        cbPrimeiro = new JComboBox<>(opcPrincipais);
        cbPrimeiro.setBounds(10, 10, 150, 30);
        painelFiltros.add(cbPrimeiro);

        cbSegundo = new JComboBox<>();
        cbSegundo.setBounds(170, 10, 150, 30);
        cbSegundo.setVisible(false);
        painelFiltros.add(cbSegundo);

        btnListar = func.botao(330, 10, 100, 30, new Color(227, 163, 36), "Listar", null, null);
        painelFiltros.add(btnListar);

        cbPrimeiro.addActionListener(e -> {
            String escolha = (String) cbPrimeiro.getSelectedItem();
            cbSegundo.removeAllItems();
            
            if (escolha.equals("Gênero")) {
                String[] generos = {"Escolha...", "Educação", "Fantasia", "Ficção Científica", 
                "História", "Romance", "Suspense", "Didático", "Outros"};
                for (String g : generos) cbSegundo.addItem(g);
                cbSegundo.setVisible(true);
                cbSegundo.setEnabled(true);
            } else if (escolha.equals("Disponibilidade")) {
                String[] disp = {"Escolha...", "Disponível", "Alocado"};
                for (String d : disp) cbSegundo.addItem(d);
                cbSegundo.setVisible(true);
                cbSegundo.setEnabled(true);
            } else {
                cbSegundo.setVisible(false);
            }
        });

        btnListar.addActionListener(e -> {
            String tipo = (String) cbPrimeiro.getSelectedItem();
            String valor = (String) cbSegundo.getSelectedItem();
            if (tipo.equals("Gênero")){
                if(!valor.equals("Escolha..."))
                AtualizarTela(controle.biblio.listarLivrosPorGenero(valor));
            }
            else if(tipo.equals("Disponibilidade")){
                if(!valor.equals("Escolha..."))
                    if(valor.equals("Disponível")) 
                        AtualizarTela(controle.biblio.listarLivrosDisponiveis());
                    else
                        AtualizarTela(controle.biblio.listarLivrosAlocados());
            }
            else if(tipo.equals("Alfabética (A-Z)")){
                AtualizarTela(controle.biblio.getPrimeiro_livro());
            }

        });
    }

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
