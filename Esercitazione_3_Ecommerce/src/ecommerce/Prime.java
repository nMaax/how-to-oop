package ecommerce;

public class Prime extends Consegna{
	
	public static final double PRIME_PERCENT = 0.1; //10% di spesa in piú per le consegne prime
	public static final String DELIVER_TYPE = "P";
	public static final String DEFAULT_DATE = "NO_DATE";
	
	private String dataConsegna;
	
	public Prime() {
		super();
		this.tipoConsegna = DELIVER_TYPE;
		this.dataConsegna = DEFAULT_DATE;
	}
	
	public Prime(int numero, String indirizzoSpedizione, Acquisto acquisto, String dataConsegna) {
		super(numero, indirizzoSpedizione, acquisto);
		this.tipoConsegna = DELIVER_TYPE;
		this.dataConsegna = dataConsegna;
	}
	
	/* GETTERS */

	public String getDataConsegna() {
		return dataConsegna;
	}
	
	/* SETTERS */
	
	public void setDataConsegna(String dataConsegna) {
		this.dataConsegna = dataConsegna;
	}
	
	/* ALTRI METODI */
	
	@Override
	public double getSpesaConsegna() {
		return PRIME_PERCENT * spesaOrdine;
	}
	
	public String descriviti() {
		return super.descriviti() + ", " + dataConsegna;
	}
	
}
