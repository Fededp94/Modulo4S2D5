import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Catalogo {


    public class Catalogo {
        private List<ElementoCatalogo> elementi;

        public Catalogo() {
            this.elementi = new ArrayList<>();
        }

        public void aggiungiElemento(ElementoCatalogo elemento) {
            elementi.add(elemento);
        }

        public void rimuoviElemento(String isbn) {
            elementi.removeIf(elemento -> elemento.getIsbn().equals(isbn));
        }

        public ElementoCatalogo cercaPerIsbn(String isbn) {
            return elementi.stream()
                    .filter(e -> e.getIsbn().equals(isbn))
                    .findFirst()
                    .orElse(null);
        }

        public List<ElementoCatalogo> cercaPerAnno(int anno) {
            return elementi.stream()
                    .filter(e -> e.getAnnoPubblicazione() == anno)
                    .collect(Collectors.toList());
        }

        public List<ElementoCatalogo> cercaPerAutore(String autore) {
            return elementi.stream()
                    .filter(e -> e instanceof Libro && ((Libro) e).getAutore().equals(autore))
                    .collect(Collectors.toList());
        }

        public void salvaSuDisco(String percorsoFile) throws IOException {
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(percorsoFile))) {
                oos.writeObject(elementi);
            }
        }

        public void caricaDaDisco(String percorsoFile) throws IOException, ClassNotFoundException {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(percorsoFile))) {
                elementi = (List<ElementoCatalogo>) ois.readObject();
            }
        }
    }
}
