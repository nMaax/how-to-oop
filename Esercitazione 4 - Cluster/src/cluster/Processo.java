package cluster;

import cluster.myMethods.*;

public class Processo implements Comparable<Processo>{
	
	/**{"PENDING" [0], "ESECUZIONE" [1], "COMPLETATO" [2]}*/
	public static final String[] STATES = {"PENDING", "ESECUZIONE", "COMPLETATO"};
	
	public static final int DEFAULT_CODE = 0;
	public static final String DEFAULT_STATE = "NO_STATE";
	public static final Timestamp DEFAULT_TIMESTAMP = new Timestamp();
	public static final String DEFAULT_DESC = "NO_DESC";
	public static final Utente DEFAULT_USER = new Utente();
	
	private int codice;
	private String stato;
	private Timestamp timestamp;
	private String descrizione;
	private Utente utente;
	
	public Processo() {
		this(DEFAULT_CODE, DEFAULT_STATE, DEFAULT_TIMESTAMP, DEFAULT_DESC, DEFAULT_USER);
	}
	
	public Processo(int codice, String timestamp, String descrizione, Utente utente) {
		this(codice, STATES[0], new Timestamp(timestamp), descrizione, utente);
	}
	
	public Processo(int codice, String stato, String timestamp, String descrizione, Utente utente) {
		this(codice, stato, new Timestamp(timestamp), descrizione, utente);
	}
	
	public Processo(int codice, String stato, Timestamp timestamp, String descrizione, Utente utente) {
		super();
		this.codice = codice;
		this.stato = stato;
		this.timestamp = timestamp;
		this.descrizione = descrizione;
		this.utente = utente;
	}

	/* GETTERS */
	
	public int getCodiceProcesso() {
		return codice;
	}
	
	public String getEmailUtente() {
		return utente.getEmail();
	}
	
	public String getPasswordUtente() {
		return utente.getPassword();
	}
	
	public String getDescrizione() {
		return descrizione;
	}
	
	public String getTimestamp() {
		return timestamp.toString();
	}

	public String getStatoEsecuzione() {
		return stato;
	}
	
	public Timestamp getRawTimestamp() {
		return timestamp;
	}
	
	/* SETTERS */
	
	/* ALTRI METODI */
	
	/**Modifica lo stato del processo a EXECUTE e aggiorna il suo timestamp con il valore passato per parametro*/
	public void execute(String timestamp) {
		update(1, new Timestamp(timestamp));
	} 	
	
	/**Modifica lo stato del processo a COMPLETATO e aggiorna il suo timestamp con il valore passato per parametro*/
	public void kill(String timestamp) {
		update(2, new Timestamp(timestamp));
	}
	
	/**Modifica lo stato del processo nel valore passato per parametro (se il valore non rientra nei possibili valori che un processo possa assumere passa a PENDING di default) e il relativo timestamp*/
	public void update(String state, String timestamp) {
		int iState;
		switch(state) {
			case "PENDING": 
				iState = 0;
				break;
			case "ESECUZIONE": 
				iState = 1;
				break;
			case "COMPLETATO": 
				iState = 2;
				break;
			default: 
				iState = 0;
				
		}
		update(iState, new Timestamp(timestamp));
	}
	
	/**Modifica lo stato del processo nel valore passato per parametro (se il valore non rientra nei possibili valori che un processo possa assumere passa a PENDING di default) e il relativo timestamp*/
	public void update(int state, Timestamp timestamp) {
		this.stato = STATES[state];
		this.timestamp = timestamp;
	}
	
	/**
	 * Riporta, separati dalla sequenza di caratteri virgola-spazio ”, ”, 
	 * il codice del processo, l’email dell’utente che ne ha richiesto l’esecuzione,
	 * la descrizione, lo stato più recente ed il relativo timestamp
	 * */
	@Override
	public String toString() {
		return codice + ", " + utente.getEmail() + ", " + descrizione + ", " + stato + ", " +timestamp;
	}
	
	/**Compare i due oggetti esclusivamente sul valore del Timestamp a loro assegnato*/
	@Override
	public int compareTo(Processo other) {
		return this.getRawTimestamp().compareTo(other.getRawTimestamp());
	}
	
}
