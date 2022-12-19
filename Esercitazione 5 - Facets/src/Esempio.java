import java.util.LinkedList;

import facets.*;

public class Esempio {

	public static void main(String[] args) throws ReportNonEsistenteException, IscrittoNonEsistenteException {

		Facets f = new Facets();
		
		System.out.println("\n/****** R1 ******/");
		
		System.out.println("Nuove iscrizioni");
		Iscritto i1 = f.nuovaIscrizione("fabio.garcea@polito.it", "Fabio", "Garcea", "Uomo", 29);
		Iscritto i2 = f.nuovaIscrizione("fabrizio.lamberti@polito.it", "Fabrizio", "Lamberti", "Uomo", 40);
		Iscritto i3 = f.nuovaIscrizione("lia.morra@polito.it", "Lia", "Morra", "Donna", 40);
		
		System.out.println("\nUtenti iscritti:\n");
		System.out.println(f.descriviIscritto(i1.getEmail()));
		System.out.println(f.descriviIscritto(i2.getEmail()));
		System.out.println(f.descriviIscritto(i3.getEmail()));
		
		System.out.println("\nRicerca iscritto:\n");
		Iscritto iTrovato = f.cercaIscrittoPerEmail("lia.morra@polito.it");
		System.out.println(f.descriviIscritto(iTrovato.getEmail()));
		
		System.out.println("\nRicerca iscritto/i per nome e cognome:\n");
		LinkedList<Iscritto> iTrovatiNomeCognome = new LinkedList<>(f.cercaIscrittoPerNomeCognome("o", "a"));
		for(Iscritto ii : iTrovatiNomeCognome)
			System.out.println(f.descriviIscritto(ii.getEmail()));
		
		System.out.println("\nElenco iscritti:\n");
		LinkedList<Iscritto> elencoIscritti = new LinkedList<>(f.elencoIscritti());
		for(Iscritto ii : elencoIscritti)
			System.out.println(f.descriviIscritto(ii.getEmail()));
		
		System.out.println("\n/****** R2 ******/");
		
		System.out.println("Nuove immagini");
		Immagine im1 = f.nuovaImmagine("fabio.garcea@polito.it", "sardegna2021.jpg", "Instagram", 30);
		Immagine im2 = f.nuovaImmagine("fabio.garcea@polito.it", "louvre_monalisa.jpg", "Viaggi");
		Immagine im3 = f.nuovaImmagine("fabio.garcea@polito.it", "profile_picture_tw.png", "Twitter", 81.3);
		Immagine im4 = f.nuovaImmagine("lia.morra@polito.it", "20200517_165503.jpg", "Conferenze");
		Immagine im5 = f.nuovaImmagine("lia.morra@polito.it", "20200821_190408.jpg", "Mostre");
		Immagine im6 = f.nuovaImmagine("lia.morra@polito.it", "20200517_165503.jpg", 56);
		Immagine im7 = f.nuovaImmagine("fabrizio.lamberti@polito.it", "profile_pic.png", "Linkedin", 50.5);
		
		System.out.println("\nImmagini create:\n");
		System.out.println(f.descriviImmagine(im1.getCodiceImmagine()));
		System.out.println(f.descriviImmagine(im2.getCodiceImmagine()));
		System.out.println(f.descriviImmagine(im3.getCodiceImmagine()));
		System.out.println(f.descriviImmagine(im4.getCodiceImmagine()));
		System.out.println(f.descriviImmagine(im5.getCodiceImmagine()));
		System.out.println(f.descriviImmagine(im6.getCodiceImmagine()));
		System.out.println(f.descriviImmagine(im7.getCodiceImmagine()));
		
		System.out.println("\nRicerca immagine:\n");
		Immagine immTrovata = f.cercaImmagine(2);
		System.out.println(f.descriviImmagine(immTrovata.getCodiceImmagine()));
		
		System.out.println("\nElenchi immagini");
		System.out.println("\nCodice, crescente:\n");
		LinkedList<Immagine> elenco1 = new LinkedList<>(f.elencoImmaginiPerCodice());
		for(Immagine ii : elenco1)
			System.out.println(f.descriviImmagine(ii.getCodiceImmagine()));
		
		System.out.println("\nTipologia:\n");
		LinkedList<Immagine> elenco2 = new LinkedList<>(f.elencoImmaginiPerTipologia());
		for(Immagine ii : elenco2)
			System.out.println(f.descriviImmagine(ii.getCodiceImmagine()));
		
		System.out.println("\nAltro social per dimensione:\n");
		LinkedList<Immagine> elenco3 = new LinkedList<>(f.elencoImmaginiAltroSocialPerDimensione());
		for(Immagine ii : elenco3)
			System.out.println(f.descriviImmagine(ii.getCodiceImmagine()));
		
		System.out.println("\n/****** R3 ******/");
		
		System.out.println("Nuovi report");
		LinkedList<String> report1 = new LinkedList<String>();
		report1.add("1;ImageNet_class:seashore(98.9%);20221202 11:30:00");
		report1.add("3;ImageNet_class:sunglasses(89.9%);20221202 11:35:00");
		LinkedList<Report> reportUtente1 = new LinkedList<Report>(f.nuoviReport("fabio.garcea@polito.it", report1));
		
		System.out.println("\nReport creati:\n");
		for(Report ri : reportUtente1)
			System.out.println(ri.getDescrizione() + " " + ri.getTimestamp());
		
		System.out.println("\nReport di un'immagine:\n");
		for(Report ri : f.reportImmagine(3))
			System.out.println(ri.getDescrizione() + " " + ri.getTimestamp());
		
		System.out.println("\nRicerca report:\n");
		Report rTrovato = f.cercaReportPerId(1);
		System.out.println(rTrovato.getDescrizione() + " " + rTrovato.getTimestamp());
		
		System.out.println("\nElenco report per iscritto\n");
		LinkedList<Report> reportTrovati = new LinkedList<>(f.elencoReportPerEmailIscritto("fabio.garcea@polito.it"));
		
		for (Report ri : reportTrovati) {
			System.out.println("Report:" + ri.getCodice());
			System.out.println(rTrovato.getDescrizione() + " " + rTrovato.getTimestamp());
		}
		
		System.out.println("\n/****** R4 ******/");
		
		System.out.println("\nLettura da file:\n");
		Facets f2 = new Facets();
		
		f2.leggiDatiFacets("input.txt");
		
		System.out.println("\nElenco iscritti:\n");
		LinkedList<Iscritto> elencoIscritti2 = new LinkedList<>(f2.elencoIscritti());
		for(Iscritto ii : elencoIscritti2)
			System.out.println(f2.descriviIscritto(ii.getEmail()));
		
		System.out.println("\nElenco immagini:\n");
		LinkedList<Immagine> elencoImmagini2 = new LinkedList<>(f2.elencoImmaginiPerCodice());
		for(Immagine ei : elencoImmagini2)
			System.out.println(f2.descriviImmagine(ei.getCodiceImmagine()));
	}

}