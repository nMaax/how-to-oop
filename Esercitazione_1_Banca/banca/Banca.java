package banca;

public class Banca {

    // Inserire tutti gli attributi necessari, eventualmente creare altre classi
	
	// Variabile per modificare la quantità di memoria assegnata al salvataggio dei dati dei clienti
    private int memoryLenght = 100;
    
    // Inserire tutti gli attributi necessari, eventualmente creare altre classi
    String nome = "";
    double tassoRicavo = 0;
    
    // Struttura dati per gestire la memorizzazione dei clienti: array di oggetti Cliente
    public Cliente clienti[] = new Cliente[memoryLenght];
    
    int numContiRegistrati = 0; //indica il numero di conti attualmente registrati, indica anche il primo indice libero negli array di salvataggio dei clienti
	
	
	/**
	* Crea una nuova Banca (costruttore)
	*/
	public Banca() {
	}
	

	/**
	* Imposta il nome della banca
	*/
	public void setNome(String nomeBanca) {
		this.nome = nomeBanca;
	}

	/**
	* Restituisce il nome della banca
	*/
	public String getNome() {
		 return this.nome;
	}

	/**
	* Imposta il tasso di ricavo della banca
	*/
	public void setTasso(double tassoRicavo) {
		this.tassoRicavo = tassoRicavo;
	}

	/**
	* Restituisce il tasso di ricavo della banca
	*/
	public double getTasso() {
		return this.tassoRicavo;
	}

	/**
	* Gestisce la creazione di un conto bancario
	*/  
	public void nuovoConto(String nomeIntestatario, String cognomeIntestatario, double capitale, double tassoInteresse) {
	
		if (numContiRegistrati<memoryLenght) {
            clienti[numContiRegistrati] = new Cliente(nomeIntestatario, cognomeIntestatario, capitale, tassoInteresse, this);
            // il this può funzionare? Pare di si
 
            numContiRegistrati += 1;
        } 
        else {
            System.out.println("\nHai finito la memoria!");
        }
	
	}
	
	/**
	* Restituisce le informazioni relative all'ultimo conto
	*/  
	public String ultimoConto() {
		
		 String ultimoConto = "";
	        
	        if(numContiRegistrati>0) {
	            
	            String nomeCliente = clienti[numContiRegistrati-1].getNome();
	            String cognomeCliente = clienti[numContiRegistrati-1].getCognome();
	            String nomeBancaCliente = nome;
	            double capitaleCliente = clienti[numContiRegistrati-1].getCapitale();
	            double tassoCliente = clienti[numContiRegistrati-1].getTassoInteresse();
	            
	            ultimoConto = nomeCliente+", "+cognomeCliente+", "+nomeBancaCliente+", "+capitaleCliente+", "+tassoCliente;
	        
	        }
	        
	        return ultimoConto;
		
	}

	/**
	* Restituisce le informazioni relative al conto di cui il nome e il cognome dell'intestatario sono passati come parametro 
	*/  
	public String conto(String nomeIntestatario, String cognomeIntestatario) {
		
		   // Inizializzazione variabili che verranno resituite, hanno già un valore di default
        // nell'eventualità che non si trovi il cliente nella struttura dati
        String nomeInt = "XXX";
        String cognomeInt = "YYY";
        String nomeBancaInt = nome;
        double capitaleInt = 0;
        double tassoInt = 0;
        
        if (numContiRegistrati > 0) { //Se sono registrati conti procedo con la ricerca
            
            int idIntestatario = -1; // Variabile che indica l'indice dove è situato il nominativo richiesto, se non individuato è pari a -1
            
            String nomeCliente;
            String cognomeCliente;
            
            for(int i=0; i<memoryLenght; i++) {
                if(clienti[i] != null) {
                    nomeCliente = clienti[i].getNome();
                    cognomeCliente = clienti[i].getCognome();
                    
                    if(nomeCliente == nomeIntestatario && cognomeCliente == cognomeIntestatario && idIntestatario == -1)
                        idIntestatario = i;
                    
                    else if(nomeCliente == nomeIntestatario && cognomeCliente == cognomeIntestatario && idIntestatario != -1) {
                        idIntestatario = i;
                        System.out.println("\n*** ATTENZIONE! Sono stati rilevati due (o più) nominativi identici nel database: "+nomeIntestatario+" "+cognomeIntestatario+", è stato restituito il più recente. ***");      
                    }
                }
            }
            
            
            if(idIntestatario != -1) { // Se la ricerca ha portato a risultati aggiorno le variabili
                nomeInt = nomeIntestatario;
                cognomeInt = cognomeIntestatario;
                capitaleInt = clienti[idIntestatario].getCapitale();
                tassoInt = clienti[idIntestatario].getTassoInteresse();
            }
            else // Se la ricerca non ha portato a risultati devo avvisarlo
                System.out.println("\n*** ATTENZIONE! Non sono stati individutati nominativi corrispondenti a "+nomeIntestatario+" "+cognomeIntestatario+" ***");
        
        }
        else //Se non vi sono conti, lo avviso e non ricerco
            System.out.println("\n*** ATTENZIONE! Non sono presenti clienti in memoria. ***");
        
        String desc =  nomeInt+", "+cognomeInt+", "+nomeBancaInt+", "+capitaleInt+", "+tassoInt;    
        return desc;
		
	}

	/**
	* Restituisce il guadagno della banca in un determinato periodo di tempo
	*/  
	public double guadagnoBanca(int numeroGiorni) {
		
		double Ctot = 0;
        double Itot = 0;
        double Rtot;
        double Gtot;
        
        // Calcolo di Ctot
        for(int i=0; i<memoryLenght; i++)
            if(clienti[i]!=null) {
                double capCliente = clienti[i].getCapitale();
                double tassoCliente = clienti[i].getTassoInteresse();
                
                Ctot = Ctot + capCliente;
                Itot = Itot + guadagnoCliente(capCliente, tassoCliente, numeroGiorni);
            }
       
        
        Rtot = (Ctot*tassoRicavo*numeroGiorni)/36500;
        Gtot = Rtot - Itot;
        
        return Gtot;
        
	}
	
	private double guadagnoCliente(double capitale, double tasso, double tempo) {
        double interesse = (capitale * tasso * tempo) / 36500;
        return interesse;
    }

}
