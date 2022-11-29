package ecommerce;

public class Prodotto {
	
	public static final String DEFAULT_PRODUCT_CODE = "NO_CODE";
	public static final String DEFAULT_DESCRIPTION = "NO_DESC";
	public static final double DEFAULT_PRICE = 0.0;
	public static final int DEFAULT_UNITS = 0;
	
	private String codiceProdotto;
	private String descrizione;
	private double prezzoUnitario;
	private int numeroUnita;
	
	public Prodotto() {
		this(DEFAULT_PRODUCT_CODE, DEFAULT_DESCRIPTION, DEFAULT_PRICE, DEFAULT_UNITS);
	}

	public Prodotto(int numeroUnita) {
		this(DEFAULT_PRODUCT_CODE, DEFAULT_DESCRIPTION, DEFAULT_PRICE, numeroUnita);
	}
	
	public Prodotto(String codiceProdotto, String descrizione, double prezzoUnitario, int numeroUnita) {
		this.codiceProdotto = codiceProdotto;
		this.descrizione = descrizione;
		this.prezzoUnitario = prezzoUnitario;
		this.numeroUnita = numeroUnita;
	}
	
	/* GETTERS */

	public String getCodiceProdotto() {
		return codiceProdotto;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public double getPrezzoUnitario() {
		return prezzoUnitario;
	}

	public int getNumeroUnita() {
		return numeroUnita;
	}

	/* SETTERS */
	
	public void setCodiceProdotto(String codiceProdotto) {
		this.codiceProdotto = codiceProdotto;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public void setPrezzoUnitario(double prezzoUnitario) {
		this.prezzoUnitario = prezzoUnitario;
	}

	public void setNumeroUnita(int numeroUnita) {
		this.numeroUnita = numeroUnita;
	}
	
	/* ALTRI METODI */

	public void add(int newPieces) {
		newPieces = Ecommerce.denegativizeInteger(newPieces);
		this.numeroUnita = this.numeroUnita + newPieces;
	}
	
	public void substract(int deadPieces) {
		deadPieces = Ecommerce.denegativizeInteger(deadPieces);
		this.numeroUnita = this.numeroUnita - deadPieces;
	}
	
	/*
	@Override
	public String toString() {
		return "Prodotto [codiceProdotto=" + codiceProdotto + ", descrizione=" + descrizione + ", prezzoUnitario="
				+ prezzoUnitario + ", numeroUnita=" + numeroUnita + "]";
	}
	*/
}
