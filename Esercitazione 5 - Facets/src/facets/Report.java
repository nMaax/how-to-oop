package facets;

import facets.util.Timestamp;

public class Report implements Comparable<Report>{
	
	public static final int DEFAULT_CODICE = -1;
	public static final Immagine DEFAULT_IMMAGINE = new Immagine();
	public static final String DEFAULT_DESCRIZIONE = "NO_DESC";
	public static final String DEFAULT_TIMESTAMP = "NO_TIMESTAMP";
	
	private int codice;
	private Immagine immagine;
	private String descrizione;
	private Timestamp timestamp;
	
	public Report() {
		this(DEFAULT_CODICE, DEFAULT_IMMAGINE, DEFAULT_DESCRIZIONE, DEFAULT_TIMESTAMP);
	}

	public Report(int codice, Immagine immagine, String descrizione, String timestamp) {
		this(codice, immagine, descrizione, new Timestamp(timestamp));
	}
	
	public Report(int codice, Immagine immagine, String descrizione, Timestamp timestamp) {
		super();
		this.codice = codice;
		this.immagine = immagine;
		this.descrizione = descrizione;
		this.timestamp = timestamp;
	}

	public String getCodice() {
		return ""+codice;
	}
	
	public int getIntegerCodice() {
		return codice;
	}
	
	public String getDescrizione() {
		return descrizione;
	}
	
	public String getTimestamp() {
		return timestamp.toString();
	}
	
	public Timestamp getRawTimestamp() {
		return timestamp;
	}
	
	public Immagine getImmagine() {
		return immagine;
	}
	
	@Override
	public int compareTo(Report other) {
		return timestamp.compareTo(other.timestamp);
	}
	
	/*
	//TODO rimuovi il toString() prima di consegnare
	@Override
	public String toString() {
		return "codice=" + codice + ", immagine=" + immagine.getCodiceImmagine() + ", descrizione=" + descrizione + ", timestamp=" + timestamp;
	}*/
	
	
}
