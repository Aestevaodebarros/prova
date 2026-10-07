package clinica;

/** Classe abstrata que representa qualquer pessoa da clínica. */
public abstract class Pessoa {
    private String nome;
    private String cpf;
    private String email;

    public Pessoa(String nome, String cpf, String email) {
        this.nome = nome;
        setCpf(cpf); // reutiliza a validação do setter
        this.email = email;
    }

    /** Papel da pessoa no sistema (ex.: "Paciente", "Medico"). */
    public abstract String getPapel();

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCpf() { return cpf; }

    /** Só aceita CPF com exatamente 11 caracteres; senão mantém o valor anterior. */
    public void setCpf(String cpf) {
        if (cpf != null && cpf.length() == 11) {
            this.cpf = cpf;
        }
    }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
