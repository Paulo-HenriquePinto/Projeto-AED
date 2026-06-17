package programa.ui.tela;

import programa.controle.Central;
import programa.ui.componente.funcoes_essenciais;

import javax.swing.*;
import java.awt.*;

/**
 Tela exibida após a remoção bem-sucedida de um livro do acervo.
 A função é informar ao usuário que a operação foi concluída e permitir o retorno à tela de operações.
 */
public class TelaPosRemoverLivro {

    // Classe auxiliar responsável pela criação dos componentes gráficos
    private funcoes_essenciais func = new funcoes_essenciais();

    // Referência ao controlador principal da aplicação
    private Central controle;

    // Janela principal da tela
    private JFrame tela;

    /**
     Construtor da tela.
     Inicializa toda a interface gráfica.
     */
    public TelaPosRemoverLivro(Central controle){
        this.controle = controle;
        Tela();
    }

    /**
     Exibe ou oculta a janela.
     ver true para exibir e false para ocultar.
     */
    public void VerTela(Boolean ver){
       tela.setVisible(ver);
    }

    /**
     Cria os componentes visuais da tela de confirmação de remoção de livro.
     */
    private void Tela() {

        // Criação da janela principal
        tela = func.criaFrame(
                400,
                200,
                800,
                500,
                new Color(24, 58, 89),
                "Sucesso",
                null
        );

        tela.getContentPane().setBackground(
                new Color(24, 58, 89)
        );

        // Faixa decorativa superior
        JPanel faixaDourada = func.criaJPanel(
                0,
                0,
                800,
                100,
                new Color(227, 163, 36),
                null
        );

        tela.add(faixaDourada);

        // Mensagem central informando o sucesso da remoção
        JLabel mensagem = func.criarLabel(
                0,
                200,
                800,
                50,
                null,
                "Livro removido com sucesso!",
                false,
                new Font("Arial", Font.BOLD, 30)
        );

        mensagem.setForeground(
                new Color(227, 163, 36)
        );

        mensagem.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        tela.add(mensagem);

        /**
         Botão responsável por retornar à tela de operações.
         */
        JButton btnVoltar = func.botao(
                20,
                400,
                140,
                40,
                new Color(227, 163, 36),
                "Voltar",
                null,
                null
        );

        btnVoltar.setForeground(
                new Color(24, 58, 89)
        );

        btnVoltar.addActionListener(ev -> {

            // Atualiza a listagem de livros após a remoção
            controle.TelaOperacoes.AtualizarTela(null);

            // Reabre a tela de operações
            controle.VerTelaOperacoes(true);

            // Fecha a tela de confirmação
            controle.VerTelaRemovido(false);
        });

        tela.add(btnVoltar);
    }
}
