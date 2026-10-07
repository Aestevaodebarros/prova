package clinica;

/** Atendimento genérico. Implementa Agendavel, mas deixa calcularValor() para as subclasses. */
public abstract class Atendimento implements Agendavel {
    private String codigo;
    private Paciente paciente; // associação ("tem um")
    private Medico medico;     // associação ("tem um")
    private String dataHora;
    private boolean agendado;

    public Atendimento(String codigo, Paciente paciente, Medico medico) {
        this.codigo = codigo;
        this.paciente = paciente;
        this.medico = medico;
    }

    /** Cada tipo de atendimento calcula seu valor de forma diferente. */
    public abstract double calcularValor();

    /** Falha se o atendimento já estiver agendado. */
    @Override
    public boolean agendar(String dataHora) {
        if (agendado) {
            return false;
        }
        this.dataHora = dataHora;
        this.agendado = true;
        return true;
    }

    @Override
    public void cancelar() {
        this.agendado = false;
        this.dataHora = null;
    }

    @Override
    public boolean isAgendado() { return agendado; }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public Paciente getPaciente() { return paciente; }
    public void setPaciente(Paciente paciente) { this.paciente = paciente; }
    public Medico getMedico() { return medico; }
    public void setMedico(Medico medico) { this.medico = medico; }
    public String getDataHora() { return dataHora; }
    public void setDataHora(String dataHora) { this.dataHora = dataHora; }

    @Override
    public String toString() {
        return String.format("[%s] %s | Paciente: %s | Medico: %s | Data: %s | Agendado: %s | Valor: R$ %.2f",
                codigo, getClass().getSimpleName(), paciente.getNome(), medico.getNome(),
                dataHora, agendado ? "sim" : "nao", calcularValor());
    }
}
