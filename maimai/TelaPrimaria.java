import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.border.LineBorder;

/**
 Tela inicial da aplicação.
 Funciona como a página de entrada do sistema, exibindo a imagem principal e o botão que dá acesso ao menu principal da biblioteca.
 */
public class TelaPrimaria {

    // Janela principal da tela
    private JFrame tela;

    // Classe auxiliar responsável pela criação dos componentes gráficos
    private funcoes_essenciais func = new funcoes_essenciais();

    // Referência ao controlador central da aplicação
    Central controle;

    /**
     Construtor da tela inicial.
     Inicializa todos os componentes visuais.
     */
    public TelaPrimaria(Central controle){
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
     Cria a interface da tela inicial.
     A tela é composta por uma imagem de fundo e um botão que direciona o usuário ao menu principal.
     */
    private void Tela() {

        // Criação da janela principal
        tela = func.criaFrame(
                400,
                200,
                800,
                500,
                Color.BLACK,
                "Tela Principal",
                null
        );

        // Imagem de capa exibida como plano de fundo
        renderizador imagem = new renderizador(
                func.capa,
                0,
                0,
                800,
                500
        );

        tela.add(imagem);

        /**
         Botão responsável por iniciar a navegação dentro do sistema.
         */
        JButton enter = func.botao(
                300,
                320,
                200,
                50,
                new Color(24, 58, 89),
                "Entrar",
                null,
                new LineBorder(Color.black, 2, true)
        );

        enter.setForeground(
                new Color(227,163,36)
        );

        enter.addActionListener(ev -> {

            // Abre o menu principal
            controle.VerMenu(true);

            // Fecha a tela inicial
            controle.VerTelaPrimaria(false);
        });

        imagem.add(enter);
    }
}
