import java.util.ArrayList;
import ecommerce.*;

@SuppressWarnings("unused")
public class myMain {

	public static void main(String[] args) {
		
		/*
		 
		// Testing delle ArrayList 
		 
		ArrayList<Prodotto> lista = new ArrayList<Prodotto>();
		
		lista.add(new Prodotto(1));
		lista.add(new Prodotto(2));
		sopln("\nLunghezza della lista: "+lista.size());
		for(Prodotto num : lista) {
			sopln(num);
		}
		
		lista.add(new Prodotto(3));
		sopln("\nLunghezza della lista: "+lista.size());
		for(Prodotto num : lista) {
			sopln(num);
		}
		*/
		
		/*
		 
		 // Testing delle modifiche retroattive
		 
 		ArrayList<String> lista = new ArrayList<String>();
		lista.add("La vita è bella");
		sopln(lista);
		retroactiveChange(lista);
		sopln(lista);
		 
		 * */
		
		/*
		//Testing substring e indexOf nelle stringhe 
		
		String acq1 = "CRLMSM;11";
		
		int indexSemicol = acq1.indexOf(';');
		
		String cf = acq1.substring(0, indexSemicol);
		String qnt = acq1.substring(indexSemicol + 1);
				
		sopln(cf);
		sopln(qnt);
		*/
		
		/*
		
		//Testing equals&co. 
		
		Utente utente1 = new Utente();
		Utente utente2 = new Utente();
		Utente utente3 = utente1;
		
		sopln(utente1);
		sopln(utente2);
		sopln(utente3);
		sopln(utente1.equals(utente2));
		
		*/
		
		/*
		
		// Testing ecommerce.Acquisto
		
		ArrayList<Prodotto> prodotti = new ArrayList<Prodotto>();
		prodotti.add(new Prodotto("cod1", "desc1", 3.5, 1));
		prodotti.add(new Prodotto(2));
		prodotti.add(new Prodotto(3));
		sopln(prodotti);
		
		
		Acquisto aq1 = new Acquisto(prodotti.get(0), 10);
		sopln(aq1);
		
		Acquisto aq2 = new Acquisto();
		sopln(aq2);
		
		Acquisto aq3 = new Acquisto("ciao;89", prodotti, true);
		sopln(aq3);
		
		sopln("\n"+prodotti);
		sopln(prodotti.get(3));
		
		*/
		
		/*
		
		String str1 = "DALLAS;89;FUEL;64;CONTRO;10;SAN;68;FRANCISCO;00;SHOCK;45";
		String[] result = otherParseString(str1, ';');
		
		for (String component : result)
			sopln(component);
		
		ArrayList<Integer> lista = new ArrayList<Integer>();
		lista.add(100);
		lista.add(101);
		lista.add(102);

		sopln(lista.size());
		Acquisto acq = null;
		sopln(acq.toString());
		
		*/
		
		//ArrayList<Consegna> consegne = new ArrayList<Consegna>();
		/*
		Consegna[] consegne = new Consegna[5];
		
		Standard conStandard = new Standard();
		Prime conPrime = new Prime();
		Consegna con = new Consegna();
		
		consegne[0] = conPrime;
		consegne[1] = conStandard;
		consegne[2] = con;
		consegne[3] = null;
		
		for (Consegna consegna : consegne) {
			sopln(consegna);
		}
		
		sopln(conPrime.getClass().getName());
		sopln(conStandard.getClass().getName());
		sopln(con.getClass().getName());
		
		Consegna testCons = conStandard;		
		sopln(testCons);
		
		*/
		
		/*
		Acquisto acq1 = new Acquisto();
		acq1.setSpesaAcquisto(40);
		sopln(acq1.getSpesaAcquisto());
		
		Prime cons1 = new Prime(0, null, acq1, "DataAcaso");
		sopln(cons1.getCostoOrdine());
		
		acq1.setSpesaAcquisto(100);
		sopln(acq1.getSpesaAcquisto());
		sopln(cons1.getCostoTotale());
		*/
		
		/*
		Standard cStand = new Standard();
		Prime cPrime = new Prime();
		
		sopln(cStand);
		sopln(cPrime);
		
		ArrayList<Integer> lista = new ArrayList<Integer>();
		
		sopln(lista.size());
		
		lista.add(420);
		sopln(lista.get(0));
		sopln(lista.size());
		
		sopln(Prime.DELIVER_TYPE);
		sopln(Standard.DELIVER_TYPE);
		
		*/
		
		/*
		int a = -5;
		int b = Ecommerce.abs(a);
		sopln(a);
		sopln(b);
		*/
		
		/*
		String original = "Ciao Mondo";
		String test = Ecommerce.removeLastChar(original);
		sopln(original);
		sopln(test);
		*/
		
		double a = -1;
		int b = -6;
		
		sopln(a + " " + Ecommerce.denegativizeDouble(a));
		sopln(b + " " + Ecommerce.denegativizeInteger(b));
		
		
	}
	
	public static void sopln(Object object) {
		System.out.println(object);
	}
	
	public static void sop(Object object) {
		System.out.print(object);
	}
	
	public static void retroactiveChange(ArrayList<String> listaStr) {
		listaStr.add("Ha funzionato");
	}
	
	public static String[] otherParseString(String str, char divider) {
			
			/*
			 * Finché non esiste piú un secondo carattere divisore nella stringa da dividere:
			 * 		Uso secondIndexOf() per individuare l'indice dove avviene la seconda occorenza del divider
			 * 		Genero la stringa che sta a sinistra del divider
			 * 		La aggiungo all'array
			 * 		Ripulisco la stringa restante togliendo la stringa di sinistra appena aggiunta all'array e il divider stesso
			 * */
		
			ArrayList<String> phrases = new ArrayList<String>(); 
			
			while (secondIndexOf(str, divider) != -1) {
				int dividingindex = secondIndexOf(str, divider);
				String sxSubString = str.substring(0, dividingindex);
				String dxSubString = str.substring(dividingindex).replaceFirst(";", "");
				phrases.add(sxSubString);
				str = dxSubString;
			}
			
			phrases.add(str);
			return phrases.toArray(new String[0]);
	}
	
	public static int secondIndexOf(String phrase, char ch) {
		
		/*
		 * Imposto l'indice di ritorno a -1
		 * Individuo la prima ricorrenza del carattere con indexOf(ch)
		 * Elimino questo carattere dalla stringa
		 * Ri-applico IndexOf(ch)
		 * Ho individuato la seconda ricorrenza --> restituisco il valore
		 * 
		 * Se non esiste nemmeno la prima ricorrenza 
		 * 		la prima invocazione diIndexOf(ch) restituirá -1 
		 * Se non esiste la seconda ricorrenza
		 * 		effettuo la prima invocazione di IndexOf(ch)
		 * 		alla seconda invocazione restituirá -1
		 * */
		
		int index = -1;
		// Se il carattere é presente almeno una volta
		
		int firstOcc = phrase.indexOf(ch);
		if (firstOcc != -1) {
			// Elimino la prima ricorrenza
			phrase = phrase.replaceFirst(Character.toString(ch), "");
		}
		
		int secondOcc = phrase.indexOf(ch);
		if (secondOcc != -1) {
			index = secondOcc + 1; // Incremento di 1 per compensare la mancanza del primo carattere
		}
		
		return index;
	}
	
}
