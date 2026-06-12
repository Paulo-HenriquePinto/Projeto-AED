public class Main {
    public static void main(String[] args) {
        Central app = new Central();
        
        // Povoando a biblioteca com 10 livros de teste
        for (int i = 1; i <= 10; i++) {
            Livro l = new Livro(
                "Livro " + i, 
                "Genero " + i, 
                "Autor " + i, 
                "2026", 
                "img\\brasao.png", 
                "Descrição detalhada do livro número " + i, 
                5 // Quantidade padrão
            );
            app.biblio.add(l);
        }

        app.VerTelaPrimaria(true);
    }
}