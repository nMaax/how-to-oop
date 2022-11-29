package cluster;

import cluster.myMethods.*;

public class Utente implements Comparable<Utente>{
	
	public static String DEFAULT_NOME = "NO_NAME";
	public static String DEFAULT_COGNOME = "NO_SUR";
	public static Data DEFAULT_BIRTHDATE = new Data();
	public static EmailAddress DEFAULT_EMAIL = new EmailAddress();
	public static String DEFAULT_PASSWORD = "PASSWORD";
	
	public static final int ADULT_AGE = 18;
	
	private String nome;
	private String cognome;
	private Data dataNascita;
	
	private EmailAddress email;
	private String password;
	
	public Utente() {
		this(DEFAULT_NOME, DEFAULT_COGNOME, DEFAULT_BIRTHDATE, DEFAULT_EMAIL, DEFAULT_PASSWORD);
	}
	
	public Utente(String nome, String cognome, String dataNascita, String email, String password) {
		this(nome, cognome, new Data(dataNascita), new EmailAddress(email), password);
	}
	
	private Utente(String nome, String cognome, Data dataNascita, EmailAddress email, String password) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.dataNascita = dataNascita;
		this.email = email;
		this.password = password;
	}

	/* GETTERS */
	
	public String getEmail() {
		return email.toString();
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getCognome() {
		return cognome;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getDataNascita() {
		return dataNascita.toString();
	}
	
	public EmailAddress getRawEmail() {
		return email;
	}
	
	public Data getRawDataNascita() {
		return dataNascita;
	}

	/* SETTERS */
	
	public void setDati(String nome, String cognome, String password, String dataNascita) {
		setDati(nome, cognome, password, dataNascita, null, null);
	}
	
	public void setDati(String nome, String cognome, String password, String dataNascita, String chiavePubblica, String chiavePrivata) {
		this.nome = nome;
		this.cognome = cognome;
		this.dataNascita = new Data(dataNascita);
		this.password = password;
	}
	
	public void setEmail(String email) {
		setEmail(new EmailAddress(email));
	}
	
	public void setEmail(EmailAddress email) {
		this.email = email;
	}
	
	/* ALTRI METODI*/

	/**Restituisce l'età dell'utente ignorando i valori di mese e giorno di nascita*/
	public int getAge() {
		return Data.CURRENT_YEAR - dataNascita.getYear();
	}
	
	public boolean isAdultAge() {
		return isAdultAge(dataNascita.toString());
	}
	
	/**
	 * Restituisce true se la data di nascita (YYYYMMDD) 
	 * passata per parametro corrisponde ad un età maggiorenne, 
	 * false altrimenti.
	 * </br>
	 * Si considera solo l'anno, ignorando mese e giorno
	 * */
	public static boolean isAdultAge(String dataNascita) {
		int age = Data.CURRENT_YEAR - new Data(dataNascita).getYear();
		return age >= Utente.ADULT_AGE;
	}
	
	/**Restituisce i dati dell'utente come richiesti dall'esercizio, se per parametro ha true la password è censurata, se false è visibile*/
	public String toString(boolean censorship) {
		String outputPsw = this.password;
		if (censorship) {
			outputPsw = "";
			for (int i = 0; i < password.length(); i++) {
				outputPsw = outputPsw + "*"; 
			}		
		}
		return email + ", " + nome + ", " + cognome + ", " + outputPsw + ", " + dataNascita;
	}
	
	@Override
	public String toString() {
		return this.toString(false);
	}
	
	@Override
	public int compareTo(Utente other) {
		return this.getEmail().compareTo(other.getEmail());
	}
	
	
}
