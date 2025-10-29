package tabelaHashLinear;

public class Main {

    public static void main(String[] args) {
        testarAdicaoSimples();
        testarColisoes();
        testarRedimensionamento();
    }

    /**
     * Testa a inserção básica de elementos sem colisões.
     */
    public static void testarAdicaoSimples() {
        System.out.println("=============================================");
        System.out.println("### TESTE DE ADIÇÃO SIMPLES DE VALORES ###");
        System.out.println("=============================================");

        TabelaHash tabelaSimples = new TabelaHash(10);

        System.out.println("--- Inserindo 4 alunos em posições diferentes ---");
        tabelaSimples.inserir(101, "Ana");      // Deve ir para o índice 1
        tabelaSimples.inserir(205, "Carlos");   // Deve ir para o índice 5
        tabelaSimples.inserir(303, "Sofia");    // Deve ir para o índice 3
        tabelaSimples.inserir(408, "Daniel");   // Deve ir para o índice 8
        tabelaSimples.imprimirTabela();

        System.out.println("\n--- Verificando se os valores foram inseridos corretamente ---");
        System.out.printf("Buscando valor da matrícula 303: %s \n", tabelaSimples.pegarValor(303));
        System.out.printf("Buscando valor da matrícula 999 (não existe): %s \n", tabelaSimples.pegarValor(999));
    }

    /**
     * Testa o mecanismo de sondagem linear forçando colisões.
     */
    public static void testarColisoes() {
        System.out.println("\n\n=============================================");
        System.out.println("### TESTE DE COLISÃO (SONDAGEM LINEAR) ###");
        System.out.println("=============================================");

        TabelaHash tabelaComColisao = new TabelaHash(5);

        System.out.println("--- Inserindo chaves que colidem no índice 0 (10, 15, 25) ---");

        System.out.println("\nInserindo matrícula 10...");
        tabelaComColisao.inserir(10, "Heitor"); // Posição original: índice 0.
        tabelaComColisao.imprimirTabela();

        System.out.println("\nInserindo matrícula 15 (colide com 10)...");
        tabelaComColisao.inserir(15, "Laura");  // Sondagem para o índice 1.
        tabelaComColisao.imprimirTabela();

        System.out.println("\nInserindo matrícula 25 (colide com 10 e 15)...");
        tabelaComColisao.inserir(25, "Miguel");// Sondagem para o índice 2.
        tabelaComColisao.imprimirTabela();

        System.out.println("\n--- Verificando se todos os valores podem ser encontrados ---");
        System.out.printf("Buscando matrícula 10 (posição original): %s \n", tabelaComColisao.pegarValor(10));
        System.out.printf("Buscando matrícula 15 (deslocado): %s \n", tabelaComColisao.pegarValor(15));
        System.out.printf("Buscando matrícula 25 (mais deslocado): %s \n", tabelaComColisao.pegarValor(25));
    }

    /**
     * Testa o redimensionamento automático da tabela ao atingir o fator de carga.
     */
    public static void testarRedimensionamento() {
        System.out.println("\n\n=============================================");
        System.out.println("### TESTE DE REDIMENSIONAMENTO (RESIZING) ###");
        System.out.println("=============================================");

        TabelaHash tabelaParaRedimensionar = new TabelaHash(4);

        System.out.println("--- Inserindo 3 elementos (ocupação: 3 de 4 = 75%) ---");
        tabelaParaRedimensionar.inserir(1, "Pedro");
        tabelaParaRedimensionar.inserir(2, "Livia");
        tabelaParaRedimensionar.inserir(5, "Gael");
        tabelaParaRedimensionar.imprimirTabela();

        System.out.println("\n--- Inserindo o 4º elemento para DISPARAR o redimensionamento ---");
        tabelaParaRedimensionar.inserir(4, "Beatriz");

        System.out.println("\n--- Tabela após o redimensionamento ---");
        tabelaParaRedimensionar.imprimirTabela();

        System.out.println("\n--- Verificando se todos os valores podem ser encontrados ---");
        System.out.printf("Buscando matrícula 1: %s \n", tabelaParaRedimensionar.pegarValor(1));
        System.out.printf("Buscando matrícula 2: %s \n", tabelaParaRedimensionar.pegarValor(2));
        System.out.printf("Buscando matrícula 5: %s \n", tabelaParaRedimensionar.pegarValor(5));
        System.out.printf("Buscando matrícula 4: %s \n", tabelaParaRedimensionar.pegarValor(4));
    }
}