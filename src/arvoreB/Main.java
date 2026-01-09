package arvoreB;

public class Main {
    public static void main(String[] args) {
        ArvoreB arvore = new ArvoreB();

        int[] valores = {40, 30, 80, 100, 90, 81, 82, 83, 10, 110};

        for (int valor : valores) {
            System.out.println("Inserindo: " + valor);
            arvore.inserir(valor);
            arvore.imprimir();
        }

        System.out.println("\n--- RESULTADO FINAL ---");
        arvore.imprimir();

        System.out.println("\n TESTES DE BUSCA");
        System.out.println("Buscar '10': " + arvore.buscar(10));
        System.out.println("Buscar '12': " + arvore.buscar(12));

        System.out.println("\n--- Árvore Original ---");
        arvore.imprimir();

        System.out.println("\n--- Removendo 110 (Simples) ---");
        arvore.remover(110);
        arvore.imprimir();

        System.out.println("\n--- Removendo 82 (Simples) ---");
        arvore.remover(82);
        arvore.imprimir();

        System.out.println("\n--- Removendo 83 (Causa Fusão/Merge) ---");
        arvore.remover(83);
        arvore.imprimir();
    }
}