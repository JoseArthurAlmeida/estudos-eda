package arvoreB;

import java.util.ArrayList;
import java.util.List;

public class ArvoreB {
    private static final int grauMinimo = 2;
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
            // 2. Se a raiz estiver cheia (3 chaves), precisamos dividir
            if (raiz.numChaves == 3) {
                NoArvoreB novaRaiz = new NoArvoreB(false);
                novaRaiz.filhos[0] = raiz;

                dividirFilho(novaRaiz, 0, raiz);

                int i = 0;
                if (novaRaiz.chaves[0] < valor) {
                    i++;
                }
                // Insere no filho apropriado
                inserirNaoCheio(novaRaiz.filhos[i], valor);

                // Atualiza a referência da raiz principal
                raiz = novaRaiz;
            } else {
                // 3. Se a raiz tem espaço, insere nela (ou desce recursivamente)
                inserirNaoCheio(raiz, valor);
            }
        }
    }

    private void inserirNaoCheio(NoArvoreB noX, int valor) {
        int i = noX.numChaves - 1; // Índice do último elemento

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
            i++; // Índice do filho onde vamos descer

            // ESTRATÉGIA PRÓ-ATIVA:
            // Antes de descer, verifica se o filho está cheio (tem 3 chaves).
            // Se estiver, divide ele AGORA para evitar voltar depois.
            if (noX.filhos[i].numChaves == 3) {
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
        irmao.numChaves = grauMinimo - 1; // Para Ordem 4, o novo nó terá 1 chave

        // --- MOVENDO CHAVES (Direita) ---
        // O filhoCheio tem chaves [0, 1, 2].
        // A chave [2] vai para o 'irmao'. A chave [1] sobe. A chave [0] fica.
        if (grauMinimo - 1 >= 0) System.arraycopy(filhoCheio.chaves, grauMinimo, irmao.chaves, 0, grauMinimo - 1);

        // --- MOVENDO FILHOS (se não for folha) ---
        // Se filhoCheio tiver filhos, move os filhos da direita para o 'irmao'
        if (!filhoCheio.folha) {
            if (grauMinimo >= 0) System.arraycopy(filhoCheio.filhos, grauMinimo, irmao.filhos, 0, grauMinimo);
        }

        // Atualiza tamanho do filhoCheio (agora só tem a chave da esquerda)
        filhoCheio.numChaves = grauMinimo - 1;

        // --- INSERINDO O NOVO FILHO NO PAI ---
        // Abre espaço no array de filhos do Pai
        for (int j = pai.numChaves; j >= i + 1; j--) {
            pai.filhos[j + 1] = pai.filhos[j];
        }
        pai.filhos[i + 1] = irmao;

        // --- SUBINDO A CHAVE MEDIANA PARA O PAI ---
        // A chave mediana está no índicegrauMinimo-1 (índice 1 para Ordem 4)
        // Abre espaço nas chaves do Pai
        for (int j = pai.numChaves - 1; j >= i; j--) {
            pai.chaves[j + 1] = pai.chaves[j];
        }

        pai.chaves[i] = filhoCheio.chaves[grauMinimo - 1];
        pai.numChaves++;
    }

    // --- NOVO MÉTODO DE IMPRESSÃO (VISUAL IGUAL AO SLIDE) ---
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
