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
import programa.ui.componente.funcoes_essenciais;
import programa.ui.componente.renderizador;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/*
 Tela principal de navegação do sistema.
 A partir dela o usuário pode acessar o acervo, adicionar novos livros ou realizar operações.
 */
public class menu {

    private JFrame tela;
    private JPanel lateralCinza;

    private funcoes_essenciais func = new funcoes_essenciais();
    private Central controle;

    public menu(Central controle){
      this.controle = controle;
      Tela();
      Botoes();
    }

    /*
     Exibe ou oculta a tela.
     */
    public void VerTela(Boolean ver){
        tela = func.VerTela(tela, ver);
    }

    /*
     Cria a estrutura visual do menu principal.
     */
    private void Tela() {
        tela = func.criaFrame(400, 200, 800, 500, Color.BLACK, "Tela Principal", null);

        lateralCinza = func.criaJPanel(0, 0, 250, 500, new Color(24,58,89), null);
        tela.add(lateralCinza);

        // Imagem principal exibida ao lado do menu
        renderizador imagemCapa = new renderizador(func.brasao, 250, 0, 550, 470);
        tela.add(imagemCapa);

        // Título do menu principal
        JLabel textoTitulo = func.criarLabel(35, 70, 210, 40, null, "Menu Principal", false, new Font("Arial", Font.BOLD, 24));
        textoTitulo.setForeground(new Color(227,163,36));
        lateralCinza.add(textoTitulo);
    }

    /*
     Cria os botões de navegação do sistema.
     */
    private void Botoes(){

        /*
         Abre a tela de visualização do acervo.
         */
        JButton botaoAcervo = func.botao(50, 160, 140, 40, new Color(227,163,36), "Acervo", null, null);
        botaoAcervo.addActionListener(ev -> {
            controle.AcervoScroll.AtualizarTela(controle.biblio.getPrimeiro_livro());
            controle.VerListarAcervo(true);
            controle.VerMenu(false);
         });

        /*
         Abre a tela de cadastro de livros.
         */
        JButton botaoAdicionar = func.botao(50, 240, 140, 40, new Color(227,163,36), "Adicionar Livro", null, null);
        botaoAdicionar.addActionListener(obs -> {
            controle.VerAddLivro(true);
            controle.VerMenu(false);
         });

        /*
         Abre a tela de operações sobre os livros (aluguel, devolução, remoção, etc.).
         */
        JButton botaoOperacoes = func.botao(50, 320, 140, 40, new Color(227,163,36), "Operações", null, null);
        botaoOperacoes.addActionListener(op -> {
            controle.TelaOperacoes.AtualizarTela(null);
            controle.VerTelaOperacoes(true);
            controle.VerMenu(false);
         });

        lateralCinza.add(botaoAcervo);
        lateralCinza.add(botaoAdicionar);
        lateralCinza.add(botaoOperacoes);
    }

}
