/*
 * Universidade Federal de Sergipe - Departamento de Computação
 * Algoritmo e Estrutura de Dados I - 2026.1 - T01
 * Trabalho da Segunda Unidade - Grupo G8
 *
 * Sistema de gerenciamento de biblioteca pública
 * Estrutura usada: Lista Duplamente Encadeada
 *
 * Integrantes:
 *   - Lauren Victoria Ferreira Roma
 *   - Paulo Henrique Sa de Oliveira Pinto
 *   - Matheus Nascimento dos Santos
 *   - Gabriel Felipe Vilela Marti
 *   - Jose Antonio Rodrigues Santos
 */

package programa.ui.tela;

import programa.controle.Central;
import programa.modelo.Livro;
import programa.ui.componente.funcoes_essenciais;
import programa.ui.componente.renderizador;
import java.util.*;
import java.util.List;

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
    private JLabel lblMensagemErro;

    private List<String> generos = new ArrayList<>(Arrays.asList(
    "Educação", "Fantasia", "Ficção Científica", 
    "História", "Romance", "Suspense", "Didático"
    ));

    private Central controle;

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

        lblMensagemErro = new JLabel("", JLabel.CENTER);
        lblMensagemErro.setForeground(Color.RED);
        lblMensagemErro.setBounds(250, 85, 550, 30); // Posição abaixo do painel Titulo
        lblMensagemErro.setVisible(true);
        tela.add(lblMensagemErro);

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


    public boolean validarCampos(Livro livro){
        boolean expressao = camposTexto[0].getText().equals("")
         || camposTexto[3].getText().equals("") || camposTexto[2].getText().equals("") || camposTexto[1].getText().equals("");
        return expressao;
    }
    
    public boolean adicaoLivro(Livro livro){
        if(func.encontrarNoOriginal(controle.biblio.getPrimeiro_livro(), livro) != null)
            return false;
        else if(controle.biblio.inserirLivro(livro)){
        controle.mem.adicionarNoJson(livro);
        controle.VerAddLivro(false);
        controle.VerTelaAdicionado(true);
        ResetTela();
        return true;
        }
        return false;
    }

    public void TranferirFoto(String nomeImagemLivro){
        File localFinal = new File(nomeImagemLivro);
            try{
                Files.copy(arquivo.toPath(), localFinal.toPath());
            }
            catch(IOException exc){
                System.out.println(exc.getLocalizedMessage());
            }
            arquivo = null;     
    }

    public void Botoes(){

        JButton adicionar = func.botao(50, 260, 140, 40, new Color(227,163,36), "Adicionar", null, null);
        adicionar.setForeground(new Color (24, 58, 89));
        lateralCinza.add(adicionar);

        /*
         Adiciona um novo livro ao acervo.
         Caso nenhuma imagem seja selecionada,  é utilizada a capa padrão do sistema.
         */
        adicionar.addActionListener(e -> {
            String genero = generos.contains(camposTexto[1].getText()) ? camposTexto[1].getText() : "Outros";
            Livro adder = new Livro(camposTexto[0].getText(), genero, camposTexto[3].getText(), camposTexto[2].getText(), func.SemCapa, txtDescricao.getText());
            if(arquivo == null || !arquivo.exists()){
                if(validarCampos(adder)){
                    lblMensagemErro.setText("Campos insuficientes ou inválidos!");
                }
                else{
                        if(!adicaoLivro(adder)){
                            lblMensagemErro.setText("Livro já existente!");
                        }
                    }
                }

            else{
                nomeImagemLivro = func.moldeCaminho + arquivo.getName();
                adder.setPathFoto(nomeImagemLivro);
                if(validarCampos(adder)){
                    lblMensagemErro.setText("Campos insuficientes ou inválidos!");
                }
                else{
                        if(!adicaoLivro(adder)){
                            lblMensagemErro.setText("Livro já existente!");
                        }
                        else{
                            TranferirFoto(nomeImagemLivro);
                        }         
                    }
            }
        });

        /*
         Retorna ao menu principal e limpa os campos da tela.
         */
        JButton voltar = func.botao(50, 330, 140, 40, new Color(227,163,36), "Voltar", null, null);
        voltar.addActionListener(ev -> {
            arquivo = null;
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
        lblMensagemErro.setText("");
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
