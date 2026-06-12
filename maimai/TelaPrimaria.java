import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.border.LineBorder; 

public class TelaPrimaria {
    JFrame tela;
    funcoes_essenciais func = new funcoes_essenciais();
    Central controle;

    public TelaPrimaria(Central controle){
        this.controle = controle;
        Tela();
    }

    public void VerTela(Boolean ver){
        tela = func.VerTela(tela, ver);
    }

    public void Tela() {
        tela = func.criaFrame(400, 200, 800, 500, Color.BLACK, "Tela Principal", null);

        
        renderizador imagem = new renderizador(func.capa);
        imagem.setBounds(0, 0, 800, 500);;
        tela.add(imagem);

        JButton enter = func.botao(300, 320, 200, 50, new Color (24, 58, 89), "Entrar", null, new LineBorder(Color.black, 2, true));
        enter.setBorderPainted(false);
        enter.setForeground( new Color(227,163,36));
        enter.addActionListener(ev -> {
           controle.VerMenu(true);
           controle.VerTelaPrimaria(false);
        });

        imagem.add(enter);
        imagem.repaint();
    }

    public void eliminacao(){
        func.autoEliminacao(tela);
    }
}
