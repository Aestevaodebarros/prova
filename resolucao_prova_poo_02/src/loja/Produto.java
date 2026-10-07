package loja;

/** Produto genérico da loja. */
public abstract class Produto {
    private String codigo;
    private String nome;
    private double preco;
    private int quantidadeEstoque;

    public Produto(String codigo, String nome, double preco, int quantidadeEstoque) {
        this.codigo = codigo;
        this.nome = nome;
        this.preco = preco;
        setQuantidadeEstoque(quantidadeEstoque);
    }

    /** Categoria usada como chave do Map na Loja. */
    public abstract String getCategoria();

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public double getPreco() { return preco; }
    public void setPreco(double preco) { this.preco = preco; }
    public int getQuantidadeEstoque() { return quantidadeEstoque; }

    /** Não aceita valores negativos. */
    public void setQuantidadeEstoque(int quantidadeEstoque) {
        if (quantidadeEstoque >= 0) {
            this.quantidadeEstoque = quantidadeEstoque;
        }
    }

    @Override
    public String toString() {
        return String.format("%s - %s (R$ %.2f, estoque: %d)", codigo, nome, preco, quantidadeEstoque);
    }
}
