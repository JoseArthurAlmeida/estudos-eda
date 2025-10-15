package listaDuplamenteEncadeadaCircular;

public class NodoDEC {
    private int valor;
    private NodoDEC proximo;
    private NodoDEC anterior;

    public NodoDEC(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public NodoDEC getProximo() {
        return proximo;
    }

    public void setProximo(NodoDEC proximo) {
        this.proximo = proximo;
    }

    public NodoDEC getAnterior() {
        return anterior;
    }

    public void setAnterior(NodoDEC anterior) {
        this.anterior = anterior;
    }

    @Override
    public String toString() {
        return "Nodo{" + "valor=" + valor + '}';
    }
}
