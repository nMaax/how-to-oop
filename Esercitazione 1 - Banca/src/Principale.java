import banca.Banca;

/**
 * Classe con un metodo main() di esempio
 */

public class Principale {

    /**
     * Esempio d'uso della classe Banca
     */
	
	public static void main(String[] args) {

		// PRIMA PARTE - Questa parte serve per familiarizzare con il metodo main() e con la sintassi del linguaggio

		System.out.println("*************************************************************************************");
		System.out.println("*                                   Prima parte                                     *");
		System.out.println("*        Far stampare qui sotto le informazioni sui conti registrati                *");
	    System.out.println("*                    interagendo solo con la classe Principale                      *");
		System.out.println("*************************************************************************************\n");

		
		// SECONDA PARTE - Iniziare a ragionare in termini di classi e oggetti
		
		System.out.println("");
		System.out.println("*************************************************************************************");
		System.out.println("*                                   Seconda parte                                   *");
		System.out.println("*   Modificando la classe Banca (ed eventualmente aggiungendo altre classi),        *");
		System.out.println("*    di seguito verranno mostrate le altre informazioni memorizzate dal sistema     *");
		System.out.println("*************************************************************************************\n");
		
		// Viene creato una nuova banca, e se ne impostano il nome e il tasso di ricavo
		
		Banca b = new Banca();
		b.setNome("Santander Consumer Bank Via Nizza 262 10126 Torino TO");
		b.setTasso(35.5);
		
		// Il nome e il tasso di ricavo della banca sono accessibil attraverso i metodi getNome() e getTasso()

		String nomeBanca = b.getNome();
		double tassoRicavo = b.getTasso();
		
		System.out.println("Creata banca "+ nomeBanca +", tasso di ricavo del "+ tassoRicavo + "%");
		
		// un nuovo conto viene gestito dal metodo nuovoConto()
		
		b.nuovoConto("Mario", "Rossi", 15050.5, 1.5);
		b.nuovoConto("Mario", "Bianchi", 5060.3, 1.0);
		b.nuovoConto("Anna", "Verdi", 31000.7, 2.0);
		
		// Per accedere alle informazioni relative all'ultimo conto si utilizza il metodo ultimoConto()

		System.out.println("\nUltimo conto:");
		String ultimoConto = b.ultimoConto();
		System.out.println(ultimoConto);
		
		// Per accedere alle informazioni relative ad uno qualsiasi dei conti si utilizza il metodo conto()

		System.out.println("\nConto del cliente Mario Bianchi");
		String contoCercato = b.conto("Mario", "Bianchi");
		System.out.println(contoCercato);
	
		// restituisce un valore di tipo double relativo al guadagno della banca in un determinato periodo
		// di tempo (espresso in giorni)

		System.out.println("\nGuadagno della banca in 5 anni");
		double guadagno = b.guadagnoBanca(5*365);
		System.out.println(guadagno);
	
	}
}
