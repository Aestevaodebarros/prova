package clinica;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.*;

/** Tela Swing para cadastrar pacientes, listá-los e ver o faturamento. */
public class TelaClinica extends JFrame {
    private final Clinica clinica = new Clinica();
    private final JTextField txtNome = new JTextField();
    private final JTextField txtCpf = new JTextField();
    private final JTextField txtEmail = new JTextField();
    private final JTextField txtPlano = new JTextField();
    private final JTextArea areaSaida = new JTextArea(10, 40);

    public TelaClinica() {
        super("Clínica - Cadastro de Paciente");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(5, 5));

        JPanel form = new JPanel(new GridLayout(4, 2, 5, 5));
        form.add(new JLabel("Nome:"));   form.add(txtNome);
        form.add(new JLabel("CPF (11 dígitos):")); form.add(txtCpf);
        form.add(new JLabel("E-mail:")); form.add(txtEmail);
        form.add(new JLabel("Plano:"));  form.add(txtPlano);

        JButton btnCadastrar = new JButton("Cadastrar");
        JButton btnListar = new JButton("Listar Pacientes");
        JButton btnFaturamento = new JButton("Faturamento");
        JPanel botoes = new JPanel();
        botoes.add(btnCadastrar); botoes.add(btnListar); botoes.add(btnFaturamento);

        areaSaida.setEditable(false);
        JPanel topo = new JPanel(new BorderLayout());
        topo.add(form, BorderLayout.CENTER);
        topo.add(botoes, BorderLayout.SOUTH);
        add(topo, BorderLayout.NORTH);
        add(new JScrollPane(areaSaida), BorderLayout.CENTER);

        btnCadastrar.addActionListener(e -> cadastrar());
        btnListar.addActionListener(e -> listar());
        btnFaturamento.addActionListener(e -> areaSaida.setText(
                String.format("Faturamento total: R$ %.2f", clinica.calcularFaturamentoTotal())));
        pack();
        setLocationRelativeTo(null);
    }

    private void cadastrar() {
        Paciente p = new Paciente(txtNome.getText(), txtCpf.getText(), txtEmail.getText(), txtPlano.getText());
        if (p.getCpf() == null) { // o setter rejeitou o CPF
            JOptionPane.showMessageDialog(this, "CPF inválido (use 11 caracteres).", "Erro", JOptionPane.ERROR_MESSAGE);
        } else if (!clinica.cadastrarPaciente(p)) {
            JOptionPane.showMessageDialog(this, "CPF já cadastrado.", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Paciente cadastrado com sucesso!");
            txtNome.setText(""); txtCpf.setText(""); txtEmail.setText(""); txtPlano.setText("");
        }
    }

    private void listar() {
        StringBuilder sb = new StringBuilder();
        for (Paciente p : clinica.getPacientes().values()) {
            sb.append(p.getNome()).append(" - Plano: ")
              .append(p.temPlano() ? p.getPlanoSaude() : "(sem plano)").append("\n");
        }
        areaSaida.setText(sb.length() == 0 ? "Nenhum paciente cadastrado." : sb.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaClinica().setVisible(true));
    }
}
