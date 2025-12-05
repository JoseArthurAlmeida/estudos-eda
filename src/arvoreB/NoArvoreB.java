package arvoreB;

public class NoArvoreB {
    // Para Ordem 4: Max Chaves(elementos inseridos dentro do nó) = 3, Max Filhos = 4
    int[] chaves = new int[3];
    NoArvoreB[] filhos = new NoArvoreB[4];

    int numChaves = 0;  // Quantidade atual de chaves no nó
    boolean folha;      // true se for folha, false se tiver filhos

    public NoArvoreB(boolean folha) {
        this.folha = folha;
    }
}