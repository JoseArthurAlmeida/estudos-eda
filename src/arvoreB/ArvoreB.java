package arvoreB;

import java.util.ArrayList;
import java.util.List;

public class ArvoreB {
    private static final int minimoFilho = 2;
    private static final int maximoChaves = 3;

    private NoArvoreB raiz;

    public ArvoreB() {
        this.raiz = null;
    }

    public void inserir(int valor) {
        if (raiz == null) {
            raiz = new NoArvoreB(true);
            raiz.chaves[0] = valor;
            raiz.numChaves = 1;
        } else {
            // 2. Se a raiz estiver cheia, precisamos dividir
            if (raiz.numChaves == maximoChaves) {
                NoArvoreB novaRaiz = new NoArvoreB(false);
                novaRaiz.filhos[0] = raiz;

                dividirFilho(novaRaiz, 0, raiz);

                int i = 0;
                if (novaRaiz.chaves[0] < valor) {
                    i++;
                }
                // Insere no filho apropriado
                inserirNaoCheio(novaRaiz.filhos[i], valor);

                raiz = novaRaiz;
            } else {
                // 3. Se a raiz tem espaço, insere nela (ou desce recursivamente)
                inserirNaoCheio(raiz, valor);
            }
        }
    }

    private void inserirNaoCheio(NoArvoreB noX, int valor) {
        int i = noX.numChaves - 1;

        if (noX.folha) {
            // Se noX é folha, encontramos a posição correta e inserimos
            // Move chaves maiores para a direita para abrir espaço
            while (i >= 0 && noX.chaves[i] > valor) {
                noX.chaves[i + 1] = noX.chaves[i];
                i--;
            }

            noX.chaves[i + 1] = valor;
            noX.numChaves = noX.numChaves + 1;
        } else {
            // Se noX não é folha, precisamos encontrar o filho correto para descer
            while (i >= 0 && noX.chaves[i] > valor) {
                i--;
            }
            i++;

            // Antes de descer, verifica se o filho está cheio (tem 3 chaves).
            // Se estiver, divide ele AGORA para evitar voltar depois.
            if (noX.filhos[i].numChaves == maximoChaves) {
                dividirFilho(noX, i, noX.filhos[i]);

                // Após dividir, uma chave subiu para 'noX'.
                // Precisamos verificar se nossa chave 'k' é maior que a chave que subiu
                if (noX.chaves[i] < valor) {
                    i++;
                }
            }

            // Agora é seguro descer recursivamente
            inserirNaoCheio(noX.filhos[i], valor);
        }
    }

    private void dividirFilho(NoArvoreB pai, int i, NoArvoreB filhoCheio) {
        // Cria um novo nó 'irmao' que armazenará as chaves maiores
        NoArvoreB irmao = new NoArvoreB(filhoCheio.folha);
        irmao.numChaves = minimoFilho - 1;

        // O filhoCheio tem chaves com indices [0, 1, 2].
        // A chave [2] vai para o 'irmao'. A chave [1] sobe. A chave [0] fica.
        System.arraycopy(filhoCheio.chaves, minimoFilho, irmao.chaves, 0, minimoFilho - 1);

        // --- MOVENDO FILHOS (se não for folha) ---
        // Se filhoCheio tiver filhos, move os filhos da direita para o 'irmao'
        if (!filhoCheio.folha) {
            if (minimoFilho >= 0) System.arraycopy(filhoCheio.filhos, minimoFilho, irmao.filhos, 0, minimoFilho);
        }

        // Atualiza tamanho do filhoCheio (agora só tem a chave da esquerda)
        filhoCheio.numChaves = minimoFilho - 1;

        // --- INSERINDO O NOVO FILHO NO PAI ---
        // Abre espaço no array de filhos do Pai
        for (int j = pai.numChaves; j >= i + 1; j--) {
            pai.filhos[j + 1] = pai.filhos[j];
        }
        pai.filhos[i + 1] = irmao;

        // --- SUBINDO A CHAVE MEDIANA PARA O PAI ---
        for (int j = pai.numChaves - 1; j >= i; j--) {
            pai.chaves[j + 1] = pai.chaves[j];
        }

        pai.chaves[i] = filhoCheio.chaves[minimoFilho - 1];
        pai.numChaves++;
    }

    public boolean buscar(int valor) {
        return buscarRecursivo(raiz, valor) != null;
    }

    private NoArvoreB buscarRecursivo(NoArvoreB no, int valor) {
        if (no == null) {
            return null;
        }

        int i = 0;

        // 1. Procura a primeira chave que é maior ou igual ao valor buscado
        while (i < no.numChaves && valor > no.chaves[i]) {
            i++;
        }

        // 2. Verifica se encontrou o valor neste nó
        if (i < no.numChaves && valor == no.chaves[i]) {
            return no;
        }

        if (no.folha) {
            return null;
        }

        return buscarRecursivo(no.filhos[i], valor);
    }

    // REMOÇÃO
    public void remover(int valor) {
        if (raiz == null) {
            System.out.println("A árvore está vazia.");
            return;
        }

        remover(raiz, valor);

        if (raiz.numChaves == 0) {
            if (raiz.folha) {
                raiz = null;
            } else {
                raiz = raiz.filhos[0];
            }
        }
    }

    private void remover(NoArvoreB no, int valor) {
        int idx = encontrarIndiceChave(no, valor);

        // CASO 1 e 2: A chave está neste nó
        if (idx < no.numChaves && no.chaves[idx] == valor) {
            if (no.folha) {
                removerDeFolha(no, idx); // Caso 1
            } else {
                removerDeNaoFolha(no, idx); // Caso 2
            }
        } else {
            // CASO 3: A chave não está aqui (precisamos descer)
            if (no.folha) {
                System.out.println("A chave " + valor + " não existe na árvore.");
                return;
            }

            // Flag para saber se a chave está na subárvore do último filho
            boolean flag = (idx == no.numChaves);

            // ESTRATÉGIA PROATIVA (Evitar Underflow):
            // Se o filho onde vamos descer tem apenas o mínimo de chaves (T-1),
            // precisamos enchê-lo antes de entrar.
            if (no.filhos[idx].numChaves < minimoFilho) {
                preencher(no, idx);
            }

            // Se houve fusão no último filho, o índice pode ter mudado
            if (flag && idx > no.numChaves) {
                remover(no.filhos[idx - 1], valor);
            } else {
                remover(no.filhos[idx], valor);
            }
        }
    }

    // Auxiliar: Encontra o índice da primeira chave maior ou igual ao valor
    private int encontrarIndiceChave(NoArvoreB no, int valor) {
        int idx = 0;
        while (idx < no.numChaves && no.chaves[idx] < valor) {
            idx++;
        }
        return idx;
    }

    // Caso 1: Remove diretamente da folha
    private void removerDeFolha(NoArvoreB no, int idx) {
        // Desloca tudo para a esquerda para cobrir o buraco
        for (int i = idx + 1; i < no.numChaves; ++i) {
            no.chaves[i - 1] = no.chaves[i];
        }
        no.numChaves--;
    }

    // Caso 2: Remove de nó interno (troca por antecessor/sucessor)
    private void removerDeNaoFolha(NoArvoreB no, int idx) {
        int k = no.chaves[idx];

        // Se o filho à esquerda tem chaves suficientes, pegamos o Antecessor
        if (no.filhos[idx].numChaves >= minimoFilho) {
            int pred = obterPredecessor(no, idx);
            no.chaves[idx] = pred;
            remover(no.filhos[idx], pred);
        }
        // Se o filho à direita tem chaves suficientes, pegamos o Sucessor
        else if (no.filhos[idx + 1].numChaves >= minimoFilho) {
            int succ = obterSucessor(no, idx);
            no.chaves[idx] = succ;
            remover(no.filhos[idx + 1], succ);
        }
        // Se ambos têm poucas chaves, fundimos os dois filhos
        else {
            merge(no, idx); // Une filho[idx] e filho[idx+1]
            remover(no.filhos[idx], k); // Agora remove do novo nó fundido
        }
    }

    private int obterPredecessor(NoArvoreB no, int idx) {
        NoArvoreB atual = no.filhos[idx];
        while (!atual.folha) {
            atual = atual.filhos[atual.numChaves];
        }
        return atual.chaves[atual.numChaves - 1];
    }

    private int obterSucessor(NoArvoreB no, int idx) {
        NoArvoreB atual = no.filhos[idx + 1];
        while (!atual.folha) {
            atual = atual.filhos[0];
        }
        return atual.chaves[0];
    }

    // Garante que o filho na posição idx tenha chaves suficientes
    private void preencher(NoArvoreB no, int idx) {
        // Tenta emprestar do irmão anterior (esquerda)
        if (idx != 0 && no.filhos[idx - 1].numChaves >= minimoFilho) {
            pegarEmprestadoDoAnterior(no, idx);
        }
        // Tenta emprestar do irmão seguinte (direita)
        else if (idx != no.numChaves && no.filhos[idx + 1].numChaves >= minimoFilho) {
            pegarEmprestadoDoProximo(no, idx);
        }
        // Se não der para emprestar, faz fusão (merge)
        else {
            if (idx != no.numChaves) {
                merge(no, idx); // Funde com o próximo
            } else {
                merge(no, idx - 1); // Funde com o anterior
            }
        }
    }

    // ROTAÇÃO À DIREITA (Irmão da Esquerda -> Pai -> Eu)
    private void pegarEmprestadoDoAnterior(NoArvoreB no, int idx) {
        NoArvoreB filho = no.filhos[idx];
        NoArvoreB irmao = no.filhos[idx - 1];

        // Abre espaço no filho (move tudo pra frente)
        for (int i = filho.numChaves - 1; i >= 0; --i) {
            filho.chaves[i + 1] = filho.chaves[i];
        }
        if (!filho.folha) {
            for (int i = filho.numChaves; i >= 0; --i) {
                filho.filhos[i + 1] = filho.filhos[i];
            }
        }

        // Pai desce para o filho
        filho.chaves[0] = no.chaves[idx - 1];
        if (!filho.folha) {
            filho.filhos[0] = irmao.filhos[irmao.numChaves];
        }

        // Irmão sobe para o pai
        no.chaves[idx - 1] = irmao.chaves[irmao.numChaves - 1];

        filho.numChaves++;
        irmao.numChaves--;
    }

    // ROTAÇÃO À ESQUERDA (Irmão da Direita -> Pai -> Eu)
    private void pegarEmprestadoDoProximo(NoArvoreB no, int idx) {
        NoArvoreB filho = no.filhos[idx];
        NoArvoreB irmao = no.filhos[idx + 1];

        // Pai desce para o final do filho
        filho.chaves[filho.numChaves] = no.chaves[idx];
        if (!filho.folha) {
            filho.filhos[filho.numChaves + 1] = irmao.filhos[0];
        }

        // Irmão sobe para o pai
        no.chaves[idx] = irmao.chaves[0];

        // Arruma o irmão (remove o primeiro elemento)
        for (int i = 1; i < irmao.numChaves; ++i) {
            irmao.chaves[i - 1] = irmao.chaves[i];
        }
        if (!irmao.folha) {
            for (int i = 1; i <= irmao.numChaves; ++i) {
                irmao.filhos[i - 1] = irmao.filhos[i];
            }
        }

        filho.numChaves++;
        irmao.numChaves--;
    }

    // FUSÃO (MERGE): Junta Filho[idx] + Pai[idx] + Filho[idx+1]
    private void merge(NoArvoreB no, int idx) {
        NoArvoreB filho = no.filhos[idx];
        NoArvoreB irmao = no.filhos[idx + 1];

        // 1. Desce a chave do pai para o final do filho da esquerda
        filho.chaves[minimoFilho - 1] = no.chaves[idx];

        // 2. Copia as chaves do irmão para o filho
        if (irmao.numChaves >= 0) System.arraycopy(irmao.chaves, 0, filho.chaves, 2, irmao.numChaves);

        // 3. Copia os filhos do irmão (se não for folha)
        if (!filho.folha) {
            if (irmao.numChaves + 1 >= 0) System.arraycopy(irmao.filhos, 0, filho.filhos, 2, irmao.numChaves + 1);
        }

        // 4. Arruma o nó Pai (remove a chave que desceu e o ponteiro para o irmão)
        for (int i = idx + 1; i < no.numChaves; ++i) {
            no.chaves[i - 1] = no.chaves[i];
        }
        for (int i = idx + 2; i <= no.numChaves; ++i) {
            no.filhos[i - 1] = no.filhos[i];
        }

        // Atualiza contagens
        filho.numChaves += irmao.numChaves + 1;
        no.numChaves--;
    }

    public void imprimir() {
        if (raiz == null) {
            System.out.println("Árvore vazia");
            return;
        }

        List<NoArvoreB> nivelAtual = new ArrayList<>();
        nivelAtual.add(raiz);
        int numeroNivel = 0;

        while (!nivelAtual.isEmpty()) {
            List<NoArvoreB> proximoNivel = new ArrayList<>();
            System.out.print("Nível " + numeroNivel + ":  ");

            // Imprime todos os nós deste nível na mesma linha
            for (NoArvoreB no : nivelAtual) {
                System.out.print("[ ");
                for (int i = 0; i < no.numChaves; i++) {
                    System.out.print(no.chaves[i] + (i < no.numChaves - 1 ? " " : ""));
                }
                System.out.print(" ]  ");

                // Prepara os filhos para o próximo loop
                if (!no.folha) {
                    for (int i = 0; i <= no.numChaves; i++) {
                        if (no.filhos[i] != null) {
                            proximoNivel.add(no.filhos[i]);
                        }
                    }
                }
            }
            System.out.println(); // Pula linha ao fim do nível
            nivelAtual = proximoNivel;
            numeroNivel++;
        }
        System.out.println("--------------------------------------------------");
    }
}
