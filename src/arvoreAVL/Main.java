package arvoreAVL;

public class Main {

    public static void main(String[] args) {
        testesDeInsercao();
    }

    /**
     * SUÍTE DE TESTES para os métodos inserir() e buscar().
     */
    public static void testesDeInsercao() {
        System.out.println("--- INICIANDO TESTES DE INSERÇÃO---");

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
    }


}