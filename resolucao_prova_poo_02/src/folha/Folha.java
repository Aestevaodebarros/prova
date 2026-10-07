package folha;

import java.util.ArrayList;
import java.util.List;

public class Folha {
    public static void main(String[] args) {
        List<Funcionario> lista = new ArrayList<>(); // ERRO 4 corrigido: Generics
        lista.add(new Gerente("Ana", 5000));
        lista.add(new Gerente("Beto", 2000));        // ERRO 5 corrigido: instancia classe concreta

        for (Funcionario f : lista) {
            System.out.println(f.getNome() + ": " + f.calcularSalario());
        }
    }
}
