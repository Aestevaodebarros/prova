package clinica;

/** Contrato para tudo que pode ser agendado/cancelado. */
public interface Agendavel {
    boolean agendar(String dataHora);
    void cancelar();
    boolean isAgendado();
}
