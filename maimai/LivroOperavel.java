import javax.swing.*;
import java.awt.*;

public class LivroOperavel {

    private funcoes_essenciais func = new funcoes_essenciais();
    private Central controle;

    JFrame tela;
    JPanel obj;
    JPanel obj2;

    private JTextField[] camposTexto = new JTextField[4];
    GridBagConstraints lay = new GridBagConstraints();

    public LivroOperavel(Central controle){
        this.controle = controle;
        Tela();
        PaineisSuperiores();
        PaineisInferiores();
        BotoesNavegaveis();
    }

    public void VerTela(Boolean ver){
        tela = func.VerTela(tela, ver);
    }

    public void Tela() {
        tela = func.criaFrame(400, 200, 800, 500, Color.BLACK, "Livro Operavel", null);

        obj = func.criaJPanel(0, 0, 250, 500, new Color(24,58,89), null);
        tela.add(obj);

        obj2 = func.criaJPanel(250, 0, 550, 80, new Color(227, 163, 36), null);
        tela.add(obj2);

        JLabel addLivro = func.criarLabel(20, 20, 550, 50, null, "Operações", false, new Font("Arial", Font.BOLD, 30));
        obj2.add(addLivro);

        JPanel Logo = func.criaJPanel(0, 0, 250, 140, Color.white, null);
        obj.add(Logo);

        renderizador imagemCapa = new renderizador(func.brasao);
        imagemCapa.setBounds(0, 0, 250, 140);
        Logo.add(imagemCapa);
        
    }

    public void PaineisSuperiores(){

        JPanel p1 = func.criaJPanel(300, 80, 440, 210, Color.white, null);
        tela.add(p1);

        JPanel painelImagem = func.criaJPanel(20, 20, 160, 160, Color.red, null);
        p1.add(painelImagem);

        JButton img = func.botao(0, 0, 160, 160, Color.LIGHT_GRAY, "Imagem", null, null); 
        painelImagem.add(img);

        JLabel descricao = func.criarLabel(200, 15, 100, 20, null, "Descrição:", false, new Font("Arial", Font.BOLD, 15));
        p1.add(descricao);

        JTextArea txtDescricao = func.areaTexto(200, 40, 235, 140, Color.LIGHT_GRAY, false);
        p1.add(txtDescricao);

        }

    
    public void PaineisInferiores(){
        GridBagConstraints lay = new GridBagConstraints();
        lay.insets = new Insets(5, 5, 5, 5);

        JPanel p2 = func.criaJPanel(300, 290, 440, 170, Color.white, new GridBagLayout());
        p2.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 15, 60, 15));
        tela.add(p2);
        
        String[] rotulos = {"Nome: ", "Gênero:", "Ano:", "Autor:"};

        for (int i = 0; i < rotulos.length; i++) {
            // Cria e posiciona o Rótulo na coluna 0, linha i
            JLabel label = func.criarLabel(0, 0, 0, 0, null, rotulos[i], false, new Font("Arial", Font.BOLD, 14));
            lay.gridx = 0; 
            lay.gridy = i; 
            lay.weightx = 0.0; 
            lay.fill = GridBagConstraints.NONE;
            p2.add(label, lay);

            // Cria, armazena no vetor global e posiciona a Caixa na coluna 1, linha i
            camposTexto[i] = func.caixaTexto(Color.LIGHT_GRAY, false);
            lay.gridx = 1; 
            lay.gridy = i; 
            lay.weightx = 1.0; 
            lay.fill = GridBagConstraints.HORIZONTAL;
            p2.add(camposTexto[i], lay);
    }
    }

    public void BotoesNavegaveis(){
        JPanel botaoDireito = func.criaJPanel(250, 80, 50, 420, Color.LIGHT_GRAY, null);
        JPanel botaoEsquerdo = func.criaJPanel(740, 80, 50, 420, Color.LIGHT_GRAY, null);
        tela.add(botaoDireito);
        tela.add(botaoEsquerdo);

        JButton botaoAlugar = func.botao(50, 170, 140, 40, new Color(227,163,36), "Alugar Livro", null, null);
        botaoAlugar.setForeground(new Color(24,58,89));
        obj.add(botaoAlugar);

        JButton botaoRemover = func.botao(50, 240, 140, 40, new Color(227,163,36), "Remover Livro", null, null);
        botaoRemover.setForeground(new Color(24,58,89));
        obj.add(botaoRemover);

        JButton botaoDevolver = func.botao(50, 310, 140, 40, new Color(227,163,36), "Devolver Livro", null, null);
        botaoDevolver.setForeground(new Color(24,58,89));
        obj.add(botaoDevolver);

        JButton voltar = func.botao(50, 390, 140, 40, new Color(227,163,36), "Voltar", null, null);
        voltar.addActionListener(ev -> {
            controle.VerTelaOperacoes(true);
            controle.VerLivroOperavel(false);
         });
         obj.add(voltar);
    }
}
