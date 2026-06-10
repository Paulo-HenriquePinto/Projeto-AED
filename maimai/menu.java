import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class menu {
    
    JFrame tela;
    JPanel lateralCinza;
    JPanel painelLogo;

    funcoes_essenciais func = new funcoes_essenciais();
    Central controle;

    public menu(Central controle){
      this.controle = controle; 
      Tela();
      Botoes();
      Titulo(); 
    }

    public void VerTela(Boolean ver){
        tela = func.VerTela(tela, ver);
    }
    
    public void Tela() {
        tela = func.criaFrame(400, 200, 800, 500, Color.BLACK, "Tela Principal", null);
        tela.getContentPane().setBackground(Color.white); //O fundo fica branco

        lateralCinza = func.criaJPanel(0, 0, 250, 500, new Color(24,58,89), null); //Cria a barra lateral esquerda(azul-escuro)
        tela.add(lateralCinza);

        renderizador imagemCapa = new renderizador(func.brasao);
        imagemCapa.setBounds(250,0,550,470);
        tela.add(imagemCapa);
    }

    public void Botoes(){
        JButton botaoAcervo = func.botao(50, 160, 140, 40, new Color(227,163,36), "Acervo", null, null);
        botaoAcervo.setForeground(new Color(24,58,89));
        botaoAcervo.addActionListener(ev -> {
            controle.AcervoScroll.eliminacao();
            controle.AcervoScroll = new acervoScroll(controle);
            controle.VerListarAcervo(true);
            controle.VerMenu(false);
         });



        JButton botaoAdicionar = func.botao(50, 240, 140, 40, new Color(227,163,36), "Adicionar Livro", null, null);
        botaoAdicionar.setForeground(new Color(24,58,89));
        botaoAdicionar.addActionListener(obs -> {
            controle.VerAddLivro(true);
            controle.VerMenu(false);
         });
        

        JButton botaoOperacoes = func.botao(50, 320, 140, 40, new Color(227,163,36), "Operações", null, null);
        botaoOperacoes.setForeground(new Color(24,58,89));
        botaoOperacoes.addActionListener(op -> {
            controle.VerTelaOperacoes(true);
            controle.VerMenu(false);
         });

        lateralCinza.add(botaoAcervo);
        lateralCinza.add(botaoAdicionar);
        lateralCinza.add(botaoOperacoes);
    }


    public void Titulo(){
        JPanel titulo = func.criaJPanel(15, 0, 250, 145, new Color(24, 58, 89), null);//Adicionando o título
        lateralCinza.add(titulo);

        JLabel textoTitulo = func.criarLabel(20, 70, 210, 40, null, "Menu Principal", false, new Font("Arial", Font.BOLD, 24));
        textoTitulo.setForeground(new Color(227,163,36));
        titulo.add(textoTitulo); // Criando o título do Menu
    }
     
}