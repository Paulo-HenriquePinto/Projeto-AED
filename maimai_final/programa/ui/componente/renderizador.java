package programa.ui.componente;

import javax.swing.*;
import java.awt.*;

/*
 Componente personalizado responsável por renderizar imagens.
 É utilizado em diversas telas para exibir logos, capas de livros e outras imagens do sistema, permitindo também a troca dinâmica da imagem exibida.
 */
public class renderizador extends JPanel{

    // Imagem atualmente exibida pelo componente
    private Image icon;

    /*
     Força o uso de layout nulo para permitir posicionamento absoluto dos componentes.
     */
    @Override
    public void setLayout(LayoutManager mgr) {
        super.setLayout(null);
    }

    /*
     Construtor do renderizador.
     Recebe o caminho da imagem e as dimensões do componente.
     */
    public renderizador(String path, int x, int y, int width, int height) {
        setOpaque(false);
        atualizarImagem(path);
        setBounds(x, y, width, height);
    }

    /*
     Atualiza a imagem exibida pelo componente.
     Após carregar a nova imagem, solicita a repintura da tela.
     */
    public void atualizarImagem(String path){
        ImageIcon icone = new ImageIcon(path);
        this.icon = icone.getImage();
        repaint(); // Força o paintComponent ser chamado novamente
    }

    /*
     Método responsável por desenhar a imagem no painel.
     A imagem é redimensionada automaticamente para ocupar toda a área disponível do componente.
     */
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D gc = (Graphics2D) g;

        // Melhora a qualidade do redimensionamento da imagem
        gc.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        if(icon == null){
            System.out.println("Imagem não encontrada!");
        }
        else
        gc.drawImage(icon, 0, 0, getWidth(), getHeight(), this);
    }
}
