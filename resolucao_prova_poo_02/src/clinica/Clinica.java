package clinica;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** Gerencia pacientes (Map por CPF) e atendimentos (List). */
public class Clinica {
    private List<Atendimento> atendimentos = new ArrayList<>();
    private Map<String, Paciente> pacientes = new HashMap<>(); // chave = CPF

    /** Retorna false se o CPF for inválido (nulo) ou já estiver cadastrado. */
    public boolean cadastrarPaciente(Paciente p) {
        if (p == null || p.getCpf() == null || pacientes.containsKey(p.getCpf())) {
            return false;
        }
        pacientes.put(p.getCpf(), p);
        return true;
    }

    public void registrarAtendimento(Atendimento a) {
        atendimentos.add(a);
    }

    public List<Atendimento> listarPorPaciente(String cpf) {
        List<Atendimento> resultado = new ArrayList<>();
        for (Atendimento a : atendimentos) {
            if (a.getPaciente().getCpf().equals(cpf)) {
                resultado.add(a);
            }
        }
        return resultado;
    }

    /** Soma polimórfica: só atendimentos agendados são faturados. */
    public double calcularFaturamentoTotal() {
        double total = 0;
        for (Atendimento a : atendimentos) {
            if (a.isAgendado()) {
                total += a.calcularValor();
            }
        }
        return total;
    }

    /** Cancela os atendimentos agendados do paciente e retorna quantos foram cancelados. */
    public int cancelarTodos(String cpf) {
        int cancelados = 0;
        for (Atendimento a : listarPorPaciente(cpf)) {
            if (a.isAgendado()) {
                a.cancelar();
                cancelados++;
            }
        }
        return cancelados;
    }

    public Map<String, Paciente> getPacientes() { return pacientes; }
    public List<Atendimento> getAtendimentos() { return atendimentos; }
}
