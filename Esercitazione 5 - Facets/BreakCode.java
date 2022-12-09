package test;

import java.util.Collection;
import java.util.LinkedList;

import facets.*;

public class BreakCode {
	
	private static Facets f = new Facets();

	public static void main(String[] args) {
		test1();
		test2();
		test3();
		test4();
	}

	public static void test1() {
		sopln("<-- TEST 1 -->\n");
		makeSubscribers();
		describeSubscribers();
		searchSubscribers();
		listSubscribers();
	}
	
	public static void test2() {
		sopln("<-- TEST 2 -->\n");
		makeImages();
		describeImages();
		searchImages();
		listImages();
	}
	
	public static void test3() {
		sopln("<-- TEST 3 -->\n");
		makeReports();
		searchReports();
		listReports();
	}
	
	//TODO Define tests of part 4
	public static void test4() {
		sopln("<-- TEST 4 -->\n");
		readTxtData();
	}
	
	public static void makeSubscribers() {
		sopln("* * * CREO ISCRITTI * * *\n");
		
		sopln("Creo un po' di iscritti normali\n");
		sopln(f.nuovaIscrizione("fabio.garcea@polito.it", "Fabio", "Garcea", "Uomo", 29));
		sopln(f.nuovaIscrizione("fabio.cegar@polito.it", "Fabio", "Cegar", "Uomo", 92));
		sopln(f.nuovaIscrizione("fabrizio.lamberti@polito.it", "Fabrizio", "Lamberti", "Uomo", 40));
		sopln(f.nuovaIscrizione("lia.morra@polito.it", "Lia", "Morra", "Donna", 40));
		sopln(f.nuovaIscrizione("renato.olivetti@olivetti.it", "Renato", "Olivetti", "Uomo", 78));
		sopln(f.nuovaIscrizione("lena.oxton@ow.blz", "Lena", "Oxton", "Donna", 27));
		sopln(f.nuovaIscrizione("pomme@spotify.com", "Claire", "Pommet", "Donna", 24));
		sopln(f.nuovaIscrizione("yumi.arai@spotify.com", "Yumi", "Arai", "Donna", 56));
		sopln(f.nuovaIscrizione("guido.saracco@polito.it", "Guido", "Saracco", "Uomo", 57));
		sopln(f.nuovaIscrizione("ikari.shinji@nerv.jp", "Ikari", "Shinji", "Uomo", 14));
		sopln(f.nuovaIscrizione("reiI.ayanami@nerv.jp", "Rei", "Ayanami", "Donna", 14));		
		sopln(f.nuovaIscrizione("reiII.ayanami@nerv.jp", "Rei", "Ayanami", "Donna", 14));		
		sopln(f.nuovaIscrizione("reiIII.ayanami@nerv.jp", "Rei", "Ayanami", "Donna", 14));		
		
		sopln(f.nuovaIscrizione("asuka.soryu@nerv.jp", "Asuka", "Langley Soryu", "Donna", 14));		
		Iscritto iscritto = f.nuovaIscrizione("misato.katsuragi@nerv.jp", "Misato", "Katsuragi", "Donna", 29);
		sopln(iscritto);
		sopln();
		
		sopln("Creo iscritti già esistenti\n");
		sopln(f.nuovaIscrizione("guido.saracco@polito.it", "Guido", "Saracco", "Uomo", 10));
		sopln(f.nuovaIscrizione("ikari.shinji@nerv.jp", "Ikari", "Shinji", "Uomo", 40));
		sopln(f.nuovaIscrizione("reiII.ayanami@nerv.jp", "Rei", "Ayanami", "Niente", 50));		
		sopln(f.nuovaIscrizione("misato.katsuragi@nerv.jp", "Mitosa", "Ragikatsu", "Uomo", 1989));
		sopln();
		
		sopln("Invoco i getter di "+ iscritto.getNome() + " " +iscritto.getCognome() + "\n");
		sopln(iscritto.getEmail());
		sopln(iscritto.getNome());
		sopln(iscritto.getCognome());
		sopln(iscritto.getGenere());
		sopln(iscritto.getEta());
		sopln();
		
		sopln();
	}
	
	public static void describeSubscribers() {
		sopln("* * * DESCRIVO ISCRITTI * * *\n");
		
		sopln("Descrivo un utente esistente\n");
		sopln(f.descriviIscritto("pomme@spotify.com"));
		sopln();
		
		sopln("Descrivo un utente non esistente\n");
		sopln(f.descriviIscritto("email@non-esistente.it"));
		sopln();
		
		sopln();
	}
	
	public static void searchSubscribers() {
		sopln("* * * CERCO ISCRITTI * * *\n");
		
		sopln("Cerco iscritto per email esistente");
		sopln(f.cercaIscrittoPerEmail("yumi.arai@spotify.com"));
		sopln();
		
		sopln("Cerco iscritto per email non esitente");
		sopln(f.cercaIscrittoPerEmail("yumi.arai@primemusic.com"));
		sopln();
		
		sopln("Cerco iscritto per nome e cognome completi");
		sopln(f.cercaIscrittoPerNomeCognome("Lena", "Oxton"));
		sopln();
		
		sopln("Cerco iscritto per nome non completo e cognome completo");
		sopln(f.cercaIscrittoPerNomeCognome("en", "Oxton"));
		sopln();
		
		sopln("Cerco iscritto per nome completo e cognome non completo");
		sopln(f.cercaIscrittoPerNomeCognome("Lena", "xto"));
		sopln();
		
		sopln("Cerco iscritto per nome e cognome non completi");
		sopln(f.cercaIscrittoPerNomeCognome("Len", "xton"));
		sopln();
		
		sopln("Cerco iscritto per nome non completo e cognome completamente sbagliato");
		sopln(f.cercaIscrittoPerNomeCognome("en", "Morrison"));
		sopln();
		
		sopln("Cerco iscritto per nome completamente sbagliato e cognome non completo");
		sopln(f.cercaIscrittoPerNomeCognome("Winston", "Oxt"));
		sopln();
		
		sopln("Cerco iscritto per nome e cognome completamente sbagliati");
		sopln(f.cercaIscrittoPerNomeCognome("Nessun nome", "e nemmeno un cognome"));
		sopln();
		
		sopln("Cerco piú iscritti per nome incompleto (e cognome vuoto)");
		sopln(f.cercaIscrittoPerNomeCognome("Re", ""));
		sopln();
		
		sopln("Cerco piú iscritti per nome completo (e cognome vuoto)");
		sopln(f.cercaIscrittoPerNomeCognome("Fabio", ""));
		sopln();
		
		sopln("Cerco piú iscritti per cognome completo (e nome vuoto)");
		sopln(f.cercaIscrittoPerNomeCognome("", "a"));
		sopln();
		
		sopln();
	}
	
	public static void listSubscribers() {
		sopln("* * * ELENCO ISCRITTI * * *\n");
		
		sopln("Elenco iscritti");
		Collection<Iscritto> elenco = f.elencoIscritti();
		sopln("Lunghezza elenco:" + elenco.size());
		for (Iscritto iscritto : elenco) {
			sopln(iscritto);
		}
		sopln();
		
		sopln();
	}
	
	public static void makeImages() {
		sopln("* * * CREO IMMAGINI * * *\n");
		
		sopln("Creo immagini normali");
		sopln(f.nuovaImmagine("fabio.garcea@polito.it", "sardegna2021.jpg", "Instagram", 30));
		sopln(f.nuovaImmagine("fabio.garcea@polito.it", "louvre_monalisa.jpg", "Viaggi"));
		sopln(f.nuovaImmagine("fabio.garcea@polito.it", "profile_picture_tw.png", "Twitter", 81.3));
		sopln(f.nuovaImmagine("lia.morra@polito.it", "20200517_165503.jpg", "Conferenze"));
		sopln(f.nuovaImmagine("lia.morra@polito.it", "20200821_190408.jpg", "Mostre"));
		sopln(f.nuovaImmagine("lia.morra@polito.it", "20200517_165503.jpg", 56));
		sopln(f.nuovaImmagine("fabrizio.lamberti@polito.it", "profile_pic.png", "Linkedin", 50.5));
		sopln(f.nuovaImmagine("asuka.soryu@nerv.jp", "profile1_pic.png", "Twitter", 50.5));
		sopln(f.nuovaImmagine("asuka.soryu@nerv.jp", "profile2_pic.png", "Tik Tok", 50.5));
		sopln(f.nuovaImmagine("asuka.soryu@nerv.jp", "profile3_pic.png", "Tumblr", 50.5));
		sopln(f.nuovaImmagine("asuka.soryu@nerv.jp", "profile4_pic.png", "YouTube", 50.5));
		sopln(f.nuovaImmagine("asuka.soryu@nerv.jp", "insta_pic.png", 150));
		sopln(f.nuovaImmagine("asuka.soryu@nerv.jp", "profile6_pic.png", 120));
		sopln(f.nuovaImmagine("asuka.soryu@nerv.jp", "story3_selfie.png", 104));
		sopln(f.nuovaImmagine("asuka.soryu@nerv.jp", "reel.jpg", 134));
		sopln(f.nuovaImmagine("asuka.soryu@nerv.jp", "profile8_pic.png", "Album foto profilo"));		
		sopln(f.nuovaImmagine("asuka.soryu@nerv.jp", "eva02_pic.png", "Vacanza in Giappone"));		
		sopln(f.nuovaImmagine("asuka.soryu@nerv.jp", "recupero_eva03.jpeg", "Vacanza Cittá del vaticano"));		
		sopln(f.nuovaImmagine("asuka.soryu@nerv.jp", "profile8_pic.png", "Album foto profilo"));		
		ImmagineFacebook imgFB = f.nuovaImmagine("reiIII.ayanami@nerv.jp", "dateWithAdam.jpeg", "hairColoring");
		ImmagineInstagram imgIG = f.nuovaImmagine("reiIII.ayanami@nerv.jp", "dateWithAdam.jpeg", 14);
		ImmagineAltroSocial imgOT = f.nuovaImmagine("reiIII.ayanami@nerv.jp", "dateWithAdam.jpeg", "TikTok", 42.0);

		sopln();
		
		sopln("Creo un immagine di un utente che non esiste su Facebook");
		sopln("Creo un immagine di un utente che non esiste su Insta");
		sopln("Creo un immagine di un utente che non esiste su un Altro Social");
		sopln();
		
		sopln("Invoco i getters di "+imgFB.getNomeImmagine()+" (codice: "+imgFB.getCodiceImmagine()+")");
		sopln(imgFB.getCodiceImmagine());
		sopln(imgFB.getEmailIscritto());
		sopln(imgFB.getNomeImmagine());
		sopln();
		
		sopln("Invoco i getter specifici di un immagine Facebook");
		sopln(imgFB.getNomeAlbum());
		sopln();
		
		sopln("Invoco i getter specifici di un immagine Instagram");
		sopln(imgIG.getNumeroLike());
		sopln();
		
		sopln("Invoco i getter specifici di un immagine Altro Social");
		sopln(imgOT.getNomeSocial());
		sopln(imgOT.getDimensioneImmagine());
		sopln();
		
		sopln();
	}
	
	public static void describeImages() {
		sopln("* * * DESCRIVO IMMAGINI * * *\n");
		
		sopln("Descrivo un immagine esistente");
		sopln("Facebook: "+f.descriviImmagine(19));
		sopln("Instagra: "+f.descriviImmagine(15));
		sopln("Altro Social: "+f.descriviImmagine(11));
		sopln();
		
		sopln("Descrivo un immagine inesistente");
		sopln(f.descriviImmagine(-1));
		sopln();
		
		sopln();
	}
	
	public static void searchImages() {
		sopln("* * * CERCO IMMAGINI * * *\n");
		
		sopln("Cerco un immagine esistente");
		sopln("Facebook: "+f.cercaImmagine(19));
		sopln("Instagra: "+f.cercaImmagine(15));
		sopln("Altro Social: "+f.cercaImmagine(11));
		sopln();
		
		sopln("Cerco un immagine inesistente");
		sopln(f.cercaImmagine(-1));
		sopln();
		
		sopln();
	}
	
	public static void listImages() {
		sopln("* * * ELENCO IMMAGINI * * *\n");
		
		Collection<Immagine> images;
		
		sopln("Elenco immagini per codice");
		images = f.elencoImmaginiPerCodice(); 
		for (Immagine img : images) {
			sopln(img);
		}		
		sopln();
		
		sopln("Elenco immagini per tipologia");
		images = f.elencoImmaginiPerTipologia(); 
		for (Immagine img : images) {
			sopln(img);
		}		
		sopln();
		
		sopln("Elenco immagini Altro Social per dimensione");
		images = f.elencoImmaginiAltroSocialPerDimensione();
		for (Immagine img : images) {
			sopln(img);
		}		
		sopln();
		
		sopln();
	}
	
	public static void makeReports() {
		sopln("* * * CREO REPORTS * * *\n");
		
		sopln("Creo un po' di reports\n");
		
		Iscritto sub = f.cercaIscrittoPerEmail("asuka.soryu@nerv.jp");
		sopln("Iscritt*: "+sub.getNome()+"\n");
		
		LinkedList<String> sReports = new LinkedList<String>();
		
		sopln("Creo dei report per ogni immagine, con timestamps non in ordine\n");
		sReports.add("8;ImageNet_class:seashore(98.9%);20221202 11:30:00");
		sReports.add("9;ImageNet_class:sunset(96.7%);20221202 11:22:30");
		sReports.add("10;ImageNet_class:sunrise(89.9%);20221202 10:35:00");
		sReports.add("11;ImageNet_class:seastar(98.9%);20221202 11:30:00");
		sReports.add("12;ImageNet_class:sunglasses(96.7%);20221202 01:01:30");
		sReports.add("13;ImageNet_class:sunfire(86.9%);20221202 11:37:00");
		sReports.add("14;ImageNet_class:seahorse(99.9%);20221202 12:30:10");
		sReports.add("15;ImageNet_class:sun(79.1%);20221202 09:24:59");
		sReports.add("16;ImageNet_class:pacific_ocean(42.0%);20221202 09:24:04");
		
		sopln("Creo altri report per ogni immagine, con timestamps successivo all'ultimo creato\n");
		sReports.add("8;ImageNet_class:seashore(98.9%);20221202 11:30:01");
		sReports.add("9;ImageNet_class:sunset(96.7%);20221202 11:23:30");
		sReports.add("10;ImageNet_class:sunrise(89.9%);20221202 10:36:00");
		sReports.add("11;ImageNet_class:seastar(98.9%);20221202 12:30:00");
		sReports.add("12;ImageNet_class:sunglasses(96.7%);20221202 23:01:30");
		sReports.add("13;ImageNet_class:sunfire(86.9%);20221202 11:39:00");
		sReports.add("14;ImageNet_class:seahorse(99.9%);20221202 12:30:11");
		sReports.add("15;ImageNet_class:sun(79.1%);20221202 09:25:00");
		sReports.add("16;ImageNet_class:pacific_ocean(42.0%);20221202 09:24:05");
		
		sopln("Creo altri report per alcune immagini, ma stavolta con timestamp antecedenti\n");
		sReports.add("8;ImageNet_class:seashore(98.9%);20221202 01:30:01");
		sReports.add("9;ImageNet_class:sunset(96.7%);20221202 01:23:30");
		sReports.add("10;ImageNet_class:sunrise(89.9%);20221202 09:36:00");
		
		sopln("Creo altri report, ma per immagini che l'utente non possiede\n");
		sReports.add("1;ImageNet_class:seashore(98.9%);20221202 01:30:01");
		sReports.add("2;ImageNet_class:seashore(98.9%);20221202 01:30:01");
		sReports.add("3;ImageNet_class:seashore(98.9%);20221202 01:30:01");
		sopln();
		
		Collection<Report> doneReports = f.nuoviReport(sub.getEmail(), sReports);
		for (Report report : doneReports) {
			sopln(report);
		}
		
		sopln("Creo un report per un utente che non esiste");
		sReports.clear();
		sReports.add("1;ImageNet_class:seashore(98.9%);20221203 01:30:01");
		doneReports = f.nuoviReport("email@non-esistente.no", sReports);
		sopln(doneReports);
		
		sopln();
	}
	
	public static void searchReports() {
		sopln("* * * CERCO REPORTS * * *\n");
		
		sopln("Cerco report di immagini con report");
		sopln(f.reportImmagine(9));
		sopln();
		
		sopln("Cerco report di immagini senza report");
		sopln(f.reportImmagine(1));
		sopln();
		
		sopln("Cerco report di immagini che non esistono");
		sopln(f.reportImmagine(-1));
		sopln();
			
		sopln("Cerco report con id che esiste");
		try {
			sopln(f.cercaReportPerId(1));
		} catch (ReportNonEsistenteException e){
			System.err.println("Report (che dovrebbe esistere) non trovato!");
		}
		sopln();
		
		sopln("Cerco report con id che non esiste");
		try {
			sopln(f.cercaReportPerId(-1));
		} catch (ReportNonEsistenteException e) {
			sopln("Errore " + e + " gestito correttamente");
		}	
		sopln();
		
		sopln();
	}
	
	public static void listReports() {
		sopln("* * * ELENCO REPORTS * * *\n");
		
		sopln("Elenco i report di Asuka");
		try {
			Collection<Report> reportAsuka = f.elencoReportPerEmailIscritto("asuka.soryu@nerv.jp");
			for (Report report : reportAsuka) {
				sopln(report);
			}
		} catch (IscrittoNonEsistenteException e) {
			System.err.println("Iscritto (che dovrebbe esistere) non trovato!");
		}
		sopln();
		
		sopln("Elenco i report di un utente che non esiste");
		try {
			Collection<Report> reportUnknown = f.elencoReportPerEmailIscritto("email@ines.istente");
			for (Report report : reportUnknown) {
				sopln(report);
			}
		} catch (IscrittoNonEsistenteException e) {
			System.out.println("Errore " + e + " gestito correttamente");
		}
		sopln();
		
		sopln();
		
	}
	
	public static void readTxtData() {
		sopln("* * * LEGGO IL FILE TXT * * *\n");
		sopln();
	}

	
	public static void sopln() {
		sopln("");
	}
	
	public static void sopln(Object o) {
		System.out.println(o);
	}
	
}
