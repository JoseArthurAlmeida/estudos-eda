package recursao;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static int soma(List<Integer> lista){
        if (lista.size() == 1){
            return lista.getFirst();
        }
        else {
            return lista.getFirst() + soma(lista.subList(1, lista.size()));
        }
    }
    public static int multiplicacao(List<Integer> lista){
        if (lista.size() == 1){
            return lista.getFirst();
        }
        else {
            return lista.getFirst() * multiplicacao(lista.subList(1, lista.size()));
        }
    }

    public static void imprimirPares(List<Integer> lista){
        if (lista.isEmpty()){
            return;
        }
        else {
            int elemento = lista.getFirst();
            if (elemento % 2 == 0){
                System.out.println(elemento);
            }
            lista.removeFirst();
            imprimirPares(lista);
        }
    }

    public static int fatorial(int numero){
        if (numero == 1){
            return 1;
        }
        else {
            return numero * fatorial(numero - 1);
        }
    }

    public static void main(String[] args) {

        List<Integer> listaNumeros = new ArrayList<>();
        listaNumeros.add(1);
        listaNumeros.add(2);
        listaNumeros.add(3);
        listaNumeros.add(4);
        listaNumeros.add(5);
        listaNumeros.add(6);

        System.out.println(soma(listaNumeros));
        System.out.println(multiplicacao(listaNumeros));
        imprimirPares(listaNumeros);

        System.out.println(fatorial(5));
    }

}