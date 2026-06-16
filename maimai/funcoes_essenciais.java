import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import java.awt.*;

/*
 Classe utilitária que reúne funções usadas em diversas telas do sistema.
 Seu objetivo é evitar repetição de código na criação de componentes gráficos.
 */
public class funcoes_essenciais{

    // Caminhos das imagens utilizadas pela aplicação
    String brasao = "dados\\img\\Brasa_Logo\\brasao.png";
    String capa = "dados\\img\\Brasa_Logo\\capa.png";
    String SemLivro = "dados\\img\\SemLivro\\SemLivro.png";
    String SemCapa = "dados\\img\\SemCapa\\SemCapa.png";

    // Layout utilizado para exibição dos cards de livros
    GridLayout gradeCards = new GridLayout(0, 3, 0, 0);

    /*
     Cria e configura um JFrame com os parâmetros recebidos.
     */
    public JFrame criaFrame(int x, int y, int width, int height, Color cor, String nome, LayoutManager layout){
        JFrame Frame = new JFrame(nome);
        Frame.setBackground(cor);
        Frame.setBounds(x,y,width,height);
        Frame.setResizable(false);
        Frame.getContentPane().setBackground(Color.white);
        Frame.setLayout(layout);
        Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        return Frame;
    }

    /*
     Cria e configura um JPanel.
     */
    public JPanel criaJPanel(int x, int y, int width, int height, Color cor, LayoutManager layout){
        JPanel Painel = new JPanel();
        Painel.setBackground(cor);
        Painel.setBounds(x,y,width,height);
        Painel.setLayout(layout);
        return Painel;
    }

    /*
     Cria um JLabel já configurado com texto, fonte e borda.
     */
    public JLabel criarLabel(int x, int y, int width, int height, Border borda, String texto, boolean opaco, Font fonte){
        JLabel Label = new JLabel(texto);
        Label.setOpaque(opaco);
        Label.setBounds(x,y,width,height);
        Label.setBorder(borda);
        Label.setFont(fonte);
        return Label;
    }

    /*
     Cria uma caixa de texto simples.
     */
    public JTextField caixaTexto(Color cor, boolean editavel){
        JTextField texto = new JTextField();
        texto.setBackground(cor);
        texto.setEditable(editavel);
        return texto;
    }

    /*
     Cria uma área de texto com quebra automática de linha.
     */
    public JTextArea areaTexto(int x, int y, int width, int height, Color cor, boolean editavel){
        JTextArea Painel = new JTextArea();
        Painel.setEditable(editavel);
        Painel.setBackground(cor);
        Painel.setBounds(x,y,width,height);
        Painel.setLineWrap(true);
        Painel.setWrapStyleWord(true);
        return Painel;
    }

    /*
      Cria e configura um botão padrão utilizado no sistema.
     */
    public JButton botao(int x, int y, int width, int height, Color cor, String texto, LayoutManager layout, LineBorder borda){
        JButton bot = new JButton();
        bot.setBounds(x, y, width, height);
        bot.setText(texto);
        bot.setBackground(cor);
        bot.setLayout(layout);
        bot.setBorder(borda);
        bot.setForeground(new Color(24,58,89));
        return bot;
    }

    /*
     Exibe ou oculta uma tela.
     */
    public JFrame VerTela(JFrame tela, boolean ver){
        tela.setVisible(ver);
        tela.repaint();
        return tela; 
    }

    /*
     Cria um JScrollPane para permitir rolagem de conteúdo.
     */
     public JScrollPane Scroll(JPanel painelExibicao){
        JScrollPane scroll = new JScrollPane(painelExibicao);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        return scroll;
    }

    /*
     Carrega uma imagem e a redimensiona para o tamanho padrão dos cards.
     */
    public ImageIcon SetImageIcon(String path){
        Image img = new ImageIcon(path).getImage();
        ImageIcon imagemFinal = new ImageIcon(img.getScaledInstance(175,160,Image.SCALE_SMOOTH)); 
        return imagemFinal;
    }

    /*
     Cria um card de exibição de livro contendo imagem e título.
     */
    public JPanel Cards(JButton imagem, JLabel nomeLivro){
        JPanel cards = new JPanel(new BorderLayout());
        cards.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 15, 10, 15));
        cards.setPreferredSize(new Dimension(0,200));
        cards.add(imagem, BorderLayout.CENTER);
        cards.add(nomeLivro, BorderLayout.SOUTH);
        return cards;
    }

    /*
     Preenche os campos de texto com as informações de um livro.
     Utilizado nas telas de visualização e operações.
     */
    public void SetCampos(JTextField[] camposTexto, JTextArea txtDescricao, Livro livroVisto){
        camposTexto[0].setText(livroVisto.getTitulo());
        camposTexto[1].setText(livroVisto.getGenero());
        camposTexto[2].setText(livroVisto.getAno());
        camposTexto[3].setText(livroVisto.getAutor());
        txtDescricao.setText(livroVisto.getDescricao());
    }

    /*
     Cria os rótulos e campos de texto utilizados para exibir ou cadastrar as informações de um livro.
     */
    public void rotulosGradeados(JPanel p2, JTextField[] camposTexto){
        GridBagConstraints lay = new GridBagConstraints();
        lay.insets = new Insets(4, 5, 4, 5);

        String[] rotulos = {"Nome: ", "Gênero: ", "Ano: ", "Autor: "};

        for (int i = 0; i < rotulos.length; i++){

            JLabel label = criarLabel(0, 0, 0, 0, null, rotulos[i], false, new Font("Arial", Font.BOLD, 15));

            lay.gridx = 0;
            lay.gridy = i;
            lay.weightx = 0.0;
            lay.fill = GridBagConstraints.NONE;
            p2.add(label, lay);

            camposTexto[i] = caixaTexto(Color.LIGHT_GRAY, true);

            lay.gridx = 1;
            lay.gridy = i;
            lay.weightx = 1.0;
            lay.fill = GridBagConstraints.HORIZONTAL;
            p2.add(camposTexto[i], lay);
        }
    }
}
