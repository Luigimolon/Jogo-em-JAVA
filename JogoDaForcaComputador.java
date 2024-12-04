import java.util.Random;

public class JogoDaForcaComputador extends JogoDaForca {
    private static final String[][] PALAVRAS = {
        {"gol", "time", "jogador", "chute", "campeonato"},
        {"pizza", "hamburguer", "sushi", "fruta", "doce"},
        {"cachorro", "gato", "elefante", "tigre", "urso"}
    };

    public JogoDaForcaComputador(int temaSelecionado) {
        super(escolherPalavraAleatoria(temaSelecionado));
    }

    private static String escolherPalavraAleatoria(int temaSelecionado) {
        Random random = new Random();
        return PALAVRAS[temaSelecionado][random.nextInt(PALAVRAS[temaSelecionado].length)];
    }
}