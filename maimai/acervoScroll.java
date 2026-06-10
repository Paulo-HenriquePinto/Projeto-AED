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
    private JFrame tela;;
    GridBagConstraints lay = new GridBagConstraints();

    public acervoScroll(Central controle){
        this.controle = controle;
        Tela();
        CriarCardsRolaveis();
    }

    public void VerTela(Boolean ver){
        tela = func.VerTela(tela, ver);
    }

    public void Tela() {
        tela = func.criaFrame(400, 200, 800, 500, Color.BLACK, "Tela Principal", null);

        JPanel obj = func.criaJPanel(0, 0, 250, 500, Color.darkGray, null);
        tela.add(obj);

        JPanel obj2 = func.criaJPanel(250, 0, 550, 80, Color.MAGENTA, null);
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
    
    public void CriarCardsRolaveis(){
        GridLayout gradeCards = new GridLayout(0, 3, 0, 0);
        JPanel painelExibicao = func.criaJPanel(0, 0, 0, 0, Color.white, gradeCards);

        JScrollPane scroll = Scroll(painelExibicao);
        scroll.setBounds(250,80,550,340);
        scroll.getVerticalScrollBar().setUnitIncrement(16);

        
        int numCards = 11;
        for(int i = 0; i < numCards; i++){

            JPanel cards = new JPanel(new BorderLayout());
            cards.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 15, 10, 15));

            JButton imagem =  new JButton("Imagem");
            JLabel nomeLivro = new JLabel("Sexo Anal", JLabel.CENTER);// cria os cards
            
            cards.setPreferredSize(new Dimension(0,200));
            cards.add(imagem, BorderLayout.CENTER);
            cards.add(nomeLivro, BorderLayout.SOUTH);
            painelExibicao.add(cards);
        }

        if (numCards % 3 != 0){
            int extras = 0;
            while(numCards % 3 != 0){
                numCards++;
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


}