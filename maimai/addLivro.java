import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class addLivro {

    funcoes_essenciais func = new funcoes_essenciais();
    private JTextField[] camposTexto = new JTextField[4]; // campos do livro


    JLabel descricao;
    JPanel lateralCinza;
    JPanel Titulo;

    private String nomeImagemLivro = null; // identifica imagem de livro
    private File arquivo = null;
    private JFrame tela = func.criaFrame(400, 200, 800, 500, Color.BLACK, "Add Livro", null);
    Central controle;

    public addLivro(Central controle){
        this.controle = controle;
        Tela();
        CamposSuperioresLivros();
        rotulosLivros();
        Botoes();
    }

    public void VerTela(Boolean ver){
        tela = func.VerTela(tela, ver);
    }


    public void Tela() {
        
        tela.getContentPane().setBackground(Color.white);
        lateralCinza = func.criaJPanel(0, 0, 250, 500, new Color (24, 58, 89), null);

        renderizador telaCinza = new renderizador(func.brasao);
        telaCinza.setBounds(0,0,250,140);
        lateralCinza.add(telaCinza);
        
        tela.add(lateralCinza);

        Titulo = func.criaJPanel(250, 0, 550, 80, new Color(227,163,36), null);
        tela.add(Titulo);

        JLabel textoTitulo = func.criarLabel(20, 20, 550, 50, null, "Adicionar Livro:", false, new Font("Arial", Font.BOLD, 30));
        textoTitulo.setForeground( new Color(24,58,89));
        Titulo.add(textoTitulo);

    }

    public void Botoes(){
        JButton adicionar = func.botao(50, 260, 140, 40, new Color(227,163,36), "Adicionar", null, null);
        adicionar.setForeground(new Color (24, 58, 89));
        lateralCinza.add(adicionar);
          
        adicionar.addActionListener(e -> {
            if(arquivo == null || !arquivo.exists()){
            ///// caso de capa padrao
            }
            else{
                nomeImagemLivro = "img/" + arquivo.getName();
                File localFinal = new File(nomeImagemLivro);
                
                try{
                    Files.copy(arquivo.toPath(), localFinal.toPath());
                }
                catch(IOException exc){
                    System.out.println(exc.getLocalizedMessage());
                }

                controle.biblio.add(new Livro(camposTexto[0].getText(), camposTexto[1].getText(), camposTexto[3].getText(), camposTexto[2].getText(), nomeImagemLivro));
                controle.VerTelaAdicionado(true);
                controle.adicionarLivro.eliminacao();
                controle.adicionarLivro = new addLivro(controle);
            }
        });

        JButton voltar = func.botao(50, 330, 140, 40, new Color(227,163,36), "Voltar", null, null);
        voltar.addActionListener(ev -> {
            controle.VerMenu(true);
            controle.VerAddLivro(false);
         });
         lateralCinza.add(voltar);

    }

    public void CamposSuperioresLivros(){
        JPanel p1 = func.criaJPanel(290, 90, 455, 200, Color.GRAY, null);

        JPanel painelImagem = func.criaJPanel(20, 20, 160, 160, Color.LIGHT_GRAY, null);
        p1.add(painelImagem);

        JButton img = func.botao(0, 0, 160, 160, Color.LIGHT_GRAY, "Imagem", null, null); 
        img.addActionListener(e -> {

            JFileChooser diretorio = new JFileChooser();
            diretorio.showOpenDialog(tela);

            arquivo = diretorio.getSelectedFile(); // arquivo seleconado

            if(arquivo != null){
            String path = arquivo.getAbsolutePath();

            Image imagem = new ImageIcon(path).getImage(); 

            ImageIcon imagemFinal = new ImageIcon( imagem.getScaledInstance(175,160,Image.SCALE_SMOOTH));

            img.setIcon(imagemFinal);
            }
            else{
                System.out.println("Sem arquivo");
            }

        });
        painelImagem.add(img);

        descricao = func.criarLabel(200, 15, 100, 20, null, "Descrição: ", false, new Font("Arial", Font.BOLD, 15));
        p1.add(descricao);
        JTextArea txtDescricao = func.areaTexto(200, 40, 235, 140, Color.LIGHT_GRAY, true); 
        p1.add(txtDescricao);
        p1.repaint();
        tela.add(p1);
    }

    public void rotulosLivros(){
        JPanel p2 = func.criaJPanel(290, 300, 455, 145, Color.white, new GridBagLayout());
        p2.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 15, 10, 15));

        GridBagConstraints lay = new GridBagConstraints();
        lay.insets = new Insets(4, 5, 4, 5);


        String[] rotulos = {"Nome: ", "Gênero: ", "Ano: ", "Autor: "};
        for (int i = 0; i < rotulos.length; i++){
            
            JLabel label = func.criarLabel(0, 0, 0, 0, null, rotulos[i], false, new Font("Arial", Font.BOLD, 15));
            lay.gridx = 0; 
            lay.gridy = i; 
            lay.weightx = 0.0; 
            lay.fill = GridBagConstraints.NONE;
            p2.add(label, lay);

            
            camposTexto[i] = func.caixaTexto(Color.LIGHT_GRAY, true);
            lay.gridx = 1; 
            lay.gridy = i; 
            lay.weightx = 1.0; 
            lay.fill = GridBagConstraints.HORIZONTAL;
            p2.add(camposTexto[i], lay);
        }
        p2.repaint();
        tela.add(p2);
    }

    public void eliminacao(){
        func.autoEliminacao(tela);
    }

}
