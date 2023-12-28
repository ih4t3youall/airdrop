package ar.com.airdrop.services;

import ar.com.airdrop.exceptions.ServerSocketReceivingException;
import ar.com.airdrop.persistencia.Persistence;
import ar.com.airdrop.threads.ReceiveMessage;
import ar.com.airdrop.vistas.MainMenu;
import ar.com.commons.send.socket.Server;


public class RecepcionService {


	public void iniciarServerSocketObjetos(MainMenu mainMenu){

		ReceiveMessage receiveMessage;
		try {
			receiveMessage = new ReceiveMessage(mainMenu);
		receiveMessage.start();
		//new RecibirArchivo().start();
			System.out.println("starting file receiver service");

			Thread t = new Thread(new Server(Persistence.getDownloadDirectory()));
			t.start();
		} catch (ServerSocketReceivingException e) {
			e.printStackTrace();
		}
	}


}
