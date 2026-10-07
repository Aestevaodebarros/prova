package loja;

/** Alimento: imposto de 7% sobre o preço. */
public class Alimento extends Produto implements Tributavel {
    private String dataValidade;

    public Alimento(String codigo, String nome, double preco, int estoque, String dataValidade) {
        super(codigo, nome, preco, estoque);
        this.dataValidade = dataValidade;
    }

    @Override
    public double calcularImposto() { return getPreco() * 0.07; }

    @Override
    public String getCategoria() { return "Alimento"; }

    public String getDataValidade() { return dataValidade; }
    public void setDataValidade(String dataValidade) { this.dataValidade = dataValidade; }
}
