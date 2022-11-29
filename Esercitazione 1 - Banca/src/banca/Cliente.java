
package banca;
 
public class Cliente {
    
    // Attributi del cliente richiesti
    
    private String nome;
    private String cognome;
    private double capitale;
    private double tassoInteresse;
    public Banca banca;
 
    public Cliente() {
        // TODO Auto-generated constructor stub
    }
    
    public Cliente(String nome, String cognome, double capitale, double tassoInteresse, Banca banca) {
        this.nome = nome;
        this.cognome = cognome;
        this.capitale = capitale;
        this.tassoInteresse = tassoInteresse;
        this.banca = banca;
    }
 
    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }
 
    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }
 
    /**
     * @return the cognome
     */
    public String getCognome() {
        return cognome;
    }
 
    /**
     * @param cognome the cognome to set
     */
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }
 
    /**
     * @return the capitale
     */
    public double getCapitale() {
        return capitale;
    }
 
    /**
     * @param capitale the capitale to set
     */
    public void setCapitale(double capitale) {
        this.capitale = capitale;
    }
 
    /**
     * @return the tassoInteresse
     */
    public double getTassoInteresse() {
        return tassoInteresse;
    }
 
    /**
     * @param tassoInteresse the tassoInteresse to set
     */
    public void setTassoInteresse(double tassoInteresse) {
        this.tassoInteresse = tassoInteresse;
    }
 
    /**
     * @return the banca
     */
    public Banca getBanca() {
        return banca;
    }
    
    public String getBancaString() {
        return banca.getNome();
    }
 
    /**
     * @param banca the banca to set
     */
    public void setBanca(Banca banca) {
        this.banca = banca;
    }
    
    
 
}