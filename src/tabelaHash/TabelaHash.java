package tabelaHash;

import java.util.LinkedList;
import java.util.List;

public class TabelaHash {
    private final int TAMANHO;
    private final List<Aluno>[] tabela;

    @SuppressWarnings("unchecked")
    public TabelaHash(int TAMANHO) {
        this.TAMANHO = TAMANHO;
        this.tabela = new LinkedList[TAMANHO];

        for (int i = 0; i < TAMANHO; i++) {
            tabela[i] = new LinkedList<>();
        }
    }

    private int funcaoHash(int matricula) {
        return matricula % TAMANHO;
    }

    public void inserir(int matricula, String nome) {
        int indice = funcaoHash(matricula);
        List<Aluno> listaInterna = tabela[indice]; // Acessa posição da lista interna

        Aluno novoAluno = new Aluno(matricula, nome);
        listaInterna.add(novoAluno);
    }

    public String pegarValor(int matricula) {
        int indice = funcaoHash(matricula);
        List<Aluno> listaInterna = tabela[indice];

        for (Aluno aluno : listaInterna) {
            if (aluno.getMatricula() == matricula) {
                return aluno.getNome();
            }
        }

        return null;
    }

    public boolean removerValor(int matricula) {
        int indice = funcaoHash(matricula);
        List<Aluno> listaInterna = tabela[indice];

        return listaInterna.removeIf(aluno -> aluno.getMatricula() == matricula);
    }

    public void imprimirTabela() {
        System.out.println("\n--- Tabela Hash ---");
        for (int i = 0; i < TAMANHO; i++) {
            System.out.print("[" + i + "]: ");
            if (tabela[i].isEmpty()) {
                System.out.println("[]");
            } else {
                System.out.println(tabela[i]);
            }
        }
        System.out.println("------------------------------------");
    }
}
