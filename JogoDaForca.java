import java.util.HashSet;

public class JogoDaForca {
    String palavraEscolhida;
    char[] letrasDescobertas;
    private int tentativasRestantes;
    private HashSet<Character> letrasErradas;
    public boolean acertou = false;

    public JogoDaForca(String palavra) {
        this.palavraEscolhida = palavra.toLowerCase();
        this.letrasDescobertas = new char[palavra.length()];
        for (int i = 0; i < letrasDescobertas.length; i++) {
            letrasDescobertas[i] = '_';
        }
        this.tentativasRestantes = 6;
        this.letrasErradas = new HashSet<>();
    }

    public boolean fazerTentativa(String entrada) {
        entrada = entrada.toLowerCase();

        if (entrada.length() == 1) {
            char letra = entrada.charAt(0);
            boolean acertouLetra = false;

            if (palavraEscolhida.indexOf(letra) != -1) {
                for (int i = 0; i < palavraEscolhida.length(); i++) {
                    if (palavraEscolhida.charAt(i) == letra) {
                        letrasDescobertas[i] = letra;
                        acertouLetra = true;
                    }
                }
            } else {
                if (!letrasErradas.contains(letra)) {
                    letrasErradas.add(letra);
                    tentativasRestantes--;
                }
            }
            return acertouLetra;
        } else {
            if (entrada.equals(palavraEscolhida)) {
                acertouPalavra();
                return true;
            } else {
                tentativasRestantes--;
                return false;
            }
        }
    }

    public void acertouPalavra() {
        acertou = true;
        for (int i = 0; i < palavraEscolhida.length(); i++) {
            letrasDescobertas[i] = palavraEscolhida.charAt(i);
        }
    }

    public boolean jogoTerminado() {
        return tentativasRestantes <= 0 || String.valueOf(letrasDescobertas).equals(palavraEscolhida);
    }

    public String obterPalavraDescoberta() {
        return String.valueOf(letrasDescobertas);
    }

    public int getTentativasRestantes() {
        return tentativasRestantes;
    }

    public String getLetrasErradas() {
        StringBuilder sb = new StringBuilder();
        for (Character letra : letrasErradas) {
            sb.append(letra).append(" ");
        }
        return sb.toString().trim();
    }

    public void revelarPalavra() {
        acertouPalavra();
    }
}
