import java.io.Serializable;

public abstract class ElementoCatalogo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String isbn;
    private String titolo;
    private int annoPubblicazione;
    private int numeroPagine;


    public String getIsbn() {
        return isbn;
    }

    public String getTitolo() {
        return titolo;
    }

    public int getAnnoPubblicazione() {
        return annoPubblicazione;
    }

    public int getNumeroPagine() {
        return numeroPagine;
    }
}
