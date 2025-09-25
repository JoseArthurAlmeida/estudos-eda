package algoritmosOrdenacao;

import java.util.ArrayList;
import java.util.List;

public class MergeSort {
    public static void main(String[] args) {
        List<Integer> lista = new ArrayList<>(List.of(100, 90, 101, 10, 1, -999));

        System.out.println(mergeSort(lista));
    }

    public static List<Integer> mergeSort(List<Integer> lista) {
        if (lista.size() <= 1) {
            return lista;
        }
        List<Integer> listaAux1 = lista.subList(0, lista.size() / 2);
        List<Integer> listaAux2 = lista.subList(lista.size() / 2, lista.size());

        listaAux1 = mergeSort(listaAux1);
        listaAux2 = mergeSort(listaAux2);

        return mesclar(listaAux1, listaAux2);
    }

    private static List<Integer> mesclar(List<Integer> listaEsquerda, List<Integer> listaDireita) {
        List<Integer> listaMerged = new ArrayList<>();

        // "Ponteiros" para percorrer as listas.
        int ponteiroEsquerda = 0;
        int ponteiroDireita = 0;

        while (ponteiroEsquerda < listaEsquerda.size() && ponteiroDireita < listaDireita.size()) {
            if (listaEsquerda.get(ponteiroEsquerda) < listaDireita.get(ponteiroDireita)) {
                listaMerged.add(listaEsquerda.get(ponteiroEsquerda));
                ponteiroEsquerda++; // Avança o ponteiro.
            } else {
                listaMerged.add(listaDireita.get(ponteiroDireita));
                ponteiroDireita++; // Avança o ponteiro.
            }
        }

        // Adiciona os elementos restantes de qualquer uma das listas.
        // Apenas um dos dois loops while abaixo será executado.
        while (ponteiroEsquerda < listaEsquerda.size()) {
            listaMerged.add(listaEsquerda.get(ponteiroEsquerda));
            ponteiroEsquerda++;
        }
        while (ponteiroDireita < listaDireita.size()) {
            listaMerged.add(listaDireita.get(ponteiroDireita));
            ponteiroDireita++;
        }

        return listaMerged;
    }
}