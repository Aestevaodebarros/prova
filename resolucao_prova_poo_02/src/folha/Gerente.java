package folha;

public class Gerente extends Funcionario implements Bonificavel {

    public Gerente(String nome, double salarioBase) {
        super(nome, salarioBase); // ERRO 2 corrigido: chama o construtor da superclasse
    }

    @Override
    public double calcularSalario() {
        return salarioBase + calcularBonus();
    }

    @Override
    public double calcularBonus() { // ERRO 3 corrigido: public
        return salarioBase * 0.20;
    }
}
