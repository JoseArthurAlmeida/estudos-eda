package algoritmosOrdenacao;

import java.util.ArrayList;
import java.util.List;

public class SelectionSort {
    public static void main(String[] args) {
        List<Integer> lista = new ArrayList<>(List.of(100, 90, 101, 10, 1, -999));
        System.out.println("Lista NÃO Ordenada: " + lista);
        System.out.println("Lista Ordenada: " + selectionSort(lista));
    }

    public static List<Integer> selectionSort(List<Integer> lista) {
        // O loop externo percorre a lista, definindo qual posição será preenchida.
        for (int i = 0; i < lista.size(); i++) {

            // Assume que o menor elemento está na posição atual (i).
            int minimo = i;

            // Procurar pelo índice do menor
            for (int j = i + 1; j < lista.size(); j++) {
                if (lista.get(j) < lista.get(minimo)) {
                    minimo = j;
                }
            }
            // Após encontrar o menor elemento, troca-o com o elemento da posição i.
            int temp = lista.get(i);
            lista.set(i, lista.get(minimo));
            lista.set(minimo, temp);
        }
        return lista;
    }
}

