package programa.ui.tela;

import programa.controle.Central;
import programa.ui.componente.funcoes_essenciais;

import javax.swing.*;
import java.awt.*;

/**
 Tela exibida após a adição bem-sucedida de um livro ao acervo.
 A função informa ao usuário que a operação foi concluída e fornece um botão para retornar ao menu principal.
 */
public class TelaPosAdicionarLivro {

    // Classe auxiliar para criação dos componentes gráficos
    private funcoes_essenciais func = new funcoes_essenciais();

    // Referência ao controlador central da aplicação
    private Central controle;

    // Janela principal da tela
    private JFrame tela;

    /**
      Construtor da tela.
     vInicializa a interface gráfica.
     */
    public TelaPosAdicionarLivro(Central controle){
        this.controle = controle;
        Tela();
    }

    /**
      Exibe ou oculta a janela.
      ver true para exibir e false para ocultar.
     */
    public void VerTela(Boolean ver){
        tela = func.VerTela(tela, ver);
    }

    /**
     Cria todos os componentes visuais da tela de confirmação.
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

        // Mensagem informando o sucesso da operação
        JLabel mensagem = func.criarLabel(
                0,
                200,
                800,
                50,
                null,
                "Livro adicionado com sucesso!",
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
         Botão responsável por retornar ao menu principal.
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

            // Reabre o menu principal
            controle.VerMenu(true);

            // Fecha a tela de confirmação
            controle.VerTelaAdicionado(false);
        });

        tela.add(btnVoltar);
    }
}
