package loja;

import java.util.List;
import java.util.Map;

/** Classe principal da Questão 3. */
public class SistemaLoja {
    public static void main(String[] args) {
        Loja loja = new Loja();
        loja.adicionarProduto(new Eletronico("E1", "Notebook", 3000.0, 5, 12));
        loja.adicionarProduto(new Eletronico("E2", "Fone", 200.0, 2, 6));
        loja.adicionarProduto(new Alimento("A1", "Arroz 5kg", 25.0, 40, "01/2027"));
        loja.adicionarProduto(new Alimento("A2", "Feijao 1kg", 8.0, 3, "03/2027"));

        System.out.println("Vender 2 notebooks: " + loja.venderProduto("E1", 2));   // true -> sobra 3
        System.out.println("Vender 10 fones: " + loja.venderProduto("E2", 10));     // false

        System.out.println("\nImposto por categoria:");
        for (Map.Entry<String, Double> e : loja.calcularImpostoPorCategoria().entrySet()) {
            System.out.printf("  %s: R$ %.2f%n", e.getKey(), e.getValue());
        }

        System.out.println("\nEstoque abaixo de 5:");
        List<Produto> baixos = loja.listarEstoqueBaixo(5);
        for (Produto p : baixos) {
            System.out.println("  " + p);
        }
    }
}
