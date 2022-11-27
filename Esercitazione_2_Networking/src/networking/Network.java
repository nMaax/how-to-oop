package networking;

public class Network {
	
	String codice;
	String regione;
	
	int numeroMassimoUtenti;
	int numeroMassimoCollegamenti;
	
	// Array per salvare tutti gli utenti creati
	public Utente[] utenti;
	
	// Indice che indica il primo posto libero nell' array utenti[], indica anche la quantitá di utenti registrati
	int iVuotoUtenti = 0;
	
	// Array per salvare tutti i collegamenti creati
	public Collegamento[] collegamenti;
	
	// Indice che indica il primo posto libero nell' array collegamenti[], indica anche la quantitá di collegamenti registrati
	int iVuotoCollegamenti = 0;
	
	
	public Network(String codice, String regione, int numeroMassimoUtenti) {
		this.codice = codice;
		this.regione = regione;
		this.numeroMassimoUtenti = numeroMassimoUtenti;
		utenti = new Utente[numeroMassimoUtenti];
	}

	public String getCodice() {
		return codice; 
	}

	public String getRegione() {
		return regione;
	}
	
	public int getNumeroMassimoUtenti () {
		return numeroMassimoUtenti;
	}
	
	public void setNumeroMassimoCollegamenti(int numeroMassimoCollegamenti){
		this.numeroMassimoCollegamenti = numeroMassimoCollegamenti;
		int collegamentiTotali = numeroMassimoUtenti * numeroMassimoUtenti; // Vincolo di Big-M
		collegamenti = new Collegamento[collegamentiTotali];
	}
	
	public String descrizioneNetwork() {
		String descrizione = codice+", "+regione+", "+numeroMassimoUtenti+", "+numeroMassimoCollegamenti;
		return descrizione;
	}
	
	public String nuovoUtente(String codiceFiscale, String nome, String cognome, String occupazione) {
	
		/*
		 * La gestione degli utenti avviene internamente al singolo network salvando degli oggetti
		 * di tipo Utente in un array interno alla classe Network
		 * 
		 * Dato che nell'esercizio non è richiesta l'interazione di qualsiasi specie tra utenti di 
		 * network diversi e/o tra network stessi posso scegliere un opzione meno elegate ma più facile da implementare:
		 * genero per ogni network ogni utente che deve essere inserito e ignoro il fatto che uno stesso utente presente
		 * in due network differenti sia, a conti fatti, lo stesso utente.
		 * 
		 * Fondamentalmente creo degli utenti doppioni e li considero in tutto e per tutto completamente diversi,
		 * se si aggiorna uno di questi in un network quello a cui corrisponde in un altro network NON verrà aggiornato
		 * se uno da una parte consuma i suoi collegamenti NON li consumerà anche nell'altro network
		 * etc.
		 * 
		 * */
		
		/*
		 * 1. Controllo che ci sia ancora spazio per aggiungere nuovi utenti, se c'è:
		 * 		a. Se l'utente è già presente
		 * 			Aggiorno i dati in cao
		 * 		b. Se l'utente NON è presente
		 * 			Lo aggiungo
		 * 
		 * Se non c'è spazio non faccio nulla 
		 * 
		 * Utilizzo la funzione utenti come return per assicurarmi che l'utente sia effettivamente registrato,
		 * se concatenassi semplicemente le stringhe non potrei sapere se la registrazione è avvenuta		
		 * 
		 * */
		
		boolean trovato = false;
		
		// Scorro l'array degli utenti
		for(int i=0; i<iVuotoUtenti; i++) {
			// Se l'utente già esiste
			if(codiceFiscale.equals(utenti[i].getCodiceFiscale())) {
				// Aggiorno il precedente con i nuovi dati
				utenti[i].setNome(nome);
				utenti[i].setCognome(cognome);
				utenti[i].setOccupazione(occupazione);
				trovato = true; // Segnalo al programma che l'utente esiste e lo ho aggiornato
			}
		}
		
		//Se l'utente non esiste ancora e nell'array vi è ancora spazio
		if (!trovato && iVuotoUtenti < numeroMassimoUtenti) {
			// Lo aggiungo
			Utente utente = new Utente(codiceFiscale, nome, cognome, occupazione);
			utenti[iVuotoUtenti] = utente;
			iVuotoUtenti = iVuotoUtenti + 1;
		}		
		
		return utente(codiceFiscale); // faccio fare il return a un altra funzione per ri-conferma
	}
	
	public String utente(String codiceFiscale) {
		
		Utente utenteTrovato = null;
		String output = "N"; // Se non trovo l'utente restituisco N (No)
		String codFisUtenteControllato;
		
		
		for(int i=0; i < iVuotoUtenti; i++) {
			codFisUtenteControllato = utenti[i].getCodiceFiscale();
			if(codiceFiscale.equals(codFisUtenteControllato)) {
				utenteTrovato = utenti[i];
			}
		}
		
		if(utenteTrovato != null) {
			output = utenteTrovato.getCodiceFiscale()+", "+utenteTrovato.getNome()+", "+utenteTrovato.getCognome()+", "+utenteTrovato.getOccupazione();
		}
		
		return output;
	}

	public String[] utenti() {
		String[] codiciFiscali = new String[iVuotoUtenti];
		for(int i=0; i < iVuotoUtenti; i++) {
			codiciFiscali[i] = utenti[i].getCodiceFiscale();
		}
		return codiciFiscali;
	}

	public String nuovoCollegamento(String codiceFiscale1, String codiceFiscale2, String data) {
		
		/*
		 * 	Controllo se: 
		 * 		
		 * 		1. entrmabi gli utenti esistono
		 * 			a. entrambi gli utenti possono creare un nuovo collegamento (hanno finito i coll. max?)
		 * 			b. il collegamento gia' esiste
		 * 
		 * 	In caso genero il collegamento;
		 * 
		 *  Altrimenti restituisco la Stringa "N" o un collegamento con codice -1 se il collegamento era già presente
		 *
		 * */
		
		// Mi salvo quanti collegamenti hanno già "consumato" i due utenti
		
		String result = "N"; // No
		int codiceCollegamento = -1;
		
		int numCollegamentiUtente1 = getNumCollegamentiUtente(codiceFiscale1);
		int numCollegamentiUtente2 = getNumCollegamentiUtente(codiceFiscale2);
		
		// Se esistono (numero di collegamenti != -1 - vedi getNumCollegamentiUtente()), hanno spazio e non sono già stati collegati... 
		
		// Con l'if mi chiedo:
		// L'utente 1 ha superato il tetto massimo di collegamenti?
		// L'utente 2 ha superato il tetto massimo di collegamenti?
		// I due utenti sono già collegati?
		// I due utenti inseriti sono lo stesso utente? 
		
		/*Distriuisci questa riga in più righe se ne hai bisgono*/
		if(	numCollegamentiUtente1 >= 0 && numCollegamentiUtente1 < numeroMassimoCollegamenti && numCollegamentiUtente2 >= 0 && numCollegamentiUtente2 < numeroMassimoCollegamenti && collegamento(codiceFiscale1, codiceFiscale2) == false && !codiceFiscale1.equals(codiceFiscale2) ){ 
			// Procedo con la creazione del collegamento
			codiceCollegamento = iVuotoCollegamenti;
			Collegamento collegamento = new Collegamento(codiceCollegamento, codiceFiscale1, codiceFiscale2, data);
			collegamenti[iVuotoCollegamenti] = collegamento;
			iVuotoCollegamenti = iVuotoCollegamenti + 1;	
		}
		
		// Delego il compito di creare la stringa di return a un metodo esterno per avere una controprova di un corretto funzionamento di questo metodo
		if (collegamento(codiceFiscale1, codiceFiscale2))
			result = codiceCollegamento + "-" + data;

		return result;
	}

	public boolean collegamento(String codiceFiscale1, String codiceFiscale2) {
		// Mi preparo la variabile di risultato
		boolean trovato = false;
		String utenteCollegamentoCF1;
		String utenteCollegamentoCF2;
		
		// Scorro l'array dei collegamenti
		for(int i=0; i < iVuotoCollegamenti; i++) {
			
			//Mi salvo i due specifi codici fiscali in delle variabili apposite
			utenteCollegamentoCF1 = collegamenti[i].getCodiceFiscale1();
			utenteCollegamentoCF2 = collegamenti[i].getCodiceFiscale2();
			
			// Controllo che ci sia match tra i codici fiscali, in entrambe le combinazioni
			/*Distriuisci questa riga in più righe se ne hai bisgono*/
			if(		(utenteCollegamentoCF1.equals(codiceFiscale1) && utenteCollegamentoCF2.equals(codiceFiscale2)) || (utenteCollegamentoCF1.equals(codiceFiscale2) && utenteCollegamentoCF2.equals(codiceFiscale1))) {
				// Se il match è positivo imposto la variabile di risultato a true
				trovato = true;
			}
		}
		// Restituisco il risultato
		return trovato;
	}
	
	public String collegamenti() {
		
		/*
		 * 1. Scorro l'array collegamenti
		 * 2. Estraggo i dati che mi servono
		 * 3. Concateno e aggiorno la Stringa di output nel formato richiesto
		 * 4. Restituisco la stringa
		 * */
		
		String output = "";
		int codice;
		String data;
		
		for (int i=0; i <iVuotoCollegamenti; i++) {
			codice = collegamenti[i].getCodiceCollegamento();
			data = collegamenti[i].getDataCollegamento();
			output = output + codice + "-" + data;
			if (i != iVuotoCollegamenti-1) {
				output = output + "\n";
			}
		}
		
		return output;
	}

	public String collegamentiPerOccupazione(String occupazione) {
		
		/*
		 * 1. Scorro l'array collegamenti
		 * 2. Se uno dei due utenti svolge l'occupazione passata per parametro
		 * 		a. Estraggo i dati che mi servono
		 * 		b. Concateno e aggiorno la Stringa di output nel formato richiesto
		 * 3. Altrimenti non modifico nulla
		 * 4. Restituisco la Stringa
		 * */
		
		// Mi preparo le variabili da utilizzare
		
		//Stringa di output che verrà restituita
		String output = "";
		
		// Variabili che serviranno a salvare la data e il codice del dato collegamento analizzato con i for
		int codice;
		String data;
		
		// Variabili che serviranno a salvare le occupazioni degli utenti presenti nel collegamento
		String occupazioneUtente1 = "";
		String occupazioneUtente2 = "";
		
		// Variabili che serviranno a salvare i codici fiscali degli utenti presenti nel dato collegamento
		String codiceFiscale1;
		String codiceFiscale2;
		
		// Scorro l'array collegamenti
		for (int i=0; i <iVuotoCollegamenti; i++) {
			
			// Mi estraggo i codici fiscali che trovo nel i-esimo collegamento
			codiceFiscale1 = collegamenti[i].getCodiceFiscale1();
			codiceFiscale2 = collegamenti[i].getCodiceFiscale2();
			
			// Scorro gli utenti
			for(int j=0; j<iVuotoUtenti; j++) {
				
				// Mi estraggo il singolo codice fiscale che trovo nell'i-esimo utente
				String codFisUtenteInScorrimento = utenti[j].getCodiceFiscale();
				
				// Se il codice fiscale del singolo utente corrisponde a uno dei due utenti in collegamento
				// Mi salvo la loro occupazione
				if(codFisUtenteInScorrimento.equals(codiceFiscale1)) {
					occupazioneUtente1 = utenti[j].getOccupazione();
				}
				else if(codFisUtenteInScorrimento.equals(codiceFiscale2)) {
					occupazioneUtente2 = utenti[j].getOccupazione();
				}
			}
			
			// Se l'occupazione di uno dei due utenti corrisponde a quella passata per parametro
			if (occupazioneUtente1.equals(occupazione) || occupazioneUtente2.equals(occupazione)) {
				
				// Aggiungo tale collegamento all'output
				codice = collegamenti[i].getCodiceCollegamento();
				data = collegamenti[i].getDataCollegamento();
				
				output = output + codice + "-" + data;
				
				// Aggiusto la parte finale per renderla idonea alle specifiche richieste
				if (i != iVuotoCollegamenti-1) {
					output = output + "\n";
				}
			}
			
			// Resetto le occupazioni per la prossima iterazione, altrimenti l'if precedente si attiverebbe
			occupazioneUtente1 = "";
			occupazioneUtente2 = "";
		}
		
		//Restituisco il risultato
		return output;
	}
	
	public int getNumCollegamentiUtente(String codiceFiscale) {
		
		/*
		 * Metodo a cui si fornisce il codiceFiscale di un qualsiasi utente e
		 * 
		 * 	se esiste restituisce quanti collegamenti ha consumato all'interno del network
		 * 	se NON esiste restituisce -1
		 * 		
		 * */
		
		int collegamentiIndividuati = -1;
		
		if(!utente(codiceFiscale).equals("N")) {
			collegamentiIndividuati = 0;
			for (int i=0; i<iVuotoCollegamenti; i++) {
				if(collegamenti[i].getCodiceFiscale1().equals(codiceFiscale) || collegamenti[i].getCodiceFiscale2().equals(codiceFiscale)) {
					collegamentiIndividuati = collegamentiIndividuati + 1;
				}
			}
		}
		
		return collegamentiIndividuati;
	}
}

