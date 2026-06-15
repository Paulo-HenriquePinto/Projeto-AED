import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.*;

public class acervoScroll {

    private funcoes_essenciais func = new funcoes_essenciais();
    private Central controle;

    private JFrame tela;
    private JPanel obj;
    private JPanel obj2;
    private JPanel painelExibicao;
    private JScrollPane scroll;

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

    public void AtualizarTela(Livro livro){
        CriarCardsRolaveis(livro);
    }

    public void Tela() {
        tela = func.criaFrame(400, 200, 800, 500, Color.BLACK, "Tela Principal", null);

        obj = func.criaJPanel(0, 0, 250, 500, new Color(24, 58, 89), null);
        tela.add(obj);

        JPanel Logo = func.criaJPanel(0, 0, 250, 140, Color.white, null);
        obj.add(Logo);

        renderizador imagemCapa = new renderizador(func.brasao, 0, 0, 250, 140);
        imagemCapa.setBounds(0, 0, 250, 140);
        Logo.add(imagemCapa);

        obj2 = func.criaJPanel(250, 0, 550, 80, new Color(227,163,36), null);
        tela.add(obj2);

        JLabel addLivro = func.criarLabel(20, 20, 550, 50, null, "Acervo:", false, new Font("Arial", Font.BOLD, 30));
        obj2.add(addLivro);
        
    }
    
    public void CriarCardsRolaveis(Livro PrimeiroLivro){

        if(painelExibicao != null){
            tela.remove(scroll);
        }

        painelExibicao = func.criaJPanel(0, 0, 0, 0, Color.white, func.gradeCards);

        scroll = func.Scroll(painelExibicao);
        scroll.setBounds(250, 130, 550, 290);
        
        Livro Iteraveis = PrimeiroLivro;
        int contador_de_cards = 0;

        while(Iteraveis != null){
           
            JButton imagem =  new JButton();
            imagem.setIcon(func.SetImageIcon(Iteraveis.getPathFoto()));

            JLabel nomeLivro = new JLabel(Iteraveis.getTitulo(), JLabel.CENTER);

            JPanel cards = func.Cards(imagem, nomeLivro);
            painelExibicao.add(cards);

            contador_de_cards++;
            Iteraveis = Iteraveis.getSeguinte();
        }

        int extras = (3 - (contador_de_cards % 3)) % 3;

        for(int i = 0; i < extras; i++){
            JPanel cards = new JPanel(new BorderLayout());
            cards.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 15, 10, 15));
            cards.setOpaque(true);
            cards.setBorder(null);
            painelExibicao.add(cards);
        }

        painelExibicao.revalidate();
        painelExibicao.repaint();
        tela.add(scroll);
        tela.revalidate(); 
        tela.repaint();

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
}