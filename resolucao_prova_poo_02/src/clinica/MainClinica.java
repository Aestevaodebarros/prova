package clinica;

/** Demonstração em console da Questão 1 (sem Swing). */
public class MainClinica {
    public static void main(String[] args) {
        Clinica c = new Clinica();
        Paciente ana = new Paciente("Ana", "12345678901", "ana@email.com", "UnimedX");
        Paciente bia = new Paciente("Bia", "10987654321", "bia@email.com", "");
        Paciente invalido = new Paciente("Zé", "123", "ze@email.com", "");
        Medico dr = new Medico("Dr. Caio", "11122233344", "caio@email.com", "CRM-BA 123", 200.0);

        System.out.println("Cadastro Ana: " + c.cadastrarPaciente(ana));
        System.out.println("Cadastro Bia: " + c.cadastrarPaciente(bia));
        System.out.println("Cadastro Ana duplicada: " + c.cadastrarPaciente(ana));
        System.out.println("CPF inválido (cpf=" + invalido.getCpf() + "): " + c.cadastrarPaciente(invalido));

        Atendimento a1 = new Consulta("C1", ana, dr);  // 200 * 0.7 = 140
        Atendimento a2 = new Consulta("C2", bia, dr);  // 200
        Atendimento a3 = new Exame("E1", ana, dr, "Hemograma", 100.0); // 115
        c.registrarAtendimento(a1); c.registrarAtendimento(a2); c.registrarAtendimento(a3);

        System.out.println("Agendar a1: " + a1.agendar("10/10 08:00"));
        System.out.println("Agendar a1 de novo: " + a1.agendar("11/10 09:00"));
        a2.agendar("10/10 09:00");
        a3.agendar("12/10 07:30");

        System.out.println("\nAtendimentos da Ana:");
        for (Atendimento a : c.listarPorPaciente("12345678901")) {
            System.out.println(a);
        }
        System.out.printf("%nFaturamento total: R$ %.2f (esperado 455,00)%n", c.calcularFaturamentoTotal());
        System.out.println("Cancelados da Ana: " + c.cancelarTodos("12345678901"));
        System.out.printf("Faturamento após cancelar: R$ %.2f (esperado 200,00)%n", c.calcularFaturamentoTotal());
    }
}
