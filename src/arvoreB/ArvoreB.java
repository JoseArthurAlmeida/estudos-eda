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
