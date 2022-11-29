package cluster;

import java.util.Collection;
import java.util.Collections;
import java.util.ArrayList;
import java.util.LinkedList;
import cluster.myMethods.*;

public class Cluster {
	
	private LinkedList<Utente> utenti = new LinkedList<Utente>();
	private int maxProcessiAttiviPerUtente;
	
	private LinkedList<Processo> processi = new LinkedList<Processo>();
	
	private Processo[] codaEsecuzione;
	private int maxLunghezzaCodaEsecuzione;
	private int processiInEsecuzione = 0;
	
	private LinkedList<Processo> pendingProcessi = new LinkedList<Processo>();
	private LinkedList<Processo> doneProcessi = new LinkedList<Processo>();
	
	/**
	 * Riceve come
	 * parametri il numero massimo di processi attivi contemporaneamente 
	 * per ciascun utente (che siano questi in attesa di essere
	 * eseguiti o già in esecuzione) e la lunghezza della coda di esecuzione,
	 * che determina il numero massimo di processi in
	 * esecuzione nello stesso momento
	 * */
	public Cluster(int maxProcessiAttivi, int lunghezzaCodaEsecuzione) {
		this.maxProcessiAttiviPerUtente = maxProcessiAttivi;
		this.maxLunghezzaCodaEsecuzione = lunghezzaCodaEsecuzione;
		codaEsecuzione = new Processo[maxLunghezzaCodaEsecuzione];
		
	}
	
	/**
	 * Riceve come parametri le informazioni sull’utente quali email 
	 * (di cui si deve garantire l’univocità), nome, cognome, password e
	 * data di nascita nel formato AAAAMMGG
	 * (si assuma che il formato del parametro sia corretto).
	 * 
	 * </br></br>
	 * 
	 * Il metodo si occupa di verificare che la mail sia valida
	 * (si assuma per semplicità che una email sia valida qualora contenga 
	 * il carattere @) e che non sia già presente fra quelle degli utenti registrati,
	 * che la password sia valida, ovvero che sia lunga non meno di 8 caratteri e che
	 * contenga almeno un carattere maiuscolo e un numero,
	 * e che l’età del nuovo utente sia di almeno 18 anni
	 * (per effettuare tale confronto è sufficiente tenere conto dell’anno di nascita). 
	 * 
	 * </br></br>
	 * 
	 * Nel caso di utente già registrato, il metodo restituisce un oggetto
	 * corrispondente all’utente registrato 
	 * (si consideri un utente come già registrato se esiste un utente con la stessa email);
	 * nel caso di nuova registrazione,
	 * il valore di ritorno sarà invece rappresentato da un oggetto 
	 * rappresentante il nuovo utente della tipologia appropriata.
	 * 
	 * </br></br>
	 * 
	 * In caso di password e/o email non valide o di età inferiore a quella ammessa, il metodo non
	 * sortirà alcun effetto ed il valore di ritorno sarà null.
	 * 
	 * </br></br>
	 * 
	 * In caso di UtenteAdmin il metodo riceve come parametri aggiuntivi
	 * due stringhe identificanti una chiave pubblica e una chiave privata.
	 * */
	public UtenteStandard registraUtente(String email, String nome, String cognome, String password, String dataNascita) {
		return (UtenteStandard) default_registraUtente(email, nome, cognome, password, dataNascita, null, null);
	}
	
	/**
	 * Riceve come parametri le informazioni sull’utente quali email 
	 * (di cui si deve garantire l’univocità), nome, cognome, password e
	 * data di nascita nel formato AAAAMMGG
	 * (si assuma che il formato del parametro sia corretto).
	 * 
	 * </br></br>
	 * 
	 * Il metodo si occupa di verificare che la mail sia valida
	 * (si assuma per semplicità che una email sia valida qualora contenga 
	 * il carattere @) e che non sia già presente fra quelle degli utenti registrati,
	 * che la password sia valida, ovvero che sia lunga non meno di 8 caratteri e che
	 * contenga almeno un carattere maiuscolo e un numero,
	 * e che l’età del nuovo utente sia di almeno 18 anni
	 * (per effettuare tale confronto è sufficiente tenere conto dell’anno di nascita). 
	 * 
	 * </br></br>
	 * 
	 * Nel caso di utente già registrato, il metodo restituisce un oggetto
	 * corrispondente all’utente registrato 
	 * (si consideri un utente come già registrato 
	 * se esiste un utente con la stessa email);
	 * nel caso di nuova registrazione,
	 * il valore di ritorno sarà invece rappresentato da un oggetto 
	 * rappresentante il nuovo utente della tipologia appropriata.
	 * 
	 * </br></br>
	 * 
	 * In caso di password e/o email non valide o di età inferiore a quella ammessa,
	 * il metodo non sortirà alcun effetto ed il valore di ritorno sarà null.
	 * 
	 * </br></br>
	 * 
	 * In caso di UtenteAdmin il metodo riceve come parametri aggiuntivi
	 * due stringhe identificanti una chiave pubblica e una chiave privata.
	 * */
	public UtenteAdmin registraUtente(String email, String nome, String cognome, String password, String dataNascita, String chiavePubblica, String chiavePrivata) {
		return (UtenteAdmin) default_registraUtente(email, nome, cognome, password, dataNascita, chiavePubblica, chiavePrivata);
	}
	
	/**
	 * Riceve come parametri le informazioni sull’utente quali email 
	 * (di cui si deve garantire l’univocità), nome, cognome, password e
	 * data di nascita nel formato AAAAMMGG
	 * (si assuma che il formato del parametro sia corretto).
	 * 
	 * </br></br>
	 * 
	 * Il metodo si occupa di verificare che la mail sia valida
	 * (si assuma per semplicità che una email sia valida qualora contenga 
	 * il carattere @) e che non sia già presente fra quelle degli utenti registrati,
	 * che la password sia valida, ovvero che sia lunga non meno di 8 caratteri e che
	 * contenga almeno un carattere maiuscolo e un numero,
	 * e che l’età del nuovo utente sia di almeno 18 anni
	 * (per effettuare tale confronto è sufficiente tenere conto dell’anno di nascita). 
	 * 
	 * </br></br>
	 * 
	 * Nel caso di utente già registrato, il metodo restituisce un oggetto
	 * corrispondente all’utente registrato 
	 * (si consideri un utente come già registrato 
	 * se esiste un utente con la stessa email);
	 * nel caso di nuova registrazione,
	 * il valore di ritorno sarà invece rappresentato da un oggetto 
	 * rappresentante il nuovo utente della tipologia appropriata.
	 * 
	 * </br></br>
	 * 
	 * In caso di password e/o email non valide o di età inferiore a quella ammessa,
	 * il metodo non sortirà alcun effetto ed il valore di ritorno sarà null.
	 * 
	 * </br></br>
	 * 
	 * In caso di UtenteAdmin il metodo riceve come parametri aggiuntivi
	 * due stringhe identificanti una chiave pubblica e una chiave privata.
	 * */
	public Utente default_registraUtente(String email, String nome, String cognome, String password, String dataNascita, String chiavePubblica, String chiavePrivata) {
		
		/*
		 * Se l'utente già esiste (cercaUtente() != null)
		 * 		lo restituisco
		 * Altrimenti lo creo
		 * 		Se email, password e dataDiNascita sono ammissibili
		 * 			Se sono state passate le chiavi 
		 * 				Creo un oggetto UtenteAdmin
		 * 			Altrimenti 
		 * 				Creo un oggetto UtenteStandard
		 * 		aggiungo l'utente all'LinkedList 
		 * Se, invece, si è chiesto un utente già esistente ma fornendo dati non compatibili
		 * (Si è chiesto la registrazione di uno Standard che già esiste come Admin o viceversa)
		 * 		restituisco null
		 * */
		
		Utente utente = cercaUtente(email);
		boolean isStandard = chiavePubblica == null && chiavePrivata == null;
		boolean legalParams = EmailAddress.validEmail(email) && validPassword(password) && Utente.isAdultAge(dataNascita);
		
		if (utente == null && legalParams) {
				if (isStandard) {
					utente = new UtenteStandard(nome, cognome, dataNascita, email, password);
				} else {
					utente = new UtenteAdmin(nome, cognome, dataNascita, email, password, chiavePubblica, chiavePrivata);
				}
				utenti.add(utente);
		}
		
		//Per risolvere problemi di casting restituisco null nel caso in cui si sia richiesto un Admin 
		//ma si sia trovato uno standard (o viceversa)
		if (utente instanceof UtenteAdmin && isStandard) {
			utente = null;
		} else if (utente instanceof UtenteStandard && !isStandard) {
			utente = null;
		}
		
		return utente;
	}
	
	/**
	 * Riceve come parametro l’email di un utente e, nel
	 * caso in cui questa corrisponda ad un utente registrato nel sistema, 
	 * restituisce una stringa contenente la descrizione delle sue
	 * informazioni.
	 * 
	 * </br></br>
	 * 
	 * Tale descrizione riporta, separati dalla sequenza di caratteri
	 * virgola-spazio ”, ”,
	 * l’email, il nome, il cognome, la password e la data di nascita.
	 * 
	 * </br></br>
	 * 
	 * Ai fini della descrizione prodotta da questo metodo,
	 * il campo della password è
	 * oscurato e sostituito da una serie di caratteri ”*” di lunghezza pari 
	 * a quella della password dell’utente che si sta
	 * descrivendo.
	 * 
	 * </br></br>
	 * 
	 * Nel caso di utenti di tipo UtenteAdmin la descrizione riporta anche le chiavi
	 * (prima la chiave pubblica e poi quella privata).
	 * 
	 * </br></br>
	 * 
	 * Una variante del metodo riceve come parametri “di autenticazione” 
	 * una email ed una password e, se queste
	 * corrispondono all’email ed alla password di un utente di tipo UtenteAdmin, 
	 * restituisce una descrizione simile a quella
	 * precedente ma con il campo password non oscurato.
	 * 
	 * </br></br>
	 * 
	 * La stessa cosa avviene anche per gli utenti di tipo UtenteStandard
	 * ma solo nel caso in cui l’email passata come parametro di “autenticazione” 
	 * sia la stessa dell’utente che si vuole descrivere
	 * (ogni utente, salvo l’admin, può descrivere 
	 * esclusivamente la propria password).
	 * 
	 * */
	public String descriviUtente(String emailUtenteDaDescrivere) {
		return default_descriviUtente(null, null, emailUtenteDaDescrivere);
	}
	
	/**
	 * Riceve come parametro l’email di un utente e, nel
	 * caso in cui questa corrisponda ad un utente registrato nel sistema, 
	 * restituisce una stringa contenente la descrizione delle sue
	 * informazioni.
	 * 
	 * </br></br>
	 * 
	 * Tale descrizione riporta, separati dalla sequenza di caratteri
	 * virgola-spazio ”, ”,
	 * l’email, il nome, il cognome, la password e la data di nascita.
	 * 
	 * </br></br>
	 * 
	 * Ai fini della descrizione prodotta da questo metodo,
	 * il campo della password è
	 * oscurato e sostituito da una serie di caratteri ”*” di lunghezza pari 
	 * a quella della password dell’utente che si sta
	 * descrivendo.
	 * 
	 * </br></br>
	 * 
	 * Nel caso di utenti di tipo UtenteAdmin la descrizione riporta anche le chiavi
	 * (prima la chiave pubblica e poi quella privata).
	 * 
	 * </br></br>
	 * 
	 * Una variante del metodo riceve come parametri “di autenticazione” 
	 * una email ed una password e, se queste
	 * corrispondono all’email ed alla password di un utente di tipo UtenteAdmin, 
	 * restituisce una descrizione simile a quella
	 * precedente ma con il campo password non oscurato.
	 * 
	 * </br></br>
	 * 
	 * La stessa cosa avviene anche per gli utenti di tipo UtenteStandard
	 * ma solo nel caso in cui l’email passata come parametro di “autenticazione” 
	 * sia la stessa dell’utente che si vuole descrivere
	 * (ogni utente, salvo l’admin, può descrivere 
	 * esclusivamente la propria password).
	 * */
	public String descriviUtente(String emailUtenteAutenticazione, String passwordUtenteAutenticazione, String emailUtenteDaDescrivere) {
		return default_descriviUtente(emailUtenteAutenticazione, passwordUtenteAutenticazione, emailUtenteDaDescrivere);
	}
	
	/**
	 * Riceve come parametro l’email di un utente e, nel
	 * caso in cui questa corrisponda ad un utente registrato nel sistema, 
	 * restituisce una stringa contenente la descrizione delle sue
	 * informazioni.
	 * 
	 * </br></br>
	 * 
	 * Tale descrizione riporta, separati dalla sequenza di caratteri
	 * virgola-spazio ”, ”,
	 * l’email, il nome, il cognome, la password e la data di nascita.
	 * 
	 * </br></br>
	 * 
	 * Ai fini della descrizione prodotta da questo metodo,
	 * il campo della password è
	 * oscurato e sostituito da una serie di caratteri ”*” di lunghezza pari 
	 * a quella della password dell’utente che si sta
	 * descrivendo.
	 * 
	 * </br></br>
	 * 
	 * Nel caso di utenti di tipo UtenteAdmin la descrizione riporta anche le chiavi
	 * (prima la chiave pubblica e poi quella privata).
	 * 
	 * </br></br>
	 * 
	 * Una variante del metodo riceve come parametri “di autenticazione” 
	 * una email ed una password e, se queste
	 * corrispondono all’email ed alla password di un utente di tipo UtenteAdmin, 
	 * restituisce una descrizione simile a quella
	 * precedente ma con il campo password non oscurato.
	 * 
	 * </br></br>
	 * 
	 * La stessa cosa avviene anche per gli utenti di tipo UtenteStandard
	 * ma solo nel caso in cui l’email passata come parametro di “autenticazione” 
	 * sia la stessa dell’utente che si vuole descrivere
	 * (ogni utente, salvo l’admin, può descrivere 
	 * esclusivamente la propria password).
	 * */
	public String default_descriviUtente(String emailUtenteAutenticazione, String passwordUtenteAutenticazione, String emailUtenteDaDescrivere) {
		
		/*
		 * Cerco l'utente, se esiste:
		 * 		Se le credenziali di login sono di Admin o dell'utente cercato stesso
		 *			Richiamo toString(censored = false)
		 * 	   Altrimenti
		 * 	   		Richiamo toString(censored = true)
		 * Se non esiste:
		 * 		Restituisco null
		 * */
		
		Utente userToDescribe = cercaUtente(emailUtenteDaDescrivere); 
		String output = null;
		
		if (userToDescribe != null) {
			Utente userTryingToLogin = cercaUtente(emailUtenteAutenticazione);
			
			boolean isAdmin = false;
			boolean sameCredential = false;
			
			if (userTryingToLogin != null) {
				isAdmin = userTryingToLogin instanceof UtenteAdmin;
				sameCredential = userToDescribe.getEmail().equals(emailUtenteAutenticazione) && userToDescribe.getPassword().equals(passwordUtenteAutenticazione);
			}
			
			if (isAdmin || sameCredential) {
				output = userToDescribe.toString(false);
			} else {
				output = userToDescribe.toString(true);
			}
		}
		
		return output;
	}
	
	/**
	 * Riceve come parametro
	 * l’email dell’utente da cercare
	 * e ritorna l’utente desiderato se registrato, 
	 * null altrimenti
	 * */
	public Utente cercaUtente(String email) {
		
		/*
		 * Scorro la lista di utenti
		 * Controllo che l'email in scorrimento coincida con il parametro
		 * 		Se true: utente trovato, me lo salvo e lo restituisco
		 * 		Se false: non faccio nulla e continuo lo scorrimento del for
		 * */
		
		Utente output = null;
		for (Utente utente : utenti) {
			String emailInLoop = utente.getEmail();
			if (emailInLoop.equals(email)) {
				output = utente;
			}
		}
		
		return output;
	}
	
	/**
	 * Restituisce un array contenente gli utenti registrati fino 
	 * a quel momento nel sistema in ordine di registrazione.
	 * Con una seconda versione del metodo, che riceve come parametri 
	 * aggiuntivi due date (nel formato AAAAMMGG),
	 * è possibile cercare solo gli utenti la cui data di nascita 
	 * sia contenuta nel range definito dalle due date.
	 * 
	 * </br></br>
	 * 
	 * Si supponga che tali date siano passate in ordine crescente e 
	 * che possano assumere lo stesso valore. Per entrambi i metodi,
	 * l’array restituito deve essere dimensionato in base al numero 
	 * effettivo di utenti trovati
	 * */
	public Utente[] cercaUtenti(){
		return default_cercaUtenti(null, null);
	}
	
	/**
	 * Restituisce un array contenente gli utenti registrati fino 
	 * a quel momento nel sistema in ordine di registrazione.
	 * Con una seconda versione del metodo, che riceve come parametri 
	 * aggiuntivi due date (nel formato AAAAMMGG),
	 * è possibile cercare solo gli utenti la cui data di nascita 
	 * sia contenuta nel range definito dalle due date.
	 * 
	 * </br></br>
	 * 
	 * Si supponga che tali date siano passate in ordine crescente e 
	 * che possano assumere lo stesso valore. Per entrambi i metodi,
	 * l’array restituito deve essere dimensionato in base al numero 
	 * effettivo di utenti trovati
	 * */
	public Utente[] cercaUtenti(String dataInizio, String dataFine){
		return default_cercaUtenti(dataInizio, dataFine);
	}
	
	/**
	 * Restituisce un array contenente gli utenti registrati fino 
	 * a quel momento nel sistema in ordine di registrazione.
	 * Con una seconda versione del metodo, che riceve come parametri 
	 * aggiuntivi due date (nel formato AAAAMMGG),
	 * è possibile cercare solo gli utenti la cui data di nascita 
	 * sia contenuta nel range definito dalle due date.
	 * 
	 * </br></br>
	 * 
	 * Si supponga che tali date siano passate in ordine crescente e 
	 * che possano assumere lo stesso valore. Per entrambi i metodi,
	 * l’array restituito deve essere dimensionato in base al numero 
	 * effettivo di utenti trovati
	 * */
	public Utente[] default_cercaUtenti(String dataInizio, String dataFine) {
		
		/*
		 * Se dataInzio e dataFine sono null:
		 * 		Le metto come valori di data massime e minime esistenti (cosí da stampare tutti gli utenti)
		 * Scorro la lista utenti
		 * 		Se l'utente in scorrimento ha la dataNascita dentro le date 
		 * 			lo aggiungo ad una lista cache
		 * Converto la lista di cache in array e restituisco
		 * */
		
		if (dataInizio == null || dataFine == null) {
			dataInizio = mostDistantBirthdate().toString();
			dataFine = mostRecentBirthdate().toString();
		}
		
		ArrayList<Utente> cacheUtenti = new ArrayList<Utente>();
		for (Utente utente: utenti) {
			Data birthdateInLoop = utente.getRawDataNascita();
			if(birthdateInLoop.isIntoPeriod(dataInizio, dataFine)) {
				cacheUtenti.add(utente);
			}
		}
		
		return cacheUtenti.toArray(new Utente[cacheUtenti.size()]);
	}
	
	/**
	 * Riceve come parametri l’email e la password dell’utente che sta 
	 * generando il nuovo processo, una stringa 
	 * riportante la descrizione del processo, 
	 * un timestamp nel formato AAAAMMGG HH:MM:SS che
	 * identifica il momento di creazione del processo 
	 * (si assuma che il formato del parametro sia corretto) e, 
	 * sulla base di determinate circostanze, schedula il processo.
	 * 
	 * </br></br>
	 * 
	 * Si consideri inoltre che tale timestamp sarà aggiornato ogni volta 
	 * che questi processi saranno interessati da 
	 * un cambio dello stato di esecuzione.
	 * 
	 * </br></br>
	 * 
	 * In particolare, il metodo verifica che l’email e la
	 * password ricevute come parametri corrispondano 
	 * a quelle di un utente registrato e, in caso positivo, 
	 * che tale utente non abbia un numero di processi 
	 * attivi all’interno del cluster superiore a quello ricevuto come parametro 
	 * nel costruttore della classe Cluster 
	 * (per evitare un’eccessiva monopolizzazione delle risorse).
	 * 
	 * </br></br>
	 * 
	 * Un processo è considerato attivo se si trova negli
	 * stati PENDING o ESECUZIONE.
	 * 
	 * </br></br>
	 * 
	 * In caso di esito positivo di tutte le verifiche, 
	 * il metodo crea un nuovo processo, gli assegna
	 * un codice incrementale univoco (a partire da 1), 
	 * imposta il suo stato a PENDING, 
	 * lo inserisce all’interno di una coda di
	 * attesa e ne restituisce il riferimento.
	 * 
	 * </br></br>
	 * 
	 * Questa coda determina l’ordine in cui i processi verranno eseguiti 
	 * dal cluster e deve essere gestita in modo da eseguire 
	 * per primi i processi schedulati prima. (FIFO)
	 * 
	 * </br></br>
	 * 
	 * Il sistema non applica tuttavia le stesse regole pertutti gli utenti. 
	 * Infatti, nel caso in cui l’email e la password corrispondano a 
	 * un utente di categoria prioritaria (UtenteAdmin) 
	 * il processo viene inserito in cima alla lista dei processi PENDING.
	 * */
	public Processo nuovoProcesso(String email, String password, String descrizione, String timestamp) {
		
		/*
		 * Controllo che :
		 * 	1. l'utente esista
		 *  2. la password sia corretta
		 *  3. l'utente non abbia finito le risorse
		 * 	Se true:
		 * 		Controllo che sia un admin
		 * 			Se true: 
		 * 				Metto il suo processo in cima alla lista pending
		 * 			Se false:
		 * 				Metto il suo processo in fondo alla lista pending
		 *	Se false: 
		 *		Non faccio nulla 
		 * */
		
		Utente utente = cercaUtente(email);
		Processo processo = null;
		
		if (utente != null && utente.getPassword().equals(password) && numProcessiAttivi(utente) < maxProcessiAttiviPerUtente) {
			
			int firstFreeIndex = processi.size()+1;
			processo = new Processo(firstFreeIndex, timestamp, descrizione, utente);
			processi.add(processo);
			
			if (utente instanceof UtenteAdmin) {
				 pendingProcessi.addFirst(processo);
			 } else {
				 pendingProcessi.addLast(processo);
			 }	 
		}
		return processo;
	}
	
	/**
	 * Restituisce la descrizione di un processo all’interno del sistema.
	 * Il metodo riceve come parametro il codice di un processo e, 
	 * se questo corrisponde a un processo del sistema (qualunque sia lo stato), 
	 * restituisce una stringa contenente la sua descrizione. 
	 * Tale descrizione riporta, separati dalla sequenza di caratteri virgola-spazio ”, ”, 
	 * il codice del processo, l’email dell’utente che ne ha richiesto l’esecuzione,
	 * la descrizione, lo stato più recente ed il relativo timestamp. 
	 * Nel caso di processo inesistente il metodo non sortisce alcun
	 * effetto e il valore di ritorno non è rilevante.
	 * */
	public String descriviProcesso(int codiceProcesso) {
		String output = null;
		Processo processo = cercaProcesso(codiceProcesso);
		if (processo != null) {
			output = processo.toString();
		}
		return output;
	}
	
	/**
	 * Porta uno o più processi 
	 * dallo stato di PENDING allo stato di ESECUZIONE. 
	 * 
	 * </br></br>
	 * 
	 * La dimensione della coda di esecuzione 
	 * (informazione passata come parametro al costruttore della classe Cluster)
	 * è limitata: il metodo riceve come parametro un timestamp
	 * (nello stesso formato dei metodi precedenti)
	 * che rappresenta il momento di inizio esecuzione 
	 * e porta quindi un numero di processi dallo stato di PENDING
	 * allo stato di ESECUZIONE fino allo riempimento della coda.
	 * 
	 * </br></br>
	 * 
	 * Il metodo restituisce un valore booleano 
	 * a true nel caso in cui almeno un processo venga 
	 * portato in stato di esecuzione, false in caso 
	 * in cui non ci siano altri processi da eseguire
	 * (nessun processo in stato PENDING) 
	 * o la coda dei processi in ESECUZIONE sia piena.
	 * */
	public boolean eseguiProcessi(String timestamp) {
		
		/*
		 * Scorro la lista di processi in pending
		 * 		Se la coda di esecuzione NON è piena:
		 * 			Eseguo il metodo .execute(timestamp) della classe Processo
		 * 			Copio il processo nella coda di esecuzione
		 * 			Elimino il processo dalla coda di pending
		 * 			Porto a true il valore di ritorno
		 * Restituisco il valore di ritorno
		 * */
		
		boolean output = false;
		ArrayList<Processo> deleteMeCache = new ArrayList<Processo>();
		
		for (Processo processo : pendingProcessi) {
			if (processiInEsecuzione < maxLunghezzaCodaEsecuzione) {
				
				processo.execute(timestamp);
				codaEsecuzione[processiInEsecuzione] = processo;
				processiInEsecuzione++;
				
				deleteMeCache.add(processo);
				output = true;
			}
		}
		
		for (Processo processo : deleteMeCache) {
			pendingProcessi.remove(processo);
		}
		
		return output;
	}
	
	/** 
	 * Riceve un array di codici dei processi in ESECUZIONE 
	 * ed un timestamp che identifica il momento in cui 
	 * tali processi vengono completati. 
	 * 
	 * </br></br>
	 * 
	 * Per ognuno dei processi effettivamente esistenti 
	 * e nello stato di ESECUZIONE il metodo aggiorna 
	 * lo stato a COMPLETATO assegnando il suddetto timestamp. 
	 * 
	 * </br></br>
	 * 
	 * Infine, nel caso in cui almeno uno dei processi ricevuti 
	 * come parametro sia passato dallo stato di ESECUZIONE 
	 * a quello COMPLETATO, viene invocato automaticamente 
	 * il metodo eseguiProcessi () utilizzando il medesimo timestamp. 
	 * 
	 * </br></br>
	 * 
	 * Il metodo restituisce un array di valori booleani 
	 * della stessa dimesione dell’array ricevuto come parametro.
	 * 
	 * </br></br>
	 *  
	 * In corrispondenza di processi che sono 
	 * passati allo stato COMPLETATO l’array riporterà il valore true,
	 * in corrispondenza di processi inesistenti 
	 * o in stato iniziale diverso da ESECUZIONE, il valore false.
	 * */
	public boolean[] completaProcessi(int[] codiciProcesso, String timestamp) {
		
		/*
		 * Scorro gli interi passati per parametro
		 * Controllo che il processo esista (cercaProcesso != null) e che sia in stato di esecuzione
		 * Se true: 
		 * 		completo il processo con kill()
		 * 		copio il processo nella lista di processi eseguiti
		 * 		rimuovo il processo dalla coda di esecuzione
		 * 		riporto true nell' array
		 * 		mi segno che dopo devo eseguire i processi in coda
		 * Se false:
		 * 		riporto false nell'array
		 * 
		 * Eseguo i processi in coda
		 * */
		
		int lenght = codiciProcesso.length;
		boolean[] output = new boolean[lenght];
		boolean execute = false;
		
		for (int i = 0; i < lenght; i++) {
			int codiceProcesso = codiciProcesso[i];
			Processo processo = cercaProcesso(codiceProcesso);
			if (processo != null && processo.getStatoEsecuzione().equals(Processo.STATES[1])) {
				
				processo.kill(timestamp);
				
				doneProcessi.add(processo);
				removeFromCoda(processo);
				processiInEsecuzione--;
				
				output[i] = true;
				execute = true;
			} else {
				output[i] = false;
			}
		}
		
		
		if (execute) {
			eseguiProcessi(timestamp);
		}
		
		return output;
	}
	
	/**
	 * Il metodo riceve come parametro il codice 
	 * identificativo del processo da cercare e restituisce 
	 * il processo cercato, se esistente, null altrimenti.
	 * */
	public Processo cercaProcesso(int codiceProcesso) {
		Processo output = null;
		for (Processo processo : processi) {
			if (processo.getCodiceProcesso() == codiceProcesso) {
				output = processo;
			}
		}
		return output;
	}
	
	/**Restituisce un array contenente i processi creati fino a quel momento, in ordine di creazione. */
	public Processo[] cercaProcessi() {
		return processi.toArray(new Processo[processi.size()]);
	}
	
	/**Restituisce una collezione di utenti ordinata per email, in ordine alfabetico inverso. Nel caso non vi siano utenti/processi da elencare, il valore di ritorno sarà null*/
	public Collection<Utente> elencoUtentiPerEmail() {
		
		/*
		 * Clono la lista di utenti
		 * Applico un bubble sort (confrontando le email al clone
		 * Restituisco il clonbe
		 * */
		
		ArrayList<Utente> cacheCloneUtenti = new ArrayList<Utente>();
		for (Utente utente : utenti) {
			cacheCloneUtenti.add(utente);
		}
		
		if (cacheCloneUtenti.size() == 0) {
			return null;
		}
		
		Collections.sort(cacheCloneUtenti);
		Collections.reverse(cacheCloneUtenti);
		return cacheCloneUtenti;
	}
	
	/**Restituisce una collezione dei processi in stato di PENDlNG nell’ordine in cui questi saranno eseguiti serviti. Nel caso non vi siano utenti/processi da elencare, il valore di ritorno sarà null*/
	public Collection<Processo> elencoProcessiPending() {
		
		if (pendingProcessi.size() == 0) return null;
		
		return pendingProcessi;
	}
	
	/**Restituisce una collezione dei processi in stato di COMPLETATO nell’ordine in cui questi sono stati completati. Nel caso non vi siano utenti/processi da elencare, il valore di ritorno sarà null*/
	public Collection<Processo> elencoProcessiCompletati() {
		
		if (doneProcessi.size() == 0) return null;
		
		return doneProcessi;
	}
	
	/**Restituisce una collezione dei processi ordinati per timestamp crescenti (dal più vecchio al più recente). Nel caso non vi siano utenti/processi da elencare, il valore di ritorno sarà null*/
	public Collection<Processo> elencoProcessiPerTimestamp(){
		/*
		 * Clono la lista dei processi
		 * Applico un bubble sort (confrontando le email al clone
		 * Restituisco il clonbe
		 * */
		
		ArrayList<Processo> cacheCloneProcesso = new ArrayList<Processo>();
		for (Processo processo : processi) {
			cacheCloneProcesso.add(processo);
		}
		
		if (cacheCloneProcesso.size() == 0) {
			return null;
		}
		
		Collections.sort(cacheCloneProcesso);		
		return cacheCloneProcesso;
	}
	
	private boolean removeFromCoda(Processo processo) {
		for (int i=0; i < codaEsecuzione.length; i++) {
			Processo processoInLoop = codaEsecuzione[i];
			if (processoInLoop != null && processoInLoop.equals(processo)) {
				codaEsecuzione[i] = null;
			}
		}
		return DataManipulationMethods.shakeArray(codaEsecuzione);
	}
	
	private int numProcessiAttivi(Utente utente) {
		return numProcessiByState(utente, Processo.STATES[0]) + numProcessiByState(utente, Processo.STATES[1]);
	}

	private int numProcessiByState(Utente utente, String state) {
		int num = 0;
		Processo[] processi;
		
		switch(state) {
			case "PENDING": //pending
				processi = pendingProcessi.toArray(new Processo[pendingProcessi.size()]); 
				break;
			
			case "ESECUZIONE": //execute
				processi = codaEsecuzione;
				break;
				
			case "COMPLETATO": //dead
				processi = doneProcessi.toArray(new Processo[doneProcessi.size()]);
				break;
			
			default:
				processi = new Processo[0];
				System.err.println("Invalid state");
		}
		
		for (Processo processo: processi) {
			if (processo != null && processo.getEmailUtente().equals(utente.getEmail())) {
				num++;
			}
		}
		
		return num;
	}
	
	/**Restituisce la data di nascita del'utente più giovane salvato (ovvero la più vicina dal presente)*/
	private Data mostRecentBirthdate() {
		
		if (utenti.size() == 0) {
			return new Data("00000000");
		}
		
		Data output = utenti.get(0).getRawDataNascita();
		for (Utente utente : utenti) {
			Data dataInLoop = utente.getRawDataNascita();
			if (dataInLoop.isAfter(output)) {
				output = dataInLoop;
			}
		}
		
		return output;
	}
	
	/**Restituisce la data di nascita del'utente più vecchio salvato (ovvero la più distante dal presente)*/
	private Data mostDistantBirthdate() {
		
		if (utenti.size() == 0) {
			return new Data("00000000");
		}
		
		Data output = utenti.get(0).getRawDataNascita();
		for (Utente utente : utenti) {
			Data dataInLoop = utente.getRawDataNascita();
			if (dataInLoop.isBefore(output)) {
				output = dataInLoop;
			}
		}
		
		return output;
		
	}
	
	/**Restituisce true se la password passata per parametro rispetta le regole, false altrimenti*/
	private boolean validPassword(String password) {
		return password.length() >= 8 && DataManipulationMethods.containsUpperCase(password) && DataManipulationMethods.containsNumber(password);
	}
	
	/* METODI IN PIU' USATI IN BREAK-CODE, DA IGNORARE*/
	
	public Processo[] getCoda() {
		return codaEsecuzione;
	}
	
	public LinkedList<Processo> getPending() {
		return pendingProcessi;
	}
	
	public LinkedList<Processo> getComplete() {
		return doneProcessi;
	}
	
	public LinkedList<Processo> getProcessi() {
		return processi;
	}
	
}
