import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/*
 Tela responsável pelo cadastro de novos livros.
 Permite informar os dados do livro, selecionar uma imagem e adicionar o livro ao acervo da biblioteca.
 */
public class addLivro {

    private funcoes_essenciais func = new funcoes_essenciais();
    private JTextField[] camposTexto = new JTextField[4]; // campos do livro

    private JLabel descricao;
    private JPanel lateralCinza;
    private JTextArea txtDescricao;
    private JPanel p1;
    private JPanel painelImagem;
    private JButton img;

    private String nomeImagemLivro = null; // identifica imagem de livro
    private File arquivo = null;
    private JFrame tela;
    Central controle;

    public addLivro(Central controle){
        this.controle = controle;
        Tela();
        rotulosLivros();
        ResetTela();
        Botoes();
    }

    /*
     Exibe ou oculta a tela.
     */
    public void VerTela(Boolean ver){
        tela = func.VerTela(tela, ver);
    }

    /*
     Cria toda a estrutura visual da tela: painel lateral, título, área da imagem e descrição.
     */
    public void Tela() {

        tela = func.criaFrame(400, 200, 800, 500, Color.BLACK, "Add Livro", null);
        lateralCinza = func.criaJPanel(0, 0, 250, 500, new Color (24, 58, 89), null);

        renderizador telaCinza = new renderizador(func.brasao, 0, 0, 250, 200);
        lateralCinza.add(telaCinza);
        tela.add(lateralCinza);

        JPanel Titulo = func.criaJPanel(250, 0, 550, 80, new Color (24, 58, 89), null);
        tela.add(Titulo);

        JLabel textoTitulo = func.criarLabel(20, 20, 550, 50, null, "Adicionar Livro:", false, new Font("Arial", Font.BOLD, 30));
        textoTitulo.setForeground( new Color(227,163,36));
        Titulo.add(textoTitulo);

        p1 = func.criaJPanel(290, 90, 455, 200, Color.GRAY, null);
        tela.add(p1);

        painelImagem = func.criaJPanel(20, 20, 160, 160, Color.LIGHT_GRAY, null);
        p1.add(painelImagem);

        descricao = func.criarLabel(200, 15, 100, 20, null, "Descrição: ", false, new Font("Arial", Font.BOLD, 15));
        p1.add(descricao);

        txtDescricao = func.areaTexto(200, 40, 235, 140, Color.LIGHT_GRAY, true); 
        p1.add(txtDescricao);
    }

    /*
     Cria os botões da tela e define suas funcionalidades.
     Inclui o cadastro do livro, retorno ao menu e seleção de imagem.
     */
    public void Botoes(){

        JButton adicionar = func.botao(50, 260, 140, 40, new Color(227,163,36), "Adicionar", null, null);
        adicionar.setForeground(new Color (24, 58, 89));
        lateralCinza.add(adicionar);

        /*
         Adiciona um novo livro ao acervo.
         Caso nenhuma imagem seja selecionada,  é utilizada a capa padrão do sistema.
         */
        adicionar.addActionListener(e -> {
            if(arquivo == null || !arquivo.exists()){
                if(camposTexto[0].getText().equals("") && camposTexto[1].getText().equals("")
                     && camposTexto[2].getText().equals("") && camposTexto[3].getText().equals("")){
                    System.out.println("Vazio");
                    }
                    else{
                        String titulo = camposTexto[0].getText().equals("") ? "NoName$" : camposTexto[0].getText();
                        Livro adder = new Livro(titulo, camposTexto[1].getText(), camposTexto[3].getText(), camposTexto[2].getText(), func.SemCapa, txtDescricao.getText());

                        // Adiciona o livro à biblioteca e salva no arquivo JSON
                        controle.biblio.inserirLivro(adder);
                        controle.mem.adicionarNoJson(adder);

                        controle.VerTelaAdicionado(true);
                        ResetTela();
                    }
            }
            else{

                // Copia a imagem escolhida para a pasta de imagens do sistema
                nomeImagemLivro = "dados\\img\\imagensSalvas\\" + arquivo.getName();
                File localFinal = new File(nomeImagemLivro);
                
                try{
                    Files.copy(arquivo.toPath(), localFinal.toPath());
                }
                catch(IOException exc){
                    System.out.println(exc.getLocalizedMessage());
                }

                String titulo = camposTexto[0].getText().equals("") ? "NoName$" : camposTexto[0].getText();
                Livro adder = new Livro(titulo, camposTexto[1].getText(), camposTexto[3].getText(), camposTexto[2].getText(), nomeImagemLivro, txtDescricao.getText());

                // Adiciona o livro à biblioteca e salva no arquivo JSON
                controle.biblio.inserirLivro(adder);
                controle.mem.adicionarNoJson(adder);

                controle.VerTelaAdicionado(true);
                ResetTela();
            }
        });

        /*
         Retorna ao menu principal e limpa os campos da tela.
         */
        JButton voltar = func.botao(50, 330, 140, 40, new Color(227,163,36), "Voltar", null, null);
        voltar.addActionListener(ev -> {
            ResetTela();
            controle.VerMenu(true);
            controle.VerAddLivro(false);
         });
         lateralCinza.add(voltar);

        /*
         Permite selecionar uma imagem de capa para o livro.
         */
        img = func.botao(0, 0, 160, 160, Color.LIGHT_GRAY, "Imagem", null, null);
        img.addActionListener(e -> {
            JFileChooser diretorio = new JFileChooser();
            diretorio.showOpenDialog(tela);
            arquivo = diretorio.getSelectedFile();

            // Exibe a imagem selecionada na interface
            if(arquivo != null){
                String path = arquivo.getAbsolutePath();
                img.setIcon(func.SetImageIcon(path));
            }
        });
        painelImagem.add(img);
    }

    /*
     Limpa todos os campos da tela, removendo texto e imagem selecionada.
     */
    public void ResetTela(){
        if (img != null) {
            img.setIcon(null);
        }

        for(JTextField e : camposTexto){
            e.setText("");
        }
        txtDescricao.setText("");
        painelImagem.revalidate();
        painelImagem.repaint();

        arquivo = null;
    }

    /*
     Cria a área de entrada dos dados do livro.
     Os campos são organizados utilizando GridBagLayout.
     */
    public void rotulosLivros(){
        JPanel p2 = func.criaJPanel(290, 300, 455, 145, Color.white, new GridBagLayout());
        p2.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 15, 10, 15));

        func.rotulosGradeados(p2, camposTexto);
        p2.repaint();
        tela.add(p2);
    }

}
