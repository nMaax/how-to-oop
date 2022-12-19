package facets;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import facets.util.*;

public class Facets {
	
	LinkedList<Iscritto> iscritti = new LinkedList<Iscritto>();
	
	LinkedList<Immagine> immagini = new LinkedList<Immagine>();
	
	LinkedList<ImmagineFacebook> immaginiFB = new LinkedList<ImmagineFacebook>();
	LinkedList<ImmagineInstagram> immaginiIG = new LinkedList<ImmagineInstagram>();
	LinkedList<ImmagineAltroSocial> immaginiOT = new LinkedList<ImmagineAltroSocial>();
	
	LinkedList<Report> reports = new LinkedList<Report>();
	
	TreeMap<String, LinkedList<Integer>> mappaturaIscrittoImmagini = new TreeMap<String, LinkedList<Integer>>();
	TreeMap<Integer, LinkedList<Report>> mappaturaImmagineReports = new TreeMap<Integer, LinkedList<Report>>();
	
	/**Costruttore vuoto, non serve*/
	public Facets() {}	
	
	/**
	 * Riceve come parametri l’email (di cui va garantita l’univocità), 
	 * il nome, il cognome, il genere e l’età della persona che intende iscriversi.
	 * Il metodo si occupa di registrare il nuovo iscritto nel sistema restituendone 
	 * il riferimento, oppure di restituire il riferimento all’oggetto precedentemente 
	 * creato in caso di email già presente nel sistema
	 * */
	public Iscritto nuovaIscrizione(String email,  String nome, String cognome, String genere, int eta) {
		
		/*
		 * Cerco l'iscritto
		 * Se non c'é
		 * 		Lo creo 
		 * 		Lo aggingo alla lista di iscritti
		 * 		Preparo il suo spazione nella mappa che associa iscritti e immagini
		 * Restituisco quello he ho trovato/creato
		 * */
		
		Iscritto iscritto = cercaIscrittoPerEmail(email);
		
		if (iscritto == null) {
			iscritto = new Iscritto(email, nome, cognome, genere, eta);
			iscritti.add(iscritto);	
			mappaturaIscrittoImmagini.put(email, new LinkedList<Integer>());
		}	
		
		return iscritto;
	}
	
	/**
	 * Riceve come parametro l’email di un iscritto
	 * e, in caso di iscritto già esistente, restituisce una stringa riportante i campi email, nome,
	 * cognome, genere ed età separati da uno spazio.
	 * */
	public String descriviIscritto(String email) {
		
		Iscritto iscritto = cercaIscrittoPerEmail(email);
		
		if (iscritto == null) return null;		
		
		return iscritto.toString();
	}
	
	/**
	 * Riceve come parametro l’email di un iscritto e restituisce
	 * l’oggetto Iscritto corrispondente (null se inesistente).
	 * */
	public Iscritto cercaIscrittoPerEmail(String email) {
			
		Iscritto output = null;
		
		for (Iscritto iscritto : iscritti) {
			if (iscritto.getEmail().equals(email)) {
				output = iscritto;
			}
		}
		
		return output;
	}
	
	/**
	 * Restituisce invece un elenco di iscritti per i quali
	 * nome e cognome corrispondono a quelli passati come parametri 
	 * (in ordine alfabetico, con eventuali omonimi in ordine di iscrizione).
	 * Quest’ultimo metodo ammette ricerche parziali, in
	 * cui possono venir passati come parametri anche solo parte del nome
	 * e del cognome.
	 * */
	public Collection<Iscritto> cercaIscrittoPerNomeCognome(String nome, String cognome){
		
		/*
		 * Scorro la lista di iscritti
		 * Se trovo un iscritto che contenga i paramentri dentro nome e/o cognome
		 * 		Lo aggiungo a una lista di cache
		 * Restituisco la lista di cache riordinata con .sort()
		 * */
		
		LinkedList<Iscritto> cacheIscritti = new LinkedList<Iscritto>();
		
		for (Iscritto iscritto : iscritti) {
			//TODO metti un ignoreCase (e Testalo)
			if (iscritto.getNome().contains(nome) && iscritto.getCognome().contains(cognome)) {
				cacheIscritti.add(iscritto);
			}
		}
		
		Collections.sort(cacheIscritti);
		return cacheIscritti;
	}
	
	/**Restituisce un elenco di tutti gli iscritti (con il medesimo ordinamento)*/
	public Collection<Iscritto> elencoIscritti(){
		
		/*
		 * Nota che per fare il sorting bisogna solo guardare le differenze in nome e cognome
		 * non c'é alcun bisogno di assegnare ad ogni iscritto un numero che indichi la sua
		 * posizione nell'ordine in modo che il sorting non vada a rovinare l'ordine di inserimento originario
		 * 
		 * infatti nella javadoc di Collections.sort() troviamo la frase
		 * 
		 * 		" This sort is guaranteed to be stable: equal elements will not be reordered as a result of the sort. "  
		 * */
		
		LinkedList<Iscritto> cacheIscritti = new LinkedList<Iscritto>(iscritti);
		Collections.sort(cacheIscritti);
		return cacheIscritti;
	}
	
	/**
	 * Riceve come parametri l’email dell’iscritto che dona l’immagine 
	 * ed una stringa che identifica il nome dato all’immagine dall’iscritto stesso.
	 * Se tale iscritto esiste, il metodo crea una nuova immagine e le assegna 
	 * un codice numerico incrementale a partire da 1 (univoco a prescindere dalla
	 * categoria di immagine).
	 * 
	 * </br></br>
	 *
	 * Il metodo riceve anche una stringa identificante il nome dell’album in
	 * cui l’immagine è salvata su Facebook.
	 * 
	 * */
	public ImmagineFacebook nuovaImmagine(String emailIscritto, String nomeImmagine, String nomeAlbum) {
		return (ImmagineFacebook) nuovaImmagine(emailIscritto, nomeImmagine, nomeAlbum, -1, null, -1.0);
	}
	
	/**
	 * Riceve come parametri l’email dell’iscritto che dona l’immagine 
	 * ed una stringa che identifica il nome dato all’immagine dall’iscritto stesso.
	 * Se tale iscritto esiste, il metodo crea una nuova immagine e le assegna 
	 * un codice numerico incrementale a partire da 1 (univoco a prescindere dalla
	 * categoria di immagine).
	 * 
	 * </br></br>
	 *
	 * Il metodo riceve un valore intero indicante il numero di like 
	 * ricevuti dall’immagine su Instagram.
	 * 
	 * */
	public ImmagineInstagram nuovaImmagine(String emailIscritto, String nomeImmagine, int numeroLike) {
		return (ImmagineInstagram) nuovaImmagine(emailIscritto, nomeImmagine, null, numeroLike, null, -1.0);
	}
	
	/**
	 * Riceve come parametri l’email dell’iscritto che dona l’immagine 
	 * ed una stringa che identifica il nome dato all’immagine dall’iscritto stesso.
	 * Se tale iscritto esiste, il metodo crea una nuova immagine e le assegna 
	 * un codice numerico incrementale a partire da 1 (univoco a prescindere dalla
	 * categoria di immagine).
	 * 
	 * </br></br>
	 *
	 * Il metodo riceve una stringa identificante il nome del 
	 * social network in cui tale immagine è utilizzata e un valore double
	 * rappresentante la dimensione in MB dell’immagine stessa.
	 * 
	 * */
	public ImmagineAltroSocial nuovaImmagine(String emailIscritto, String nomeImmagine, String nomeSocial, double dimensioneImmagine) {
		return (ImmagineAltroSocial) nuovaImmagine(emailIscritto, nomeImmagine, null, -1, nomeSocial, dimensioneImmagine);
	}
	
	/**
	 * Riceve come parametri l’email dell’iscritto che dona l’immagine 
	 * ed una stringa che identifica il nome dato all’immagine dall’iscritto stesso.
	 * Se tale iscritto esiste, il metodo crea una nuova immagine e le assegna 
	 * un codice numerico incrementale a partire da 1 (univoco a prescindere dalla
	 * categoria di immagine).
	 * */
	private Immagine nuovaImmagine(String emailIscritto, String nomeImmagine, String nomeAlbum, int numeroLike, String nomeSocial, double dimensioneImmagine) {
		
		/*
		 * Trovo l'iscritto che vuole creare l'immagine
		 * Se l'iscritto non esiste restituisco null (null si può comunque castare, quindi no problem)
		 * 
		 * Creo uno switch (equivale a un lungo else-if) per capire in quale caso mi trovo
		 * 		In base al caso mi creo lo specifico tipo di Immagine richiesta
		 * 		La aggiungo alla lista di immagini di quella tipologia
		 * 
		 * Qualsiasi tipo di immagine abbia creato la aggiungo alla lista generale di immagini
		 * 
		 * Aggiugno l'immagine creata alla lista presente nella mappa che associa l'iscritto e le sue immagini
		 * Mi genero le liste di reports e timestamp relative a questa nuova immagine nelle singole mappe dedicate
		 * 
		 * Restituisco l'immagine creata
		 * */
		
		Iscritto iscritto = cercaIscrittoPerEmail(emailIscritto);
		
		if (iscritto == null) return null;
		
		boolean caseFB = nomeAlbum != null;
		boolean caseIG = numeroLike != -1;
		boolean caseOther = nomeSocial != null && dimensioneImmagine != -1.0;
		
		//Per non avere eventuali problemi successivamente metto comunque di default un valore "neutro" all'immagine
		Immagine immagine = new Immagine();
		int codiceImmagine = immagini.size()+1;
		
		if (caseFB) {
			immagine = new ImmagineFacebook(codiceImmagine, nomeImmagine, iscritto, nomeAlbum);
			immaginiFB.add((ImmagineFacebook) immagine);
		} else if (caseIG) {
			immagine = new ImmagineInstagram(codiceImmagine, nomeImmagine, iscritto, numeroLike);
			immaginiIG.add((ImmagineInstagram) immagine);
		} else if (caseOther) {
			immagine = new ImmagineAltroSocial(codiceImmagine, nomeImmagine, iscritto, nomeSocial, dimensioneImmagine);
			immaginiOT.add((ImmagineAltroSocial) immagine);
		}
		
		immagini.add(immagine);
		
		mappaturaIscrittoImmagini.get(emailIscritto).add(codiceImmagine);
		mappaturaImmagineReports.put(codiceImmagine, new LinkedList<Report>());
		
		return immagine;
	}
	
	/**
	 * Riceve il codice di un’immagine e restituisce una stringa 
	 * riportante le informazioni relative a tale immagine (se esistente)
	 * separate da spazio nell’ordine codice immagine, email dell’iscritto, 
	 * nome dell’immagine, tipo di immagine 
	 * (nelle forme “FACEB”, “INSTA”, “ALTRO”) e l’attributo o gli
	 * attributi specifici del particolare tipo di immagine 
	 * (ad esempio, nel caso di immagini di
	 * categoria ImmagineAltroSocial vengono riportati il 
	 * nome del social network e la dimensione
	 * dell’immagine in quest’ordine).
	 * */
	public String descriviImmagine(int codiceImmagine) {
		Immagine img = cercaImmagine(codiceImmagine);
		if (img == null) return null;
		return img.toString();
	}
	
	/**Riceve come parametro il codice di un’immagine e, in caso di immagine trovata, restituisce il riferimento all’oggetto corrispondente (null se non trovata).*/
	public Immagine cercaImmagine(int codiceImmagine) {
		if (codiceImmagine <= 0 || codiceImmagine >= immagini.size()+1) {
			return null; //Controllo che il codice immagine sia corretto per non avere un errore di indice fuori dal range disponibile
		}
		return immagini.get(codiceImmagine-1);
	}
	
	/**Restituisce un elenco di immagini ordinate in modo crescente rispetto al codice identificativo dell’immagine.*/
	public Collection<Immagine> elencoImmaginiPerCodice() {		
		return immagini;
	}
	
	/**
	 * Restituisce invece una collezione di immagini che riporta
	 * prima le immagini di categoria ImmagineFacebook, 
	 * poi quelle di categoria ImmagineInstagram, 
	 * ed infine quelle di categoria ImmagineAltroSocial; 
	 * le immagini di categoria ImmagineInstagram 
	 * vengono riportate in ordine decrescente rispetto al numero di like.*/
	public Collection<Immagine> elencoImmaginiPerTipologia() {
		
		/*
		 * Genero una copia delle Immagini di Instagram
		 * La ordino (ho implementato Comparable in ImmagineInstagram)
		 * 
		 * Genero una lista di output vuota
		 * Vi concateno dentro le altre liste con addAll()
		 * 
		 * Restituisco la lista di output
		 * */
		
		LinkedList<ImmagineInstagram> cacheImmaginiIG = new LinkedList<ImmagineInstagram>(immaginiIG);
		Collections.sort(cacheImmaginiIG);
		
		LinkedList<Immagine> output = new LinkedList<Immagine>();
		output.addAll(immaginiFB);
		output.addAll(cacheImmaginiIG);
		output.addAll(immaginiOT);
		
		return output;
	}
	
	/**Restituisce un elenco delle immagini che vengono da un altro social network ordinate per dimensione crescente*/
	public Collection<Immagine> elencoImmaginiAltroSocialPerDimensione() {		
		LinkedList<ImmagineAltroSocial> cacheImmaginiOT = new LinkedList<ImmagineAltroSocial>(immaginiOT);
		Collections.sort(cacheImmaginiOT);
		return new LinkedList<Immagine>(cacheImmaginiOT);
	}
	
	
	/**
	 * Riceve come parametri l’email di un iscritto 
	 * e una collezione di stringhe che rappresentano le
	 * descrizioni di alcune delle immagini di quell’iscritto.
	 * Tali stringhe sono formate da tre campi
	 * separati dal carattere “;”.
	 * 
	 * </br></br>
	 * 
	 * Il primo campo riporta il codice di un’immagine, 
	 * il secondo una descrizione testuale dell’immagine come elaborata dall’IA 
	 * e il terzo un timestamp nel formato
	 * AAAAMMGG HH:MM:SS che rappresenta il momento in cui il report per tale
	 * immagine viene generato.
	 * 
	 * </br></br>
	 * 
	 * Nel caso in cui l’iscritto esista, il metodo verifica l’esistenza 
	 * delle immagini il cui codice è riportato nelle descrizioni 
	 * passate come parametro. 
	 * 
	 * </br></br>
	 * 
	 * Per ciascuna delle immagini esistenti e appartenenti a tale iscritto, 
	 * nel caso in cui il nuovo timestamp sia successivo a
	 * quello registrato per l’ultimo report su tale immagine 
	 * (o in caso di primo report per quell’immagine) il metodo 
	 * crea un nuovo report su quell’immagine, rappresentato da un
	 * oggetto di classe Report.
	 * 
	 * </br></br>
	 * 
	 * Il metodo assegna al report un codice numerico incrementale a
	 * partire da 1. 
	 * 
	 * </br></br>
	 * 
	 * Si consideri l’eventualità che per ogni immagine possa 
	 * esistere un numero non definito a priori di report con 
	 * timestamp diversi tra loro.
	 * 
	 * </br></br>
	 * 
	 * Il metodo restituisce una collezione di
	 * report contenente i report creati sulla base dei criteri 
	 * appena descritti, in ordine di creazione.
	 * 
	 * </br></br>
	 * 
	 * In caso di un iscritto inesistente il metodo non sortisce alcun effetto (ed il valore di ritorno è null).
	 * */
	public Collection<Report> nuoviReport(String emailIscritto, Collection<String> report) {
		
		/*
		 * Controllo che l'iscritto esista
		 * 		Se non esiste interrompo e restituisco null
		 * 
		 * Mi preparo una lista di output, vuota
		 * Scorro la collezione di stringhe
		 * 		Estraggo le singole componenti
		 * 		Controllo che le componenti rispettino i vincoli assegnati:
		 * 			- Immagine esistente
		 * 			- Iscritto possessore di tale immagine
		 * 			- Timestamp successivo ai timestamp giá relativi a quell'immagine
		 * 		Se le componenti rispettano tali vincoli
		 * 			Creo un report con i dati passati 
		 * 				(Assegno un codice, il riferimento all'immagine, la descrizione, il timestamp)
		 * 			Aggiorno la TreeMap che incrocia le immagini e i relativi timestamps
		 * 			Aggiungo il report alla lista di report generali della classe Facets
		 * 			Aggiungo il report creato alla lista di output
		 * 		Altrimenti
		 * 			Non faccio nulla e passo alla prossima iterazione del ciclo
		 *
		 *	Restituisco la lista di output
		 * 
		 * */
		
		//TODO controlla che il formato della stringa passato sia corretto
		
		Iscritto iscritto = cercaIscrittoPerEmail(emailIscritto);		
		if (iscritto == null) return null;
		
		LinkedList<Report> output = new LinkedList<Report>();
		//Scorro la collezione di Stringe passate per parametro
		for (String datiReport : report) {
			// Mi estraggo e converto i dati nella singola stringa iterata
			String[] componentsDatiReport = datiReport.split(";");
			
			int codiceImmagine = Integer.parseInt(componentsDatiReport[0]);
			String descrizioneReport = componentsDatiReport[1];
			Timestamp timestampReport = new Timestamp(componentsDatiReport[2]);
			
			// Cerco l'immagine
			Immagine immagine = cercaImmagine(codiceImmagine);
			boolean immagineExists = immagine != null;
			
			// Controllo che l'utente la possegga (se l'immagine non esiste di sicuro l'utente non la possiede, quindi sono a posto)
			boolean isOwner = mappaturaIscrittoImmagini.get(emailIscritto).contains(codiceImmagine);
			
			// Controllo che il timestamp fornito sia successivo a quelli giá associati al'immagine
			LinkedList<Report> reportsOfImmagine = mappaturaImmagineReports.get(codiceImmagine);			
			boolean isNewTS = false; //TS sta per TimeStamp (isNewTimestamp)
			if (immagineExists && reportsOfImmagine.size() > 0) {
				// Dato che i reporto sono, per definizione, inseriti in ordine di timestamp
				// (dal piú vecchio al piú recente) prendo l'ultimo e ne chiedo il timestamp
				Timestamp lastTimestampImmagine = reportsOfImmagine.getLast().getRawTimestamp();
				isNewTS = timestampReport.compareTo(lastTimestampImmagine) > 0;
			} else if (immagineExists && reportsOfImmagine.size() == 0) {
				// Se non ci sono report (quindi timestamps) bypasso il controllo e carico lo stesso il nuovo timestamp
				isNewTS = true;
			}
			
			if (immagineExists && isOwner && isNewTS) {
				int codiceReport = reports.size()+1;
				Report newReport = new Report(codiceReport, immagine, descrizioneReport, timestampReport);
				reports.add(newReport);
				// Aggiungo il report alla mappa che associa ogni immagine ai suoi report
				mappaturaImmagineReports.get(codiceImmagine).add(newReport);
				output.add(newReport);
			}
		}
		return output;
	}
	
	
	/**
	 * Riceve come parametro il codice di un’immagine e 
	 * restituisce una collezione contenente i report associati a quella particolare 
	 * immagine per timestamp crescenti (null in caso di immagine inesistente).
	 * */
	public Collection<Report> reportImmagine(int codiceImmagine){
		//Le mappe, se fornisci una chiave che non esiste, returnano null di default
		//Dato che giá di defualt i report DEVONO essere in ordine di timestamp non devo usare alcun sort
		//Infatti un report puó entrare esclusivamente se ha un timestamp successivo al piú recente
		return mappaturaImmagineReports.get(codiceImmagine);
	}
	
	/**
	 * Riceve come parametro il codice di un report e, in caso di report
	 * esistente, restituisce il riferimento all’oggetto corrispondente.
	 * Nel caso in cui il codice passato come parametro non corrisponda 
	 * ad alcun report, il metodo scatena un’eccezione 
	 * di tipo ReportNonEsistenteException.
	 * */
	public Report cercaReportPerId(int codiceReport) throws ReportNonEsistenteException{
		Report output = null;
		for (Report report : reports) {
			if (report.getIntegerCodice() == codiceReport) {
				output = report;
			}
		}
		
		if (output == null) throw new ReportNonEsistenteException();
		
		return output;
	}
	
	/**
	 * Riceve come parametro l’email di un iscritto e restituisce una 
	 * collezione di report ad esso associati ordinata per timestamp 
	 * consecutivi. 
	 * Nel caso in cuil’email passata come parametro non corrisponda
	 * ad alcun iscritto, verrà scatenata un’eccezione 
	 * di tipo IscrittoNonEsistenteException.
	 * */
	public Collection<Report> elencoReportPerEmailIscritto(String emailIscritto) throws IscrittoNonEsistenteException{
		
		if (cercaIscrittoPerEmail(emailIscritto) == null) throw new IscrittoNonEsistenteException();
		
		LinkedList<Integer> immaginiUtente = mappaturaIscrittoImmagini.get(emailIscritto);
		LinkedList<Report> reportsUtente = new LinkedList<Report>();
		
		for (Integer codiceImmagine : immaginiUtente) {			
			reportsUtente.addAll(mappaturaImmagineReports.get(codiceImmagine));
		}
		
		Collections.sort(reportsUtente);
		
		return reportsUtente;
	}
	
	/**
	 * 
	 * Riceve come parametro il percorso di un file di testo 
	 * organizzato per righe, nel quale ogni riga può riportare le
	 * informazioni relative ad un iscritto o ad un’immagine.
	 * 
	 * Le righe relative ad un iscritto iniziano con la stringa “ISC”, 
	 * seguita da email, nome, cognome,
	 * genere ed età. 
	 * 
	 * Le righe relative alle immagini iniziano invece con 
	 * la stringa “IMM”; la stringa iniziale sarà seguita da email 
	 * dell’iscritto, nome dell’immagine, tipologia dell’immagine
	 * (“FACEB”, “INSTA” oppure “ALTRO”) e dagli 
	 * attributi specifici del tipo di immagine.
	 * 
	 * Gli elementi di ciascuna riga sono separati gli uni dagli altri tramite il carattere “;”.
	 * 
	 * Si assuma che il programma debba gestire eventuali errori di formato del file.
	 * */
	public void leggiDatiFacets(String nomeFile) {	
		
		/*
		 * Apro il file e istanzio un reader di linea
		 * 
		 * Fiché esistono linee
		 * 		Le scompongo nei loro componenti
		 * 		Se é una riga ISC (5 componenti)
		 * 			Creo il nuovo iscritto con nuovaIscrizione()
		 * 		Se é una riga IMM (3 + 1-1-2 componenti)
		 * 			Controllo che tipo di immagine sia
		 * 				Creo la nuova immagine con nuovaImmagine()
		 * 
		 * Chiudo il reder e il file
		 * */
		
		try {
			FileReader file = new FileReader(nomeFile);
			BufferedReader reader = new BufferedReader(file);
						
			String line; 
			while ( (line = reader.readLine())  != null  ) {
				try {
					String[] componentsOfLine = line.split(";");
					String lineType = componentsOfLine[0];
					
					if (lineType.equals("ISC")) {
						
						String email = componentsOfLine[1].trim();
						String nome = componentsOfLine[2];
						String cognome = componentsOfLine[3];
						String genere = componentsOfLine[4];
						int eta = Integer.parseInt(componentsOfLine[5]);
	
						nuovaIscrizione(email, nome, cognome, genere, eta);
						
					} else if (lineType.equals("IMM")) {
						
						String email = componentsOfLine[1];
						
						String nome = componentsOfLine[2];
						String tipo = componentsOfLine[3];
						
						if (tipo.equals("FACEB")) {
							String album = componentsOfLine[4];
							nuovaImmagine(email, nome, album);
						} else if (tipo.equals("INSTA")) {
							int numeroLike = Integer.parseInt(componentsOfLine[4]);
							nuovaImmagine(email, nome, numeroLike);
						} else if (tipo.equals("ALTRO")) {
							String nomeSocial = componentsOfLine[4];
							double dimensione = Double.parseDouble(componentsOfLine[5]);
							nuovaImmagine(email, nome, nomeSocial, dimensione);
						}
						
					}
				} catch (Exception e) {
					// Salto la linea
					// E-e... non faccio nulla?
					// No davvero ragazzi, che cosa dovrei scrivere qui?
					// E la megafin
					//System.err.println("Errore di formato della stringa\n");
					//e.printStackTrace();
				}
				
			}
			
			reader.close();
			file.close();
			
		} catch (FileNotFoundException e) {
			//System.err.println("File inesistente\n");
			//e.printStackTrace();
		} catch (IOException e) {
			//System.err.println("Errore di input/output\n");
			//e.printStackTrace();
		}
		
		
	}
	
}
