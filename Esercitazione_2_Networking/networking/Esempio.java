import networking.Network;

/**
 * Classe con un main di esempio
 */

public class Esempio {

	public static void main(String[] args) {
		
		System.out.println("Creazione rete e impostazione degli attributi\n\n");

		Network n = new Network("TO-1", "Piemonte", 100);
		
		String codice = n.getCodice();
		String regione = n.getRegione();
		int numeroMassimoUtenti = n.getNumeroMassimoUtenti();
		
		System.out.println("Creata rete " + codice + " operativa nella regione " + regione + " che puo registrare un massimo di " + numeroMassimoUtenti + " utenti\n\n");
		System.out.println("Impostazione numero di decolli e descrizione dell'aeroporto\n\n");
		
		n.setNumeroMassimoCollegamenti(2);
		String descrizionenetwork = n.descrizioneNetwork();
		System.out.println("Descrizione network: " + descrizionenetwork + "\n\n");
		
		System.out.println("Creazione di tre utenti\n\n");
		String infoUtetnte1 = n.nuovoUtente("CDCFSCL1", "Fabio", "Garcea", "Dottorando");
		String infoUtetnte2 = n.nuovoUtente("CDCFSCL2", "Fabrizio", "Lamberti", "Professore");
		String infoUtetnte3 = n.nuovoUtente("CDCFSCL3", "Mario", "Rossi", "Ingegnere Informatico");
		
		System.out.println("Creati gli utenti con le seguenti descrizioni");
		System.out.println("Informazioni utente 1: " + infoUtetnte1 +"\n\n");
		System.out.println("Informazioni utente 2: " + infoUtetnte2 +"\n\n");
		System.out.println("Informazioni utente 3: " + infoUtetnte3 +"\n\n");
		
		String utenteTrovato = n.utente("CDCFSCL2");
		System.out.println("Trovato l'utente con la seguente descrizione");
		System.out.println("Informazioni utente: " + utenteTrovato +"\n\n");
		
		System.out.println("Stampa utenti\n\n");
		if(n.utenti() != null)
			for (String s : n.utenti())
				if (s != null)
					System.out.println(s);
		
		
		System.out.println("\n\nCreazione di un collegamento\n\n");
		String collegamento1 = n.nuovoCollegamento("CDCFSCL1", "CDCFSCL3", "17/09/2021");
		
		System.out.println("\n\nCreazione di un collegamento\n\n");
		String collegamento2 = n.nuovoCollegamento("CDCFSCL1", "CDCFSCL2", "31/12/2021");
		
		System.out.println("Collegamenti creati\n\n");
		System.out.println("Collegamento 1: " + collegamento1);
		System.out.println("Collegamento 2: " + collegamento2);
		
		
		System.out.println("Ricerca di un collegamento\n\n");
		boolean collegamentoTrovato = n.collegamento("CDCFSCL2", "CDCFSCL1");
		System.out.println("I due profili sono collegati: " + collegamentoTrovato);
		
		System.out.println("Elenco dei collegamenti:\n\n");
		String collegamenti = n.collegamenti();
		System.out.println(collegamenti);
		
		System.out.println("\n\nElenco dei collegamenti che coinvolgono l'occupazione Ingegnere Informatico:\n\n");
		String collegamentiPerOccupazione = n.collegamentiPerOccupazione("Ingegnere Informatico");
		System.out.println(collegamentiPerOccupazione);
	} 
}
