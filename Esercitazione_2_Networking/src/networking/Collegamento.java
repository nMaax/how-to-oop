package networking;

public class Collegamento {
	
	int codiceCollegamento;
	
	String codiceFiscale1;
	String codiceFiscale2;
	
	String dataCollegamento;
	
	public Collegamento(int codiceCollegamento, String codiceFiscale1, String codiceFiscale2, String dataCollegamento) {
			this.codiceCollegamento = codiceCollegamento;
			this.codiceFiscale1 = codiceFiscale1;
			this.codiceFiscale2 = codiceFiscale2;
			this.dataCollegamento = dataCollegamento;
}

	public int getCodiceCollegamento() {
		return codiceCollegamento;
	}

	public void setCodiceCollegamento(int codiceCollegamento) {
		this.codiceCollegamento = codiceCollegamento;
	}

	public String getCodiceFiscale1() {
		return codiceFiscale1;
	}

	public void setCodiceFiscale1(String codiceFiscale1) {
		this.codiceFiscale1 = codiceFiscale1;
	}

	public String getCodiceFiscale2() {
		return codiceFiscale2;
	}

	public void setCodiceFiscale2(String codiceFiscale2) {
		this.codiceFiscale2 = codiceFiscale2;
	}

	public String getDataCollegamento() {
		return dataCollegamento;
	}

	public void setDataCollegamento(String dataCollegamento) {
		this.dataCollegamento = dataCollegamento;
	}
	
}
