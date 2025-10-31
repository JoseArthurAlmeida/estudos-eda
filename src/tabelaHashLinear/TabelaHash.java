package tabelaHashLinear;

public class TabelaHash {
    private static final double FATOR_DE_CARGA_LIMITE = 0.75;
    private final Aluno ALUNO_DELETADO = new Aluno(-1, "DELETED");
    private int TAMANHO;
    private Aluno[] tabela;
    private int quantidade;

    public TabelaHash(int tamanhoInicial) {
        this.TAMANHO = Math.max(tamanhoInicial, 1);
        this.tabela = new Aluno[this.TAMANHO];
        this.quantidade = 0;
    }

    private int funcaoHash(int matricula) {
        return Math.abs(matricula % TAMANHO);
    }

    public void inserir(int matricula, String nome) {
        if ((double) quantidade / TAMANHO >= FATOR_DE_CARGA_LIMITE) {
            redimensionar();
        }

        int indice = funcaoHash(matricula);

        // Procura o indice correto: um espaço vazio/deletado ou a própria chave para atualização.
        while (tabela[indice] != null && tabela[indice] != ALUNO_DELETADO && tabela[indice].getMatricula() != matricula) {
            indice = (indice + 1) % TAMANHO;
        }

        // Se a posição era nula ou deletada, significa que é um novo elemento.
        if (tabela[indice] == null || tabela[indice] == ALUNO_DELETADO) {
            quantidade++;
        }

        // Insere o novo aluno ou atualiza o nome do existente.
        tabela[indice] = new Aluno(matricula, nome);
    }

    private void redimensionar() {
        System.out.println("!!! Fator de carga atingido. Redimensionando a tabela !!!");

        Aluno[] tabelaAntiga = this.tabela;
        int TAMANHO_ANTIGO = this.TAMANHO;

        this.TAMANHO *= 2;
        this.tabela = new Aluno[this.TAMANHO];
        this.quantidade = 0;

        for (int i = 0; i < TAMANHO_ANTIGO; i++) {
            if (tabelaAntiga[i] != null && tabelaAntiga[i] != ALUNO_DELETADO) {
                inserir(tabelaAntiga[i].getMatricula(), tabelaAntiga[i].getNome());
            }
        }
    }

    public String pegarValor(int matricula) {
        int indiceInicial = funcaoHash(matricula);
        int indiceAtual = indiceInicial;

        do {
            // Se encontrarmos um espaço nulo, o elemento não está na tabela.
            if (tabela[indiceAtual] == null) {
                return null;
            }

            // Se encontrarmos o aluno, retornamos seu nome.
            if (tabela[indiceAtual] != ALUNO_DELETADO && tabela[indiceAtual].getMatricula() == matricula) {
                return tabela[indiceAtual].getNome();
            }

            // Move para a próxima posição.
            indiceAtual = (indiceAtual + 1) % TAMANHO;

        } while (indiceAtual != indiceInicial); // Continua até dar a volta completa na tabela.

        // Se o loop terminar, o elemento não foi encontrado.
        return null;
    }

    public boolean removerValor(int matricula) {
        int indiceInicial = funcaoHash(matricula);
        int indiceAtual = indiceInicial;

        do {
            // Se encontrarmos um espaço nulo, não há o que remover.
            if (tabela[indiceAtual] == null) {
                return false;
            }

            // Se encontrarmos o aluno, o marcamos como deletado.
            if (tabela[indiceAtual] != ALUNO_DELETADO && tabela[indiceAtual].getMatricula() == matricula) {
                tabela[indiceAtual] = ALUNO_DELETADO;
                quantidade--;
                return true;
            }

            indiceAtual = (indiceAtual + 1) % TAMANHO;

        } while (indiceAtual != indiceInicial);

        return false;
    }

    public void imprimirTabela() {
        System.out.println("\n--- Tabela Hash (Tamanho: " + TAMANHO + ", Ocupação: " + quantidade + ") ---");
        for (int i = 0; i < TAMANHO; i++) {
            System.out.print("[" + i + "]: ");
            if (tabela[i] == null) {
                System.out.println("null");
            } else if (tabela[i] == ALUNO_DELETADO) {
                System.out.println("[DELETED]");
            } else {
                System.out.println(tabela[i]);
            }
        }
        System.out.println("------------------------------------");
    }
}