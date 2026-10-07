# Resolução – Prova 02 de POO

```
resolucao_prova_poo_02/
├── RESPOSTAS_TEORICAS.md   # Q2, Q4 e Q5 (erros explicados)
└── src/
    ├── clinica/            # Q1 – Clínica Médica (inclui TelaClinica.java, Swing)
    ├── loja/               # Q3 – Loja com Map<String, List<Produto>>
    └── folha/              # Q5 – código corrigido
```

## Como compilar e executar (JDK 11+)
```bash
mkdir out
javac -d out $(find src -name "*.java")      # Linux/Mac
java -cp out clinica.MainClinica     # demo da Q1 em console
java -cp out clinica.TelaClinica     # tela Swing da Q1
java -cp out loja.SistemaLoja        # Q3
java -cp out folha.Folha             # Q5 corrigida
```
No NetBeans: crie um projeto Java e copie as pastas `clinica`, `loja` e `folha` para `Source Packages`.

## Decisões de interpretação (Q1)
- O faturamento da clínica soma **apenas atendimentos agendados**, para que `cancelarTodos` tenha efeito sobre o total.
- O setter de CPF rejeita valores inválidos mantendo o anterior; se o CPF inicial for inválido, o campo fica `null` e `cadastrarPaciente` recusa o paciente.
