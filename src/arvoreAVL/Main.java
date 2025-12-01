package arvoreAVL;

public class Main {

    public static void main(String[] args) {
        testeRotacaoSimplesEsquerda_RR();
        testeRotacaoSimplesDireita_LL();
        testeRotacaoDuplaEsquerdaDireita_LR();
        testeRotacaoDuplaDireitaEsquerda_RL();
    }

    /**
     * CENÁRIO 1: Rotação Simples à Esquerda (Caso RR)
     * Insere valores em ordem crescente. A árvore tende a cair para a direita.
     */
    public static void testeRotacaoSimplesEsquerda_RR() {
        System.out.println("=============================================");
        System.out.println("TESTE 1: Rotação Simples à Esquerda (Caso RR)");
        System.out.println("Inserindo: 10 -> 20 -> 30");
        System.out.println("Expectativa: 20 sobe para a raiz.");
        System.out.println("=============================================");

        ArvoreAVL arvore = new ArvoreAVL();
        arvore.inserir(10);
        arvore.inserir(20);
        arvore.inserir(30);

        arvore.exibirEstrutura();
        System.out.println("\n");
    }

    /**
     * CENÁRIO 2: Rotação Simples à Direita (Caso LL)
     * Insere valores em ordem decrescente. A árvore tende a cair para a esquerda.
     */
    public static void testeRotacaoSimplesDireita_LL() {
        System.out.println("=============================================");
        System.out.println("TESTE 2: Rotação Simples à Direita (Caso LL)");
        System.out.println("Inserindo: 30 -> 20 -> 10");
        System.out.println("Expectativa: 20 sobe para a raiz.");
        System.out.println("=============================================");

        ArvoreAVL arvore = new ArvoreAVL();
        arvore.inserir(30);
        arvore.inserir(20);
        arvore.inserir(10);

        arvore.exibirEstrutura();
        System.out.println("\n");
    }

    /**
     * CENÁRIO 3: Rotação Dupla Esquerda-Direita (Caso LR)
     * Ocorre quando o pai pende para a esquerda, mas o filho pende para a direita.
     * (Formato de "Joelho" para a esquerda)
     */
    public static void testeRotacaoDuplaEsquerdaDireita_LR() {
        System.out.println("=============================================");
        System.out.println("TESTE 3: Rotação Dupla Esq-Dir (Caso LR)");
        System.out.println("Inserindo: 30 (Raiz) -> 10 (Esq) -> 20 (Dir do 10)");
        System.out.println("Expectativa: 20 sobe para a raiz (rot. esq no 10, depois rot. dir no 30).");
        System.out.println("=============================================");

        ArvoreAVL arvore = new ArvoreAVL();
        arvore.inserir(30);
        arvore.inserir(10);

        System.out.println("--- Antes de inserir o 20 (Sem desbalanceamento crítico) ---");
        arvore.exibirEstrutura();

        System.out.println("--- Após inserir o 20 (Deve balancear) ---");
        arvore.inserir(20);
        arvore.exibirEstrutura();
        System.out.println("\n");
    }

    /**
     * CENÁRIO 4: Rotação Dupla Direita-Esquerda (Caso RL)
     * Ocorre quando o pai pende para a direita, mas o filho pende para a esquerda.
     * (Formato de "Joelho" para a direita)
     */
    public static void testeRotacaoDuplaDireitaEsquerda_RL() {
        System.out.println("=============================================");
        System.out.println("TESTE 4: Rotação Dupla Dir-Esq (Caso RL)");
        System.out.println("Inserindo: 10 (Raiz) -> 30 (Dir) -> 20 (Esq do 30)");
        System.out.println("Expectativa: 20 sobe para a raiz (rot. dir no 30, depois rot. esq no 10).");
        System.out.println("=============================================");

        ArvoreAVL arvore = new ArvoreAVL();
        arvore.inserir(10);
        arvore.inserir(30);

        System.out.println("--- Antes de inserir o 20 ---");
        arvore.exibirEstrutura();

        System.out.println("--- Após inserir o 20 (Deve balancear) ---");
        arvore.inserir(20);
        arvore.exibirEstrutura();
        System.out.println("\n");
    }
}