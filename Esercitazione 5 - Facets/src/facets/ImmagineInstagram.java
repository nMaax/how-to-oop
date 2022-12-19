package facets;

public class ImmagineInstagram extends Immagine implements Comparable<ImmagineInstagram>{
	
	public static final int DEFAULT_NUM_LIKE = -1;
	public static final String DEFAULT_TYPE = "INSTA";
	
	private int numeroLike;
	
	public ImmagineInstagram() {
		super();
		tipoImmagine = DEFAULT_TYPE;
		numeroLike = DEFAULT_NUM_LIKE;
	}

	public ImmagineInstagram(int codiceImmagine, String nomeImmagine, Iscritto iscritto, int numeroLike) {
		super(codiceImmagine, nomeImmagine, iscritto);
		tipoImmagine = DEFAULT_TYPE;
		this.numeroLike = numeroLike;
	}

	public int getNumeroLike() {
		return numeroLike;
	}
	
	@Override
	public int compareTo(ImmagineInstagram other) {
		return -(numeroLike - other.getNumeroLike());
	}
	
	@Override
	public String toString() {
		return super.toString() + " " + numeroLike;
	}
}
