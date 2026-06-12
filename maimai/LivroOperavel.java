import javax.swing.*;
import java.awt.*;

public class LivroOperavel {

    private funcoes_essenciais func = new funcoes_essenciais();
    private Central controle;

    JFrame tela;
    JPanel obj;
    JPanel obj2;

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

    public void Tela() {
        tela = func.criaFrame(400, 200, 800, 500, Color.BLACK, "Livro Operavel", null);

        obj = func.criaJPanel(0, 0, 250, 500, new Color(24,58,89), null);
        tela.add(obj);

        obj2 = func.criaJPanel(250, 0, 550, 80, new Color(227, 163, 36), null);
        tela.add(obj2);

        JLabel addLivro = func.criarLabel(20, 20, 550, 50, null, "Operações", false, new Font("Arial", Font.BOLD, 30));
        obj2.add(addLivro);

        JPanel logo = func.criaJPanel(0, 0, 250, 140, Color.white, null);
        obj.add(logo);

        renderizador imagemCapa = new renderizador(func.brasao);
        imagemCapa.setBounds(0, 0, 250, 140);
        logo.add(imagemCapa);
    }

    public void PaineisSuperiores(){

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

        camposTexto[0].setText(livroVisto.getTitulo());
        camposTexto[1].setText(livroVisto.getGenero());
        camposTexto[2].setText(livroVisto.getAno());
        camposTexto[3].setText(livroVisto.getAutor());

        txtDescricao.setText(livroVisto.getDescricao());

        tela.revalidate();
        tela.repaint();
    }

    public void PaineisInferiores(){
        GridBagConstraints lay = new GridBagConstraints();
        lay.insets = new Insets(5, 5, 5, 5);

        JPanel p2 = func.criaJPanel(300, 290, 440, 170, Color.white, new GridBagLayout());
        p2.setBorder(BorderFactory.createEmptyBorder(0, 15, 60, 15));
        tela.add(p2);

        String[] rotulos = {"Nome:", "Gênero:", "Ano:", "Autor:"};

        for (int i = 0; i < rotulos.length; i++) {
            JLabel label = func.criarLabel(0, 0, 0, 0, null, rotulos[i], false, new Font("Arial", Font.BOLD, 14));

            lay.gridx = 0;
            lay.gridy = i;
            lay.weightx = 0.0;
            lay.fill = GridBagConstraints.NONE;
            p2.add(label, lay);

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
        botaoAlugar.addActionListener(a-> {
            
        });
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