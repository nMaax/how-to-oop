package cluster;

public class UtenteAdmin extends Utente{
	
	public static final String DEFAULT_PUBLIC_KEY = "NO_PUB_KEY";
	public static final String DEFAULT_PRIVATE_KEY = "NO_PRV_KEY";
	
	private String chiavePubblica;
	private String chiavePrivata;

	public UtenteAdmin() {
		super();
		this.chiavePubblica = DEFAULT_PUBLIC_KEY;
		this.chiavePrivata = DEFAULT_PRIVATE_KEY;
	}

	public UtenteAdmin(String nome, String cognome, String dataNascita, String email, String password, String chiavePubblica, String chiavePrivata) {
		super(nome, cognome, dataNascita, email, password);
		this.chiavePubblica = chiavePubblica;
		this.chiavePrivata = chiavePrivata;
	}

	public String getChiavePubblica() {
		return this.chiavePubblica;
	}
	
	public String getChiavePrivata() {
		return this.chiavePrivata;
	}
	
	@Override
	public void setDati(String nome, String cognome, String password, String dataNascita, String chiavePubblica, String chiavePrivata) {
		super.setDati(nome, cognome, password, dataNascita, chiavePubblica, chiavePrivata);
		this.chiavePubblica = chiavePubblica;
		this.chiavePrivata = chiavePrivata;
	}
	
	@Override
	public String toString(boolean censoredPsw) {
		return super.toString(censoredPsw) + ", " + chiavePubblica + ", " + chiavePrivata;
	}
	
	@Override
	public String toString() {
		return this.toString(false);
	}
	
}
