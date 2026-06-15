import javax.swing.*;
import java.awt.*;

public class TelaPosAdicionarLivro {

    private funcoes_essenciais func = new funcoes_essenciais();
    private Central controle;
    private JFrame tela;

    public TelaPosAdicionarLivro(Central controle){
        this.controle = controle;
        Tela();
    }

    public void VerTela(Boolean ver){
        tela = func.VerTela(tela, ver);
    }

    private void Tela() {
        tela = func.criaFrame(400, 200, 800, 500, new Color(24, 58, 89), "Sucesso", null);
        tela.getContentPane().setBackground(new Color(24, 58, 89));
       
        JPanel faixaDourada = func.criaJPanel(0, 0, 800, 100, new Color(227, 163, 36), null);
        tela.add(faixaDourada);

        
        JLabel mensagem = func.criarLabel(0, 200, 800, 50, null, "Livro adicionado com sucesso!", false, new Font("Arial", Font.BOLD, 30));
        mensagem.setForeground(new Color(227, 163, 36));
        mensagem.setHorizontalAlignment(SwingConstants.CENTER); 
        tela.add(mensagem);

        JButton btnVoltar = func.botao(20, 400, 140, 40, new Color(227, 163, 36), "Voltar", null, null);
        btnVoltar.setForeground(new Color(24, 58, 89));
        btnVoltar.addActionListener(ev -> {
            controle.VerMenu(true);
            controle.VerTelaAdicionado(false);
         });
         tela.add(btnVoltar);
    }

}