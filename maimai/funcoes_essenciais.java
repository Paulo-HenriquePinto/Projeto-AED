import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import java.awt.*;

public class funcoes_essenciais{

    String brasao = "img\\brasao.png";
    String capa = "img\\capa.png";

    public JFrame criaFrame(int x, int y, int width, int height, Color cor, String nome, LayoutManager layout){
        JFrame Frame = new JFrame(nome);
        Frame.setBackground(cor);
        Frame.setBounds(x,y,width,height);
        Frame.setResizable(false);
        // Frame.setVisible(true);
        Frame.setLayout(layout);
        Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        return Frame;
    }
    
    public JPanel criaJPanel(int x, int y, int width, int height, Color cor, LayoutManager layout){
        JPanel Painel = new JPanel();
        Painel.setBackground(cor);
        Painel.setBounds(x,y,width,height);
        Painel.setLayout(layout);
        return Painel;
    }

    public JLabel criarLabel(int x, int y, int width, int height, Border borda, String texto, boolean opaco, Font fonte){
        JLabel Label = new JLabel(texto);
        Label.setOpaque(opaco);
        Label.setBounds(x,y,width,height);
        Label.setBorder(borda);
        Label.setFont(fonte);
        return Label;
    }

    public JTextField caixaTexto(Color cor, boolean editavel){
        JTextField texto = new JTextField();
        texto.setBackground(cor);
        texto.setEditable(editavel);
        return texto;
    }

    public JTextArea areaTexto(int x, int y, int width, int height, Color cor, boolean editavel){
        JTextArea Painel = new JTextArea();
        Painel.setEditable(editavel);
        Painel.setBackground(cor);
        Painel.setBounds(x,y,width,height);
        Painel.setLineWrap(true);       
        Painel.setWrapStyleWord(true);
        return Painel;
    }

    public JButton botao(int x, int y, int width, int height, Color cor, String texto, LayoutManager layout, LineBorder borda){
        JButton bot = new JButton();
        bot.setBounds(x, y, width, height);
        bot.setText(texto);
        bot.setBackground(cor);
        bot.setLayout(layout);
        bot.setBorder(borda);
        return bot;
    }

    public JButton BotaoVoltar(JButton voltar, JFrame tela, java.util.function.Supplier<JFrame> proximaTelaSupplier){
        voltar.setForeground(new Color (24, 58, 89));

        voltar.addActionListener(ev -> {
            JFrame proximaTela = proximaTelaSupplier.get();
            tela.dispose();
        });
        return voltar;
    }

    public JFrame VerTela(JFrame tela, boolean ver){
        tela.setVisible(ver);
        tela.repaint();
        return tela; 
    }
    
    public void autoEliminacao(JFrame tela){
        tela.dispose();
    }
}