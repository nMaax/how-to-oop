package ecommerce;

import java.util.ArrayList;

public class Acquisto {
	
	public static final int DEFAULT_PURCHASE_CODE = 0;
	public static final int DEFAULT_PIECES_NUMBER = 0;
	
	private int codice;
	
	private Prodotto prodotto;
	private int numeroUnita;
	private double spesaAcquisto;
	
	private Utente utente;
	
	
	public Acquisto() {
		this(DEFAULT_PURCHASE_CODE, new Prodotto(), DEFAULT_PIECES_NUMBER, new Utente());
	}

	public Acquisto(int codice, Prodotto prodotto, int numeroUnita, Utente utente) {
		this.codice = codice;
		this.prodotto = prodotto;
		this.numeroUnita = numeroUnita;
		this.spesaAcquisto = prodotto.getPrezzoUnitario() * numeroUnita;
		this.utente = utente;
	}
	
	/**
	 * Fornendo una stringa e una lista di Prodotti dove cercare il prodotto genera un oggetto Acquista
	 * @param codice il codice che si vuole assegnare all'acquisto
	 * @param acquisto la stringa riportante l'aquisto nel formato "codiceProdotto;quantità"
	 * @param utente l'utente che effettua l'acquisto
	 * @param prodotti la lista (Array List) di prodotti
	 * @param add 	nel caso in cui non ci sia il prodotto dentro la lista esso viene automaticamente creato, 
	 * 				se add == true ed è accessibile (public) allora esso viene anche inserito nella lista passata per parametro, 
	 * 				altrimenti no
	 * */
	public Acquisto(int codice, String acquisto, Utente utente, ArrayList<Prodotto> prodotti, boolean add) {
		
		this.codice = codice;
		
		// Estraggo i dati dalla stringa
		String[] componentsOfAcquisto = acquisto.split(";");
		String codiceProdottoFromString = componentsOfAcquisto[0];
		String sNumeroUnitaFromString = componentsOfAcquisto[1]; // Hungarian notation
		int iNumeroUnitaFromString = Integer.parseInt(sNumeroUnitaFromString); 
		
		// Cerco l'oggetto Prodotto con il dato codice nella lista
		
		boolean trovato = false;
		for (Prodotto prodotto : prodotti) {
			
			String codiceProdottoFromMem = prodotto.getCodiceProdotto();
			
				// se lo trovo salvo quell'oggetto come parametro, 
			if (codiceProdottoFromMem.equals(codiceProdottoFromString)) {
				this.prodotto = prodotto;
				trovato = true;
			}
		}
			
		if(!trovato) {
			if (add) {
			// altrimento genero io un prodotto e (se add == true) lo aggiungo alla lista (effetto retroattivo)
			// ora l'oggetto Prodotto "vive" sia nella lista che nell'oggetto Acquisto (grazie all'uso dei riferimenti e puntatori)
				this.prodotto = new Prodotto(codiceProdottoFromString, "NO_DESC", 0.0, 0);
				prodotti.add(prodotto);
			} else {
			// altrimenti (add == false) genero un prodotto e non lo metto in nessuna struttura dati
				this.prodotto = new Prodotto(codiceProdottoFromString, "NO_DESC", 0.0, 0);
			}
		}
		
		this.numeroUnita = iNumeroUnitaFromString;
		this.spesaAcquisto = prodotto.getPrezzoUnitario() * numeroUnita;
		this.utente = utente;
		
	}
	
	/**
	 * Fornendo una stringa e una lista di Prodotti dove cercare il prodotto genera un oggetto Acquista
	 * @param codice il codice che si vuole assegnare all'acquisto
	 * @param acquisto la stringa riportante l'aquisto nel formato "codiceProdotto;quantità"
	 * @param utente l'utente che effettua l'acquisto
	 * @param prodotti la lista (Array List) di prodotti
	 * */
	public Acquisto(int codice, String acquisto, Utente utente, ArrayList<Prodotto> prodotti) {
		this(codice, acquisto, utente, prodotti, false);
	}
	
	/* GETTERS */

	public Prodotto getProdotto() {
		return prodotto;
	}

	public int getNumeroUnita() {
		return numeroUnita;
	}

	public int getCodice() {
		return codice;
	}
	
	public double getSpesaAcquisto() {
		return spesaAcquisto;
	}
	
	public Utente getUtente() {
		return utente;
	}
	
	/* SETTERS */

	public void setProdotto(Prodotto prodotto) {
		this.prodotto = prodotto;
	}

	public void setNumeroUnita(int numeroUnita) {
		this.numeroUnita = numeroUnita;
	}

	public void setCodice(int codice) {
		this.codice = codice;
	}

	public void setSpesaAcquisto(double spesaAcquisto) {
		this.spesaAcquisto = spesaAcquisto;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}
	
	/* ALTRI METODI */

	public String descriviti() {
		return codice + ";" + utente.getCodiceFiscale() + ";" + prodotto.getCodiceProdotto() + ";" + numeroUnita + ";" + prodotto.getPrezzoUnitario() * numeroUnita;
	}

}
