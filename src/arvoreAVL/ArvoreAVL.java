package arvoreAVL;

public class ArvoreAVL {
    private No raiz;

    public ArvoreAVL() {
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
                        novoNo.setPai(atual);

                        verificarBalanceamento(novoNo.getPai());

                        return;
                    }
                    atual = atual.getEsquerda();
                } else {
                    // Se o valor for maior, navega para a subárvore direita.
                    if (atual.getDireita() == null) {
                        atual.setDireita(novoNo);
                        novoNo.setPai(atual);

                        verificarBalanceamento(novoNo.getPai());

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
                noAtual.getDireita().setPai(noAtual.getPai());
                return noAtual.getDireita(); // Retorna o filho direito para substituir o nó atual.
            }
            if (noAtual.getDireita() == null) {
                noAtual.getEsquerda().setPai(noAtual.getPai());
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
        verificarBalanceamento(noAtual);
        return noAtual;
    }

    /**
     * Inicia a travessia Pré-Ordem (Pre-Order) a partir da raiz da árvore e imprime os valores
     * dos nós no console.
     * <p>
     * A ordem de visitação é: <b>Raiz, Sub-árvore Esquerda, Sub-árvore Direita</b>.
     * Este método é o ponto de entrada público que chama a implementação recursiva.
     * Ao final da travessia, uma nova linha é impressa para formatação.
     */
    public void travessiaPreOrdem() {
        travessiaPreOrdem(this.raiz);
        System.out.println();
    }

    /**
     * Método auxiliar recursivo que executa a travessia Pré-Ordem em uma dada sub-árvore.
     * <p>
     * Para cada nó visitado, o método primeiro imprime o valor do próprio nó, depois chama
     * a si mesmo para a sub-árvore esquerda e, finalmente, para a sub-árvore direita.
     * O caso base da recursão é um nó nulo, que interrompe o caminho.
     *
     * @param no O nó raiz da sub-árvore a ser percorrida.
     */
    private void travessiaPreOrdem(No no) {
        if (no == null) {
            return;
        }

        System.out.print(no.getValor() + " ");
        travessiaPreOrdem(no.getEsquerda());
        travessiaPreOrdem(no.getDireita());
    }

    /**
     * Inicia a travessia Em-Ordem (In-Order) a partir da raiz da árvore e imprime os valores
     * dos nós no console.
     * <p>
     * A ordem de visitação é: <b>Sub-árvore Esquerda, Raiz, Sub-árvore Direita</b>.
     * Para uma Árvore Binária de Pesquisa (BST), esta travessia resulta na impressão
     * de todos os valores em <strong>ordem crescente</strong>.
     */
    public void travessiaEmOrdem() {
        travessiaEmOrdem(this.raiz);
        System.out.println();
    }

    /**
     * Método auxiliar recursivo que executa a travessia Em-Ordem em uma dada sub-árvore.
     * <p>
     * O método primeiro chama a si mesmo para toda a sub-árvore esquerda. Ao retornar,
     * imprime o valor do nó atual e, em seguida, chama a si mesmo para a sub-árvore direita.
     *
     * @param no O nó raiz da sub-árvore a ser percorrida.
     */
    private void travessiaEmOrdem(No no) {
        if (no == null) {
            return;
        }

        travessiaEmOrdem(no.getEsquerda());
        System.out.print(no.getValor() + " ");
        travessiaEmOrdem(no.getDireita());
    }

    /**
     * Inicia a travessia Pós-Ordem (Post-Order) a partir da raiz da árvore e imprime os valores
     * dos nós no console.
     * <p>
     * A ordem de visitação é: <b>Sub-árvore Esquerda, Sub-árvore Direita, Raiz</b>.
     * Esta travessia é particularmente útil para operações como a exclusão de todos os
     * nós de uma árvore, pois garante que um nó pai seja processado apenas após todos os
     * seus descendentes.
     */
    public void travessiaPosOrdem() {
        travessiaPosOrdem(this.raiz);
        System.out.println();
    }

    /**
     * Método auxiliar recursivo que executa a travessia Pós-Ordem em uma dada sub-árvore.
     * <p>
     * O método chama recursivamente a si mesmo primeiro para a sub-árvore esquerda e depois
     * para a sub-árvore direita. Somente após o retorno de ambas as chamadas, ele imprime
     * o valor do nó atual.
     *
     * @param no O nó raiz da sub-árvore a ser percorrida.
     */
    private void travessiaPosOrdem(No no) {
        if (no == null) {
            return;
        }
        travessiaPosOrdem(no.getEsquerda());
        travessiaPosOrdem(no.getDireita());
        System.out.print(no.getValor() + " ");
    }

    /**
     * Método auxiliar para encontrar o nó com o menor valor em uma sub-árvore.
     * <p>
     * Em uma Árvore Binária de Pesquisa (BST), o menor valor de qualquer sub-árvore
     * está sempre localizado no nó mais à esquerda. Este método explora essa
     * propriedade de forma iterativa, começando no nó fornecido e navegando
     * continuamente para o filho da esquerda até que não seja mais possível.
     * <p>
     * É uma função crucial para o "Caso 3" da operação de remoção, onde é
     * utilizado para encontrar o <strong>sucessor</strong> de um nó que possui dois filhos.
     *
     * @param no O nó que serve como raiz da sub-árvore na qual a busca será realizada.
     *           Normalmente, é o filho direito do nó que se deseja remover.
     * @return O nó (o objeto {@code No} completo) que contém o menor valor
     * encontrado na sub-árvore.
     */
    private No encontrarMenor(No no) {
        while (no.getEsquerda() != null) {
            no = no.getEsquerda();
        }

        return no;
    }

    /**
     * Calcula a altura de um nó.
     * A altura de um nó é o número de arestas no MAIOR CAMINHO entre ele e uma folha.
     * Um nó folha tem altura 1. Um nó nulo tem altura 0.
     *
     * @param no O nó a partir do qual a altura será calculada.
     * @return A altura do nó.
     */
    public int getAltura(No no) {
        // Caso base: se o nó for nulo, sua altura é -1.
        if (no == null) {
            return -1;
        }

        // A altura do nó é 1 + a altura da sua maior sub-árvore (esquerda ou direita).
        // A chamada recursiva desce até as folhas e sobe calculando.
        int alturaEsquerda = getAltura(no.getEsquerda());
        int alturaDireita = getAltura(no.getDireita());

        // Math.max garante que pegamos o caminho mais longo.
        return 1 + Math.max(alturaEsquerda, alturaDireita);
    }

    /**
     * Calcula o Fator de Balanceamento (FB) de um nó.
     * O FB é a diferença entre a altura da sub-árvore esquerda e a altura da sub-árvore direita.
     * FB = Altura(Esquerda) - Altura(Direita)
     *
     * @param no O nó para o qual o FB será calculado.
     * @return O Fator de Balanceamento do nó. Retorna 0 se o nó for nulo.
     */
    public int getFatorBalanceamento(No no) {
        if (no == null) {
            return 0;
        }
        return getAltura(no.getEsquerda()) - getAltura(no.getDireita());
    }

    /**
     * Rotação Simples à Direita.
     * Usada quando o desbalanceamento é na esquerda-esquerda (FB do pai +2, FB do filho +1 ou 0).
     *
     * @param y O nó desbalanceado (pai).
     * @return A nova raiz da sub-árvore após a rotação.
     */
    private No rotacaoDireita(No y) {
        No x = y.getEsquerda();
        No T2 = x.getDireita();

        // Realiza a rotação
        x.setDireita(y);
        y.setEsquerda(T2);

        // Ajusta os pais
        if (T2 != null) {
            T2.setPai(y);
        }

        // Ajusta o pai de x para ser o antigo pai de y
        x.setPai(y.getPai());

        // Se y era a raiz, x agora é a nova raiz
        if (y.getPai() == null) {
            this.raiz = x;
        } else if (y == y.getPai().getDireita()) {
            y.getPai().setDireita(x);
        } else {
            y.getPai().setEsquerda(x);
        }

        // O pai de y agora é x
        y.setPai(x);

        return x;
    }

    /**
     * Rotação Simples à Esquerda.
     * Usada quando o desbalanceamento é na direita-direita (FB do pai -2, FB do filho -1 ou 0).
     *
     * @param x O nó desbalanceado (pai).
     * @return A nova raiz da sub-árvore após a rotação.
     */
    private No rotacaoEsquerda(No x) {
        No y = x.getDireita();
        No T2 = y.getEsquerda();

        // Realiza a rotação
        y.setEsquerda(x);
        x.setDireita(T2);

        // Ajusta os pais
        if (T2 != null) {
            T2.setPai(x);
        }

        // Ajusta o pai de y para ser o antigo pai de x
        y.setPai(x.getPai());

        // Se x era a raiz, y agora é a nova raiz
        if (x.getPai() == null) {
            this.raiz = y;
        } else if (x == x.getPai().getEsquerda()) {
            x.getPai().setEsquerda(y);
        } else {
            x.getPai().setDireita(y);
        }

        // O pai de x agora é y
        x.setPai(y);

        return y;
    }

    /**
     * Sobe a árvore verificando o balanceamento de cada ancestral.
     * Se encontrar um nó desbalanceado, aplica a rotação necessária.
     *
     * @param no O nó a partir do qual começamos a verificar (geralmente onde ocorreu a inserção/remoção).
     */
    private void verificarBalanceamento(No no) {
        while (no != null) {
            int fb = getFatorBalanceamento(no);

            // Caso 1: Desbalanceado para a Esquerda
            if (fb > 1) {
                // Verifica o sinal do filho da esquerda para saber se é Rotação Simples ou Dupla
                // Se FB do filho ≥ 0: Sinais iguais (Positivo/Positivo) → Rotação Simples Direita
                if (getFatorBalanceamento(no.getEsquerda()) >= 0) {
                    no = rotacaoDireita(no);
                }
                // Se FB do filho < 0: Sinais diferentes (Positivo/Negativo) → Rotação Dupla Esquerda-Direita
                else {
                    // 1. Rotação à Esquerda no filho
                    rotacaoEsquerda(no.getEsquerda());
                    // 2. Rotação à Direita no pai
                    no = rotacaoDireita(no);
                }
            }

            // Caso 2: Desbalanceado para a Direita
            else if (fb < -1) {
                // Verifica o sinal do filho da direita
                // Se FB do filho ≤ 0: Sinais iguais (Negativo/Negativo) → Rotação Simples Esquerda
                if (getFatorBalanceamento(no.getDireita()) <= 0) {
                    no = rotacaoEsquerda(no);
                }
                // Se FB do filho > 0: Sinais diferentes (Negativo/Positivo) → Rotação Dupla Direita-Esquerda
                else {
                    // 1. Rotação à Direita no filho
                    rotacaoDireita(no.getDireita());
                    // 2. Rotação à Esquerda no pai
                    no = rotacaoEsquerda(no);
                }
            }

            // Continua subindo para verificar os ancestrais
            no = no.getPai();
        }
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
        int alturaTotal = getAltura(this.raiz);
        // Aumentamos a largura mínima por nó para acomodar as novas informações (h e fb)
        int larguraMinimaPorNo = 16;
        int larguraTela = (int) Math.pow(2, alturaTotal) * larguraMinimaPorNo;

        // A altura da tela precisa de 2 linhas por nível (valor + galho)
        char[][] tela = new char[alturaTotal * 2 + 1][larguraTela];
        for (char[] linha : tela) {
            java.util.Arrays.fill(linha, ' ');
        }

        // 3. Preencher a tela recursivamente com a árvore
        preencherTela(this.raiz, tela, 0, 0, larguraTela - 1);

        // 4. Imprimir a tela
        for (char[] linha : tela) {
            // Usamos trim() para remover espaços em branco desnecessários no final da linha
            System.out.println(new String(linha).trim());
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

        // Calcula a posição central para o nó atual neste espaço
        int meio = esquerda + (direita - esquerda) / 2;
        int linhaValor = nivel * 2;
        int linhaGalho = linhaValor + 1;

        // Formata a string com valor, altura e FB
        String infoNo = String.format("%d (h:%d, fb:%d)", no.getValor(), getAltura(no), getFatorBalanceamento(no));

        // Coloca a string do nó na tela, centralizada na sua posição 'meio'
        int posInicioValor = meio - (infoNo.length() / 2);
        for (int i = 0; i < infoNo.length(); i++) {
            // Garante que não vamos escrever fora dos limites da tela
            if (posInicioValor + i >= 0 && posInicioValor + i < tela[linhaValor].length) {
                tela[linhaValor][posInicioValor + i] = infoNo.charAt(i);
            }
        }

        // Se houver filhos, desenha os galhos e chama a recursão
        if (linhaGalho < tela.length) {
            // Galho para a esquerda
            if (no.getEsquerda() != null) {
                int meioFilho = esquerda + (meio - 1 - esquerda) / 2;
                // --- CORREÇÃO DE ALINHAMENTO ---
                // O galho é posicionado na metade do caminho entre o centro do pai e o centro do filho
                int posGalho = meioFilho + (meio - meioFilho) / 2;
                if (posGalho < tela[linhaGalho].length) tela[linhaGalho][posGalho] = '/';
                preencherTela(no.getEsquerda(), tela, nivel + 1, esquerda, meio - 1);
            }
            // Galho para a direita
            if (no.getDireita() != null) {
                int meioFilho = meio + 1 + (direita - (meio + 1)) / 2;
                // --- CORREÇÃO DE ALINHAMENTO ---
                // O galho é posicionado na metade do caminho entre o centro do pai e o centro do filho
                int posGalho = meioFilho - (meioFilho - meio) / 2;
                if (posGalho >= 0 && posGalho < tela[linhaGalho].length) tela[linhaGalho][posGalho] = '\\';
                preencherTela(no.getDireita(), tela, nivel + 1, meio + 1, direita);
            }
        }
    }

}