package arvoreBinaria;

public class Main {

    public static void main(String[] args) {
        testesDeInsercaoEBusca();

        testesDeRemocao();
    }

    /**
     * SUÍTE DE TESTES para os métodos inserir() e buscar().
     */
    public static void testesDeInsercaoEBusca() {
        System.out.println("--- INICIANDO TESTES DE INSERÇÃO E BUSCA ---");

        // 1. Setup: Criação e população da árvore
        Arvore arvore = new Arvore();
        arvore.inserir(50);
        arvore.inserir(30);
        arvore.inserir(70);
        arvore.inserir(20);
        arvore.inserir(40);
        arvore.inserir(60);
        arvore.inserir(80);

        System.out.println("\nÁrvore construída para os testes:");
        arvore.exibirEstrutura();

        System.out.println("\n--- Executando buscas ---");

        // 2. Testes de verificação
        System.out.println("Buscando valor 50 (raiz): " + arvore.buscar(50));
        System.out.println("Buscando valor 80 (nó folha): " + arvore.buscar(80));
        System.out.println("Buscando valor 99 (não existe): " + arvore.buscar(99));

        System.out.println("\n--- TESTES DE INSERÇÃO E BUSCA CONCLUÍDOS ---");
    }

    /**
     * SUÍTE DE TESTES para o método remover().
     */
    public static void testesDeRemocao() {
        System.out.println("\n\n--- INICIANDO TESTES DE REMOÇÃO ---");

        // --- TESTE 1: Removendo um nó folha (sem filhos) ---
        System.out.println("\n--- CASO 1: Removendo um nó folha (20) ---");
        Arvore arvoreCaso1 = new Arvore();
        arvoreCaso1.inserir(50);
        arvoreCaso1.inserir(30);
        arvoreCaso1.inserir(20);
        System.out.println("Árvore ANTES de remover o 20:");
        arvoreCaso1.exibirEstrutura();
        arvoreCaso1.remover(20);
        System.out.println("Árvore DEPOIS de remover o 20:");
        arvoreCaso1.exibirEstrutura();
        System.out.println("Verificação: buscando o nó removido (20): " + arvoreCaso1.buscar(20)); // Esperado: false

        // --- TESTE 2: Removendo um nó com um único filho ---
        System.out.println("\n--- CASO 2: Removendo um nó com um filho (80) ---");
        Arvore arvoreCaso2 = new Arvore();
        arvoreCaso2.inserir(50);
        arvoreCaso2.inserir(70);
        arvoreCaso2.inserir(80);
        arvoreCaso2.inserir(75); // 75 é o único filho de 80
        System.out.println("Árvore ANTES de remover o 80:");
        arvoreCaso2.exibirEstrutura();
        arvoreCaso2.remover(80);
        System.out.println("Árvore DEPOIS de remover o 80 (75 deve tomar seu lugar):");
        arvoreCaso2.exibirEstrutura();
        System.out.println("Verificação: buscando o nó removido (80): " + arvoreCaso2.buscar(80)); // Esperado: false
        System.out.println("Verificação: buscando o nó substituto (75): " + arvoreCaso2.buscar(75)); // Esperado: true

        // --- TESTE 3: Removendo um nó com dois filhos ---
        System.out.println("\n--- CASO 3: Removendo um nó com dois filhos (30) ---");
        Arvore arvoreCaso3 = new Arvore();
        arvoreCaso3.inserir(50);
        arvoreCaso3.inserir(30);
        arvoreCaso3.inserir(20);
        arvoreCaso3.inserir(40);
        arvoreCaso3.inserir(35); // Sucessor de 30 será 35
        System.out.println("Árvore ANTES de remover o 30:");
        arvoreCaso3.exibirEstrutura();
        arvoreCaso3.remover(30);
        System.out.println("Árvore DEPOIS de remover o 30 (35 deve tomar seu lugar):");
        arvoreCaso3.exibirEstrutura();
        System.out.println("Verificação: buscando o nó removido (30): " + arvoreCaso3.buscar(30)); // Esperado: false
        System.out.println("Verificação: buscando o nó sucessor (35): " + arvoreCaso3.buscar(35)); // Esperado: true

        // --- TESTE 4: Removendo a RAIZ com dois filhos ---
        System.out.println("\n--- CASO 4: Removendo a raiz (50) ---");
        Arvore arvoreCaso4 = new Arvore();
        arvoreCaso4.inserir(50);
        arvoreCaso4.inserir(30);
        arvoreCaso4.inserir(70);
        arvoreCaso4.inserir(60); // Sucessor da raiz 50 será 60
        System.out.println("Árvore ANTES de remover a raiz 50:");
        arvoreCaso4.exibirEstrutura();
        arvoreCaso4.remover(50);
        System.out.println("Árvore DEPOIS de remover a raiz 50 (60 deve ser a nova raiz):");
        arvoreCaso4.exibirEstrutura();
        System.out.println("Verificação: buscando o nó removido (50): " + arvoreCaso4.buscar(50)); // Esperado: false
        System.out.println("Verificação: buscando a nova raiz (60): " + arvoreCaso4.buscar(60)); // Esperado: true

        System.out.println("\n--- TESTES DE REMOÇÃO CONCLUÍDOS ---");
    }
}