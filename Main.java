import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    private static DatabaseConnector databaseConnector = new DatabaseConnector();
    private static JogoDaForca jogo;

    private static JTextArea bonecoArea;
    private static JLabel palavraLabel;
    private static JLabel tentativasLabel;
    private static JLabel letrasErradasLabel;
    private static JTextField inputField;

    private static final String[] BONECOS = {
        "    _____ \n" +
        "   |     |   \n" +
        "   |      \n" +
        "   |      \n" +
        "   |      \n" +
        "   |      \n" +
        "   |______\n",

        "    _____ \n" +
        "   |     |   \n" +
        "   |     O\n" +
        "   |      \n" +
        "   |      \n" +
        "   |      \n" +
        "   |______\n",

        "    _____ \n" +
        "   |     |   \n" +
        "   |     O\n" +
        "   |     |\n" +
        "   |     | \n" +
        "   |      \n" +
        "   |______\n",

        "    _____ \n" +
        "   |     |   \n" +
        "   |     O\n" +
        "   |    /|\n" +
        "   |     |\n" +
        "   |      \n" +
        "   |______\n",

        "    _____ \n" +
        "   |     |   \n" +
        "   |     O\n" +
        "   |    /|\\\n" +
        "   |     | \n" +
        "   |     \n" +
        "   |______\n",

        "    _____ \n" +
        "   |     |   \n" +
        "   |     O\n" +
        "   |    /|\\\n" +
        "   |     |\n" +
        "   |    / \n" +
        "   |______\n",

        "    _____ \n" +
        "   |     |   \n" +
        "   |     O\n" +
        "   |    /|\\\n" +
        "   |     |\n" +
        "   |    / \\\n" +
        "   |______\n",
    };

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::criarInterface);
    }

    private static void criarInterface() {
        JFrame frame = new JFrame("Jogo da Forca");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);
        frame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel welcomeLabel = new JLabel("BEM VINDO AO JOGO DA FORCA");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        frame.add(welcomeLabel, gbc);

        JLabel palavraEscolhidaLabel = new JLabel("Escolha uma palavra:");
        palavraEscolhidaLabel.setFont(new Font("Arial", Font.BOLD, 18));
        JTextField palavraField = new JTextField(30);
        palavraField.setFont(new Font("Arial", Font.PLAIN, 16));
        JButton iniciarButton = new JButton("Iniciar Jogo");

        JButton jogarComputadorButton = new JButton("Jogar contra o computador");

        String[] temas = {"Futebol", "Comida", "Animais"};
        JComboBox<String> temaComboBox = new JComboBox<>(temas);

        iniciarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String palavraEscolhida = palavraField.getText().toLowerCase();
                jogo = new JogoDaForca(palavraEscolhida);
                iniciarJogo(frame);
            }
        });

        jogarComputadorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int temaSelecionado = temaComboBox.getSelectedIndex();
                jogo = new JogoDaForcaComputador(temaSelecionado);
                iniciarJogo(frame);
            }
        });

        gbc.gridy = 1;
        frame.add(palavraEscolhidaLabel, gbc);

        gbc.gridx = 1;
        frame.add(palavraField, gbc);

        gbc.gridy = 2;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        frame.add(iniciarButton, gbc);

        JPanel painelJogar = new JPanel();
        painelJogar.setLayout(new FlowLayout(FlowLayout.CENTER));
        painelJogar.add(temaComboBox);
        painelJogar.add(jogarComputadorButton);
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        frame.add(painelJogar, gbc);

        frame.setVisible(true);
    }

    private static void iniciarJogo(JFrame frame) {
        frame.getContentPane().removeAll();
        frame.revalidate();
        frame.repaint();

        frame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);

        bonecoArea = new JTextArea(BONECOS[0]);
        bonecoArea.setEditable(false);
        bonecoArea.setFont(new Font("Monospaced", Font.PLAIN, 40));
        bonecoArea.setRows(7);
        bonecoArea.setColumns(10);
        bonecoArea.setBackground(Color.WHITE);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        frame.add(bonecoArea, gbc);

        palavraLabel = new JLabel(jogo.obterPalavraDescoberta());
        palavraLabel.setFont(new Font("Arial", Font.BOLD, 30));
        gbc.gridy = 1;
        frame.add(palavraLabel, gbc);

        tentativasLabel = new JLabel("Tentativas restantes: " + jogo.getTentativasRestantes());
        tentativasLabel.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridy = 2;
        frame.add(tentativasLabel, gbc);

        letrasErradasLabel = new JLabel("Letras erradas: ");
        letrasErradasLabel.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridy = 3;
        frame.add(letrasErradasLabel, gbc);

        inputField = new JTextField(60);  // Aumentei a largura para 60 colunas
        inputField.setFont(new Font("Arial", Font.PLAIN, 20)); // Aumentando o tamanho da fonte também
        JButton submitButton = new JButton("Enviar");

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String entrada = inputField.getText().toLowerCase();
                inputField.setText("");

                if (entrada.length() == 1) {
                    jogo.fazerTentativa(entrada);
                } else if (entrada.equals(jogo.palavraEscolhida)) {
                    jogo.acertou = true;
                    jogo.revelarPalavra();
                } else {
                    JOptionPane.showMessageDialog(frame, "Você deve tentar uma letra ou a palavra completa!");
                }

                palavraLabel.setText(jogo.obterPalavraDescoberta());
                tentativasLabel.setText("Tentativas restantes: " + jogo.getTentativasRestantes());
                letrasErradasLabel.setText("Letras erradas: " + jogo.getLetrasErradas());

                bonecoArea.setText(BONECOS[6 - jogo.getTentativasRestantes()]);

                if (jogo.jogoTerminado()) {
                    String mensagem;
                    if (jogo.acertou || jogo.obterPalavraDescoberta().equals(jogo.palavraEscolhida)) {
                        mensagem = "Parabéns! Você acertou a palavra: " + jogo.palavraEscolhida;
                    } else {
                        mensagem = "Você errou! A palavra era: " + jogo.palavraEscolhida;
                    }
                    JOptionPane.showMessageDialog(frame, mensagem);
                    salvarResultado();
                    System.exit(0);
                }
            }
        });

        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        frame.add(inputField, gbc);

        gbc.gridx = 1;
        frame.add(submitButton, gbc);

        frame.setVisible(true);
    }

    private static void salvarResultado() {
        String letrasChutadas = jogo.getLetrasErradas();
        String palavraEscolhida = jogo.palavraEscolhida;
        int tentativasNecessarias = 6 - jogo.getTentativasRestantes();
        databaseConnector.salvarResultado(letrasChutadas, palavraEscolhida, tentativasNecessarias);
    }
}

