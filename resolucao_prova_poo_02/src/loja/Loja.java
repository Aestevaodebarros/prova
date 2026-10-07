package loja;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/** Loja que organiza os produtos por categoria: Map<categoria, lista de produtos>. */
public class Loja {
    private Map<String, List<Produto>> catalogo = new LinkedHashMap<>();

    /** Cria a lista da categoria caso ainda não exista. */
    public void adicionarProduto(Produto p) {
        List<Produto> lista = catalogo.get(p.getCategoria());
        if (lista == null) {
            lista = new ArrayList<>();
            catalogo.put(p.getCategoria(), lista);
        }
        lista.add(p);
    }

    /** Baixa o estoque; false se o produto não existe ou a quantidade é insuficiente. */
    public boolean venderProduto(String codigo, int qtd) {
        if (qtd <= 0) {
            return false;
        }
        for (List<Produto> lista : catalogo.values()) {
            for (Produto p : lista) {
                if (p.getCodigo().equals(codigo)) {
                    if (p.getQuantidadeEstoque() < qtd) {
                        return false;
                    }
                    p.setQuantidadeEstoque(p.getQuantidadeEstoque() - qtd);
                    return true;
                }
            }
        }
        return false;
    }

    /** Soma imposto x estoque por categoria. Produto não implementa Tributavel, então usa instanceof. */
    public Map<String, Double> calcularImpostoPorCategoria() {
        Map<String, Double> resultado = new LinkedHashMap<>();
        for (Map.Entry<String, List<Produto>> entrada : catalogo.entrySet()) {
            double soma = 0;
            for (Produto p : entrada.getValue()) {
                if (p instanceof Tributavel) {
                    soma += ((Tributavel) p).calcularImposto() * p.getQuantidadeEstoque();
                }
            }
            resultado.put(entrada.getKey(), soma);
        }
        return resultado;
    }

    /** Produtos (de todas as categorias) com estoque menor que o limite. */
    public List<Produto> listarEstoqueBaixo(int limite) {
        List<Produto> baixos = new ArrayList<>();
        for (List<Produto> lista : catalogo.values()) {
            for (Produto p : lista) {
                if (p.getQuantidadeEstoque() < limite) {
                    baixos.add(p);
                }
            }
        }
        return baixos;
    }
}
