package clinica;

/** Médico da clínica, com CRM e valor de consulta. */
public class Medico extends Pessoa {
    private String crm;
    private double valorConsulta;

    public Medico(String nome, String cpf, String email, String crm, double valorConsulta) {
        super(nome, cpf, email);
        this.crm = crm;
        setValorConsulta(valorConsulta);
    }

    @Override
    public String getPapel() { return "Medico"; }

    public String getCrm() { return crm; }
    public void setCrm(String crm) { this.crm = crm; }
    public double getValorConsulta() { return valorConsulta; }

    /** Valores negativos são ignorados. */
    public void setValorConsulta(double valorConsulta) {
        if (valorConsulta >= 0) {
            this.valorConsulta = valorConsulta;
        }
    }
}
