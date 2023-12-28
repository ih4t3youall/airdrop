package ar.com.airdrop.exceptions;

public class ServerSocketReceivingException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ServerSocketReceivingException(Exception e , String ip , String mensaje){
		
		
		
		System.out.println(mensaje+ip+e.getCause());
		
		
		
		
	}
	
	

}
