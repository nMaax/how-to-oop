package ecommerce;

public class Standard extends Consegna{
	
	public static final String DELIVER_TYPE = "S";

	public Standard() {
		super();
		this.tipoConsegna = DELIVER_TYPE;
	}
	
	public Standard(int numero, String indirizzoSpedizione, Acquisto acquisto) {
		super(numero, indirizzoSpedizione, acquisto);
		this.tipoConsegna = DELIVER_TYPE;
	}
	
}
