package listaEncadeada;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- INICIANDO TESTE DE EXECUÇÃO DA LISTA ENCADEADA ---");
        ListaSEncadeada lista = new ListaSEncadeada();

        // 1. Testando o estado inicial da lista
        System.out.println("\n1. Teste de Lista Vazia:");
        System.out.println("A lista está vazia? " + lista.isVazia());
        System.out.println("Tamanho da lista: " + lista.getTamanho());
        System.out.println("Conteúdo da lista: " + lista);

        // 2. Adicionando elementos no final da lista
        System.out.println("\n2. Adicionando elementos (10, 20, 30) no final:");
        lista.adiciona(10);
        lista.adiciona(20);
        lista.adiciona(30);
        System.out.println("A lista está vazia? " + lista.isVazia());
        System.out.println("Tamanho da lista: " + lista.getTamanho());
        System.out.println("Conteúdo da lista: " + lista);

        // 3. Adicionando elementos em posições específicas
        System.out.println("\n3. Adicionando elementos em posições específicas:");
        System.out.println("Adicionando o valor 5 na posição 1...");
        lista.adiciona(1, 5); // Adiciona no início
        System.out.println("Conteúdo da lista: " + lista);
        System.out.println("Tamanho da lista: " + lista.getTamanho());

        System.out.println("\nAdicionando o valor 25 na posição 4...");
        lista.adiciona(4, 25); // Adiciona no meio
        System.out.println("Conteúdo da lista: " + lista);
        System.out.println("Tamanho da lista: " + lista.getTamanho());

        System.out.println("\nAdicionando o valor 40 na posição 6 (final)...");
        lista.adiciona(6, 40); // Adiciona no final
        System.out.println("Conteúdo da lista: " + lista);
        System.out.println("Tamanho da lista: " + lista.getTamanho());

        // 4. Acessando um elemento com get()
        System.out.println("\n4. Acessando elementos:");
        Nodo no = lista.get(3);
        System.out.println("Valor na posição 3: " + (no != null ? no.getValor() : "não encontrado"));

        Nodo noInvalido = lista.get(10);
        System.out.println("Valor na posição 10: " + (noInvalido != null ? noInvalido.getValor() : "não encontrado (correto)"));

        // 5. Removendo elementos
        System.out.println("\n5. Removendo elementos:");
        System.out.println("Removendo da posição 1 (valor 5)...");
        lista.remove(1);
        System.out.println("Conteúdo da lista: " + lista);
        System.out.println("Tamanho da lista: " + lista.getTamanho());

        System.out.println("\nRemovendo da posição 5 (último elemento, valor 40)...");
        lista.remove(5);
        System.out.println("Conteúdo da lista: " + lista);
        System.out.println("Tamanho da lista: " + lista.getTamanho());

        System.out.println("\nRemovendo da posição 2 (valor 20)...");
        lista.remove(2);
        System.out.println("Conteúdo da lista: " + lista);
        System.out.println("Tamanho da lista: " + lista.getTamanho());

        // 6. Testando operações inválidas
        System.out.println("\n6. Testando operações inválidas:");
        boolean removeu = lista.remove(5);
        System.out.println("Tentativa de remover da posição 5 (inválida): " + removeu);
        boolean adicionou = lista.adiciona(10, 100);
        System.out.println("Tentativa de adicionar na posição 10 (inválida): " + adicionou);
        System.out.println("Conteúdo final da lista: " + lista);
        System.out.println("Tamanho final da lista: " + lista.getTamanho());

        // 7. Esvaziando a lista
        System.out.println("\n7. Esvaziando a lista completamente:");
        lista.remove(1);
        lista.remove(1);
        lista.remove(1);
        System.out.println("A lista está vazia? " + lista.isVazia());
        System.out.println("Tamanho da lista: " + lista.getTamanho());
        System.out.println("Conteúdo da lista: " + lista);

        System.out.println("\n--- FIM DO TESTE DE EXECUÇÃO ---");
    }
}
