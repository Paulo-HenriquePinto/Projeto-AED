package programa.ui.tela;

import programa.controle.Central;
import programa.modelo.Livro;
import programa.ui.componente.funcoes_essenciais;
import programa.ui.componente.renderizador;

import javax.swing.*;
import java.awt.*;

/*
 Tela responsável pela visualização do acervo utilizando a estrutura da lista duplamente encadeada.
 O usuário navega entre os livros utilizando os botões de próximo e anterior.
 */
public class listarAcervo {

    private funcoes_essenciais func = new funcoes_essenciais();
    private Central controle;

    private JFrame tela;
    private JPanel lateralAzul;
    private JLabel descricao;
    private JTextArea txtDescricao;
    private renderizador ImagemLivro = null;

    // Livro atualmente exibido na tela
    private Livro livroVisto;

    private JTextField[] camposTexto = new JTextField[4];

    public listarAcervo(Central controle){
        this.controle = controle;
        livroVisto = null;
        Tela();
        BotoesNavegaveis();
        atualizarPaineis();
    }

    /*
     Exibe a tela e posiciona a visualização no primeiro livro do acervo.
     */
    public void VerTela(Boolean ver){
        livroVisto = controle.biblio.getPrimeiro_livro();
        atualizarPaineis();
        tela = func.VerTela(tela, ver);
    }

    /*
     Cria toda a estrutura visual da tela.
     */
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

    /*
     Cria o painel superior contendo a capa do livro e sua descrição.
     */
    private void PainelSuperiorBranco(){
        JPanel PainelBranco = func.criaJPanel(300, 80, 440, 210, Color.white, null);
        tela.add(PainelBranco);

        descricao = func.criarLabel(200, 15, 100, 20, null, "Descrição:", false, new Font("Arial", Font.BOLD, 15));
        PainelBranco.add(descricao);

        txtDescricao = func.areaTexto(200, 40, 235, 140, Color.LIGHT_GRAY, false);
        PainelBranco.add(txtDescricao);

        // Imagem padrão exibida quando não há livro selecionado
        ImagemLivro = new renderizador(func.SemLivro, 20, 20, 160, 160);
        PainelBranco.add(ImagemLivro);
    }

    /*
     Atualiza as informações exibidas na tela de acordo com o livro atualmente selecionado.
     */
    private void atualizarPaineis(){
        if(livroVisto == null){

            // Caso não exista livro para exibir
            ImagemLivro.atualizarImagem(func.SemLivro);
            txtDescricao.setText("");

            for(JTextField e : camposTexto){
                e.setText("");
            }
        }

        else{
            // Atualiza imagem e informações do livro
            ImagemLivro.atualizarImagem(livroVisto.getPathFoto());
            func.SetCampos(camposTexto, txtDescricao, livroVisto);
        }
    }

    /*
     Cria o painel inferior contendo os dados básicos do livro (nome, gênero, ano e autor).
     */
    private void PaineisInferiores(){
        GridBagConstraints lay = new GridBagConstraints();
        lay.insets = new Insets(5, 5, 5, 5);

        JPanel p2 = func.criaJPanel(300, 290, 440, 170, Color.white, new GridBagLayout());
        p2.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 15, 60, 15));
        tela.add(p2);

        String[] rotulos = {"Nome: ", "Gênero:", "Ano:", "Autor:"};

        for (int i = 0; i < rotulos.length; i++) {

            // Cria os rótulos dos campos
            JLabel label = func.criarLabel(0, 0, 0, 0, null, rotulos[i], false, new Font("Arial", Font.BOLD, 14));
            lay.gridx = 0;
            lay.gridy = i;
            lay.weightx = 0.0;
            lay.fill = GridBagConstraints.NONE;
            p2.add(label, lay);

            // Cria os campos de exibição dos dados
            camposTexto[i] = func.caixaTexto(Color.LIGHT_GRAY, false);
            lay.gridx = 1;
            lay.gridy = i;
            lay.weightx = 1.0;
            lay.fill = GridBagConstraints.HORIZONTAL;
            p2.add(camposTexto[i], lay);
        }
    }

    /*
     Cria os botões responsáveis pela navegação entre os livros e troca de telas.
     */
    private void BotoesNavegaveis(){

        /*
         Avança para o próximo livro da lista encadeada.
         */
        JButton botaoDireito = func.botao(740, 80, 50, 420, Color.LIGHT_GRAY, "=>", null, null);
        botaoDireito.addActionListener(x -> {
            if(livroVisto != null && livroVisto.getSeguinte() != null){
                livroVisto = livroVisto.getSeguinte();
                atualizarPaineis();
            }
            else{}
        });

        /*
         Retorna para o livro anterior da lista encadeada.
         */
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

        /*
         Retorna ao menu principal.
         */
        JButton voltar = func.botao(50, 330, 140, 40, new Color(227,163,36), "Voltar", null, null);
        voltar.addActionListener(e -> {
            controle.VerMenu(true);
            controle.VerListarAcervo(false);
         });
         lateralAzul.add(voltar);

        /*
         Alterna para o modo de visualização com scroll, onde todos os livros são exibidos simultaneamente.
         */
        JButton ModoScroll = func.botao(50, 270, 140, 40, new Color(227,163,36), "Acervo Scroll", null, null);
        ModoScroll.addActionListener(i -> {
            controle.VerListarAcervo(false);
            controle.VerAcervoScroll(true);
        });
        lateralAzul.add(ModoScroll);

    }
}
