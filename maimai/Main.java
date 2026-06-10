public class Main {
    public static void main(String[] args) {
        // 1. Cria a Central (que instancia todas as telas automaticamente pelo construtor)
        Central app = new Central();
        app.VerTelaPrimaria(true); // Faz o setVisible(true)
    }
}