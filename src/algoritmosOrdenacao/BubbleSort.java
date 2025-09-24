package algoritmosOrdenacao;

import java.util.ArrayList;
import java.util.List;

public class BubbleSort {
    public static void main(String[] args) {
        List<Integer> lista = new ArrayList<>(List.of(100, 90, 101, 10, 1, -999));

        System.out.println(bubbleSort(lista));
    }

    public static List<Integer> bubbleSort(List<Integer> lista) {
        // Primeiro loop: Responsável por controlar o número de passos necessários.
        // Em cada passe, o maior elemento "flutua" para sua posição correta no final da parte não ordenada.
        for (int i = 0; i < lista.size(); i++) {
/*
             Segundo loop: Percorre a parte não ordenada da lista para realizar as comparações e trocas.
             O 'j' começa do final da lista e vai diminuindo.
             Ele para quando 'j' for igual a 'i'
             Portanto, o loop interno foca apenas nos elementos da parte desordenada da lista, que vai do índice 0 até 'i - 1'.
*/
            for (int j = lista.size() - 1; j > i; j--) {
                // Compara o elemento atual (j-1) com o seu próximo elemento (j).
                if (lista.get(j - 1) > lista.get(j)) {
                    // Se o elemento anterior for maior que o elemento atual, eles estão na ordem errada.
                    // Realiza a troca dos elementos.
                    int maior = lista.get(j - 1);
                    int menor = lista.get(j);
                    // Define o elemento anterior com o valor do elemento atual.
                    lista.set(j - 1, menor);
                    // Define o elemento atual com o valor armazenado em 'maior' (o valor original do elemento anterior).
                    lista.set(j, maior);
                }
            }
        }
        return lista;
    }
}
