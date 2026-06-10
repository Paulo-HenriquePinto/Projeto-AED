import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.*;

public class Operacoes{

    JFrame tela;
    funcoes_essenciais func = new funcoes_essenciais();
    Central controle;

    public Operacoes(Central controle){
        this.controle = controle;
        Tela();
        PainelPesquisa();
        CriarCardsRolaveis();
    }

    public void VerTela(Boolean ver){
        tela = func.VerTela(tela, ver);
    }

     public void Tela() {
        tela = func.criaFrame(400, 200, 800, 500, Color.black, "Tela Operações", null);

        JPanel obj = func.criaJPanel(0, 0, 250, 500, new Color(24,58,89), null);
        tela.add(obj);
        
        JButton botaoAlugar = func.botao(50, 150, 140, 40, new Color(227,163,36), "Alugar Livro", null, null);
        botaoAlugar.setForeground(new Color(24,58,89));
        obj.add(botaoAlugar);

        JButton botaoRemover = func.botao(50, 220, 140, 40, new Color(227,163,36), "Remover Livro", null, null);
        botaoRemover.setForeground(new Color(24,58,89));
        obj.add(botaoRemover);

        JButton botaoDevolver = func.botao(50, 290, 140, 40, new Color(227,163,36), "Devolver Livro", null, null);
        botaoDevolver.setForeground(new Color(24,58,89));
        obj.add(botaoDevolver);

        JPanel obj2 = func.criaJPanel(250, 0, 550, 80, new Color(227, 163, 36), null);
        tela.add(obj2);

        JLabel textoTitulo = func.criarLabel(20, 15, 210, 40, null, "Operações", false, new Font("Arial", Font.BOLD, 24));
        textoTitulo.setForeground(new Color(24,58,89));
        obj2.add(textoTitulo); // Criando o título do Menu
        
        JPanel Logo = func.criaJPanel(0, 0, 250, 140, Color.white, null);
        obj.add(Logo);

        renderizador imagemCapa = new renderizador(func.brasao);
        imagemCapa.setBounds(0, 0, 250, 140);
        Logo.add(imagemCapa);

      
     }

     public void botoe(){
        
     }

     public void PainelPesquisa(){
        JPanel painelPesquisa = func.criaJPanel(250, 80, 550, 50, Color.WHITE, null);
        tela.add(painelPesquisa);

        JTextField txtPesquisa = func.caixaTexto(Color.LIGHT_GRAY, true);
        txtPesquisa.setBounds(20, 10, 360, 30);
        painelPesquisa.add(txtPesquisa);


        JButton btnPesquisa = func.botao(390, 10, 140, 30, Color.LIGHT_GRAY, "Pesquisa", null, null);
        painelPesquisa.add(btnPesquisa);
     }

     public JScrollPane Scroll(JPanel painelExibicao){
        JScrollPane scroll = new JScrollPane(painelExibicao);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        return scroll;
    }

     public void CriarCardsRolaveis(){
        GridLayout gradeCards = new GridLayout(0, 3, 0, 0);
        JPanel painelExibicao = func.criaJPanel(0, 0, 0, 0, Color.white, gradeCards);

        JScrollPane scroll = Scroll(painelExibicao);
        scroll.setBounds(250,130,550,340);
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