package ecommerce;

public class Acquisto {
	
	public static final int DEFAULT_PURCHASE_CODE = 0;
	public static final int DEFAULT_PIECES_NUMBER = 0;
	
	private int codice;
	
	private Prodotto prodotto;
	private int numeroUnita;
	private double spesaAcquisto;
	
	private Utente utente;
	
	
	public Acquisto() {
		this(DEFAULT_PURCHASE_CODE, new Prodotto(), DEFAULT_PIECES_NUMBER, new Utente());
	}

	public Acquisto(int codice, Prodotto prodotto, int numeroUnita, Utente utente) {
		this.codice = codice;
		this.prodotto = prodotto;
		this.numeroUnita = numeroUnita;
		this.spesaAcquisto = prodotto.getPrezzoUnitario() * numeroUnita;
		this.utente = utente;
	}
	
	/* GETTERS */

	public Prodotto getProdotto() {
		return prodotto;
	}

	public int getNumeroUnita() {
		return numeroUnita;
	}

	public int getCodice() {
		return codice;
	}
	
	public double getSpesaAcquisto() {
		return spesaAcquisto;
	}
	
	public Utente getUtente() {
		return utente;
	}
	
	/* SETTERS */

	public void setProdotto(Prodotto prodotto) {
		this.prodotto = prodotto;
	}

	public void setNumeroUnita(int numeroUnita) {
		this.numeroUnita = numeroUnita;
	}

	public void setCodice(int codice) {
		this.codice = codice;
	}

	public void setSpesaAcquisto(double spesaAcquisto) {
		this.spesaAcquisto = spesaAcquisto;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}
	
	/* ALTRI METODI */

	public String descriviti() {
		return codice + ";" + utente.getCodiceFiscale() + ";" + prodotto.getCodiceProdotto() + ";" + numeroUnita + ";" + spesaAcquisto;
	}

}
