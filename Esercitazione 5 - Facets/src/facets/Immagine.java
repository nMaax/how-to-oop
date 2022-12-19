package facets;

public class Immagine {

	public static final int DEFAULT_ID = -1;
	public static final String DEFAULT_NOME = "NO_NAME";
	public static final Iscritto DEFAULT_ISCRITTO = new Iscritto();
	public static final String DEFAULT_TYPE = "NONE";
	
	protected int codiceImmagine;
	protected String nomeImmagine;
	protected String tipoImmagine;
	protected Iscritto iscritto;
	
	public Immagine() {
		this(DEFAULT_ID, DEFAULT_NOME, DEFAULT_ISCRITTO);
	}

	public Immagine(int codiceImmagine, String nomeImmagine, Iscritto iscritto) {
		super();
		this.codiceImmagine = codiceImmagine;
		this.nomeImmagine = nomeImmagine;
		tipoImmagine = DEFAULT_TYPE;
		this.iscritto = iscritto;
	}

	public int getCodiceImmagine() {
		return codiceImmagine;
	}
	
	public String getEmailIscritto() {
		return iscritto.getEmail();
	}
	
	public String getNomeImmagine() {
		return nomeImmagine;
	}

	@Override
	public String toString() {
		return codiceImmagine + " " + iscritto.getEmail() + " " + nomeImmagine + " " + tipoImmagine;
	}
	
}
