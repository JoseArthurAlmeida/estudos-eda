package algoritmosOrdenacao;

import java.util.ArrayList;
import java.util.List;

public class InsertionSort {

    public static void main(String[] args) {
        List<Integer> lista = new ArrayList<>(List.of(100, 90, 101, 10, 1, -999));

        System.out.println(insertionSort(lista));
    }

    public static List<Integer> insertionSort(List<Integer> lista) {

        // O loop externo percorre a lista a partir do segundo elemento (índice 1).
        // O primeiro elemento (índice 0) é considerado a "parte ordenada" inicial.
        for (int j = 1; j < lista.size(); j++) {

            // 'chave' é o elemento atual que será inserido na parte ordenada da lista.
            int chave = lista.get(j);

            // 'i' é o índice do último elemento da parte já ordenada.
            int i = j - 1;

            // O loop interno move os elementos da parte ordenada que são maiores que a 'chave'
            // uma posição para a direita, para abrir espaço para a inserção da 'chave'.
            while (i > -1 && lista.get(i) > chave) {
                // Desloca o elemento maior (lista.get(i)) uma posição para a direita.
                lista.set(i + 1, lista.get(i));
                i--;
            }

            // Após o loop 'while', 'i + 1' é a posição correta para inserir a 'chave'.
            // Isso acontece porque o loop para quando encontramos um elemento menor ou igual à chave, ou quando chegamos ao início da lista.
            lista.set(i + 1, chave);
        }

        return lista;
    }


}
