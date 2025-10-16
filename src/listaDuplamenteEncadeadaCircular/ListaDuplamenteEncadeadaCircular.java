package listaDuplamenteEncadeadaCircular;

public class ListaDuplamenteEncadeadaCircular {
    private NodoDEC primeiro;
    private int tamanho;

    public ListaDuplamenteEncadeadaCircular() {
        this.primeiro = null;
        this.tamanho = 0;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void adiciona(int valor) {
        NodoDEC novoNodo = new NodoDEC(valor);

        if (this.primeiro == null) {
            this.primeiro = novoNodo;
            this.primeiro.setAnterior(this.primeiro);
            this.primeiro.setProximo(this.primeiro);
        } else {
            NodoDEC ultimoNodo = this.primeiro.getAnterior();
            novoNodo.setAnterior(ultimoNodo);
            novoNodo.setProximo(this.primeiro);
            ultimoNodo.setProximo(novoNodo);
            this.primeiro.setAnterior(novoNodo);
        }
        this.tamanho++;
    }

    public boolean adiciona(int posicao, int valor) {
        if (posicao < 1 || posicao > this.tamanho + 1) {
            return false;
        }

        NodoDEC novoNodo = new NodoDEC(valor);

        // Quando a lista é vazia
        if (this.primeiro == null) {
            this.primeiro = novoNodo;
            this.primeiro.setAnterior(this.primeiro);
            this.primeiro.setProximo(this.primeiro);
            this.tamanho++;
            return true;
        }

        // primeira posição
        if (posicao == 1) {
            NodoDEC ultimoNodo = this.primeiro.getAnterior();
            novoNodo.setAnterior(ultimoNodo);
            novoNodo.setProximo(this.primeiro);

            this.primeiro.setAnterior(novoNodo);
            ultimoNodo.setProximo(novoNodo);

            this.primeiro = novoNodo;
            this.tamanho++;
            return true;
        }

        // ultima posição
        if (posicao == tamanho + 1) {
            NodoDEC ultimoNodo = this.primeiro.getAnterior();

            novoNodo.setAnterior(ultimoNodo);
            novoNodo.setProximo(this.primeiro);

            ultimoNodo.setProximo(novoNodo);
            this.primeiro.setAnterior(novoNodo);

            this.tamanho++;
            return true;
        }

        // outras posições
        NodoDEC atual = this.primeiro;
        int posicaoTemp = 1;

        while (posicaoTemp < posicao - 1) {
            atual = atual.getProximo();
            posicaoTemp++;
        }

        NodoDEC proximoNodo = atual.getProximo();
        novoNodo.setAnterior(atual);
        novoNodo.setProximo(proximoNodo);

        atual.setProximo(novoNodo);
        proximoNodo.setAnterior(novoNodo);
        this.tamanho++;
        return true;
    }

    public NodoDEC get(int posicao) {
        if (posicao < 1 || posicao > this.tamanho) {
            return null;
        }

        // Se for o primeiro
        if (posicao == 1) {
            return this.primeiro;
        }
        // Se for o ultimo
        if (posicao == this.tamanho) {
            return this.primeiro.getAnterior();
        }

        // Qualquer oytra posição
        NodoDEC atual = this.primeiro;
        int posicaoTemp = 1;
        while (posicaoTemp < posicao) {
            atual = atual.getProximo();
            posicaoTemp++;
        }
        return atual;
    }

    public boolean remove(int posicao) {
        if (posicao < 1 || posicao > this.tamanho || this.primeiro == null) {
            return false;
        }
        // Existe só 1 Nodo
        if (this.tamanho == 1) {
            this.primeiro = null;
            this.tamanho--;
            return true;
        }
        // Se for o primeiro
        if (posicao == 1) {
            NodoDEC ultimo = this.primeiro.getAnterior();
            NodoDEC novoPrimeiro = this.primeiro.getProximo();

            // Atualiza os dois sentidos
            ultimo.setProximo(novoPrimeiro);
            novoPrimeiro.setAnterior(ultimo);

            this.primeiro = novoPrimeiro;
            this.tamanho--;
            return true;
        }

        // Se for o ultimo
        if (posicao == this.tamanho) {
            NodoDEC ultimo = this.primeiro.getAnterior();
            NodoDEC antecessorUltimo = ultimo.getAnterior();

            antecessorUltimo.setProximo(this.primeiro);
            this.primeiro.setAnterior(antecessorUltimo);
            this.tamanho--;
            return true;
        }
        // Qualquer oytra posição
        NodoDEC atual = this.primeiro;
        int posicaoTemp = 1;
        while (posicaoTemp < posicao - 1) {
            atual = atual.getProximo();
            posicaoTemp++;
        }
        NodoDEC sucessorRemovido = atual.getProximo().getProximo();
        atual.setProximo(sucessorRemovido);
        sucessorRemovido.setAnterior(atual);

        this.tamanho--;
        return true;

    }

    @Override
    public String toString() {
        if (this.primeiro == null) {
            return "Lista Vazia";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Tamanho: ").append(this.tamanho).append("\n");
        sb.append("... <-> "); // Indica a circularidade vinda do fim

        NodoDEC atual = this.primeiro;
        for (int i = 0; i < this.tamanho; i++) {
            // Adiciona o valor do nó
            sb.append(atual.getValor());

            // Se não for o último, adiciona o conector
            if (i < this.tamanho - 1) {
                sb.append(" <-> ");
            }

            atual = atual.getProximo();
        }

        sb.append(" <-> ..."); // Indica a circularidade voltando ao início
        return sb.toString();
    }
}
