package facets;

public class Iscritto implements Comparable<Iscritto>{
	
	public static final String DEFAULT_EMAIL = "no-reply@domain.com";
	public static final String DEFAULT_NOME = "NO_NAME";
	public static final String DEFAULT_COGNOME = "NO_SURNAME";
	public static final String DEFAULT_GENERE = "NO_GENDER";
	public static final int DEFAULT_ETA = 0;
	
	private String email;
	private String nome;
	private String cognome;
	private String genere;
	private int eta;

	public Iscritto() {
		this(DEFAULT_EMAIL, DEFAULT_NOME, DEFAULT_COGNOME, DEFAULT_GENERE, DEFAULT_ETA);
	}
	
	public Iscritto(String email, String nome, String cognome, String genere, int eta) {
		super();
		this.email = email;
		this.nome = nome;
		this.cognome = cognome;
		this.genere = genere;
		this.eta = eta;
	}

	public String getEmail() {
		return email;
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getCognome() {
		return cognome;
	}
	
	public String getGenere() {
		return genere;
	}
	
	public int getEta() {
		return eta;
	}
	
	/*Compara l'istanza con un oggetto Iscritto sulla base di nome e, se nome omonimo, cognome */
	@Override
	public int compareTo(Iscritto other) {
		int nomeDiff = nome.compareTo(other.getNome());
		int cognomeDiff = cognome.compareTo(other.getCognome());
		
		if (nomeDiff != 0) return nomeDiff;
		
		return cognomeDiff;
	}
	
	@Override
	public String toString() {
		return email + " " + nome + " " + cognome + " " + genere + " " + eta;
	}
	
	
}