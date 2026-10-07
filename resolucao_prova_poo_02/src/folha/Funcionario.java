package folha;

/** Funcionário genérico (abstrata). */
public abstract class Funcionario {
    private String nome;               // ERRO 6 corrigido: private + getter
    protected double salarioBase;

    public Funcionario(String nome, double salarioBase) {
        this.nome = nome;
        this.salarioBase = salarioBase;
    }

    public abstract double calcularSalario(); // ERRO 1 corrigido: sem corpo

    public String getNome() { return nome; }
}
