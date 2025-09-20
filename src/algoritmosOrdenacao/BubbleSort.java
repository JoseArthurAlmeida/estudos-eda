package algoritmosOrdenacao;

import java.util.ArrayList;
import java.util.List;

public class BubbleSort {
    public static void main(String[] args) {
        List<Integer> lista = new ArrayList<>(List.of(100, 90, 101, 10, 1, -999));

        System.out.println(bubbleSort(lista));
    }

    public static List<Integer> bubbleSort(List<Integer> lista) {
        for (int i = 0; i < lista.size(); i++) {
            for (int j = lista.size() - 1; j > i; j--) {
                if (lista.get(j - 1) > lista.get(j)) {
                    int aux = lista.get(j - 1);
                    lista.set(j - 1, lista.get(j));
                    lista.set(j , aux);
                }
            }
        }
        return lista;
    }
}
