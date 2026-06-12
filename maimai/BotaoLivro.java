import javax.swing.JButton;

public class BotaoLivro extends JButton {
    private Livro livroAssociado;

    public BotaoLivro(Livro livro) {
        super();
        this.livroAssociado = livro;
    }

    public Livro getLivroAssociado() {
        return livroAssociado;
    }
}