package clinica;

/** Exame: custo base + 15% de taxa de laboratório. */
public class Exame extends Atendimento {
    private String tipoExame;
    private double custoBase;

    public Exame(String codigo, Paciente paciente, Medico medico, String tipoExame, double custoBase) {
        super(codigo, paciente, medico);
        this.tipoExame = tipoExame;
        this.custoBase = custoBase;
    }

    @Override
    public double calcularValor() {
        return custoBase * 1.15;
    }

    public String getTipoExame() { return tipoExame; }
    public void setTipoExame(String tipoExame) { this.tipoExame = tipoExame; }
    public double getCustoBase() { return custoBase; }
    public void setCustoBase(double custoBase) { this.custoBase = custoBase; }
}
