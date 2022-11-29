import cluster.*;
import cluster.myMethods.*;

public class Esempio {

	public static void main(String[] args) {
		
		System.out.println("Creazione Cluster\n");
		Cluster c = new Cluster(5, 3);
		
		System.out.println("Creazione utenti\n");
		UtenteAdmin u1 = c.registraUtente("fabio.garcea@polito.it", "Fabio", "Garcea", "Password1", "19930917", "ssh-rsaAAABBBCCC", "RASPRIVATE---MIIBOgIBAAJBAKj34---ENDRSA");
		System.out.println("Creato utente fabio.garcea@polito.it\n");
		System.out.println(u1.getEmail());
		System.out.println(u1.getNome());
		System.out.println(u1.getCognome());
		System.out.println(u1.getPassword());
		System.out.println(u1.getDataNascita());
		System.out.println(u1.getChiavePubblica());
		System.out.println(u1.getChiavePrivata());
		
		System.out.println("Creazione altri utenti\n");
		c.registraUtente("fabrizio.lamberti@polito.it", "Fabrizio", "Lamberti", "passWord2", "19800101");
		c.registraUtente("lia.morra@polito.it", "Lia", "Morra", "3passworD", "19850101");
		c.registraUtente("guido.marchetto@polito.it", "Guido", "Marchetto", "pass4woRd", "19850202");
		
		System.out.println("Descrizione utente fabrizio.lamberti@polito.it\n");
		String descrizioneUtente = c.descriviUtente("fabrizio.lamberti@polito.it");
		System.out.println(descrizioneUtente);
		
		System.out.println("Ricerca utente lia.morra@polito.it\n");
		Utente utenteTrovato = c.cercaUtente("lia.morra@polito.it");
		System.out.println(c.descriviUtente("fabio.garcea@polito.it", "Password1", utenteTrovato.getEmail()));
		
		System.out.println("Ricerca utenti creati finora\n");
		Utente[] utenti = c.cercaUtenti();
		for (Utente u : utenti) {
			System.out.println(c.descriviUtente(u.getEmail()));
		}
				
		
		System.out.println("Creazione processi");
		Processo p1 = c.nuovoProcesso("fabrizio.lamberti@polito.it", "passWord2", "python render_texture.py --verbose", "20221118 16:45:00");
		System.out.println("Creato processo 1\n");
		System.out.println(p1.getCodiceProcesso());
		System.out.println(p1.getEmailUtente());
		System.out.println(p1.getPasswordUtente());
		System.out.println(p1.getDescrizione());
		System.out.println(p1.getTimestamp());
		System.out.println(p1.getStatoEsecuzione());
		
		System.out.println("Creazione altri processi\n");
		c.nuovoProcesso("fabio.garcea@polito.it", "Password1", "python train_ai.py --verbose", "20221118 17:45:00");
		c.nuovoProcesso("lia.morra@polito.it", "3passworD", "python compute_statistics.py --verbose", "20221118 18:00:00");
		c.nuovoProcesso("lia.morra@polito.it", "3passworD", "python validate_model.py --verbose", "20221118 18:20:00");

		System.out.println("Descrizione processo 2\n");
		String descrizioneProcesso = c.descriviProcesso(2);
		System.out.println(descrizioneProcesso);
		
		System.out.println("Esecuzione processi\n");
		boolean successo = c.eseguiProcessi("20221118 18:25:00");
		System.out.println(successo);
		
		System.out.println("Completamento processi\n");
		int[] codiciProcesso = {3};
		boolean[] successi = c.completaProcessi(codiciProcesso, Data.TODAY + " " + "18:30:00");
		for (boolean b : successi) {
			System.out.println(b);
		}
		
		
		System.out.println("Ricerca processo 3\n");
		Processo pTrovato = c.cercaProcesso(3);
		System.out.println(c.descriviProcesso(pTrovato.getCodiceProcesso()));
		
		System.out.println("Ricerca processi\n");
		Processo[] processi = c.cercaProcessi();
		for (Processo p : processi) {
			System.out.println(c.descriviProcesso(p.getCodiceProcesso()));
		}
		
		System.out.println("Elenco utenti per email");
		for (Utente u : c.elencoUtentiPerEmail()) {
			System.out.println(c.descriviUtente(u.getEmail()));
		}
		
		System.out.println("Elenco processi Pending");
		for (Processo p : c.elencoProcessiPending()) {
			System.out.println(c.descriviProcesso(p.getCodiceProcesso()));
		}
	}
	
}