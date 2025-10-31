package tabelaHashLinear;

public class Main {

    public static void main(String[] args) {
        testarAdicaoSimples();
        testarColisoes();
        testarRedimensionamento();
        testarRemocao();
    }

    /**
     * Testa a inserção básica de elementos sem colisões.
     */
    public static void testarAdicaoSimples() {
        System.out.println("=============================================");
        System.out.println("### 1. TESTE DE ADIÇÃO DE VALORES ###");
        System.out.println("=============================================");

        TabelaHash tabelaSimples = new TabelaHash(10);

        System.out.println("--- Inserindo 4 alunos em posições diferentes ---");
        tabelaSimples.inserir(101, "Ana");
        tabelaSimples.inserir(205, "Carlos");
        tabelaSimples.inserir(303, "Sofia");
        tabelaSimples.inserir(408, "Daniel");
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
        System.out.println("### 2. TESTE DE COLISÃO (SONDAGEM LINEAR) ###");
        System.out.println("=============================================");

        TabelaHash tabelaComColisao = new TabelaHash(5);

        System.out.println("--- Inserindo chaves que colidem no índice 0 (10, 15, 25) ---");

        tabelaComColisao.inserir(10, "Heitor");
        tabelaComColisao.inserir(15, "Laura");
        tabelaComColisao.inserir(25, "Miguel");
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
        System.out.println("### 3. TESTE DE REDIMENSIONAMENTO (RESIZING) ###");
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

    /**
     * Testa a remoção de elementos, especialmente em cenários de colisão.
     */
    public static void testarRemocao() {
        System.out.println("\n\n=============================================");
        System.out.println("### 4. TESTE DE REMOÇÃO ###");
        System.out.println("=============================================");

        // Cria uma tabela e força uma colisão para o teste mais importante.
        TabelaHash tabelaParaRemover = new TabelaHash(5);
        tabelaParaRemover.inserir(10, "Heitor"); // Posição 0
        tabelaParaRemover.inserir(15, "Laura");  // Posição 1 (após colisão)
        tabelaParaRemover.inserir(20, "Miguel"); // Posição 2 (após colisão)
        System.out.println("--- Tabela inicial com uma cadeia de colisão ---");
        tabelaParaRemover.imprimirTabela();

        System.out.println("\n--- Removendo o elemento do MEIO da cadeia (matrícula 15) ---");
        tabelaParaRemover.removerValor(15);
        System.out.println("A posição [1] agora deve estar marcada como [DELETED].");
        tabelaParaRemover.imprimirTabela();

        System.out.println("\n--- Verificando se a busca pelo último elemento (20) ainda funciona ---");
        System.out.println("A busca deve 'pular' a marca [DELETED] para encontrar o valor.");
        System.out.printf("Buscando matrícula 20: %s \n", tabelaParaRemover.pegarValor(20));
        System.out.printf("Buscando matrícula 15 (removida): %s \n", tabelaParaRemover.pegarValor(15));

        System.out.println("\n--- Inserindo um novo elemento que deve ocupar o espaço [DELETED] ---");
        tabelaParaRemover.inserir(5, "Valentina"); // 5%5=0, colide com 10, e deve ocupar a posição 1.
        tabelaParaRemover.imprimirTabela();
    }
}