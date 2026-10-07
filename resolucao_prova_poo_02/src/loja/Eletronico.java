package loja;

/** Eletrônico: imposto de 18% sobre o preço. */
public class Eletronico extends Produto implements Tributavel {
    private int garantiaMeses;

    public Eletronico(String codigo, String nome, double preco, int estoque, int garantiaMeses) {
        super(codigo, nome, preco, estoque);
        this.garantiaMeses = garantiaMeses;
    }

    @Override
    public double calcularImposto() { return getPreco() * 0.18; }

    @Override
    public String getCategoria() { return "Eletronico"; }

    public int getGarantiaMeses() { return garantiaMeses; }
    public void setGarantiaMeses(int garantiaMeses) { this.garantiaMeses = garantiaMeses; }
}
