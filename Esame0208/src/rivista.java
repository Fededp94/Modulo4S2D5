public class rivista extends ElementoCatalogo {
    private static final long serialVersionUID = 1L;

    public enum Periodicita {
        SETTIMANALE, MENSILE, SEMESTRALE
    }

    private Periodicita periodicita;


    public Periodicita getPeriodicita() {
        return periodicita;
    }
}
