package arvoreB;

public class Main {
    public static void main(String[] args) {
        ArvoreB arvore = new ArvoreB();

        int[] valores = {5, 10, 15, 20, 25, 30, 35, 40, 45, 50};

        for (int valor : valores) {
            System.out.println("Inserindo: " + valor);
            arvore.inserir(valor);
            arvore.imprimir();
        }

        System.out.println("\n--- RESULTADO FINAL ---");
        arvore.imprimir();
    }
}