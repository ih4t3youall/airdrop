package ar.com.airdrop.exceptions;

public class SendThroughtSocketException extends Exception {

	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;

	public SendThroughtSocketException(Exception e, String ip, String mensaje) {

		
		e.printStackTrace();
		
		System.out.println(mensaje + ip + " causa : " + e.getCause());

	}

}
