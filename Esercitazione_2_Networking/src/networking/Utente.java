package networking;

public class Utente {
	
	String codiceFiscale;
	String nome;
	String cognome;
	String occupazione;

	public Utente(String codiceFiscale, String nome, String cognome, String occupazione) {
		this.codiceFiscale = codiceFiscale;
		this.nome = nome;
		this.cognome = cognome;
		this.occupazione = occupazione;
	}
	
	public String getCodiceFiscale() {
		return codiceFiscale;
	}
	
	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCognome() {
		return cognome;
	}
	
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	public String getOccupazione() {
		return occupazione;
	}
	
	public void setOccupazione(String occupazione) {
		this.occupazione = occupazione;
	}

}
