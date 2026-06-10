import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.*;

public class acervoScroll {

    private funcoes_essenciais func = new funcoes_essenciais();
    private Central controle;

    JFrame tela;
    JPanel obj;
    JPanel obj2;

    GridBagConstraints lay = new GridBagConstraints();

    public acervoScroll(Central controle){
        this.controle = controle;
        Tela();
        CriarCardsRolaveis(controle.biblio.getLivro());
        BotoesNavegaveis();
    }

    public void VerTela(Boolean ver){
        tela = func.VerTela(tela, ver);
    }

    public void Tela() {
        tela = func.criaFrame(400, 200, 800, 500, Color.BLACK, "Tela Principal", null);

        obj = func.criaJPanel(0, 0, 250, 500, new Color(24, 58, 89), null);
        tela.add(obj);

        JPanel Logo = func.criaJPanel(0, 0, 250, 140, Color.white, null);
        obj.add(Logo);

        renderizador imagemCapa = new renderizador(func.brasao);
        imagemCapa.setBounds(0, 0, 250, 140);
        Logo.add(imagemCapa);

        obj2 = func.criaJPanel(250, 0, 550, 80, new Color(227,163,36), null);
        tela.add(obj2);

        JLabel addLivro = func.criarLabel(20, 20, 550, 50, null, "Acervo:", false, new Font("Arial", Font.BOLD, 30));
        obj2.add(addLivro);
        
    }

    public JScrollPane Scroll(JPanel painelExibicao){
        JScrollPane scroll = new JScrollPane(painelExibicao);
        scroll.setBounds(250,80,550,340);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        return scroll;
    }
    
    public void CriarCardsRolaveis(Livro PrimeiroLivro){
        GridLayout gradeCards = new GridLayout(0, 3, 0, 0);
        JPanel painelExibicao = func.criaJPanel(0, 0, 0, 0, Color.white, gradeCards);

        JScrollPane scroll = Scroll(painelExibicao);
        scroll.setBounds(250,80,550,340);
        scroll.getVerticalScrollBar().setUnitIncrement(16);

        
        Livro Iteraveis = PrimeiroLivro;
        int contador_de_cards = 0;
        while(Iteraveis != null){

            JPanel cards = new JPanel(new BorderLayout());
            cards.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 15, 10, 15));

            JButton imagem =  new JButton("Imagem");
            Image img = new ImageIcon(Iteraveis.pathFoto).getImage();
            ImageIcon imagemFinal = new ImageIcon( img.getScaledInstance(175,160,Image.SCALE_SMOOTH)); // dá pra encapsular
            imagem.setIcon(imagemFinal);

            JLabel nomeLivro = new JLabel(Iteraveis.getTitulo(), JLabel.CENTER);// cria os cards
            
            cards.setPreferredSize(new Dimension(0,200));
            cards.add(imagem, BorderLayout.CENTER);
            cards.add(nomeLivro, BorderLayout.SOUTH);
            painelExibicao.add(cards);

            contador_de_cards++;
            Iteraveis = Iteraveis.getSeguinte();
        }

        if (contador_de_cards % 3 != 0){
            int extras = 0;
            while(contador_de_cards % 3 != 0){
                contador_de_cards++;
                extras++;
            }

            for(int i = 0; i < extras; i++){
                JPanel cards = new JPanel(new BorderLayout());
                cards.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 15, 10, 15));
                cards.setOpaque(true);
                cards.setBorder(null);
                painelExibicao.add(cards);
            }

        }
        tela.add(scroll);

    }

    public void BotoesNavegaveis(){
        JButton voltar = func.botao(50, 330, 140, 40, new Color(227,163,36), "Voltar Menu", null, null);
        voltar.addActionListener(ev -> {
            controle.VerMenu(true);
            controle.VerAcervoScroll(false);
         });
         obj.add(voltar);

        JButton ModoEncadeado = func.botao(50, 270, 140, 40, new Color(227,163,36), "Voltar", null, null);
        ModoEncadeado.addActionListener(ev -> {
            controle.VerListarAcervo(true);
            controle.VerAcervoScroll(false);
        });
        obj.add(ModoEncadeado);

    }

    public void eliminacao(){
        func.autoEliminacao(tela);
    }
}