package clinica;

/** Consulta: valor do médico, com 30% de desconto se o paciente tiver plano. */
public class Consulta extends Atendimento {

    public Consulta(String codigo, Paciente paciente, Medico medico) {
        super(codigo, paciente, medico);
    }

    @Override
    public double calcularValor() {
        double valor = getMedico().getValorConsulta();
        if (getPaciente().temPlano()) {
            valor = valor * 0.70;
        }
        return valor;
    }
}
