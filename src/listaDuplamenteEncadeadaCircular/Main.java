package listaDuplamenteEncadeadaCircular;

public class Main {
    public static void main(String[] args) {
        ListaDuplamenteEncadeadaCircular listaDEC = new ListaDuplamenteEncadeadaCircular();
        System.out.println(listaDEC);
        listaDEC.adiciona(0);
        System.out.println(listaDEC);
        listaDEC.adiciona(2);
        System.out.println(listaDEC);
        listaDEC.adiciona(1, 1);
        System.out.println(listaDEC);
        System.out.println(listaDEC.get(1));
        System.out.println(listaDEC.get(3));

    }
}
