package listaEncadeada;

public class Nodo {
    private int valor;
    private Nodo prox;

    public Nodo(int valor) {
        this.valor = valor;
        this.prox = null;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public Nodo getProx() {
        return prox;
    }

    public void setProx(Nodo prox) {
        this.prox = prox;
    }

    @Override
    public String toString() {
        return "Nodo{" + "valor=" + valor + '}';
    }
}