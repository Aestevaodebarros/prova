# Gabarito – Prova 02 de POO (questões teóricas e análise de código)

## Questão 2 – Teóricas sobre a Questão 1

**1. `Atendimento` é abstrata e implementa `Agendavel` sem implementar `calcularValor()`. Por quê?**
Uma classe abstrata pode deixar métodos sem implementação; ela não pode ser instanciada, então ninguém chamará um método incompleto. Aqui `Atendimento` implementa os métodos da interface (`agendar`, `cancelar`, `isAgendado`), pois são iguais para todos os atendimentos, e delega `calcularValor()`, que muda de tipo para tipo. `Consulta` e `Exame` são concretas e por isso **são obrigadas a sobrescrever `calcularValor()`**; sem isso, não compilam.

**2. Polimorfismo em `calcularFaturamentoTotal()` e a classe `Cirurgia`.**
O método percorre uma `List<Atendimento>` e chama `a.calcularValor()` sem saber se o objeto é `Consulta` ou `Exame`. Em tempo de execução a JVM escolhe a versão correta (ligação dinâmica). Para adicionar `Cirurgia`, basta criar `Cirurgia extends Atendimento` com seu `calcularValor()`. **Nenhuma classe existente muda**, nem `Clinica` (princípio aberto/fechado).

**3. Por que `Map<String, Paciente>` e não `List<Paciente>`?**
Com o `Map` (chave = CPF), `containsKey(cpf)` verifica duplicidade em tempo praticamente constante (O(1) no `HashMap`). Com a `List` seria preciso percorrer todos os pacientes comparando CPFs (O(n)). Além disso, o `Map` impede chaves repetidas e permite buscar por CPF diretamente com `get`.

**4. Validar o CPF no setter e não na tela.**
O setter garante a regra **em qualquer lugar do sistema** (Swing, console, testes, outro módulo): é o objeto que protege seu próprio estado (encapsulamento). Se a validação ficar só na tela, qualquer outro código pode criar um `Paciente` com CPF inválido, e o sistema passa a ter dados inconsistentes. Também evita duplicar a regra em vários pontos.

**5. Por que `Atendimento` tem `Paciente` e `Medico` como atributos, em vez de herdar?**
Herança expressa **"é um"**: `Paciente` *é uma* `Pessoa`. Um atendimento **não é** um paciente nem um médico; ele **tem um** paciente e **tem um** médico (associação/composição). Herdar geraria hierarquia incoerente (e Java não permite herança múltipla de classes para herdar dos dois).

## Questão 4 – Teóricas gerais

**1. `Produto p = new Eletronico(...)`; `p.getGarantiaMeses()` é possível?**
Não. O compilador verifica o tipo da **referência** (`Produto`), que não possui `getGarantiaMeses()`. Solução com downcasting seguro:
```java
if (p instanceof Eletronico) {
    Eletronico e = (Eletronico) p;
    System.out.println(e.getGarantiaMeses());
}
```
O upcasting (`Eletronico` → `Produto`) é automático e seguro; o downcasting precisa de cast explícito e de `instanceof`, senão pode lançar `ClassCastException`.

**2. Por que só uma classe pode ser estendida, mas várias interfaces podem ser implementadas?**
Para evitar o "problema do diamante": se duas superclasses tivessem o mesmo método com implementações diferentes, a subclasse não saberia qual herdar (e haveria ambiguidade de estado, pois classes têm atributos). Interfaces definem apenas **contratos** (sem estado de instância), então combinar vários contratos não gera ambiguidade. Assim uma classe pode estender uma superclasse e implementar vários comportamentos, como `Gerente extends Funcionario implements Bonificavel, Tributavel`.

**3. Risco de `List` sem Generics.**
O compilador não checa o tipo dos elementos; qualquer objeto entra e o erro só aparece em tempo de execução:
```java
List lista = new ArrayList();
lista.add("texto");
lista.add(new Gerente("Ana", 5000));
Gerente g = (Gerente) lista.get(0); // ClassCastException em execução
```
Com `List<Gerente>`, o `add("texto")` nem compila.

**4. Sobrecarga × sobrescrita.**
- **Sobrescrita (override):** a subclasse redefine um método da superclasse/interface com a **mesma assinatura**, com `@Override`. Ex.: `Consulta.calcularValor()` e `Exame.calcularValor()` redefinem `Atendimento.calcularValor()`. Resolvida em tempo de execução.
- **Sobrecarga (overload):** mesmo nome com **parâmetros diferentes** na mesma classe. Ex.: `venderProduto(String codigo, int qtd)` e uma variante `venderProduto(String codigo)` (vende 1 unidade). Resolvida em tempo de compilação.

## Questão 5 – Análise e correção de código

| # | Erro | Explicação | Correção |
|---|------|-----------|----------|
| 1 | `public abstract double calcularSalario() { ... }` | Método abstrato **não pode ter corpo**. | `public abstract double calcularSalario();` |
| 2 | Construtor de `Gerente` não chama `super(...)` | `Funcionario` não tem construtor padrão; o construtor da superclasse precisa ser chamado explicitamente. Além disso, atribuir campos da superclasse diretamente quebra a responsabilidade da superclasse. | `super(nome, salarioBase);` |
| 3 | `double calcularBonus()` sem `public` | Métodos de interface são `public`; ao implementar, não se pode **reduzir a visibilidade** (aqui, pacote). Erro de compilação. Falta também `@Override`. | `@Override public double calcularBonus()` |
| 4 | `List lista = new ArrayList();` e `for (Object o ...) o.calcularSalario()` | *Raw type*: sem Generics não há checagem de tipo, e `Object` não possui `calcularSalario()`. | `List<Funcionario> lista = new ArrayList<>();` e `for (Funcionario f : lista)` |
| 5 | `new Funcionario("Beto", 2000)` | **Classe abstrata não pode ser instanciada.** | Instanciar uma subclasse concreta (`new Gerente(...)`) |
| 6 | `public String nome;` | Atributo público quebra o **encapsulamento**: qualquer classe altera o valor sem controle. | `private String nome;` + `getNome()` |

Código corrigido: veja `src/folha/`.
