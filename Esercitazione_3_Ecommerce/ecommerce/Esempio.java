import ecommerce.*;
 
public class Esempio {
 
    public static void main(String[] args) {
        
        Ecommerce e = new Ecommerce(500);
 
        // R1. Ecommerce
        
        System.out.println("Creato prodotto");
        Prodotto p1 = e.nuovoProdotto("P1", "Scarpe da tennis", 79.99, 100);
 
        System.out.println("Codice prodotto: "+p1.getCodiceProdotto());
        System.out.println("Descrizione: "+p1.getDescrizione());
        System.out.println("Prezzo unitario: "+p1.getPrezzoUnitario());
        System.out.println("Numero di unita: "+p1.getNumeroUnita());
 
        System.out.println("\nDefinito altro prodotto");
        Prodotto p2 = e.nuovoProdotto("P2", "Palline da tennis", 4.99, 250);
 
        System.out.println("Codice prodotto: "+p2.getCodiceProdotto());
        System.out.println("Descrizione: "+p2.getDescrizione());
        System.out.println("Prezzo unitario: "+p2.getPrezzoUnitario());
        System.out.println("Numero di unita: "+p2.getNumeroUnita());
 
        System.out.println("\nRicerca prodotto P1");
        Prodotto prodottoTrovato = e.cercaProdotto("P1");
 
        System.out.println("\nInformazioni prodotto trovato");
        System.out.println("Codice prodotto: "+prodottoTrovato.getCodiceProdotto());
        System.out.println("Descrizione: "+prodottoTrovato.getDescrizione());
        System.out.println("Prezzo unitario: "+prodottoTrovato.getPrezzoUnitario());
        System.out.println("Numero di unita: "+prodottoTrovato.getNumeroUnita());
        
        System.out.println("\nRicerca prodotti contenenti 'nis'");
        
        Prodotto prodottiTrovati[] = e.cercaProdotti("nis");
 
        System.out.println("\nInformazioni prodotti trovati");
        for(Prodotto p : prodottiTrovati)
            if(p!=null) {
                System.out.println("Codice prodotto: "+p.getCodiceProdotto());
                System.out.println("Descrizione: "+p.getDescrizione());
                System.out.println("Prezzo unitario: "+p.getPrezzoUnitario());
                System.out.println("Numero di unita: "+p.getNumeroUnita() + "\n\n");
            }
                
        
        // R2. Utenti e Acquisti
        
        System.out.println("\nDefinito utente");
        e.nuovoUtente("CDCFSCL1", "Garcea", "Fabio", 500.0);
 
        System.out.println("\nRicerca utente CDCFSCL1");
 
        Utente uTrovato1 = e.cercaUtente("CDCFSCL1");
        System.out.println("Codice Fiscale: "+uTrovato1.getCodiceFiscale());
        System.out.println("Cognome: "+uTrovato1.getCognome());
        System.out.println("Nome: "+uTrovato1.getNome());
        System.out.println("Disponibilita: "+uTrovato1.getDisponibilita());
 
        System.out.println("\nAcquisto per utente con codice CDCFSCL1");
        
        String[] acquisti = {"P1;2", "P2;30"};
 
        e.acquisto("CDCFSCL1", acquisti);
 
        System.out.println("\nUltimo acquisto utente CDCFSCL1");
        String ultimoAcquisto = e.ultimoAcquisto("CDCFSCL1");
        System.out.println(ultimoAcquisto);
        
        System.out.println("\nAcquisti utente CDCFSCL1");
        String acquistiUtente = e.acquistiUtente("CDCFSCL1");
        System.out.println(acquistiUtente);
        
        System.out.println("\nUtenti che hanno acquistato il prodotto P1");
        String utentiProdotto = e.utentiProdotto("P1");
        System.out.println(utentiProdotto);
        
        
        // R3. Consegne
        
        System.out.println("\nNuova consegna per acquisto 1");
        e.nuovaConsegna(1, "Via Roma 10");
        
        System.out.println("\nRicerca consegna S1");
        String consegnaTrovata = e.descriviConsegna("S1");
        System.out.println(consegnaTrovata);
                
    }
 
}