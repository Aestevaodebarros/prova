package clinica;

/** Paciente da clínica, possui plano de saúde (pode ser vazio). */
public class Paciente extends Pessoa {
    private String planoSaude;

    public Paciente(String nome, String cpf, String email, String planoSaude) {
        super(nome, cpf, email);
        this.planoSaude = planoSaude;
    }

    @Override
    public String getPapel() { return "Paciente"; }

    public String getPlanoSaude() { return planoSaude; }
    public void setPlanoSaude(String planoSaude) { this.planoSaude = planoSaude; }

    /** Indica se o paciente possui plano (diferente de nulo/vazio). */
    public boolean temPlano() {
        return planoSaude != null && !planoSaude.trim().isEmpty();
    }
}
