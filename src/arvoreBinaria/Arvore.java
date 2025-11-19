package arvoreBinaria;

import java.util.Arrays;

public class Arvore {
    private No raiz;

    public Arvore() {
        this.raiz = null;
    }

    /**
     * Insere um novo valor na árvore binária de pesquisa.
     * <p>
     * O método segue a propriedade fundamental de uma Árvore Binária de Pesquisa (BST):
     * valores menores que um nó são inseridos em sua sub-árvore esquerda, e valores
     * maiores são inseridos em sua sub-árvore direita.
     * <p>
     * Se a árvore estiver vazia, o novo valor se torna a raiz. O método percorre
     * a árvore de forma iterativa para encontrar a posição correta para o novo nó,
     * garantindo que a estrutura da BST seja mantida.
     *
     * @param valor o valor inteiro a ser adicionado à árvore.
     */
    public void inserir(int valor) {
        No novoNo = new No(valor);

        if (this.raiz == null) {
            this.raiz = novoNo;
        } else {
            No atual = this.raiz;

            while (true) {
                // Se o valor for menor, navega para a subárvore esquerda.
                if (valor < atual.getValor()) {
                    if (atual.getEsquerda() == null) {
                        atual.setEsquerda(novoNo);
                        return;
                    }
                    atual = atual.getEsquerda();
                } else {
                    // Se o valor for maior, navega para a subárvore direita.
                    if (atual.getDireita() == null) {
                        atual.setDireita(novoNo);
                        return;
                    }
                    atual = atual.getDireita();
                }
            }
        }
    }

    /**
     * Busca por um valor na árvore de forma iterativa.
     *
     * @param valor - O valor a ser procurado.
     * @return {boolean} - True se o valor for encontrado, senão false.
     */
    public boolean buscar(int valor) {
        // Caso base: se a raiz for nula, o valor não está na árvore.
        if (this.raiz == null)
            return false; // Se a árvore estiver vazia, retorna false.

        // Inicia a busca a partir da raiz
        No atual = this.raiz;
        while (atual != null) {
            if (valor == atual.getValor())
                return true;

            // Se o valor for menor, navega para a subárvore esquerda. Se for maior, navega
            // para a direita.
            atual = valor < atual.getValor() ? atual.getEsquerda() : atual.getDireita();
        }
        // Se o nó atual se tornar nulo, o valor não foi encontrado.
        return false;
    }

    /**
     * Método público que inicia o processo de remoção.
     * Ele chama o método recursivo auxiliar começando pela raiz.
     */
    public void remover(int valor) {
        // A chamada recursiva pode alterar a raiz, então precisamos reatribuí-la.
        this.raiz = remover(this.raiz, valor);
    }

    private No remover(No noAtual, int valor) {
        if (noAtual == null) {
            return null;
        }

        // Passo 1: Busca pelo nó a ser removido.
        if (valor < noAtual.getValor()) {
            noAtual.setEsquerda(remover(noAtual.getEsquerda(), valor));
        } else if (valor > noAtual.getValor()) {
            noAtual.setDireita(remover(noAtual.getDireita(), valor));
        } else {
            // Encontramos o nó a ser removido (valor == noAtual.getValor()).
            // Agora, tratamos os 3 casos.

            // CASO 1: O nó é uma folha (não tem filhos).
            if (noAtual.getEsquerda() == null && noAtual.getDireita() == null) {
                return null; // O pai deste nó receberá null, efetivamente removendo-o.
            }
            // CASO 2: O nó tem apenas um filho.
            if (noAtual.getEsquerda() == null) {
                return noAtual.getDireita(); // Retorna o filho direito para substituir o nó atual.
            }
            if (noAtual.getDireita() == null) {
                return noAtual.getEsquerda(); // Retorna o filho esquerdo para substituir o nó atual.
            }

            // CASO 3: O nó tem dois filhos
            // O sucessor é o menor valor na sub-árvore direita.
            No sucessor = encontrarMenor(noAtual.getDireita());

            // Copiamos o valor do sucessor para o nó que queremos "remover".
            noAtual.setValor(sucessor.getValor()); // Assumindo que sua classe No tem um setValor()

            // Agora, removemos o nó sucessor (que agora é um duplicado) da sub-árvore direita.
            // O nó sucessor sempre se encaixará no Caso 1 ou Caso 2, pois ele não tem filho esquerdo.
            noAtual.setDireita(remover(noAtual.getDireita(), sucessor.getValor()));
        }
        return noAtual;
    }

    public void travessiaPreOrdem() {
        travessiaPreOrdem(this.raiz);
        System.out.println();
    }

    private void travessiaPreOrdem(No no) {
        if (no == null) {
            return;
        }

        System.out.print(no.getValor() + " ");
        travessiaPreOrdem(no.getEsquerda());
        travessiaPreOrdem(no.getDireita());
    }

    public void travessiaEmOrdem() {
        travessiaEmOrdem(this.raiz);
        System.out.println();
    }

    private void travessiaEmOrdem(No no) {
        if (no == null) {
            return;
        }

        travessiaEmOrdem(no.getEsquerda());
        System.out.print(no.getValor() + " ");
        travessiaEmOrdem(no.getDireita());
    }

    public void travessiaPosOrdem() {
        travessiaPosOrdem(this.raiz);
        System.out.println();
    }

    private void travessiaPosOrdem(No no) {
        if (no == null) {
            return;
        }
        travessiaPosOrdem(no.getEsquerda());
        travessiaPosOrdem(no.getDireita());
        System.out.print(no.getValor() + " ");
    }

    private No encontrarMenor(No no) {
        while (no.getEsquerda() != null) {
            no = no.getEsquerda();
        }

        return no;
    }

    /**
     * Método público que inicia a exibição da árvore.
     */
    public void exibirEstrutura() {
        if (this.raiz == null) {
            System.out.println("A árvore está vazia.");
            return;
        }

        // 1. Calcular as dimensões da tela
        int altura = getAltura(this.raiz);
        // A largura é calculada para garantir espaço suficiente no último nível
        int largura = (int) Math.pow(2, altura - 1) * 4;

        // 2. Criar a tela em branco
        char[][] tela = new char[altura * 2][largura];
        for (char[] linha : tela) {
            Arrays.fill(linha, ' ');
        }

        // 3. Preencher a tela recursivamente com a árvore
        preencherTela(this.raiz, tela, 0, 0, largura - 1);

        // 4. Imprimir a tela
        for (char[] linha : tela) {
            System.out.println(new String(linha));
        }
    }

    /**
     * Preenche recursivamente a tela com os nós e galhos da árvore.
     *
     * @param no       O nó atual.
     * @param tela     A matriz 2D que representa a tela.
     * @param nivel    O nível (profundidade) atual do nó.
     * @param esquerda O limite esquerdo do espaço disponível para esta sub-árvore.
     * @param direita  O limite direito do espaço disponível para esta sub-árvore.
     */
    private void preencherTela(No no, char[][] tela, int nivel, int esquerda, int direita) {
        if (no == null) {
            return;
        }

        // Calcula a posição do meio para o nó atual
        int meio = esquerda + (direita - esquerda) / 2;
        int linhaValor = nivel * 2;
        int linhaGalho = linhaValor + 1;

        // Coloca o valor do nó na tela
        String valorStr = String.valueOf(no.getValor());
        int posInicioValor = meio - (valorStr.length() / 2);
        for (int i = 0; i < valorStr.length(); i++) {
            if (posInicioValor + i < tela[linhaValor].length) {
                tela[linhaValor][posInicioValor + i] = valorStr.charAt(i);
            }
        }

        // Se houver filhos, desenha os galhos e chama a recursão
        if (linhaGalho < tela.length) {
            // Galho para a esquerda
            if (no.getEsquerda() != null) {
                int meioFilho = esquerda + (meio - 1 - esquerda) / 2;
                tela[linhaGalho][meioFilho + 1] = '/';
                preencherTela(no.getEsquerda(), tela, nivel + 1, esquerda, meio - 1);
            }
            // Galho para a direita
            if (no.getDireita() != null) {
                int meioFilho = meio + 1 + (direita - (meio + 1)) / 2;
                tela[linhaGalho][meioFilho] = '\\';
                preencherTela(no.getDireita(), tela, nivel + 1, meio + 1, direita);
            }
        }
    }

    /**
     * Calcula a altura (profundidade máxima) da árvore.
     *
     * @param no O nó raiz da sub-árvore.
     * @return A altura.
     */
    private int getAltura(No no) {
        if (no == null) {
            return 0;
        }
        return 1 + Math.max(getAltura(no.getEsquerda()), getAltura(no.getDireita()));
    }
}