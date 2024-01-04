package ar.com.airdrop.services;

import ar.com.airdrop.exceptions.ServerSocketReceivingException;
import ar.com.airdrop.threads.ReceiveMessage;
import ar.com.airdrop.vistas.MainMenu;


public class ReceptionService {


	public void initReceivingSocket(MainMenu mainMenu){

		ReceiveMessage receiveMessage;
		try {
			receiveMessage = new ReceiveMessage(mainMenu);
			receiveMessage.start();
		} catch (ServerSocketReceivingException e) {
			e.printStackTrace();
		}
	}


}
