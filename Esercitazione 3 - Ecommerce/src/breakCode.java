import ecommerce.*;

public class breakCode {

	public static void main(String[] args) {
		
		Ecommerce e = new Ecommerce(500);
		
		/* TEST PARTE 1 */
		
		testPart1(e);
		sopln("\n***************\n");
		
		/* TEST PARTE 2 */
		 
		testPart2(e);
		sopln("\n***************\n");
		
		/* TEST PARTE 2 */
		
		testPart3(e);
		sopln("\n***************\n");
		
		
	}
	
	public static void testPart1(Ecommerce e) {
		sopln("Test Parte 1 \n");
		
		sopln("Cerco i prodotti quando non c'è nulla");
		sopln("Cerco i prodotti che contengono la parola Eva:");
		for(Prodotto prodotto : e.cercaProdotti("Eva")) {
			sopln(prodotto);
		}
		sopln();
		sopln("Cerco i prodotti che contengono la parola eva (in minuscolo):");
		for(Prodotto prodotto : e.cercaProdotti("eva")) {
			sopln(prodotto);
		}
		sopln();
		sopln("Cerco i prodotti che contengono la parola DONT PANIC!: ");
		for(Prodotto prodotto : e.cercaProdotti("DON'T PANIC!")) {
			sopln(prodotto);
		}
		sopln();
		sopln("Cerco il prodotto con codice AM1:\n" + e.cercaProdotto("AM1"));
		sopln();
		sopln("Cerco il prodotto con codice SIUM:\n" + e.cercaProdotto("SIUM"));
		sopln();
		
		sopln("Creo i prodotti");
		Prodotto overwatch = e.nuovoProdotto("VG1", "Overwatch", 20.99, 100);
		Prodotto valorant = e.nuovoProdotto("VG2", "Valorant", 42.00, 4);
		Prodotto Agario = e.nuovoProdotto("VG3", "Agar.io", 20, 2);
		Prodotto Evangelion = e.nuovoProdotto("AM1", "Evangelion", 99.99, 3);
		Prodotto PrincessMononoke = e.nuovoProdotto("AM2", "Principessa Mononoke", 11.99, 7);
		Prodotto EvaTshirt = e.nuovoProdotto("AP1", "Evangelion T-Shirt Rei Ayanami", 49.95, 50);
		Prodotto TotoroPlush = e.nuovoProdotto("PL1", "Tototro Fluffy Plush", 3.00, 50);
		Prodotto saintGraahal = e.nuovoProdotto("KEY2EVEN", "Santo Graahal", 9999.00, 1);
		Prodotto windows10 = e.nuovoProdotto("WIN10", "Windows 10", 99.98, 0);
		
		sopln("Inventory state 1:");
		printInventory(e);
		sopln("");
		sopln("Cerco un prodotto quando il magazzino è mezzo vuoto");
		sopln(e.cercaProdotto("VG1"));
		sopln();
		
		sopln("Stampo il return di 3 incrementi di 2 prodotti: VG1 e VG2");
		sopln(e.nuovoProdotto("VG2", "Rantoval", 69, 1));
		sopln(e.nuovoProdotto("VG1", "Overwatch 2", 99.99, 500));
		sopln(e.nuovoProdotto("VG1", "Overwatch 3", 99.99, 500));
		sopln("");
		
		sopln("Inventory state 2:");
		printInventory(e);
		sopln("");
		
		sopln("Test cerca Prodotti:");
		sopln("Cerco i prodotti che contengono la parola Eva:");
		for(Prodotto prodotto : e.cercaProdotti("Eva")) {
			sopln(prodotto);
		}
		sopln();
		sopln("Cerco i prodotti che contengono la parola eva (in minuscolo):");
		for(Prodotto prodotto : e.cercaProdotti("eva")) {
			sopln(prodotto);
		}
		sopln();
		sopln("Cerco i prodotti che contengono la parola DONT PANIC!: ");
		for(Prodotto prodotto : e.cercaProdotti("DON'T PANIC!")) {
			sopln(prodotto);
		}
		sopln();
		sopln("Cerco il prodotto con codice AM1:\n" + e.cercaProdotto("AM1"));
		sopln();
		sopln("Cerco il prodotto con codice SIUM:\n" + e.cercaProdotto("SIUM"));
		sopln();
		
		sopln("Creo un nuovo ecommerce e provo a riempirlo tutto con un solo prodotto:");
		Ecommerce e2 = new Ecommerce(10);
		e2.nuovoProdotto("TEST1", "Giappone Spettaculari", 0, 500);
		printInventory(e2);
		sopln(e2.cercaProdotto("TEST1"));
		sopln();
	}
	
	public static void testPart2(Ecommerce e) {
		
		sopln("Creo alcuni utenti:");
		sopln(e.nuovoUtente("TRCR", "Oxton" , "Lena", 999999));
		sopln(e.nuovoUtente("SLDR76", "Morrison", "John Francis", 1580));
		sopln(e.nuovoUtente("WNSTN", "Monkey", "Winston", 420));
		sopln(e.nuovoUtente("KRK", "Kamori", "Kiriko", 750));
		
		sopln("\nStampo tutti gli utenti");
		printUsers(e);
		sopln();
		
		// Test univocitá codice fiscale
		sopln("Modifico un utente:");
		sopln(e.nuovoUtente("WNSTN", "S. Paceape", "Winston", 890));
		
		sopln("\nStampo tutti gli utenti");
		printUsers(e);
		sopln();
		
		sopln("Genero 1000 utenti nuovi, tutti con lo stesso codice fiscale");
		for (Integer i=0; i < 1000; i++) {
			e.nuovoUtente("NO_CODFIS", "NO_SURNAME", "NO_NAME", 1000);
		}
		
		sopln("\nStampo tutti gli utenti");
		printUsers(e);
		sopln();
		
		sopln("Genero 1000 utenti nuovi, tutti con codice fiscale diverso");
		for (Integer i=1; i < 1000; i++) {
			e.nuovoUtente(i.toString(), "NO_SURNAME", "NO_NAME", 1000);
		}
		
		sopln("\nStampo tutti gli utenti");
		printUsers(e);
		sopln();
		
		sopln("Aggiungo un altro utente e stampo il return:");
		sopln(e.nuovoUtente("SIUM", "Ronaldo", "Cristiano", 37));
		sopln();
		
		sopln("Cerco due utenti, uno che esiste e l'altro che non esiste");
		Utente utenteCercato1 = e.cercaUtente("WNSTN");
		Utente utenteCercato2 = e.cercaUtente("SIUM");
		sopln(utenteCercato1 + " | " + utenteCercato2);
		sopln();
		
		
		sopln("Disponibilitá dell'utente prima degli acquisti: "+e.cercaUtente("KRK").getDisponibilita());
		sopln("Effetto degli acquisi con Kiriko");
		
		/*
		 * Prodotto [codiceProdotto=VG1, descrizione=Overwatch, prezzoUnitario=20.99, numeroUnita=382]
		 * Prodotto [codiceProdotto=VG2, descrizione=Valorant, prezzoUnitario=42.0, numeroUnita=5]
		 * Prodotto [codiceProdotto=VG3, descrizione=Agar.io, prezzoUnitario=20.0, numeroUnita=2]
		 * Prodotto [codiceProdotto=AM1, descrizione=Evangelion, prezzoUnitario=99.99, numeroUnita=3]
		 * Prodotto [codiceProdotto=AM2, descrizione=Principessa Mononoke, prezzoUnitario=11.99, numeroUnita=7]
		 * Prodotto [codiceProdotto=AP1, descrizione=Evangelion T-Shirt Rei Ayanami, prezzoUnitario=49.95, numeroUnita=50]
		 * Prodotto [codiceProdotto=PL1, descrizione=Tototro Fluffy Plush, prezzoUnitario=3.0, numeroUnita=50]
		 * Prodotto [codiceProdotto=KEY2EVEN, descrizione=Santo Graahal, prezzoUnitario=9999.0, numeroUnita=1]
		 * Prodotto [codiceProdotto=WIN10, descrizione=Windows 10, prezzoUnitario=99.98, numeroUnita=0]
		 * 
		 * Effettuo un acquisto con...
		 * F quantita' oltre il limite di magazzino, 
		 * F uno di un prodotto che é giá a quantitá zero 
		 * T uno normale, 
		 * F uno con quanta' eccessive, 
		 * T uno con quantita pari a quanti elementi sono rimasti in magazzino, 
		 * F uno con quantitá 0 
		 * T un altro normale
		 * F uno con quantitá negativa 
		 * 
		 * Gli acquisti non seguono l'ordine dell'inventario
		 * */
		
		String[] acquistiKiriko = {"VG1;505", "WIN10;1", "VG2;4", "AM1;56", "AM2;7", "PL1;0", "VG3;2", "AP1;-10002"};
		boolean[] outputAcquistiKRK = e.acquisto("KRK", acquistiKiriko);
		
		sop("[");
		for (boolean outputAcquisto : outputAcquistiKRK) {
			sop(outputAcquisto + "; ");
		}
		sopln("]");
		sopln();
		
		sopln("Inventario dopo gli acquisti:");
		printInventory(e);
		sopln();
		
		sopln("Disponibilitá dell'utente dopo gli acquisti: "+e.cercaUtente("KRK").getDisponibilita());
		sopln();
		
		sopln("Effetto altri acquisti, che superino, in somma (dove alcuni sarebbero possibili se fatti da soli) la disponibiitá dell'utente con Winston");
		String[] acquistiWinston = {"VG1;1", "VG2;1", "KEY2EVEN;1"};
		boolean[] outputAcquistiWNSTN = e.acquisto("WNSTN", acquistiWinston);
		
		sop("[");
		for (boolean outputAcquisto : outputAcquistiWNSTN) {
			sop(outputAcquisto + "; ");
		}
		sopln("]");
		sopln();
		
		sopln("Inventario dopo gli acquisti:");
		printInventory(e);
		sopln();
		
		// Caso in cui si passi un formato sbagliato in acquisti
		sopln("Effetto altri acquisti scritti male con Tracer");
		String[] acquistiTracer = {" VaGina1 ; 1 ", "VG2;1", "VG3,1"};
		boolean[] outputAcquistiTRCR = e.acquisto("TRCR", acquistiTracer);
		
		sop("[");
		for (boolean outputAcquisto : outputAcquistiTRCR) {
			sop(outputAcquisto + "; ");
		}
		sopln("]");
		sopln();
		
		sopln("Inventario dopo gli acquisti:");
		printInventory(e);
		sopln();
		
		sopln("Ecco gli acquisti di Kiriko");
		sopln("i" + e.acquistiUtente("KRK") + "f");
		sopln();
		
		sopln("Ecco gli acquisti di Winston");
		sopln("i" + e.acquistiUtente("WNSTN") + "f");
		sopln();
		
		sopln("Ecco gli acquisti di Soldato 76");
		sopln("i" + e.acquistiUtente("SLDR76") + "f");
		sopln();
		
		sopln("Individuo l'ultimo acquisto di un utente che ha fatto acquisti");
		sopln(e.ultimoAcquisto("KRK"));
		sopln("Individuo l'ultimo acquisto di un utente che ha provato ma non ha fatto acquisti");
		sopln(e.ultimoAcquisto("WNSTN"));
		sopln("Individuo l'ultimo acquisto di un utente che non ha ne provato ne fatto acquisti");
		sopln(e.ultimoAcquisto("SLDR76"));
		sopln("Individuo l'ultimo acquisto di un utente che non esiste");
		sopln(e.ultimoAcquisto("GESU"));
		sopln();
		
		sopln("Tutti gli utenti che hanno acquistato VG2");
		sopln("i" + e.utentiProdotto("VG2") + "f");
		sopln();
		
		sopln("Tutti gli utenti che hanno acquistato KEY2EVEN");
		sopln("i" + e.utentiProdotto("KEY2EVEN") + "f");
		sopln();
		
		sopln("Tutti gli utenti che hanno acquistato BICHO");
		sopln("i" + e.utentiProdotto("BICHO") + "f");
		sopln();

	}
	
	public static void testPart3(Ecommerce e) {

		e.nuovoProdotto("VG2", "", 0, 10);
		String[] newPurch = {"VG2;10"}; // Spesa di 420euro, Rimangono 38.07 euro
		e.acquisto("KRK", newPurch);
		sopln(e.acquistiUtente("KRK"));
		sopln();
		
		sopln(e.cercaUtente("KRK").getDisponibilita());
		sopln();
		
		/*
		 * Ecco gli acquisti di Kiriko 
		 * 1;KRK;VG2;4;168.0
		 * 2;KRK;AM2;7;83.93
		 * 3;KRK;VG3;2;40.0
		 * 5;KRK;VG2;10;420.0
		 * 
		 * disp finale (38.07 euro)
		 * */
		
		sopln("Genero una consegna per Tracer");
		//sopln(e.acquistiUtente("TRCR"));
		e.nuovaConsegna(4, "New Eaven Street", "11/9/01");
		
		sopln("Genero delle consegne con Kiriko\n");
		
		sopln("Genero delle consegne standard");
		sopln(e.nuovaConsegna(1, "Kanezaka no ie"));
		sopln(e.cercaUtente("KRK").getDisponibilita());
		sopln();
		
		sopln("Genero delle consegne prime, in alcune l'utente puó permettersele, in altre no");
		sopln(e.nuovaConsegna(3, "Kanezaka no ie", "02/10/23")); //Spesa di 4 euro, rimangono 34.07
		sopln(e.cercaUtente("KRK").getDisponibilita());
		sopln();
		
		sopln(e.nuovaConsegna(5, "Kanezaka no ie", "03/11/24")); //Spesa eccessiva, fail
		sopln(e.cercaUtente("KRK").getDisponibilita());
		sopln();
		
		sopln("Provo a generare consegne che giá esistono");
		sopln(e.nuovaConsegna(1, "Kanezaka no ie", "03/11/24"));
		sopln(e.nuovaConsegna(3, "Kanezaka no ie", "03/11/24"));
		sopln(e.nuovaConsegna(3, "Kanezaka no ie"));
		sopln(e.nuovaConsegna(1, "Kanezaka no ie"));
		sopln(e.cercaUtente("KRK").getDisponibilita());
		sopln();
		
		sopln("Provo a generare consegne su acquisti che non esistono");
		sopln(e.nuovaConsegna(998, "Kanezaka no ie"));
		sopln(e.nuovaConsegna(999, "Kanezaka no ie", "03/11/24"));
		sopln(e.nuovaConsegna(999, "Kanezaka no ie"));
		sopln(e.cercaUtente("KRK").getDisponibilita());
		sopln();
		
		
		
		sopln("Provo a fare un descrivi consegna per una consegna standard");
		sopln(e.descriviConsegna("S1"));
		sopln("Provo a fare un descrivi consegna per una consegna prime");
		sopln(e.descriviConsegna("P3"));
		sopln();
		
		sopln("Provo a fare un descrivi consegna per una consegna standard che non esiste");
		sopln(e.descriviConsegna("S99"));
		sopln("Provo a fare un descrivi consegna per una consegna standard che non esiste ma dove esiste il numero");
		sopln(e.descriviConsegna("S3"));
		sopln("Provo a fare un descrivi consegna per una consegna prime che non esiste");
		sopln(e.descriviConsegna("P99"));
		sopln("Provo a fare un descrivi consegna per una consegna prime che non esiste ma dove esiste il numero");
		sopln(e.descriviConsegna("P1"));
		
		sopln("Tutte le consegne finora richieste");
		sopln(e.consegne());
		sopln();
		
		sopln("Tutte le consegne di Kiriko");
		sopln(e.consegne("KRK"));
		sopln("Tutte le consegne di Kiriko di tipo S");
		sopln(e.consegne("KRK", "S"));
		sopln("Tutte le consegne di Kiriko di tipo P");
		sopln(e.consegne("KRK", "P"));
		sopln("Tutte le consegne di Kiriko di un tipo che non esiste");
		sopln(e.consegne("KRK", "C"));
		sopln("Tutte le consegne di Kiriko di tipo" + Consegna.DEFAULT_TYPE);
		sopln(e.consegne("KRK", Consegna.DEFAULT_TYPE));
		sopln();
		
		sopln("Tutte le consegne di Winston");
		sopln(e.consegne("WNSTN"));
		sopln("Tutte le consegne di Winston di tipo S");
		sopln(e.consegne("WNSTN", "S"));
		sopln("Tutte le consegne di Winston di tipo P");
		sopln(e.consegne("WNSTN", "P"));
		sopln("Tutte le consegne di Winston di un tipo che non esiste");
		sopln(e.consegne("WNSTN", "C"));
		sopln("Tutte le consegne di Winston di tipo" + Consegna.DEFAULT_TYPE);
		sopln(e.consegne("WNSTN", Consegna.DEFAULT_TYPE));
		sopln();
		
		sopln("Tutte le consegne di Tracer");
		sopln(e.consegne("TRCR"));
		sopln("Tutte le consegne di Tracer di tipo S");
		sopln(e.consegne("TRCR", "S"));
		sopln("Tutte le consegne di Tracer di tipo P");
		sopln(e.consegne("TRCR", "P"));
		sopln("Tutte le consegne di Tracer di un tipo che non esiste");
		sopln(e.consegne("TRCR", "C"));
		sopln("Tutte le consegne di Tracer di tipo" + Consegna.DEFAULT_TYPE);
		sopln(e.consegne("TRCR", Consegna.DEFAULT_TYPE));
		sopln();
		
		sopln("Tutte le consegne di un utente che non esiste");
		sopln(e.consegne("JESUS"));
		sopln("Tutte le consegne di un utente che non esiste di tipo S");
		sopln(e.consegne("JESUS", "S"));
		sopln("Tutte le consegne di un utente che non esiste di tipo P");
		sopln(e.consegne("JESUS", "P"));
		sopln("Tutte le consegne di un utente che non esiste di un tipo che non esiste");
		sopln(e.consegne("JESUS", "C"));
		sopln("Tutte le consegne di un utente che non esiste di tipo" + Consegna.DEFAULT_TYPE);
		sopln(e.consegne("JESUS", Consegna.DEFAULT_TYPE));
		sopln();
		
	}
	
	public static void printInventory(Ecommerce e) {
		Prodotto[] allProducts = e.cercaProdotti("");
		for (Prodotto prodotto : allProducts) {
			sopln(prodotto);
		}
	}
	
	public static void printUsers(Ecommerce e) {
		for (Utente utente : e.getUtenti()) {
			if (utente != null)
				sopln(utente);
			else
				sop(utente+ ", ");
		}
		sopln();
	}

	public static void sepln(Object object) {
		System.err.println("❌ ERROR: "+ object);
	}
		
	public static void sopln(Object object) {
		System.out.println(object);
	}
	
	public static void sop(Object object) {
		System.out.print(object);
	}
	
	public static void sopln() {
		sopln("");
	}
	
	public static void sopln(Consegna[] consegne) {
		for (Consegna consegna : consegne) {
			sopln(consegna);
		}
	}
	
}
