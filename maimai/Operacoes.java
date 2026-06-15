import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.*;

public class Operacoes{

    private JFrame tela;
    private JPanel obj;
    private JPanel obj2;
    private JPanel painelExibicao;
    private JTextField txtPesquisa;
    private JScrollPane scroll;
    private Livro exibicao;

    private funcoes_essenciais func = new funcoes_essenciais();
    private Central controle;

    public Operacoes(Central controle){
        this.controle = controle;
        Tela();
        PainelPesquisa();
        CriarCardsRolaveis(controle.biblio.getLivro());
        exibicao = null;
    }

    public void VerTela(Boolean ver){
        tela = func.VerTela(tela, ver);
    }

    private void Tela() {
        tela = func.criaFrame(400, 200, 800, 500, Color.black, "Tela Operações", null);

        obj = func.criaJPanel(0, 0, 250, 500, new Color(24,58,89), null);
        tela.add(obj);
        
        obj2 = func.criaJPanel(250, 0, 550, 80, new Color(227, 163, 36), null);
        tela.add(obj2);

        JLabel textoTitulo = func.criarLabel(20, 15, 210, 40, null, "Operações", false, new Font("Arial", Font.BOLD, 24));
        textoTitulo.setForeground(new Color(24,58,89));
        obj2.add(textoTitulo);
        
        JPanel Logo = func.criaJPanel(0, 0, 250, 140, Color.white, null);
        obj.add(Logo);

        renderizador imagemCapa = new renderizador(func.brasao, 0, 0, 250, 140);
        Logo.add(imagemCapa);

        JButton btnVoltar = func.botao(50, 245, 140, 40, new Color(227,163,36), "Voltar", null, null);
        btnVoltar.setForeground(new Color(24,58,89));
        btnVoltar.addActionListener(ev -> {
            controle.VerMenu(true);
            controle.VerTelaOperacoes(false);
         });
        obj.add(btnVoltar);
    }

    private void PainelPesquisa(){
        JPanel painelPesquisa = func.criaJPanel(250, 80, 550, 50, Color.WHITE, null);
        tela.add(painelPesquisa);

        txtPesquisa = func.caixaTexto(Color.LIGHT_GRAY, true);
        txtPesquisa.setBounds(20, 10, 360, 30);
        painelPesquisa.add(txtPesquisa);


        JButton btnPesquisa = func.botao(390, 10, 140, 30, Color.LIGHT_GRAY, "Pesquisa", null, null);
        btnPesquisa.addActionListener(e -> {
            String Busca = txtPesquisa.getText();
            exibicao = controle.biblio.buscarLivro(Busca); 
            CriarCardsRolaveis(exibicao);
        });
        painelPesquisa.add(btnPesquisa);
     }

    public void AtualizarTela(Livro livro){
        CriarCardsRolaveis(livro);
        txtPesquisa.setText("");
    }

    private void CriarCardsRolaveis(Livro PrimeiroLivro){

        if(painelExibicao != null){
            tela.remove(scroll);
        }

        painelExibicao = func.criaJPanel(0, 0, 0, 0, Color.white, func.gradeCards);

        scroll = func.Scroll(painelExibicao);
        scroll.setBounds(250, 130, 550, 290);
        
        Livro Iteraveis = PrimeiroLivro;
        int contador_de_cards = 0;

        while(Iteraveis != null){
        
            BotaoLivro imagem =  new BotaoLivro(Iteraveis);
            imagem.addActionListener(e -> {
                controle.LivroOp.AtualizarTela(imagem.getLivroAssociado());
                controle.VerLivroOperavel(true);
                controle.VerTelaOperacoes(false);
            });

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

}