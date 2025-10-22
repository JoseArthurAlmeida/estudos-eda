package tabelaHash;

public class Main {
    public static void main(String[] args) {
        TabelaHash tabelaHashAlunos = new TabelaHash(10);

        tabelaHashAlunos.inserir(101, "Ana");
        tabelaHashAlunos.inserir(205, "Bonner");
        tabelaHashAlunos.inserir(301, "Carla");
        tabelaHashAlunos.inserir(401, "Fulano");

        System.out.printf("Pegando o valor de 401: %s \n", tabelaHashAlunos.pegarValor(401));

        tabelaHashAlunos.imprimirTabela();
        tabelaHashAlunos.removerValor(401);

        System.out.println("Tabela após remoção");
        tabelaHashAlunos.imprimirTabela();
    }
}
