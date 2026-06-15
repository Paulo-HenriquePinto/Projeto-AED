import javax.swing.*;
import java.awt.*;

public class LivroOperavel {

    private funcoes_essenciais func = new funcoes_essenciais();
    private Central controle;

    private JFrame tela;
    private JPanel obj;
    

    JTextArea txtDescricao;
    JLabel descricao;
    JButton img;
    JTextField[] camposTexto = new JTextField[4];

    public LivroOperavel(Central controle){
        this.controle = controle;
        Tela();
        PaineisSuperiores();
        PaineisInferiores();
        BotoesNavegaveis();
        tela.revalidate();
        tela.repaint();
    }

    public void VerTela(Boolean ver){
        tela = func.VerTela(tela, ver);
    }

    private void Tela() {
        tela = func.criaFrame(400, 200, 800, 500, Color.BLACK, "Livro Operavel", null);

        obj = func.criaJPanel(0, 0, 250, 500, new Color(24,58,89), null);
        tela.add(obj);

        JPanel obj2 = func.criaJPanel(250, 0, 550, 80, new Color(227, 163, 36), null);
        tela.add(obj2);

        JLabel addLivro = func.criarLabel(20, 20, 550, 50, null, "Operações", false, new Font("Arial", Font.BOLD, 30));
        obj2.add(addLivro);

        JPanel logo = func.criaJPanel(0, 0, 250, 140, Color.white, null);
        obj.add(logo);

        renderizador imagemCapa = new renderizador(func.brasao, 0, 0, 250, 140);
        logo.add(imagemCapa);
    }

    private void PaineisSuperiores(){

        JPanel p1 = func.criaJPanel(300, 80, 440, 210, Color.white, null);
        tela.add(p1);

        img = func.botao(20, 20, 160, 160, Color.LIGHT_GRAY, "", null, null);
        p1.add(img);

        descricao = func.criarLabel(200, 15, 100, 20, null, "Descrição:", false, new Font("Arial", Font.BOLD, 15));
        p1.add(descricao);

        txtDescricao = func.areaTexto(200, 40, 235, 140, Color.LIGHT_GRAY, false);
        p1.add(txtDescricao);
    }

    public void AtualizarTela(Livro livroVisto){
        Image imagem = new ImageIcon(livroVisto.getPathFoto()).getImage();
        ImageIcon imagemFinal = new ImageIcon(imagem.getScaledInstance(175,160,Image.SCALE_SMOOTH));

        img.setIcon(imagemFinal);

        func.SetCampos(camposTexto, txtDescricao, livroVisto);

        tela.revalidate();
        tela.repaint();
    }

    private void PaineisInferiores(){
        GridBagConstraints lay = new GridBagConstraints();
        lay.insets = new Insets(5, 5, 5, 5);

        JPanel p2 = func.criaJPanel(300, 290, 440, 170, Color.white, new GridBagLayout());
        p2.setBorder(BorderFactory.createEmptyBorder(0, 15, 60, 15));
        tela.add(p2);

        func.rotulosGradeados(p2, camposTexto);
    }

    private void BotoesNavegaveis(){

        JPanel botaoDireito = func.criaJPanel(250, 80, 50, 420, Color.LIGHT_GRAY, null);
        JPanel botaoEsquerdo = func.criaJPanel(740, 80, 50, 420, Color.LIGHT_GRAY, null);

        tela.add(botaoDireito);
        tela.add(botaoEsquerdo);

        JButton botaoAlugar = func.botao(50, 170, 140, 40, new Color(227,163,36), "Alugar Livro", null, null);
        botaoAlugar.setForeground(new Color(24,58,89));
        botaoAlugar.addActionListener(a-> {
            //alugar
        });
        obj.add(botaoAlugar);

        JButton botaoRemover = func.botao(50, 240, 140, 40, new Color(227,163,36), "Remover Livro", null, null);
        botaoRemover.setForeground(new Color(24,58,89));
        botaoRemover.addActionListener(a-> {
            //alugar()
            controle.VerTelaRemovido(true);
            controle.VerLivroOperavel(false);
        });
        obj.add(botaoRemover);

        JButton botaoDevolver = func.botao(50, 310, 140, 40, new Color(227,163,36), "Devolver Livro", null, null);
        botaoDevolver.setForeground(new Color(24,58,89));
        botaoDevolver.addActionListener(a-> {
            //devolver()
        });
        obj.add(botaoDevolver);

        JButton voltar = func.botao(50, 390, 140, 40, new Color(227,163,36), "Voltar", null, null);
        voltar.addActionListener(ev -> {
            controle.VerTelaOperacoes(true);
            controle.VerLivroOperavel(false);
        });

        obj.add(voltar);
    }
}