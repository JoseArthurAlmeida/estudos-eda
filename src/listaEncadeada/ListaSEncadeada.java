package listaEncadeada;

public class ListaSEncadeada {
    private Nodo primeiro;
    private int tamanho;

    public ListaSEncadeada() {
        this.primeiro = null;
        this.tamanho = 0;
    }

    public void adiciona(int valor) {
        Nodo novoNodo = new Nodo(valor);

        if (this.primeiro == null) {
            this.primeiro = novoNodo;
        } else {
            Nodo ultimoNodo = this.primeiro;

            while (ultimoNodo.getProx() != null) {
                ultimoNodo = ultimoNodo.getProx();
            }

            ultimoNodo.setProx(novoNodo);
        }
        this.tamanho++;
    }

    public int getTamanho() {
        return this.tamanho;
    }

    public boolean isVazia() {
        return this.primeiro == null;
    }

    public Nodo get(int posicao) {
        if (this.isVazia() || posicao < 1 || posicao > this.tamanho) {
            return null;
        } else {

            Nodo nodoAtual = this.primeiro;
            for (int i = 1; i < posicao; i++) {
                nodoAtual = nodoAtual.getProx();
            }

            return nodoAtual;
        }
    }

    public boolean adiciona(int posicao, int valor) {
        if (posicao < 1 || posicao > this.tamanho + 1) {
            return false;
        }
        Nodo novoNo = new Nodo(valor);
        if (posicao == 1) {
            novoNo.setProx(this.primeiro);
            this.primeiro = novoNo;
        } else {
            Nodo nodoAnterior = this.primeiro;
            // Navega até o nodo anterior à posição de inserção
            for (int i = 1; i < posicao - 1; i++) {
                nodoAnterior = nodoAnterior.getProx();
            }
            novoNo.setProx(nodoAnterior.getProx());
            nodoAnterior.setProx(novoNo);
        }
        this.tamanho++;
        return true;
    }

    public boolean remove(int posicao) {
        if (posicao < 1 || posicao > this.tamanho) {
            return false;
        }

        if (posicao == 1) {
            this.primeiro = this.primeiro.getProx();
        } else {
            Nodo nodoAnterior = this.primeiro;

            // muda até o anterior
            for (int i = 2; i < posicao; i++) {
                nodoAnterior = nodoAnterior.getProx();
            }
//          faz com que o anterior aponte para o proximo do proximo
            Nodo sucessorDoAtual = nodoAnterior.getProx();
            nodoAnterior.setProx(sucessorDoAtual.getProx());
        }
        this.tamanho--;
        return true;
    }

    @Override
    public String toString() {
        if (this.isVazia()) {
            return "ListaSEncadeada{}";
        }
        StringBuilder sb = new StringBuilder("ListaSEncadeada{primeiro=");
        Nodo atual = this.primeiro;
        while (atual != null) {
            sb.append(atual.getValor()).append(" -> ");
            atual = atual.getProx();
        }
        sb.append("null}");
        return sb.toString();
    }
}