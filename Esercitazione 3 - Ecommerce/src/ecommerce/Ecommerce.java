package ecommerce;

import java.util.ArrayList;

/**
 * Nomenclatura: 
 *
 * 	Spesa != Costo
 * 		Costo: diminuzione di soldi
 * 		Spesa: costo per il cliente
 * 
 * 	Ordine != Consegna, 
 * 		Ordine: insieme di oggetti che sono stati acquistati --> Spesa ordine = spesa acquisto relativo all'ordine
 * 		Consegna: atto del trasporto stesso --> Spesa consegna = 10% della spessa relativa all'acquisto (se Prime) altrimenti uguale alla spesa dell'ordine (se Standard)
 * 	
 * 	Unita != Prodotti
 * 		In 500 iphone e 300 ipad ci sono 2 prodotti, 500 unita di iphone, 300 unita di ipad e 800 unita totali
 * */
public class Ecommerce {
	
	private int numeroMassimoUnitaInMagazzino;
	private int unitaInMagazzino = 0;
	
	private Utente[] utenti = new Utente[100];
	private int indexUtenteLibero = 0;

	private ArrayList<Prodotto> prodotti = new ArrayList<Prodotto>();	
	private ArrayList<Acquisto> acquisti = new ArrayList<Acquisto>();
	private ArrayList<Consegna> consegne = new ArrayList<Consegna>();
	
	/**
	 * Genera un nuovo e-commerce dove gli utenti sono salvati in un Array (massimo 100 utenti) 
	 * mentre i prodotti e gli acquisti in delle liste (ArrayList)
	 * 
	 * */
	public Ecommerce(int numeroMassimoUnita) {
		numeroMassimoUnita = denegativizeInteger(numeroMassimoUnita);
		this.numeroMassimoUnitaInMagazzino = numeroMassimoUnita;	
	}
	
	/**
	 * Crea un nuovo prodotto.
	 * Cercando di aggiungere un prodotto già esistente nel sistema, incrementa le unità per il
	 * prodotto in considerazione (fino a esaurimento spazio).
	 * Nel caso di incremento di almeno un’unità il metodo restituisce il
	 * riferimento all’oggetto aggiornato; diversamente, in caso di spazio terminato, il metodo non sortisce invece alcun effetto (e il
	 * valore di ritorno è null).
	 * 
	 * */
	public Prodotto nuovoProdotto(String codiceProdotto, String descrizione, double prezzoUnitario, int numeroUnita) {
		
		/*
		 * Definisco quanto spazio ho ancora in magazzino
		 * 
		 * Scorro la lista e vedo se il prodotto già esiste in magazzino
		 * 
		 * Se esiste
		 * 	Incremento la quantità fino a esaurimento spazio (min tra numeroUnita che si voglino aggiungere e lo spazio ancora disponibile in magazzino)
		 * 		  
		 * Se non esiste:
		 * 	Lo aggiungo fino a esaurimento spazio (min tra numeroUnita che si vogliono aggiungere e lo spazio ancora disponibile)
		 * 
		 * */
		
		int spazioDisponibileInMagazzino = numeroMassimoUnitaInMagazzino - unitaInMagazzino;
		int unitaDaAggiungere = Integer.min(numeroUnita, spazioDisponibileInMagazzino);
		
		boolean legalParameters = (numeroUnita >= 0) && (prezzoUnitario >= 0.0) && (spazioDisponibileInMagazzino > 0);
		
		Prodotto output = null;		
		
		// Se non c'é spazio in magazzino o il numero di unita da inserire è illegale: non faccio nulla
		if (legalParameters) {
			Prodotto foundProdotto = null;
			
			// Scorro la lista dei prodotti in cerca del prodotto passato per parametro
			for (Prodotto prodotto : prodotti) {
				boolean matchCodice = prodotto.getCodiceProdotto().equals(codiceProdotto);
				if (matchCodice) {
					foundProdotto = prodotto; 
				}	
			}
			
			// Semplifico l'if successivo scorpornado la fase di controllo booleana
			boolean trovato = (foundProdotto != null);
			
			//Se trovo il prodotto ne incremento le quantità (non modifico il resto perché il pdf NON lo chiede)
			if (trovato) {
				foundProdotto.add(unitaDaAggiungere);
				//unitaInMagazzino = unitaInMagazzino + unitaDaAggiungere;
				increaseUnitaMagazzino(unitaDaAggiungere);
				output = foundProdotto;
			// Se non ho trovato il prodotto lo creo
			} else {
				Prodotto prodotto = new Prodotto(codiceProdotto, descrizione, prezzoUnitario, unitaDaAggiungere);
				prodotti.add(prodotto);
				//unitaInMagazzino = unitaInMagazzino + unitaDaAggiungere;
				increaseUnitaMagazzino(unitaDaAggiungere);
				output = prodotto;
			}
		}
		
		return output;
		
	}
	

	/**
	 * Cerca il prodotto il cui codice è passato come parametro. 
	 * Il metodo restituisce il riferimento all’oggetto trovato (null nel caso il prodotto non sia definito)
	 * */
	public Prodotto cercaProdotto(String codiceProdotto) {
		
		/*
		 * Scorre la lista
		 * Se trova il prodotto aggiorna la variabile a esso relativa
		 * altrimenti rimane null
		 * */
		
		Prodotto prodottoTrovato = null;

		for (Prodotto prodotto : prodotti) {					
			String codiceProdottoInScorrimento = prodotto.getCodiceProdotto();
			
			if (codiceProdottoInScorrimento.equals(codiceProdotto)) {
				// Ho trovato il prodotto, lo salvo
				prodottoTrovato = prodotto;
			}
		}
		
		return prodottoTrovato;
	}

	/**
	 * Cerca tutti i prodotti che contengono la stringa passata come
	 * parametro – o parte di essa – nella descrizione del prodotto o nel codice alfanumerico
	 * (ignorando le differenze tra caratteri maiuscoli e minuscoli).
	 * Il metodo restituisce un array, della dimensione pari al numero di prodotti trovati, contenente i
	 * riferimenti agli oggetti che soddisfano il criterio di ricerca (riportati nell’ordine in cui i prodotti sono stati definiti
	 * */
	public Prodotto[] cercaProdotti(String daCercare) {
		
		/*
		 * Genera una lista da riempire con i prodotti individuati
		 * Scorre la lista prodotti
		 * A ogni iterazione 
		 * 		1. estrae codice e descrizione
		 * 		2. controlla (con il metodo String.contains()) se la string per parametro sia contenuta dentro la descrizione o il codice
		 * 		3. se la trova aggiunge il prodotto alla lista
		 * Convertiamo la lista in array e la restituiamo
		 * 
		 * */
		
		ArrayList<Prodotto> prodottiTrovati = new ArrayList<Prodotto>();
		
		for (Prodotto prodotto : prodotti) {
			
			// Non esiste un containsIgnoreCase(), quindi non posso usarlo
			
			String codProdotto = prodotto.getCodiceProdotto().toLowerCase();
			String desProdotto = prodotto.getDescrizione().toLowerCase();
			
			boolean codContienteStringa = codProdotto.contains(daCercare.toLowerCase());
			boolean desContieneStringa = desProdotto.contains(daCercare.toLowerCase());
			
			if (codContienteStringa || desContieneStringa) {
				prodottiTrovati.add(prodotto);
			}
		}
		
		int numProdottiTrovati = prodottiTrovati.size();
		Prodotto[] arrayProdotti = prodottiTrovati.toArray(new Prodotto[numProdottiTrovati]);
		
		return arrayProdotti;
	}
	
	
	/**
	 * Riceve come parametri il codice fiscale (di cui deve essere controllata l’univocità), il cognome, il nome 
	 * e la disponibilità economica dell’utente.
	 * Nel caso in cui esista già un utente con quel codice fiscale, vengono semplicemente
	 * aggiornate le informazioni e ne viene restituito il riferimento.
	 * */
	public Utente nuovoUtente(String codiceFiscale, String cognome, String nome, double disponibilita) {
		
		/*
		 * Scorro l'array
		 * 	Se trovo l'utente lo aggiorno e lo sengalo al programma con una boolean
		 * 
		 * Se non ho trovato l'utente: lo creo, lo metto alla fine dell'Array e incremento il contatore del posto libero
		 * 
		 * Nota bene: L'univocitá del codice fiscale viene indirettamente verificata con questo algoritmo
		 * 
		 * */
		
		Utente output = null;
		boolean trovato = false;
		boolean legalParameters = (disponibilita >= 0);
		
		if (legalParameters) {
			for (int i=0; i < indexUtenteLibero; i++) {
				// Posso togliere questo if?
				if (!trovato) {
					
					String codiceFiscaleInScorrimento = utenti[i].getCodiceFiscale();
					
					if (codiceFiscaleInScorrimento.equals(codiceFiscale)) {
						trovato = true;
						//utenti[i].setCognome(cognome);
						//utenti[i].setNome(nome);
						//utenti[i].setDisponibilita(disponibilita);
						utenti[i].setDati(cognome, nome, disponibilita);
						output = utenti[i];
					}
				}
			}
			
			if (!trovato && indexUtenteLibero < utenti.length) {
				output = new Utente(codiceFiscale, cognome, nome, disponibilita);
				utenti[indexUtenteLibero] = output;
				indexUtenteLibero = indexUtenteLibero + 1;
			}
		}
		
		return output;
	}
	
	/**
	 * Riceve come parametro un codice fiscale di un utente e restituisce l’oggetto corrispondente (oppure null).
	 * */
	public Utente cercaUtente(String codiceFiscale) {
		
		/*
		 * Imposta utente trovato a null
		 * Scorre l'array di utenti
		 * Se lo trova lo salva in utente trovato
		 * Restituisce l'utente trovato
		 * */
		
		Utente output = null;
		
		for (int i=0; i < indexUtenteLibero; i++) {
			String codiceFiscaleInScorrimento = utenti[i].getCodiceFiscale();
			if (codiceFiscaleInScorrimento.equals(codiceFiscale)) {
				output = utenti[i];
			}
		}
		
		return output;
	}
	
	/**
	 * Un utente acquista una serie di prodotti, in quantita variabile.
	 * 
	 * Nel caso in cui l’utente esista il metodo effettua un controllo sulla possibilità di portare a termine l’acquisto.
	 * 
	 * L’acquisto di ciascuno dei prodotti passati come parametro è infatti possibile solo se il codice prodotto corrisponde ad un prodotto esistente e se il
	 * numero di unità richieste è minore o uguale al numero di unità disponibili (si assuma che all’interno dell’array, lo stesso
	 * prodotto non possa comparire in più acquisti).
	 * 
	 * Infine, il metodo verifica che la spesa complessiva (per i prodotti e le unità che
	 * hanno superato la prima verifica) non superi la disponibilità economica dell’utente.
	 * 
	 * Inoltre, il metodo aggiorna le quantità dei prodotti e la disponibilità economica
	 * dell’utente, ed assegna a ciascuno degli acquisti un codice numerico incrementale (a partire da 1);
	 * tale codice identifica in modo univoco un determinato acquisto nel sistema di e-commerce
	 * 
	 * @param CodiceFiscale il codice fiscale dell’utente
	 * @param acquisti un array di stringhe identificanti una serie di acquisti. Ogni stringa riporta il codice di un prodotto e il numero di unità di quel prodotto che l’utente desidera acquistare separati dal carattere ’;’.
	 * @return un array di valori booleani della stessa lunghezza dell’array di stringhe passato come parametro; in corrispondenza
	 * della posizione di una coppia prodotto-unità il cui acquisto sia andato a buon fine l’array risultante riporterà il valore true,
	 * in caso di acquisto non riuscito il valore false. 
	 * Nel caso in cui la spesa complessiva superi la disponibilità
	 * economica dell’utente, il valore di ritorno è un array della stessa lunghezza riportante il valore false per ogni coppia prodottounità
	 * (nessuno degli acquisti va a buon fine).
	 * */
	public boolean[] acquisto(String codiceFiscale, String[] acquisti) {
		
		/*
		 * Creo un array di booleani vuoto
		 * 
		 * Controllo che l'utente esista
		 * Se true:
		 * 
		 * 	Creo una lista vuota di acquisti (opererá come lista temporanea "cache" dove salveró gli acquisti andati a buon fine)
		 * 
		 * 	Scorro l'array degli acquisti
		 * 			Controllo, ad ogni iterazione, che il prodotto esista e che vi sia disponibilita' in magazzino
		 * 					Se true: Salvo l'acquisto nella lista appena creata (con indice in sintonia con l'ultimo acquisto che trovo
		 * 							 nella lista Acquisti globale)
		 * 							 Aggiungo i valori bool true all array di output
		 * 							 Cumolo la spesa dei prodotti in una variabile double
		 * 					Se false: Aggiungo i valori bool false all array di output
		 * 
		 *	 Controllo, fuori dal ciclo, che la somma dei costi sia minore della disponibilita dell'utente
		 * 			Se true:
		 * 				Sottraggo alla disponibilita dell'utente la sua spesa
		 * 				Attraverso un ciclo:
		 * 						 Aggiungo gli acquisti unendo le due liste, nell'ordine opportuno
		 * 						 Sottraggo a ogni prodotto le sue quantità
		 * 						 Sottraggo al magazzino i posti occupati
		 * 
		 * 			Se false: Non faccio nulla (non salvo le modifiche)
		 * 
		 * 	Restituisco l'array di bool
		 * */
		
		int acquistiLength = acquisti.length;
		boolean[] output = new boolean[acquistiLength];
		
		Utente utenteInAcquisto = cercaUtente(codiceFiscale);
		boolean utenteExists = (utenteInAcquisto != null);
		
		if (utenteExists) {
			
			// Se l'utente esiste
			
			// Genero le mie variabili ausiliarie
			ArrayList<Acquisto> cacheAcquisti = new ArrayList<Acquisto>();
			double spesaTotaleAcquisti = 0.0;
			
			// Scorro l'array di stringhe Acquisti[] passato per parametro
			for (int i=0; i<acquistiLength; i++ ) {
				
				boolean accetableBuy = acquisti[i].contains(";");
				
				if (accetableBuy) {
					// Esplodo ogni stringa Acquisto passata per parametro nei suoi componenti
					String[] componentsOfAcquisto = acquisti[i].split(";");
	
					// Componente 1: un oggetto Prodotto (presente nell Ecommerce - se non presente allora é null) 
					Prodotto prodottoInScorrimento = cercaProdotto(componentsOfAcquisto[0].strip());
					// Componente 2: un intero che indica la quantitá che si vuole acquistare
					int numeroUnitaInScorrimento = Integer.parseInt(componentsOfAcquisto[1].strip()); 
					
					boolean productExists_AND_acceptableQuantity = (prodottoInScorrimento != null) && (prodottoInScorrimento.getNumeroUnita() >= numeroUnitaInScorrimento) && (numeroUnitaInScorrimento > 0);
	
						
					// Controllo che l'acquisto sia corretto
					if(productExists_AND_acceptableQuantity) {
	
						// Assegno un codice provvisorio al nuovo acquisto (-1), dopo verrá cambiato
						// Aggiungo l'acquisto alla lista di acquisti provvisoria (cache)
						Acquisto nuovoAcquisto = new Acquisto(-1, prodottoInScorrimento, numeroUnitaInScorrimento, utenteInAcquisto);
						cacheAcquisti.add(nuovoAcquisto);
						
						// Calcolo la spesa dell'acquisto e lo sommo alla spesa totale
						//double spesaAcquisto = prodottoInScorrimento.getPrezzoUnitario() * numeroUnitaInScorrimento;
						double spesaAcquisto = nuovoAcquisto.getSpesaAcquisto();
						spesaTotaleAcquisti = spesaTotaleAcquisti + spesaAcquisto;
						
						// Registro il boolean di output
						output[i] = true;
					} else {
						// Se non é corretto lo indico nell'array di output
						output[i] = false;
					}
				}
			}
			
			// Se l'utente puó permettersi gli acquisti
			if (spesaTotaleAcquisti <= utenteInAcquisto.getDisponibilita()) {
				
				//Sposto gli acquisti dalla cache alla memoria principale (sempre una lista, chiamata "acquisti", ma presente come parametro)
				for (Acquisto acquistoInCache : cacheAcquisti) {
					// Dalla lista acquisti estraggo il codice dell'ultimo Acquisto registrato (uguale alla grandezza della lista essendo l'enumerazione identica)
					// e lo incremento di 1 per il codice del nuovo acquisto da spostare
					int primoCodiceDisponibile = this.acquisti.size() + 1; // Per non confondere la lista acquisti (memoria principale) con l'array passato per parametro uso la keyword "this"
					// Aggiorno il codice dell'acquisto che voglio spostare
					acquistoInCache.setCodice(primoCodiceDisponibile);
					//Tolgo le unitá di prodotti acquistati dall'inventario
					Prodotto prodottoAcquistato = acquistoInCache.getProdotto();
					int numeroUnitaProdottoAcquistate = acquistoInCache.getNumeroUnita();
					// int numeroUnitaProdottoInMagazzino = prodottoAcquistato.getNumeroUnita();
					// prodottoAcquistato.setNumeroUnita(numeroUnitaProdottoInMagazzino - numeroUnitaProdottoAcquistate);
					prodottoAcquistato.substract(numeroUnitaProdottoAcquistate);
					// Tolgo le unità dal numero di posti occupati in magazzino
					//unitaInMagazzino = unitaInMagazzino - numeroUnitaProdottoAcquistate;
					decreaseUnitaMagazzino(numeroUnitaProdottoAcquistate);
					
					// Sposto l'acquisto
					this.acquisti.add(acquistoInCache);
				}
				
				utenteInAcquisto.pay(spesaTotaleAcquisti);
			} else {
				// Se l'utente non non ha soldi abortisco il processo e riempio l'array di output con solo false
				output = generateBoolArray(false, acquistiLength);
			}
			
		} else {
			// Se l'utente non esiste riempio l'array di output con solo false
			output = generateBoolArray(false, acquistiLength);
		}
		
		return output;
	}
	
	
	/**
	 * Restituisce l’ultimo acquisto per l’utente il cui codice fiscale è passato come parametro 
	 * (null se l’utente non è definito o se non ha ancora fatto acquisti).
	 * Il valore di ritorno consiste in una stringa riportante il codice dell’acquisto,
	 * il codice fiscale dell’utente, il codice del prodotto, il numero delle unità acquistate e la spesa
	 * relativa a quel determinato acquisto separati dal carattere ’;’
	 * 
	 * Nel caso in cui l'utente non é definito il metodo restituisce null.
	 * */
	public String ultimoAcquisto(String codiceFiscale) {
		
		/*
		 * Preparo una variabile String inizializzata a null
		 * Se l'utente esiste
		 * 		Scorro la lista di acquisti
		 * 		Se trovo l'utente ricercato
		 * 			Aggiorno il valore di output con i nuovi dati (Acquisto.descriviti())
		 * 		Altrimenti no (restituisco null)
		 * Altrimenti restituisco null
		 * 
		 * */
		
		String output = null;
		
		if (cercaUtente(codiceFiscale) != null) {
			
			for (Acquisto acquisto : acquisti) {
				Utente acquirente = acquisto.getUtente();
				String codFisAcquirente = acquirente.getCodiceFiscale();
				
				if (codFisAcquirente.equals(codiceFiscale)) {
					output = acquisto.descriviti();
				}
			}
		}
		
		return output;
	}
	
	/**
	 * Riceve come parametro un codice di un utente e restituisce la lista dei suoi acquisti andati a buon
	 * fine uno per riga, in ordine di esecuzione, nel formato:
	 * codice dell’acquisto, il codice fiscale dell’utente, il codice del prodotto,
	 * il numero delle unità acquistate e la spesa relativa a quel determinato acquisto
	 * 
	 * Nel caso in cui l'utente non sia definito, il metodo restituisce null.
	 * */
	public String acquistiUtente(String codiceFiscale) {
		
		/*
		 * Scorro la lista di acquisti
		 * Se trovo l'utente nell'acquisto
		 * 	Converto in stringa l'acquisto e la concateno alla stringa di output con \n
		 * Se ho inserito almeno un acquisto
		 * 	Rimuovo l'ultimo \n (ovvero l'ultimo carattere della stringa di output - con String.ubstring())
		 * 
		 * Indirettamente risolvo anche la richiesta: 
		 * "Nel caso in cui l'utente non é definito il metodo restituisce null."
		 * */
		
		String output = "";
		boolean cleanLineInterrupt = false;
		
		for (int i=0; i<acquisti.size(); i++) {
			
			Acquisto acquisto = acquisti.get(i);
			Utente acquirente = acquisto.getUtente();
			String codFisAcquirente = acquirente.getCodiceFiscale();
			
			if (codFisAcquirente.equals(codiceFiscale)) {
				output = output + acquisto.descriviti() + "\n";
				cleanLineInterrupt = true;
			}
			
		}
		
		if (cleanLineInterrupt) {
			//int indexLastChar = output.length()-1;
			//output = output.substring(0, indexLastChar);
			output = removeLastChar(output);
		} else {
			output = null;
		}
		
		return output;
	}
	
	
	/**
	 * Riceve come parametro il codice di un prodotto e restituisce i codici fiscali degli utenti che lo hanno
	 * acquistato almeno una volta, uno per riga, in ordine di acquisto ed evitando duplicati
	 * 
	 * Nel caso in cui utente o prodotto non siano definiti, il metodo restituisce null.
	 * */
	public String utentiProdotto(String codiceProdotto) {
		
		/*
		 * Scorro la lista di acquisti
		 * Se il prodotto si trova nel dato acquisto
		 * 	Estraggo l'utente e lo metto in una lista di utenti "cache" (controllando che non ci siano doppioni)
		 * Converto l'array di utenti in una stringa e restituisco
		 * */
		
		ArrayList<Utente> utentiCache = new ArrayList<Utente>();
		
		for (Acquisto acquisto : acquisti) {
			
			Prodotto prodottoInScorrimento = acquisto.getProdotto();
			Utente utenteInScorrimento = acquisto.getUtente();
			String codiceProdInScorrimento = prodottoInScorrimento.getCodiceProdotto();
			
			boolean sameProduct = codiceProdInScorrimento.equals(codiceProdotto);
			boolean userAlreadyAdded = !utentiCache.contains(utenteInScorrimento);
			
			if (sameProduct && userAlreadyAdded) {
				utentiCache.add(utenteInScorrimento);
			}
		}
		
		boolean cleanLineInterrupt = false;
		String output = "";
		
		for (Utente utenteInCache : utentiCache) {
			String codiceFiscaleDaConcatenare = utenteInCache.getCodiceFiscale();
			output = output + codiceFiscaleDaConcatenare + "\n";
			cleanLineInterrupt = true;
		}
		
		if (cleanLineInterrupt) {
			//int indexLastChar = output.length()-1;
			//output = output.substring(0, indexLastChar);
			output = removeLastChar(output);
		} else {
			output = null;
		}
		
		return output;
	}
	
	/**
	 * Il metodo riceve come parametri il codice di un acquisto, un indirizzo di consegna e la data di consegna
	 * e restituisce un oggetto di classe Prime.
	 * Il metodo verifica che il codice acquisto esista e che l’utente 
	 * possa permettersi la spesa della spedizione prime. Infatti, su tali spedizioni viene applicato un costo aggiuntivo pari al 10% 
	 * della spesa complessiva dei prodotti acquistati.
	 * 
	 * Cercando di attivare una consegna per un acquisto non definito il metodo non sortisce alcun effetto
	 * e il valore di ritorno è null
	 * 
	 * In caso di corretta creazione di 
	 * una nuova consegna, il metodo assegna all’oggetto creato un codice alfanumerico 
	 * univoco composto dal carattere che identifica il tipo di consegna (S oppure P) 
	 * e da un numero incrementale che rappresenta il numero di consegne finora richieste 
	 * al sistema di e-commerce (a partire da 1)
	 * 
	 * */
	public Prime nuovaConsegna(int codiceAcquisto, String indirizzoConsegna, String dataConsegna) {
		
		/*
		 * Scorro la lista cquisti e cerco il codice acquisto
		 * Se lo trovo
		 * 	 	genero l'oggetto Prime (ma ancora non lo inserisco nella lista)
		 * 		gli assegno un codice incrementale
		 * 		chiedo la spesa per la consegna prime
		 * 		Vedo se l'utente può permettersi il costo della consegna
		 * 		Se true: 
		 * 			tolgo i soldi all'utente
		 * 			aggiungo la consegna Prime alla lista consegne
		 * 		Se false:
		 * 			non aggiungo la consegna da nessuna parte e la elimino con finalize()
		 * Altrimenti no
		 * */
		
		/*
		Prime output = null;
		
		for (Acquisto acquistoInScorrimento : acquisti) {
			
			int codAcqInScorr = acquistoInScorrimento.getCodice();
			Utente utenteInScorrimento = acquistoInScorrimento.getUtente();
			
			if (codAcqInScorr == codiceAcquisto) {
				int numeroNuovaConsegna = consegne.size() + 1;
				output = new Prime(numeroNuovaConsegna, indirizzoConsegna, acquistoInScorrimento, dataConsegna);
				
				double spesaPrime = output.getSpesaConsegna();
				boolean affordablePrime = utenteInScorrimento.getDisponibilita() >= spesaPrime;
				
				if (affordablePrime) {
					utenteInScorrimento.pay(spesaPrime);
					consegne.add(output);
				} else {
					output = null;
				}
			}
		}
		
		return output;
		*/
		
		return (Prime) nuovaConsegna(codiceAcquisto, indirizzoConsegna, dataConsegna, true);
		
	}
	
	/**
	 * Il metodo riceve come parametri il codice di un acquisto e un indirizzo di consegna, e restituisce un 
	 * oggetto di classe Standard.
	 * Il metodo verifica che il codice dell’acquisto esista e, in caso positivo, crea la nuova consegna e 
	 * restituisce un riferimento all’oggetto creato.
	 * 
	 * Cercando di attivare una consegna per un acquisto non definito il metodo non sortisce alcun effetto
	 * e il valore di ritorno è null
	 * 
	 * In caso di corretta creazione di 
	 * una nuova consegna, il metodo assegna all’oggetto creato un codice alfanumerico 
	 * univoco composto dal carattere che identifica il tipo di consegna (S oppure P) 
	 * e da un numero incrementale che rappresenta il numero di consegne finora richieste 
	 * al sistema di e-commerce (a partire da 1)
	 * 
	 * */
	public Standard nuovaConsegna(int codiceAcquisto, String indirizzoConsegna) {
		
		/*
		 * Scorro la lista cquisti e cerco il codice acquisto
		 * Se lo trovo
		 * 	 	genero l'oggetto Standard
		 * 		gli assegno un codice incrementale
		 * 		lo aggiungo alla lista consegne
		 * Altrimenti no
		 * */
		
		/*
		Standard output = null;
		
		for (Acquisto acquistoInScorrimento : acquisti) {
			
			int codAcqInScorr = acquistoInScorrimento.getCodice();
			
			if(codAcqInScorr == codiceAcquisto) {
				int numeroNuovaConsegna = consegne.size() + 1;
				output = new Standard(numeroNuovaConsegna, indirizzoConsegna, acquistoInScorrimento);
				consegne.add(output);
			}
		}
		
		return output;
		*/
		
		return (Standard) nuovaConsegna(codiceAcquisto, indirizzoConsegna, Prime.DEFAULT_DATE, false);
	}
	
	public Consegna nuovaConsegna(int codiceAcquisto, String indirizzoConsegna, String dataConsegna, boolean prime) {
		/*
		 * Scorro la lista cquisti e cerco il codice acquisto
		 * Se lo trovo
		 * 	 	genero l'oggetto Prime/Standard (ma ancora non lo inserisco nella lista)
		 * 		gli assegno un codice incrementale
		 * 		chiedo la spesa per la consegna
		 * 		Vedo se l'utente può permettersi il costo della consegna
		 * 		Se true: 
		 * 			tolgo i soldi all'utente
		 * 			aggiungo la consegna alla lista consegne
		 * 		Se false:
		 * 			non aggiungo la consegna da nessuna parte e la elimino con finalize()
		 * Altrimenti no
		 * */
		
		Consegna output = null;
		boolean consegneContainsAcquisto = consegneContainsAcquisto(codiceAcquisto);
		
		if (!consegneContainsAcquisto) {
			for (Acquisto acquistoInScorrimento : acquisti) {
				
				int codAcqInScorr = acquistoInScorrimento.getCodice();
				Utente utenteInScorrimento = acquistoInScorrimento.getUtente();
				
				if (codAcqInScorr == codiceAcquisto) {
					int numeroNuovaConsegna = consegne.size() + 1;
					
					if (prime) {
						output = new Prime(numeroNuovaConsegna, indirizzoConsegna, acquistoInScorrimento, dataConsegna);
					} else {
						output = new Standard(numeroNuovaConsegna, indirizzoConsegna, acquistoInScorrimento);
					}
					
					double spesaConsegna = output.getSpesaConsegna();
					boolean affordableConsegna = utenteInScorrimento.getDisponibilita() >= spesaConsegna;
					
					if (affordableConsegna) {
						utenteInScorrimento.pay(spesaConsegna);
						consegne.add(output);
					} else {
						output = null;
					}
				}
			}
		}
		
		return output;
	}
	
	/**
	 * Riceve come parametro il codice di una 
	 * consegna e, in caso di consegna esistente, restituisce una stringa contenente codice fiscale dell’utente, 
	 * il codice dell’acquisto, 
	 * il tipo di consegna (S oppure P) 
	 * e la spesa totale legata ad acquisto e consegna
	 * 
	 * Per la classe Standard, l’ammontare sarà 
	 * semplicemente quello relativo all’acquisto; per la classe Prime sarà invece comprensivo dei costi aggiuntivi di spedizione e 
	 * la stringa includerà – dopo l’ammontare totale – anche la data di consegna richiesta dall’utente.
	 * 
	 */
	public String descriviConsegna(String codiceConsegna) {
		
		/*
		 * Estraggo il codice della consegna
		 * Nota che posizione e numero di consegna-1 coincidono
		 * Se l'indice non supera la grandezza della lista (il ché significa che la consegna esiste)
		 * 		Vado nella lista consegne all indice indicato
		 * 		Estraggo i dati dall'oggetto consegna
		 * 		Metto tali dati in una stringa di output
		 * */
		
		String output = null;
		
		/* Notazione Ungherese */
		
		String sTypeConsegna = codiceConsegna.substring(0, 1);
		String sNumberConsegna = codiceConsegna.substring(1, codiceConsegna.length());
		int iNumberConsegna = Integer.parseInt(sNumberConsegna);
		Consegna consegna = null;
		
		if (iNumberConsegna <= consegne.size()) {
			consegna = consegne.get(iNumberConsegna - 1);
			boolean legalType = consegna.tipoConsegna.equals(sTypeConsegna);
			if (legalType) {	
				output = consegna.descriviti();
			}
		}

		return output;
	}
	
	/**
	 * Restituisce un array contenente tutte le consegne finora richieste (in ordine di richiesta)
	 * */
	public Consegna[] consegne() {
		
		/*
		 * Converto la lista consegne in un array
		 * */
		/*
		Consegna[] output = null;
		
		//int lenghtConsegne = consegne.size();
		//Consegna[] output = consegne.toArray(new Consegna[lenghtConsegne]);
		if (consegne.size() > 0) {
			output = generateConsegneArray(consegne);
		}
		
		return output;*/
		return consegne(Utente.DEFAULT_USER_CODE, Consegna.DEFAULT_TYPE, false, false);
	}
	
	/**
	 * Riceve come parametro il codice fiscale di un utente e restituisce le sole consegne richieste da quell’utente (in ordine di richiesta)
	 * */
	public Consegna[] consegne(String codiceFiscale) {
		
		/*
		 * Genero una lista ausiliaria "cache"
		 * Scorro la lista consegne
		 * 		Controllo se trovo l'utente nella consegna
		 * 		Se true:
		 * 			Aggiungo tale consegna alla cache
		 * 		Se false:
		 * 			Non faccio nulla
		 * Converto la lista di cache in un array e lo restituisco
		 * */
		
		/*
		Consegna[] output = null;
		
		ArrayList<Consegna> cacheConsegne = new ArrayList<Consegna>();
		for (Consegna consegnaInScorrimento : consegne) {
			Utente utenteInScorrimento = consegnaInScorrimento.getAcquisto().getUtente();
			String codFisInScorrimento = utenteInScorrimento.getCodiceFiscale();
			
			if (codFisInScorrimento.equals(codiceFiscale)) {
				cacheConsegne.add(consegnaInScorrimento);
			}	
		}
		
		//int lenghtCacheConsegne = cacheConsegne.size();
		//output = cacheConsegne.toArray(new Consegna[lenghtCacheConsegne]);
		if (cacheConsegne.size() > 0) {
			output = generateConsegneArray(cacheConsegne);
		}
		
		return output;
		*/
		
		return consegne(codiceFiscale, Consegna.DEFAULT_TYPE, true, false);
	}
	
	/**
	 * Riceve oltre al codice fiscale di un utente anche una stringa che 
	 * identifica un tipo di consegna (S oppure P) e restituisce solo le consegne di quel tipo associate a tale utente
	 * */
	public Consegna[] consegne(String codiceFiscale, String tipo) {
		
		/*
		 * Genero una lista ausiliaria "cache"
		 * Scorro la lista consegne
		 * 		Controllo se trovo l'utente nella consegna e che il tipo di consegna sia corretto
		 * 		Se true:
		 * 			Aggiungo tale consegna alla cache
		 * 		Se false:
		 * 			Non faccio nulla
		 * Converto la lista di cache in un array e lo restituisco
		 * */
		
		/*
		Consegna[] output = null;
		
		ArrayList<Consegna> cacheConsegne = new ArrayList<Consegna>();
		for (Consegna consegnaInScorrimento : consegne) {
			
			String tipoConsegnaInScorrimento = consegnaInScorrimento.getTipoConsegna();
			Utente utenteInScorrimento = consegnaInScorrimento.getAcquisto().getUtente();
			String codFisInScorrimento = utenteInScorrimento.getCodiceFiscale();
			
			if (codFisInScorrimento.equals(codiceFiscale) && tipoConsegnaInScorrimento.equals(tipo)) {
				cacheConsegne.add(consegnaInScorrimento);
			}	
		}
		
		//int lenghtCacheConsegne = cacheConsegne.size();
		//output = cacheConsegne.toArray(new Consegna[lenghtCacheConsegne]);
		if (cacheConsegne.size() > 0) {
			output = generateConsegneArray(cacheConsegne);
		}
		
		return output;*/
		
		return consegne(codiceFiscale, tipo, true, true);
	}
	
	/**
	 * Riceve il codice fiscale di un utente e una stringa che identifica un tipo di consegna (S oppure P) 
	 * Attraverso i valori booleani decide se deve utilizzare i precedenti parametri per inserirli nell'array di output
	 * Restituisce le consegne in base alla combianzione di booleani che sono stai usati per parametro
	 * */
	private Consegna[] consegne(String codiceFiscale, String tipo, boolean controlUser, boolean controlType) {
		/*
		 * Genero una lista ausiliaria "cache"
		 * Scorro la lista consegne
		 * 		Controllo se trovo l'utente nella consegna e che il tipo di consegna sia corretto
		 * 		Se true:
		 * 			Aggiungo tale consegna alla cache
		 * 		Se false:
		 * 			Non faccio nulla
		 * Converto la lista di cache in un array e lo restituisco
		 * */
		
		Consegna[] output = new Consegna[0];
		
		// Caso default: Si vogliono restituire tutte le consegne
		int outputCase = -1;
		if (controlUser && !controlType) {
			// Caso 1: Si vuole controllare il codice fiscale ma non il tipo di consegna
			outputCase = 1;
		} else if (controlUser && controlType) {
			// Caso 2: Si vuole controllare il codice fiscale e anche il tipo di consegna
			outputCase = 2;
		}
		
		ArrayList<Consegna> cacheConsegne = new ArrayList<Consegna>();
		for (Consegna consegnaInScorrimento : consegne) {
			
			String tipoConsegnaInScorrimento = consegnaInScorrimento.getTipoConsegna();
			
			Utente utenteInScorrimento = consegnaInScorrimento.getAcquisto().getUtente();
			String codFisInScorrimento = utenteInScorrimento.getCodiceFiscale();
			
			switch (outputCase) {
				case 1:
					if (codFisInScorrimento.equals(codiceFiscale))
						cacheConsegne.add(consegnaInScorrimento);
					break;
				
				case 2:
					if (codFisInScorrimento.equals(codiceFiscale) && tipoConsegnaInScorrimento.equals(tipo))
						cacheConsegne.add(consegnaInScorrimento);
					break;
					
				default:
					cacheConsegne.add(consegnaInScorrimento);
					
			}
		}
		
		//int lenghtCacheConsegne = cacheConsegne.size();
		//output = cacheConsegne.toArray(new Consegna[lenghtCacheConsegne]);
		if (cacheConsegne.size() > 0) {
			output = generateConsegneArray(cacheConsegne);
		}
		
		return output;
	}
	
	public Utente[] getUtenti() {
		return utenti;
	}
	
	/**Fornito un codice acquisto come parametro restituisce false se l'acquisto non é collegato a nessuna consegna, true altrimenti*/
	private boolean consegneContainsAcquisto(int codiceAcquisto) {
		
		boolean output = false;
		for (Consegna consegna : consegne) {
			int codiceAcquistoInScorrimento = consegna.getAcquisto().getCodice();
			if (codiceAcquistoInScorrimento == codiceAcquisto) {
				output = true;
			}
		}
		
		return output;
	}
	
	/**
	 * Aumenta la quantitá di prodotti in magazzino secondo il valore passato per parametro, 
	 * se il valore supera la capacitá massima del magazzino questo si riempie all 100%
	 * se si passa un valore negativo questo viene convertito in 0*/
	private void increaseUnitaMagazzino(int nuoveUnita) {
		nuoveUnita = denegativizeInteger(nuoveUnita);
		unitaInMagazzino = Integer.min(unitaInMagazzino + nuoveUnita, numeroMassimoUnitaInMagazzino);
	}
	
	/**
	 * Diminuisce la quantitá di prodotti in magazzino secondo il valore passato per parametro, 
	 * va con la diminuzione il magazzino dovrebbe andare sotto lo zero allora si svuota completamente il magazzino
	 * se si passa un valore negativo questo viene convertito in 0
	 * */
	private void decreaseUnitaMagazzino(int deadUnita) {
		deadUnita = denegativizeInteger(deadUnita);
		unitaInMagazzino = Integer.max(unitaInMagazzino - deadUnita, 0);
		
	}
	
	private Consegna[] generateConsegneArray(ArrayList<Consegna> list) {
		int lenghtCacheConsegne = list.size();
		Consegna[] output = list.toArray(new Consegna[lenghtCacheConsegne]);
		return output;
	}
	
	/**Rimuove l'ultimo carattere dalla stringa passata per parametro e la restituisce*/
	public static String removeLastChar(String s) {
		int indexLastChar = s.length()-1;
		s = s.substring(0, indexLastChar);
		return s;
	}
	
	/**Restituisce 0 se il valore passato per parametro é 0 o negativo, altrimenti restiuisce il valore cosí com'é*/
	public static int denegativizeInteger(int x) {
		if (x<0) {
			x = 0;
		}
		return x;
	}
	
	/**Restituisce 0.0 se il valore passato per parametro é 0.0 o negativo, altrimenti restiuisce il valore cosí com'é*/
	public static double denegativizeDouble(double x) {
		if (x<0) {
			x = 0.00;
		}
		return x;
	}
	
	/**Genera un array di variabili booleane tutte secondo il valore passato per parametro*/
	public static boolean[] generateBoolArray(boolean value, int lenght) {
		boolean[] output = new boolean[lenght];
		
		for (int i=0; i<lenght; i++ ) {
			output[i] = value ;
		}
		
		return output;
	}

}