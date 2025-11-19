package arvoreBinaria;

public class Travessia {
    public static void main(String[] args) {
        Arvore arvore = new Arvore();
        arvore.inserir(50);
        arvore.inserir(30);
        arvore.inserir(70);
        arvore.inserir(20);
        arvore.inserir(40);
        arvore.inserir(60);
        arvore.inserir(80);

        arvore.exibirEstrutura();

        // Chamada para a nova travessia
        System.out.print("Pré-Ordem: ");
        arvore.travessiaPreOrdem();

        System.out.print("Em-Ordem: ");
        arvore.travessiaEmOrdem();

        System.out.print("Pos-Ordem: ");
        arvore.travessiaPosOrdem();

    }
}
