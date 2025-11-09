package arvoreBinaria;

public class Main {
    public static void main(String[] args) {
        Arvore arvore = new Arvore();
        arvore.inserir(50);
        arvore.inserir(30);
        arvore.inserir(70);
        arvore.inserir(20);
        arvore.inserir(40);
        arvore.inserir(60);
        arvore.inserir(80);

        // Exibe a estrutura da árvore primeiro
        arvore.exibirEstrutura();

        System.out.println("\n--- Testes de Busca ---"); // Adiciona um separador para clareza

        // Concatena a string descritiva com o resultado da busca
        System.out.println("Buscando valor 50: " + arvore.buscar(50));
        System.out.println("Buscando valor 30: " + arvore.buscar(30));
        System.out.println("Buscando valor 70: " + arvore.buscar(70));
        System.out.println("Buscando valor 20: " + arvore.buscar(20));
        System.out.println("Buscando valor 40: " + arvore.buscar(40));
        System.out.println("Buscando valor 60: " + arvore.buscar(60));
        System.out.println("Buscando valor 80: " + arvore.buscar(80));

        // Teste com um valor que não existe na árvore
        System.out.println("Buscando valor 99: " + arvore.buscar(99));
    }
}