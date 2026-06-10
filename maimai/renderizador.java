import javax.swing.*;
import java.awt.*;

public class renderizador extends JPanel{
    private Image icon;
    @Override
    public void setLayout(LayoutManager mgr) {
        // TODO Auto-generated method stub
        super.setLayout(null);
    }
    public  renderizador(String path){
        ImageIcon icone = new ImageIcon();
        icone = new ImageIcon(path);
        icon = icone.getImage();
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D gc = (Graphics2D) g;

        gc.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        if(icon == null){
            System.out.println("Imagem não encontrada!");
        }
        else
        gc.drawImage(icon, 0, 0, getWidth(), getHeight(), this);
    }
}
