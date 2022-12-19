package facets;

public class ImmagineFacebook extends Immagine {

	public static final String DEFAULT_NOME_ALBUM = "NO_NAME";
	public static final String DEFAULT_TYPE = "FACEB";
	
	private String nomeAlbum;
	
	public ImmagineFacebook() {
		super();
		tipoImmagine = DEFAULT_TYPE;
		nomeAlbum = DEFAULT_NOME_ALBUM;
	}

	public ImmagineFacebook(int codiceImmagine, String nomeImmagine, Iscritto iscritto, String nomeAlbum) {
		super(codiceImmagine, nomeImmagine, iscritto);
		tipoImmagine = DEFAULT_TYPE;
		this.nomeAlbum = nomeAlbum;
	}

	public String getNomeAlbum() {
		return nomeAlbum;
	}
	
	@Override
	public String toString() {
		return super.toString() + " " + nomeAlbum;
	}
}
