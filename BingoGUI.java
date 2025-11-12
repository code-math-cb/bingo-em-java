import java.awt.*;
import java.util.*;
import javax.swing.*;

/**
 * Bingo com interface gráfica (Swing)
 * - Gera números aleatórios de 1 a 75 sem repetição.
 * - Mostra os números sorteados em uma grade colorida.
 * - Possui botões de controle: "Sortear", "Limpar", "Sair".
 * - Interface responsiva e intuitiva.
 */
public class BingoGUI extends JFrame {

    private static final int LIMITE = 75;
    private final Set<Integer> numerosSorteados = new HashSet<>();
    private final JLabel[] labelsNumeros = new JLabel[LIMITE];
    private final JLabel labelUltimoNumero = new JLabel("—", SwingConstants.CENTER);
    private final JButton botaoSortear = new JButton("🎲 Sortear Número");
    private final JButton botaoLimpar = new JButton("🔁 Reiniciar Jogo");
    private final JButton botaoSair = new JButton("🚪 Sair");

    private final Random random = new Random();

    public BingoGUI() {
        setTitle("🎱 Bingo Interativo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        setSize(700, 600);
        setLocationRelativeTo(null); // Centraliza na tela
        getContentPane().setBackground(new Color(25, 25, 25));

        // Painel do título e número sorteado
        JPanel painelSuperior = new JPanel(new BorderLayout());
        painelSuperior.setBackground(new Color(40, 40, 40));
        JLabel titulo = new JLabel("🎱 BINGO", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 26));
        titulo.setForeground(Color.WHITE);
        painelSuperior.add(titulo, BorderLayout.NORTH);

        labelUltimoNumero.setFont(new Font("Arial", Font.BOLD, 90));
        labelUltimoNumero.setForeground(Color.YELLOW);
        painelSuperior.add(labelUltimoNumero, BorderLayout.CENTER);
        add(painelSuperior, BorderLayout.NORTH);

        // Painel da grade dos números (1 a 75)
        JPanel painelNumeros = new JPanel(new GridLayout(5, 15, 5, 5));
        painelNumeros.setBackground(new Color(30, 30, 30));

        for (int i = 0; i < LIMITE; i++) {
            JLabel label = new JLabel(String.valueOf(i + 1), SwingConstants.CENTER);
            label.setOpaque(true);
            label.setBackground(new Color(70, 70, 70));
            label.setForeground(Color.WHITE);
            label.setFont(new Font("Arial", Font.BOLD, 18));
            label.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
            labelsNumeros[i] = label;
            painelNumeros.add(label);
        }
        add(painelNumeros, BorderLayout.CENTER);

        // Painel dos botões
        JPanel painelBotoes = new JPanel();
        painelBotoes.setBackground(new Color(35, 35, 35));

        configurarBotao(botaoSortear, new Color(0, 180, 0));
        configurarBotao(botaoLimpar, new Color(0, 120, 200));
        configurarBotao(botaoSair, new Color(200, 0, 0));

        painelBotoes.add(botaoSortear);
        painelBotoes.add(botaoLimpar);
        painelBotoes.add(botaoSair);
        add(painelBotoes, BorderLayout.SOUTH);

        // Ações dos botões
        botaoSortear.addActionListener(e -> sortearNumero());
        botaoLimpar.addActionListener(e -> reiniciarJogo());
        botaoSair.addActionListener(e -> System.exit(0));
    }

    // ===== CONFIGURAÇÃO DE BOTÕES =====
    private void configurarBotao(JButton botao, Color cor) {
        botao.setFocusPainted(false);
        botao.setBackground(cor);
        botao.setForeground(Color.WHITE);
        botao.setFont(new Font("Arial", Font.BOLD, 16));
        botao.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    // ===== SORTEIO =====
    private void sortearNumero() {
        if (numerosSorteados.size() >= LIMITE) {
            JOptionPane.showMessageDialog(this, "Todos os números já foram sorteados!", "Fim do Jogo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        int numero;
        do {
            numero = random.nextInt(LIMITE) + 1;
        } while (numerosSorteados.contains(numero));

        numerosSorteados.add(numero);
        labelUltimoNumero.setText(String.valueOf(numero));

        JLabel label = labelsNumeros[numero - 1];
        label.setBackground(Color.YELLOW);
        label.setForeground(Color.BLACK);
    }

    // ===== REINICIAR JOGO =====
    private void reiniciarJogo() {
        numerosSorteados.clear();
        labelUltimoNumero.setText("—");
        for (JLabel label : labelsNumeros) {
            label.setBackground(new Color(70, 70, 70));
            label.setForeground(Color.WHITE);
        }
    }

    // ===== MAIN =====
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BingoGUI bingo = new BingoGUI();
            bingo.setVisible(true);
        });
    }
}
