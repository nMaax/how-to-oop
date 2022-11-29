package ecommerce;

public class Consegna {	
	
	public static final int DEFAULT_DELIVER_NUMBER = 0;
	public static final String DEFAULT_TYPE = "NONE";
	public static final String DEFAULT_ADDRESS = "NO_ADRS";
	
	protected int numero;
	protected String tipoConsegna;
	protected String indirizzoSpedizione;
	protected Acquisto acquisto;
	
	// Riscrivo la spesa dell'ordine per non renderlo soggetto alle eventuali variazioni di prezzo del prodotto 
	protected double spesaOrdine;
	
	protected Consegna() {		
		this(DEFAULT_DELIVER_NUMBER, DEFAULT_ADDRESS, new Acquisto());
	}
	
	protected Consegna(int numero, String indirizzoSpedizione, Acquisto acquisto) {
		this.numero = numero;
		this.tipoConsegna = DEFAULT_TYPE;
		this.indirizzoSpedizione = indirizzoSpedizione;
		this.acquisto = acquisto;
		this.spesaOrdine = acquisto.getSpesaAcquisto();
	}
	
	/* GETTERS */

	public int getNumero() {
		return numero;
	}

	public String getIndirizzoSpedizione() {
		return indirizzoSpedizione;
	}

	public Acquisto getAcquisto() {
		return acquisto;
	}
	
	public String getTipoConsegna() {
		return tipoConsegna;
	}
	
	public double getSpesaOrdine() {
		return this.spesaOrdine;
	}
	
	public String getCodice() {
		return tipoConsegna + numero;
	}
	
	/* SETTERS */
	
	public void setNumero(int numero) {
		this.numero = numero;
	}

	public void setIndirizzoSpedizione(String indirizzoSpedizione) {
		this.indirizzoSpedizione = indirizzoSpedizione;
	}
	
	public void setAcquisto(Acquisto acquisto) {
		this.acquisto = acquisto;
	}
	
	public void setTipoConsegna(String tipoConsegna) {
		this.tipoConsegna = tipoConsegna;
	}

	public void setSpesaOrdine(double spesaOrdine) {
		this.spesaOrdine = spesaOrdine;
	}
	
	/* ALTRI METODI */
	
	public String getCodiceConsegna() {
		return tipoConsegna + numero;
	}
	
	public double getSpesaConsegna() {
		return 0.0;
	}
	
	public double getSpesaTotale() {
		return spesaOrdine + getSpesaConsegna();
	}

	public String descriviti() {
		return acquisto.getUtente().getCodiceFiscale() + ", " + acquisto.getCodice() + ", " + tipoConsegna + ", " + getSpesaTotale();
	}
	
	
	
}
