package listaDuplamenteEncadeadaCircular;

public class TesteLista {

    public static void main(String[] args) {
        System.out.println("--- INICIANDO SUÍTE DE TESTES ---");

        testeAdicionaFinal();
        testeAdicionaPorPosicao();
        testeGet();
        testeRemove();
        testeCasosExtremos(); // Testes para casos de borda

        System.out.println("\n--- SUÍTE DE TESTES FINALIZADA ---");
    }

    private static void printResultado(String nomeTeste, boolean sucesso) {
        if (sucesso) {
            System.out.println("[SUCESSO] " + nomeTeste);
        } else {
            System.out.println("[FALHA]   " + nomeTeste);
        }
    }

    public static void testeAdicionaFinal() {
        System.out.println("\n--- Testando adiciona(valor) ---");
        ListaDuplamenteEncadeadaCircular lista = new ListaDuplamenteEncadeadaCircular();

        lista.adiciona(10);
        lista.adiciona(20);
        lista.adiciona(30);

        boolean sucesso = lista.getTamanho() == 3 && lista.toString().contains("10 <-> 20 <-> 30");
        printResultado("Adicionar 3 elementos no final", sucesso);
        System.out.println("Estado da lista: " + lista);
    }

    public static void testeAdicionaPorPosicao() {
        System.out.println("\n--- Testando adiciona(posicao, valor) ---");
        ListaDuplamenteEncadeadaCircular lista = new ListaDuplamenteEncadeadaCircular();

        // 1. Adicionar no início de uma lista vazia
        lista.adiciona(1, 10);
        boolean sucesso1 = lista.get(1).getValor() == 10 && lista.getTamanho() == 1;
        printResultado("Adicionar na posição 1 em lista vazia", sucesso1);

        // 2. Adicionar no início de uma lista populada
        lista.adiciona(1, 5); // Lista deve ser: 5, 10
        boolean sucesso2 = lista.get(1).getValor() == 5 && lista.get(2).getValor() == 10;
        printResultado("Adicionar na posição 1 em lista populada", sucesso2);

        // 3. Adicionar no final
        lista.adiciona(3, 30); // Lista deve ser: 5, 10, 30
        boolean sucesso3 = lista.get(3).getValor() == 30 && lista.getTamanho() == 3;
        printResultado("Adicionar na última posição (tamanho + 1)", sucesso3);

        // 4. Adicionar no meio (aqui o bug seria encontrado)
        // CORRIJA O MÉTODO na sua classe antes de rodar para ver o sucesso!
        lista.adiciona(3, 20); // Lista deve ser: 5, 10, 20, 30
        boolean sucesso4 = lista.get(3).getValor() == 20 && lista.get(4).getValor() == 30 && lista.get(2).getProximo().getValor() == 20;
        printResultado("Adicionar no meio (posição 3)", sucesso4);

        // 5. Testar posição inválida
        boolean sucesso5 = !lista.adiciona(0, 99) && !lista.adiciona(lista.getTamanho() + 2, 99);
        printResultado("Adicionar em posição inválida (0 e tamanho + 2)", sucesso5);

        System.out.println("Estado final da lista: " + lista);
    }

    public static void testeGet() {
        System.out.println("\n--- Testando get(posicao) ---");
        ListaDuplamenteEncadeadaCircular lista = new ListaDuplamenteEncadeadaCircular();
        lista.adiciona(100);
        lista.adiciona(200);
        lista.adiciona(300);

        boolean sucesso1 = lista.get(1).getValor() == 100;
        printResultado("Get do primeiro elemento", sucesso1);

        boolean sucesso2 = lista.get(3).getValor() == 300;
        printResultado("Get do último elemento", sucesso2);

        boolean sucesso3 = lista.get(2).getValor() == 200;
        printResultado("Get do elemento do meio", sucesso3);

        boolean sucesso4 = lista.get(0) == null && lista.get(4) == null;
        printResultado("Get de posições inválidas", sucesso4);
    }

    public static void testeRemove() {
        System.out.println("\n--- Testando remove(posicao) ---");
        ListaDuplamenteEncadeadaCircular lista = new ListaDuplamenteEncadeadaCircular();
        lista.adiciona(10);
        lista.adiciona(20);
        lista.adiciona(30);
        lista.adiciona(40);

        // 1. Remover do meio
        lista.remove(3); // Remove o 30. Lista: 10, 20, 40
        boolean sucesso1 = lista.getTamanho() == 3 && lista.get(3).getValor() == 40;
        printResultado("Remover do meio (posição 3)", sucesso1);

        // 2. Remover do início
        lista.remove(1); // Remove o 10. Lista: 20, 40
        boolean sucesso2 = lista.getTamanho() == 2 && lista.get(1).getValor() == 20;
        printResultado("Remover do início (posição 1)", sucesso2);

        // 3. Remover do fim
        lista.adiciona(50); // Lista: 20, 40, 50
        lista.remove(3); // Remove o 50. Lista: 20, 40
        boolean sucesso3 = lista.getTamanho() == 2 && lista.get(2).getValor() == 40 && lista.get(1).getProximo() == lista.get(2);
        printResultado("Remover do fim (posição tamanho)", sucesso3);

        System.out.println("Estado final da lista: " + lista);
    }

    public static void testeCasosExtremos() {
        System.out.println("\n--- Testando Casos Extremos ---");
        ListaDuplamenteEncadeadaCircular lista = new ListaDuplamenteEncadeadaCircular();

        // 1. Remover o único elemento
        lista.adiciona(100);
        lista.remove(1);
        boolean sucesso1 = lista.getTamanho() == 0 && lista.toString().contains("Vazia");
        printResultado("Remover o único elemento da lista", sucesso1);

        // 2. Adicionar após esvaziar a lista
        lista.adiciona(200);
        boolean sucesso2 = lista.getTamanho() == 1 && lista.get(1).getValor() == 200;
        printResultado("Adicionar a uma lista previamente esvaziada", sucesso2);

        System.out.println("Estado final da lista: " + lista);
    }
}
