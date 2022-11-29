package cluster.myMethods;

public class EmailAddress {
	
	public final static String DEFAULT_RECIPIENT = "RECIPIENT";
	public final static String DEFAULT_DOMAIN = "DOMAIN.DOM";
	
	private String recipient ; // eg 'alan.mellor'   
	private String domain ; // eg 'example.com'    
	
	public EmailAddress() {
		this(DEFAULT_RECIPIENT, DEFAULT_DOMAIN);
	}

	public EmailAddress(String recipient, String domain){
		super(); 
		this.recipient = recipient ;
	    this.domain = domain ;
	}
	  
	public EmailAddress(String email) {
		super();
		  
		int at = email.indexOf('@');
		String recipient = email.substring(0, at);
		String domain = email.substring(at+1);
	  
		this.recipient = recipient ;
		this.domain = domain;
	}
	
	public String getAddress() {
		return toString();
	}
	
	/**Restituisce true se la email contiente @, false altrimenti*/
	public static boolean validEmail(String email) {
		boolean output = false;
		if (email.indexOf('@') != -1)
			output = true;
		return output;
	}
	
	public String toString() {
		return recipient + "@" + domain ;
	}
	  
}
