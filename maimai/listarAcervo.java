import javax.swing.*;
import java.awt.*;

public class listarAcervo {

    private funcoes_essenciais func = new funcoes_essenciais();
    private Central controle;
    
    private JFrame tela;
    private JPanel lateralAzul;
    private JLabel descricao;
    private JTextArea txtDescricao;
    private renderizador ImagemLivro = null;
    private Livro livroVisto;

    private JTextField[] camposTexto = new JTextField[4];

    public listarAcervo(Central controle){
        this.controle = controle;
        livroVisto = null;
        Tela();
        BotoesNavegaveis();
        atualizarPaineis();
    }

    public void VerTela(Boolean ver){
        livroVisto = controle.biblio.getLivro();
        atualizarPaineis();
        tela = func.VerTela(tela, ver);
    }

    private void Tela() {
        tela = func.criaFrame(400, 200, 800, 500, Color.BLACK, "Tela Principal", null);

        lateralAzul = func.criaJPanel(0, 0, 250, 500, new Color(24,58,89), null);
        tela.add(lateralAzul);

        JPanel Logo = func.criaJPanel(0, 0, 250, 140, Color.white, null);
        lateralAzul.add(Logo);

        renderizador imagemCapa = new renderizador(func.brasao, 0, 0, 250, 140);
        Logo.add(imagemCapa);

        JPanel obj2 = func.criaJPanel(250, 0, 550, 80, new Color(227, 163, 36), null);
        tela.add(obj2);

        JLabel addLivro = func.criarLabel(20, 20, 550, 50, null, "Acervo:", false, new Font("Arial", Font.BOLD, 30));
        obj2.add(addLivro);

        PainelSuperiorBranco();
        PaineisInferiores();
        
    }

    private void PainelSuperiorBranco(){
        JPanel PainelBranco = func.criaJPanel(300, 80, 440, 210, Color.white, null);
        tela.add(PainelBranco);

        descricao = func.criarLabel(200, 15, 100, 20, null, "Descrição:", false, new Font("Arial", Font.BOLD, 15));
        PainelBranco.add(descricao);

        txtDescricao = func.areaTexto(200, 40, 235, 140, Color.LIGHT_GRAY, false);
        PainelBranco.add(txtDescricao);

        ImagemLivro = new renderizador(func.SemLivro, 20, 20, 160, 160);
        PainelBranco.add(ImagemLivro);
    }

    private void atualizarPaineis(){
        if(livroVisto == null){
            ImagemLivro.atualizarImagem(func.SemLivro);
            txtDescricao.setText("");
            for(JTextField e : camposTexto){
                e.setText("");
            }
        }

        else{
            ImagemLivro.atualizarImagem(livroVisto.getPathFoto());
            func.SetCampos(camposTexto, txtDescricao, livroVisto);
        }
    }

    
    private void PaineisInferiores(){
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

    private void BotoesNavegaveis(){
        JButton botaoDireito = func.botao(740, 80, 50, 420, Color.LIGHT_GRAY, "=>", null, null);
        botaoDireito.addActionListener(x -> {
            if(livroVisto != null && livroVisto.getSeguinte() != null){
                livroVisto = livroVisto.getSeguinte();
                atualizarPaineis();
            }
            else{}
        });

        JButton botaoEsquerdo = func.botao(250, 80, 50, 420, Color.LIGHT_GRAY, "<=", null, null);
        botaoEsquerdo.addActionListener(x -> {
            if(livroVisto != null && livroVisto.getAnterior() != null){
                livroVisto = livroVisto.getAnterior();
                atualizarPaineis();
            }
            else{}
        });

        tela.add(botaoDireito);
        tela.add(botaoEsquerdo);

        JButton voltar = func.botao(50, 330, 140, 40, new Color(227,163,36), "Voltar", null, null);
        voltar.addActionListener(e -> {
            controle.VerMenu(true);
            controle.VerListarAcervo(false);
         });
         lateralAzul.add(voltar);

        JButton ModoScroll = func.botao(50, 270, 140, 40, new Color(227,163,36), "Acervo Scroll", null, null);
        ModoScroll.addActionListener(i -> {
            controle.AcervoScroll.AtualizarTela(controle.biblio.getLivro());
            controle.VerListarAcervo(false);
            controle.VerAcervoScroll(true);
        });
        lateralAzul.add(ModoScroll);

    }
}