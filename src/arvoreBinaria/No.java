package arvoreBinaria;

public class No {
    private int valor;
    private No esquerda;
    private No direita;

    public No(int valor) {
        this.valor = valor;
        this.esquerda = null;
        this.direita = null;
    }

    public No getEsquerda() {
        return esquerda;
    }

    public void setEsquerda(No esquerda) {
        this.esquerda = esquerda;
    }

    public No getDireita() {
        return direita;
    }

    public void setDireita(No direita) {
        this.direita = direita;
    }

    public int getValor() {
        return valor;
    }
}
