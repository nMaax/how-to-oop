package facets;

public class ImmagineAltroSocial extends Immagine implements Comparable<ImmagineAltroSocial>{

	public static final String DEFAULT_SOCIAL_NAME = "NO_NAME";
	public static final double DEFAULT_SIZE = -1.0;
	public static final String DEFAULT_TYPE = "ALTRO";
	
	private String nomeSocial;
	private double dimensioneImmagine;
	
	public ImmagineAltroSocial() {
		super();
		tipoImmagine = DEFAULT_TYPE;
		nomeSocial = DEFAULT_SOCIAL_NAME; 
		dimensioneImmagine = DEFAULT_SIZE;
	}

	public ImmagineAltroSocial(int codiceImmagine, String nomeImmagine, Iscritto iscritto, String nomeSocial, double dimensioneImmagine) {
		super(codiceImmagine, nomeImmagine, iscritto);
		tipoImmagine = DEFAULT_TYPE;
		this.nomeSocial = nomeSocial;
		this.dimensioneImmagine = dimensioneImmagine;
	}

	public String getNomeSocial() {
		return nomeSocial;
	}
	
	public double getDimensioneImmagine() {
		return dimensioneImmagine;
	}
	
	@Override
	public int compareTo(ImmagineAltroSocial other) {
		return (int) (dimensioneImmagine - other.getDimensioneImmagine());
	}
	
	@Override
	public String toString() {
		return super.toString() + " " + nomeSocial + " " + dimensioneImmagine;
	}

}
