package com.example.biblioteca;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Catalogo catalogo = new Catalogo();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean continua = true;

        while (continua) {
            System.out.println("1. Aggiungi elemento");
            System.out.println("2. Rimuovi elemento");
            System.out.println("3. Cerca per ISBN");
            System.out.println("4. Cerca per anno");
            System.out.println("5. Cerca per autore");
            System.out.println("6. Salva su disco");
            System.out.println("7. Carica da disco");
            System.out.println("8. Esci");

            int scelta = leggiIntero();
            switch (scelta) {
                case 1:
                    aggiungiElemento();
                    break;
                case 2:
                    rimuoviElemento();
                    break;
                case 3:
                    cercaPerIsbn();
                    break;
                case 4:
                    cercaPerAnno();
                    break;
                case 5:
                    cercaPerAutore();
                    break;
                case 6:
                    salvaSuDisco();
                    break;
                case 7:
                    caricaDaDisco();
                    break;
                case 8:
                    continua = false;
                    break;
                default:
                    System.out.println("Scelta non valida");
            }
        }
    }

    private static void aggiungiElemento() {
        System.out.println("Inserisci il tipo di elemento (libro/rivista):");
        String tipo = scanner.nextLine().toLowerCase();

        try {
            System.out.println("Inserisci ISBN:");
            String isbn = scanner.nextLine();
            System.out.println("Inserisci titolo:");
            String titolo = scanner.nextLine();
            System.out.println("Inserisci anno di pubblicazione:");
            int anno = scanner.nextInt();
            System.out.println("Inserisci numero di pagine:");
            int pagine = scanner.nextInt();
            scanner.nextLine();

            if (tipo.equals("libro")) {
                System.out.println("Inserisci autore:");
                String autore = scanner.nextLine();
                System.out.println("Inserisci genere:");
                String genere = scanner.nextLine();
                Libro libro = new Libro(isbn, titolo, anno, pagine, autore, genere);
                catalogo.aggiungiElemento(libro);
            } else if (tipo.equals("rivista")) {
                System.out.println("Inserisci periodicità (SETTIMANALE/MENSILE/SEMESTRALE):");
                Rivista.Periodicita periodicita = Rivista.Periodicita.valueOf(scanner.nextLine().toUpperCase());
                Rivista rivista = new Rivista(isbn, titolo, anno, pagine, periodicita);
                catalogo.aggiungiElemento(rivista);
            } else {
                System.out.println("Tipo non valido");
            }
        } catch (InputMismatchException e) {
            System.out.println("Input non valido");
            scanner.nextLine(); // Consuma il newline
        }
    }

    private static void rimuoviElemento() {
        System.out.println("Inserisci ISBN dell'elemento da rimuovere:");
        String isbn = scanner.nextLine();
        catalogo.rimuoviElemento(isbn);
    }

    private static void cercaPerIsbn() {
        System.out.println("Inserisci ISBN:");
        String isbn = scanner.nextLine();
        ElementoCatalogo elemento = catalogo.cercaPerIsbn(isbn);
        if (elemento != null) {
            System.out.println(elemento);
        } else {
            System.out.println("Elemento non trovato");
        }
    }

    private static void cercaPerAnno() {
        System.out.println("Inserisci anno di pubblicazione:");
        int anno = leggiIntero();
        List<ElementoCatalogo> risultati = catalogo.cercaPerAnno(anno);
        risultati.forEach(System.out::println);
    }

    private static void cercaPerAutore() {
        System.out.println("Inserisci autore:");
        String autore = scanner.nextLine();
        List<ElementoCatalogo> risultati = catalogo.cercaPerAutore(autore);
        risultati.forEach(System.out::println);
    }

    private static void salvaSuDisco() {
        System.out.println("Inserisci il percorso del file:");
        String percorso = scanner.nextLine();
        try {
            catalogo.salvaSuDisco(percorso);
            System.out.println("Salvataggio completato");
        } catch (IOException e) {
            System.out.println("Errore durante il salvataggio: " + e.getMessage());
        }
    }

    private static void caricaDaDisco() {
        System.out.println("Inserisci il percorso del file:");
        String percorso = scanner.nextLine();
        try {
            catalogo.caricaDaDisco(percorso);
            System.out.println("Caricamento completato");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Errore durante il caricamento: " + e.getMessage());
        }
    }

    private static int leggiIntero() {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Input non valido, inserisci un numero intero:");
                scanner.nextLine();
            }
        }
    }
}

}