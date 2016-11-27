

/**
 *
 * @author charlesportoferreira
 */
public class GeradoraPermutacao {

    private final char[] simbolos;
    private char palavra[];
    private int posControle = 0;
    private int contadorPalavras = 0;
    private int simboloAtual = 0;

    public GeradoraPermutacao(char[] simbolos) {
        this.simbolos = simbolos;
        palavra = new char[]{'a'};
    }

    public String getNext() {
        if (ultimoCaracter() == ultimoSimbolo()) {
            if (caracterControle() == ultimoSimbolo()) {
                while (caracterControle() == ultimoSimbolo()) {
                    posControle--;
                    if (posControle >= 0 && caracterControle() != ultimoSimbolo()) {
                        atualizaCaracterControle();
                    }
                    if (posControle < 0) {
                        addCaracter();
                        posControle = penultimoCaracter();
                        break;
                    }
                }
            } else {
                atualizaCaracterControle();
            }
        }
        palavra[palavra.length - 1] = simbolos[simboloAtual];
        simboloAtual = proximoSimbolo();
        contadorPalavras++;
        return new String(palavra);
    }

    private int proximoSimbolo() {
        return (simboloAtual + 1) % simbolos.length;
    }

    private char caracterControle() {
        return palavra[posControle];
    }

    private char ultimoCaracter() {
        return palavra[palavra.length - 1];
    }

    private char ultimoSimbolo() {
        return simbolos[simbolos.length - 1];
    }

    private void atualizaCaracterControle() {
        palavra[posControle] = getProximoSimbolo();
        for (int j = posControle + 1; j < palavra.length - 1; j++) {
            palavra[j] = simbolos[0];
        }
        posControle = penultimoCaracter();
    }

    private char getProximoSimbolo() {
        char atual = palavra[posControle];
        for (int i = 0; i < simbolos.length; i++) {
            if (simbolos[i] == atual) {
                return simbolos[i + 1];
            }
        }
        return 0;
    }

    private int penultimoCaracter() {
        return palavra.length - 2;
    }

    private void addCaracter() {
        String sb = new String(palavra);
        palavra = new char[palavra.length + 1];
        for (int i = 0; i < palavra.length; i++) {
            palavra[i] = simbolos[0];
        }
    }

    public int getContadorPalavras() {
        return contadorPalavras;
    }

    public static void main(String args[]) {
        char simbolos[] = "abc".toCharArray();
        GeradoraPermutacao gp = new GeradoraPermutacao(simbolos);
        for (int i = 0; i < 30; i++) {
            String p = gp.getNext();
           System.out.println(i + 1 + ":" + p);
        }
        System.out.println(gp.getContadorPalavras() + " palavras geradas permutando os simbolos " + new String(simbolos));
    }


}
