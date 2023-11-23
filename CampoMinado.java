import java.util.Random;
import java.util.Scanner;

public class CampoMinado {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o tamanho do campo minado (por favor utilize um numero inteiro): ");
        int tamanho = scanner.nextInt();

        char[][] campo = gerarCampoMinado(tamanho);
        char[][] campoVisivel = inicializarCampoVisivel(tamanho);

        boolean jogoAtivo = true;

        while (jogoAtivo) {
            exibirCampo(campoVisivel);

            System.out.print("Digite a linha: ");
            int linha = scanner.nextInt();
            System.out.print("Digite a coluna: ");
            int coluna = scanner.nextInt();

            if (campo[linha][coluna] == '*') {
                System.out.println("Você atingiu uma mina! Fim de jogo!");
                jogoAtivo = false;
            } else {
                campoVisivel[linha][coluna] = campo[linha][coluna];
                if (verificarVitoria(campo, campoVisivel)) {
                    System.out.println("Parabéns! Você ganhou!");
                    jogoAtivo = false;
                }
            }
        }

        scanner.close();
    }

    private static char[][] gerarCampoMinado(int tamanho) {
        char[][] campo = new char[tamanho][tamanho];
        Random random = new Random();

        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                campo[i][j] = (random.nextInt(100) < 20) ? '*' : ' ';
            }
        }

        return campo;
    }

    private static char[][] inicializarCampoVisivel(int tamanho) {
        char[][] campoVisivel = new char[tamanho][tamanho];

        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                campoVisivel[i][j] = '-';
            }
        }

        return campoVisivel;
    }

    private static void exibirCampo(char[][] campo) {
        System.out.println("Campo Minado:");
        for (char[] linha : campo) {
            for (char celula : linha) {
                System.out.print(celula + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static boolean verificarVitoria(char[][] campo, char[][] campoVisivel) {
        for (int i = 0; i < campo.length; i++) {
            for (int j = 0; j < campo[i].length; j++) {
                if (campo[i][j] != '*' && campoVisivel[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }
}
