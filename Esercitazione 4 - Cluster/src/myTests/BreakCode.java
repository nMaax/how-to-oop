package myTests;

import cluster.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class BreakCode {
	
	public static void main(String[] args) {
		sopln("Creazione Cluster\n");
		Cluster c = new Cluster(5, 10);
		
		test1(c);
		test2(c);
		test3(c);
		
		/*
		Cluster d = new Cluster(5, 2);
		sopln(d.eseguiProcessi("20221225 10:10:11"));
		Utente u1 = d.registraUtente("max@max.max", "max", "max", "MaxMin0102", "19991225");
		Utente u2 = d.registraUtente("max@max.max", "max", "max", "howInGodsName", "19991225");
		Utente u3 = d.registraUtente("max@max.z", "maz", "maz", "Mazda01Mazda", "00000101 00:00:00");
		sopln(u1);
		sopln(u2);
		sopln();
		d.nuovoProcesso(u2.getEmail(), "MaxMin0102", "My 1st process", "20221225 10:10:10");
		d.nuovoProcesso(u2.getEmail(), "MaxMin0102", "My 1st process", "20221225 10:10:10");
		d.nuovoProcesso(u2.getEmail(), "MaxMin0102", "My 1st process", "20221225 10:10:10");
		d.nuovoProcesso(u2.getEmail(), "MaxMin0102", "My 1st process", "20221225 10:10:10");
		d.nuovoProcesso(u2.getEmail(), "MaxMin0102", "My 1st process", "20221225 10:10:10");
		d.nuovoProcesso(u2.getEmail(), "MaxMin0102", "My 1st process", "20221225 10:10:10");
		d.nuovoProcesso(u2.getEmail(), "MaxMin0102", "My 1st process", "20221225 10:10:10");
		d.nuovoProcesso(u2.getEmail(), "MaxMin0102", "My 1st process", "20221225 10:10:10");
		
		d.nuovoProcesso(u3.getEmail(), "Mazda01Mazda", "My 2st process", "20221225 10:10:10");
		d.nuovoProcesso(u3.getEmail(), "Mazda01Mazda", "My 2st process", "20221225 10:10:10");
		d.nuovoProcesso(u3.getEmail(), "Mazda01Mazda", "My 2st process", "20221225 10:10:10");
		d.nuovoProcesso(u3.getEmail(), "Mazda01Mazda", "My 2st process", "20221225 10:10:10");
		d.nuovoProcesso(u3.getEmail(), "Mazda01Mazda", "My 2st process", "20221225 10:10:10");
		d.nuovoProcesso(u3.getEmail(), "Mazda01Mazda", "My 2st process", "20221225 10:10:10");
		d.nuovoProcesso(u3.getEmail(), "Mazda01Mazda", "My 2st process", "20221225 10:10:10");
		
		d.nuovoProcesso(u3.getEmail(), "MaxMin0102", "My 1st process", "20221225 10:10:10");
		sopln(d.eseguiProcessi("20221225 10:10:11"));
		sopln(d.eseguiProcessi("20221225 10:10:11"));
		sopln(d.eseguiProcessi("20221225 10:10:11"));
		int[] processi = {2, 2, 1};
		d.completaProcessi(processi, "20221225 10:10:12");
		d.completaProcessi(processi, "20221225 10:10:12");
		sopln(d.elencoUtentiPerEmail());
		sopln(d.elencoProcessiPending());
		sopln(d.elencoProcessiCompletati());
		sopln(d.elencoProcessiPerTimestamp());
		*/
	}
	
	public static void test1(Cluster c) {
		
		/* TEST PARTE 1 */
		
		makeUsers(c);
		describeUsers(c);
		searchUsers(c);
		
	}
	
	public static void test2(Cluster c) {
		
		/* TEST PARTE 2 */
		
		makeProcesses(c);
		executeProcesses(c);
		completeProcesess(c);
		describeProcesses(c);
		searchProcesses(c);
		
	}
	
	public static void test3(Cluster c) {
		
		/* TEST PARTE 3 */
		
		System.out.println("Elenco utenti per email");
		for (Utente u : c.elencoUtentiPerEmail()) {
			System.out.println(c.descriviUtente(u.getEmail()));
		}
		sopln();
		
		System.out.println("Elenco processi per timestamp");
		for (Processo p : c.elencoProcessiPerTimestamp()) {
			System.out.println(c.descriviProcesso(p.getCodiceProcesso()));
		}
		sopln();
		
		sopln("Aggiungo un processo di un admin per testare...");
		sopln(c.nuovoProcesso("elonmusk@twitter.com", "toTheMoon3", "sudo echo 'you are fired' --push", "20220609 04:20:00"));
		sopln();
		System.out.println("Elenco processi Pending");
		for (Processo p : c.elencoProcessiPending()) {
			System.out.println(c.descriviProcesso(p.getCodiceProcesso()));
		}
		sopln();
		
		sopln("Eseguo i processi in coda per testare...");
		int[] processi = {8, 5, 7};
		sopln(c.completaProcessi(processi, "20230101 01:01:01"));
		sopln();
		System.out.println("Elenco processi Completati");
		for (Processo p : c.elencoProcessiCompletati()) {
			System.out.println(c.descriviProcesso(p.getCodiceProcesso()));
		}
		sopln();
		
	}
	
	/*
	 * fabio.garcea@polito.it, Fabio, Garcea, Password1, 19930917, ssh-rsaAAABBBCCC, RASPRIVATE---MIIBOgIBAAJBAKj34---ENDRSA
	 * grandiraggazzi@forzajjuve.it, Cristiano, Ronaldo, jesusChrist3, 20000101, SHA-1-PUBKEY, SHA-1-PRIV_KEY
	 * fabrizio.lamberti@polito.it, Fabrizio, Lamberti, passWord2, 19800101
	 * lia.morra@polito.it, Lia, Morra, 3passworD, 19850101
	 * guido.marchetto@polito.it, Guido, Marchetto, pass4woRd, 19850202
	 * winston.churchill@gov.uk, Winston, Churchill, nukeBerlinN0W, 18901225
	 * elonmusk@twitter.com, Elon, Musk, toTheMoon3, 19850101, SHA101-PUB, SHA101-PRIV
	 */
	public static void makeUsers(Cluster c) {
		sopln(" * * * " + "Creazione utenti".toUpperCase() + " * * *\n");
		//Creo un primo admin e testo di getter
		UtenteAdmin u1 = c.registraUtente("fabio.garcea@polito.it", "Fabio", "Garcea", "Password1", "19930917", "ssh-rsaAAABBBCCC", "RASPRIVATE---MIIBOgIBAAJBAKj34---ENDRSA");
		sopln("Creato utente admin: " + u1.getEmail());
		sopln(u1.getNome());
		sopln(u1.getCognome());
		sopln(u1.getPassword());
		sopln(u1.getDataNascita());
		sopln(u1.getChiavePubblica());
		sopln(u1.getChiavePrivata());
		sopln();
		
		// Creo un secondo admin
		c.registraUtente("grandiraggazzi@forzajjuve.it", "Cristiano", "Ronaldo", "jesusChrist3", "20000101", "SHA-1-PUBKEY", "SHA-1-PRIV_KEY");
		
		// Creo utenti standard
		sopln("Creazione altri utenti (standard)...");
		c.registraUtente("fabrizio.lamberti@polito.it", "Fabrizio", "Lamberti", "passWord2", "19800101");
		c.registraUtente("lia.morra@polito.it", "Lia", "Morra", "3passworD", "19850101");
		c.registraUtente("guido.marchetto@polito.it", "Guido", "Marchetto", "pass4woRd", "19850202");
		
		// Genero un utente e provo a rifarlo ma con un tipo diverso
		c.registraUtente("winston.churchill@gov.uk", "Winston", "Churchill", "nukeBerlinN0W", "18901225");
		c.registraUtente("winston.churchill@gov.uk", "Winston", "Churchill", "nukeBerlinN0W", "18901225", "ThisKeyWillBeIgnored", "ThisOneToo");
		c.registraUtente("elonmusk@twitter.com", "Elon", "Musk", "toTheMoon3", "19850101", "SHA101-PUB", "SHA101-PRIV");
		c.registraUtente("elonmusk@twitter.com", "Elon", "Muschio", "toTheMoon3", "19850101");
		
		//Genero degli utenti con formati di email, password o dataNascita incompatibili
		c.registraUtente("passwordErrta@test.it", "Man", "Ahah Women", "jesusChrist", "19850101");
		c.registraUtente("emailErrata", "Man", "Ahah Women", "jesusChrist3", "19850101");
		c.registraUtente("eta@minorenne", "Man", "Ahah Women ", "jesusChrist3", "20220101");
		sopln();
	}
	
	public static void describeUsers(Cluster c) {
		sopln("* * * " + "Descrizione utenti".toUpperCase() + " * * *\n");
		sopln("Descrizione utenti senza credenziali\n");
		sopln("Descrizione utente (admin): ");
		String descrizioneUtente1 = c.descriviUtente("fabio.garcea@polito.it");
		sopln(descrizioneUtente1);
		
		sopln("Descrizione utente (standard):");
		String descrizioneUtente2 = c.descriviUtente("fabrizio.lamberti@polito.it");
		sopln(descrizioneUtente2);
		sopln();
		
		Utente u1 = c.cercaUtente("fabio.garcea@polito.it");
		Utente u2 = c.cercaUtente("fabrizio.lamberti@polito.it");
		
		String emailAdmin = u1.getEmail();
		String pswAdmin = u1.getPassword();
		
		String emailStd = u2.getEmail();
		String pswStd = u2.getPassword();
		
		sopln("Descrizione utente con credenziali\n");
		sopln("Descrizione utente admin con credenziali admin (admin)");
		String descrizioneUtente3 = c.descriviUtente(emailAdmin, pswAdmin, emailAdmin);
		sopln(descrizioneUtente3);
		sopln();
		
		sopln("Descrizione utente standard con credenziali admin (admin)");
		String descrizioneUtente4 = c.descriviUtente(emailAdmin, pswAdmin, emailStd);
		sopln(descrizioneUtente4);
		sopln();
		
		sopln("Descrizione utente standard con credenziali standrd (stesso user)...");
		String descrizioneUtente5 = c.descriviUtente(emailStd, pswStd, emailStd);
		sopln(descrizioneUtente5);
		sopln();
		
		sopln("Descrizione utente standard con credenziali standrd (user diverso)...");
		String descrizioneUtente6 = c.descriviUtente(emailStd, pswStd, "lia.morra@polito.it");
		sopln(descrizioneUtente6);
		sopln();
		
		sopln("Descrizione utente admin con credenziali standrd ...");
		String descrizioneUtente7 = c.descriviUtente(emailStd, pswStd, emailAdmin);
		sopln(descrizioneUtente7);
		sopln();
	}
	
	public static void searchUsers(Cluster c) {
		
		sopln("* * * " + "Ricerca Utenti".toUpperCase() + " * * *\n");
		
		sopln("Ricerca utenti creati finora ...");
		Utente[] utenti = c.cercaUtenti();
		for (Utente u : utenti) {
			sop(" - ");
			sopln(c.descriviUtente(u.getEmail()));
		}
		sopln();
		
		sopln("Ricerca utenti nati tra il 1 Febbraio 1985 (19850201) e il 1 Gennaio 2022 (20220101)...");
		Utente[] utentiInDate = c.cercaUtenti("19850201","20220101");
		for (Utente u : utentiInDate) {
			sop(" - ");
			sopln(c.descriviUtente(u.getEmail()));
		}
		sopln();
		
		sopln("Ricerca utenti nati il 1 Gennaio 1985(19850101)...");
		Utente[] utentiInData = c.cercaUtenti("19850101","19850101");
		for (Utente u : utentiInData) {
			sop(" - ");
			sopln(c.descriviUtente(u.getEmail()));
		}
		sopln();
		
		sopln("Ricerca utenti nati tra il 1 Febbraio 1985 (19850201) e il 1 Gennaio 2022 (20220101) - usando le credenziali di Elon Musk (Admin)...");
		Utente[] utentiInDateWithPsw = c.cercaUtenti("19850201","20220101");
		Utente adminAux = c.cercaUtente("elonmusk@twitter.com");
		String emailAdmin = adminAux.getEmail();
		String pswAdmin = adminAux.getPassword();
		for (Utente u : utentiInDateWithPsw) {
			sop(" - ");
			sopln(c.descriviUtente(emailAdmin, pswAdmin, u.getEmail()));
		}
		sopln();
		
		sopln("Ricerca utenti nati tra il 1 Febbraio 1985 (19850201) e il 1 Gennaio 2022 (20220101) - usando le credenziali di Winston Churchill (Standard)...");
		Utente[] utentiInDateWithStdPsw = c.cercaUtenti("19850201","20220101");
		Utente stdAux = c.cercaUtente("winston.churchill@gov.uk");
		String emailStd = stdAux.getEmail();
		String pswStd = stdAux.getPassword();
		for (Utente u : utentiInDateWithStdPsw) {
			sop(" - ");
			sopln(c.descriviUtente(emailStd, pswStd, u.getEmail()));
		}
		sopln();
		
	}
	
	/*
	 *  1, fabio.garcea@polito.it, python train_ai.py --verbose, PENDING, 20221118 16:45:00
	 *  2, lia.morra@polito.it, python compute_statistics.py --verbose, PENDING, 20221118 17:00:00
	 *  3, lia.morra@polito.it, ruby validate_diamoond.rb --verbose, PENDING, 20221118 18:20:00
	 *  4, fabio.garcea@polito.it, python deploy_graph.py --now, PENDING, 20221118 19:45:00
	 *  5, grandiraggazzi@forzajjuve.it, javascript start_animation.js --verbose, PENDING, 20221118 20:00:00
	 *  6, grandiraggazzi@forzajjuve.it, sudo git pull_request --README.md , PENDING, 20221118 21:20:00
	 *  7, elonmusk@twitter.com, sudo echo 'Commedy is legal on twitter now' --push, PENDING, 20220609 04:20:00
	 *  8, elonmusk@twitter.com, sudo echo 'Commedy is legal on twitter now' --push, PENDING, 20220609 04:20:00
	 *  9, elonmusk@twitter.com, sudo echo 'Commedy is legal on twitter now' --push, PENDING, 20220609 04:20:00
	 * 10, elonmusk@twitter.com, sudo echo 'Commedy is legal on twitter now' --push, PENDING, 20220609 04:20:00
	 * 11, elonmusk@twitter.com, sudo echo 'Commedy is legal on twitter now' --push, PENDING, 20220609 04:20:00
	 * 12, lia.morra@polito.it, golang loopingProcess --kill, PENDING, 20221118 18:20:00
	 * 13, lia.morra@polito.it, golang loopingProcess --kill, PENDING, 20221118 18:20:00
	 * 14, lia.morra@polito.it, golang loopingProcess --kill, PENDING, 20221118 18:20:00
	 * 15, winston.churchill@gov.uk, bytecode 010001001 -bomb, PENDING, 19451010 12:12:12
	 * 16, winston.churchill@gov.uk, bytecode 010001001 -bomb, PENDING, 19451010 12:12:13
	 * 17, winston.churchill@gov.uk, bytecode 010001001 -bomb, PENDING, 19451010 12:12:14
	 * 18, winston.churchill@gov.uk, bytecode 010001001 -bomb, PENDING, 19451010 12:12:15
	 * */
	public static void makeProcesses(Cluster c) {
		
		sopln("* * * " + "Creazione nuovi processi".toUpperCase() +  " * * *\n");
		
		soplnProcesses(c);
		
		sopln("Creo nuovi processi");
		sopln(c.nuovoProcesso("fabio.garcea@polito.it", "Password1", "python train_ai.py --verbose", "20221118 16:45:00"));
		sopln(c.nuovoProcesso("lia.morra@polito.it", "3passworD", "python compute_statistics.py --verbose", "20221118 17:00:00"));
		sopln(c.nuovoProcesso("lia.morra@polito.it", "3passworD", "ruby validate_diamoond.rb --verbose", "20221118 18:20:00"));
		sopln(c.nuovoProcesso("fabio.garcea@polito.it", "Password1", "python deploy_graph.py --now", "20221118 19:45:00"));
		sopln(c.nuovoProcesso("grandiraggazzi@forzajjuve.it", "jesusChrist3", "javascript start_animation.js --verbose", "20221118 20:00:00"));
		sopln(c.nuovoProcesso("grandiraggazzi@forzajjuve.it", "jesusChrist3", "sudo git pull_request --README.md ", "20221118 21:20:00"));
		sopln();
		
		sopln("Creo processi con email non valide");
		sopln(c.nuovoProcesso("lorra.mia@polito.it", "3passworD", "ruby validate_diamoond.rb --verbose", "20221118 18:20:00"));
		sopln(c.nuovoProcesso("emailerrata@forzajjuve.it", "jesusChrist3", "sudo git pull_request --README.md ", "20221118 21:20:00"));
		sopln();
		
		sopln("Creo processi con password non valide");
		sopln(c.nuovoProcesso("lia.morra@polito.it", "passwordSbagliata", "ruby validate_diamoond.rb --verbose", "20221118 18:20:00"));
		sopln(c.nuovoProcesso("grandiraggazzi@forzajjuve.it", "jesusChrist4", "sudo git pull_request --README.md ", "20221118 21:20:00"));
		sopln();
		
		sopln("Cerco di creare piú processi ATTIVI (Pending + Esecuzione) rispetto la soglia limite di ogni utente");
		for (int i=0; i < 10; i++) {
			sopln(c.nuovoProcesso("elonmusk@twitter.com", "toTheMoon3", "sudo echo 'Commedy is legal on twitter now' --push", "20220609 04:20:00"));
		}
		sopln();
		
		sopln("Creo altri processi che ci serviranno dopo");
		for (int i=0; i < 10; i++) {
			sopln(c.nuovoProcesso("lia.morra@polito.it", "3passworD", "golang loopingProcess --kill", "20221118 18:20:00"));
		}
		
		sopln(c.nuovoProcesso("winston.churchill@gov.uk", "nukeBerlinN0W", "bytecode 010001001 -bomb", "19451010 12:12:12"));		
		sopln(c.nuovoProcesso("winston.churchill@gov.uk", "nukeBerlinN0W", "bytecode 010001001 -bomb", "19451010 12:12:13"));		
		sopln(c.nuovoProcesso("winston.churchill@gov.uk", "nukeBerlinN0W", "bytecode 010001001 -bomb", "19451010 12:12:14"));		
		sopln(c.nuovoProcesso("winston.churchill@gov.uk", "nukeBerlinN0W", "bytecode 010001001 -bomb", "19451010 12:12:15"));		
		sopln();
		
		soplnProcessesMinimal(c);
		
	}
	
	public static void executeProcesses(Cluster c) {
		
		sopln("* * * " + "Esecuzione processi".toUpperCase() +  " * * *\n");
		
		sopln("Eseguo i processi in pending");
		sopln(c.eseguiProcessi("20221123 17:53:00"));
		sopln();
		
		soplnProcessesMinimal(c);
		sopln();
		
		
	}

	public static void completeProcesess(Cluster c) {
		
		sopln("* * * " + "Esecuzione processi".toUpperCase() +  " * * *\n");
		
		sopln("Completo i processi in coda");
		int[] processi = {11, 3, 10, 9, 19};
		boolean[] check = c.completaProcessi(processi, "20220505 18:52:59");
		for (boolean bool : check) {
			sop(bool + ", ");
		}
		sop("\n");
		sopln();
		
		soplnProcessesMinimal(c);
		sopln();
		
	}
	
	public static void describeProcesses(Cluster c) {
		
		sopln("* * * " + "Descrizione dei processi".toUpperCase() +  " * * *\n");
		
		sopln("Descrivo processi che esistono");
		sopln(c.descriviProcesso(1));
		sopln(c.descriviProcesso(11));
		sopln();
		
		sopln("Descrivo processi che non esistono");
		sopln(c.descriviProcesso(0));
		sopln(c.descriviProcesso(100));
		sopln(c.descriviProcesso(2190));
		sopln();
	}
	
	public static void searchProcesses(Cluster c) {
		sopln("* * * " + "Ricerca processi".toUpperCase() +  " * * *\n");
		
		sopln("Cerco un processo esistente");
		sopln(c.cercaProcesso(18));
		sopln();
		
		sopln("Cerco un processo in-esistente");
		sopln(c.cercaProcesso(20));
		sopln();
		
		sopln("Cerco tutti i processi ");
		Processo[] processi = c.cercaProcessi();
		for (Processo processo : processi) {
			sopln(processo);
		}
		sopln();
		
	}
	
	public static void sopln() {
		sopln("");
	}
	
	public static void sopln(Object o) {
		System.out.println(o);
	}
	
	public static void sop() {
		sop("");
	}
	
	public static void sop(Object o) {
		System.out.print(o);
	}
	
	public static void sopln(ArrayList<Object> list) {
		Object[] arr = list.toArray(new Object[list.size()]);
		sopln(arr);
	}
	
	public static void sopln(LinkedList<Object> list) {
		Object[] arr = list.toArray(new Object[list.size()]);
		sopln(arr);
	}
	
	public static void sopln(Object[] arr) {
		sop("[");
		for (Object o : arr) {
			sop(" " + o + ";");
		}
		sopln("]");
	}
	
	public static void soplnProcesses(Cluster c) {
		
		sopln(" -- Stato delle liste e code di processi -- \n");
		
		sopln("Processi in PENDING");
		sopln(c.getPending());
		sopln();
		
		sopln("Processi in ESECUZIONE");
		sopln(c.getCoda());
		sopln();
		
		sopln("Processi in COMPLETATO");
		sopln(c.getComplete());
		sopln();
		
		sopln("Processi in totale");
		sopln(c.getProcessi());
		sopln();
	}
	
	public static void soplnProcessesMinimal(Cluster c) {
		
		sopln(" -- Stato delle liste e code di processi -- \n");
		
		sopln("Processi in PENDING");
		LinkedList<Processo> pending = c.getPending();
		
		sop("[");
		for (Processo processo : pending) {
			sop(" " + processo.getCodiceProcesso() + ";");
		}
		sopln("]");
		sopln();
		
		sopln("Processi in ESECUZIONE");
		Processo[] exe = c.getCoda();
		
		sop("[");
		for (Processo processo : exe) {
			if (processo != null)
				sop(" " + processo.getCodiceProcesso() + ";");
			else 
				sop(" " + processo + ";");
		}
		sopln("]");
		sopln();
		
		sopln("Processi in COMPLETATO");
		LinkedList<Processo> done = c.getComplete();
		
		sop("[");
		for (Processo processo : done) {
			sop(" " + processo.getCodiceProcesso() + ";");
		}
		sopln("]");
		sopln();
		
		sopln("Processi in totale");
		LinkedList<Processo> processi = c.getProcessi();
		
		sop("[");
		for (Processo processo : processi) {
			sop(" " + processo.getCodiceProcesso() + ";");
		}
		sopln("]");
		sopln();
	}
	
}