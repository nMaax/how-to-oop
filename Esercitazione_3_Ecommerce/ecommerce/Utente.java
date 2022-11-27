package ecommerce;

public class Utente {
	
	public static final String DEFAULT_USER_CODE = "NO_CODE";
	public static final String DEFAULT_SURNAME = "NO_SUR";
	public static final String DEFAULT_NAME = "NO_NAME";
	public static final double DEFAULT_NETWORTH = 0.0;

	private String codiceFiscale;
	private String cognome;
	private String nome;
	private double disponibilitaEconomica;
	
	public Utente() {
		this(DEFAULT_USER_CODE, DEFAULT_SURNAME, DEFAULT_NAME, DEFAULT_NETWORTH);
	}
	
	public Utente(double disponibilita) {
		this(DEFAULT_USER_CODE, DEFAULT_SURNAME, DEFAULT_NAME, disponibilita);
	}

	public Utente(String codiceFiscale, String cognome, String nome, double disponibilita) {
		this.codiceFiscale = codiceFiscale;
		setDati(cognome, nome, disponibilita);
	}
	
	/* GETTERS */

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public String getCognome() {
		return cognome;
	}

	public String getNome() {
		return nome;
	}

	public double getDisponibilita() {
		return disponibilitaEconomica;
	}
	
	/* SETTERS */

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setDisponibilita(double disponibilita) {
		this.disponibilitaEconomica = disponibilita;
	}
	
	/* ALTRI METODI */

	public void setDati(String cognome, String nome, double disponibilita) {
		this.cognome = cognome;
		this.nome = nome;
		this.disponibilitaEconomica = disponibilita;
	}
	
	public void pay(double spesa) {
		this.disponibilitaEconomica = this.disponibilitaEconomica - spesa;
	}
	
	public void earn(double salary) {
		this.disponibilitaEconomica = this.disponibilitaEconomica + salary;
	}

	/*
	@Override
	public String toString() {
		return "Utente [codiceFiscale=" + codiceFiscale + ", cognome=" + cognome + ", nome=" + nome
				+ ", disponibilitaEconomica=" + disponibilitaEconomica + "]";
	}
	*/
	
}
