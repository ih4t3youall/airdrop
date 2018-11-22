package ar.com.airdrop.services;

import ar.com.airdrop.exceptions.RecibirServerSocketException;
import ar.com.airdrop.threads.RecibirArchivo;
import ar.com.airdrop.threads.RecibirMensaje;
import ar.com.airdrop.vistas.MenuPrincipal;

public class RecepcionService {

	
	public void iniciarServerSocketObjetos(MenuPrincipal menuPrincipal){
		
		RecibirMensaje recibirMensaje;
		try {
			recibirMensaje = new RecibirMensaje(menuPrincipal);
		recibirMensaje.start();
		new RecibirArchivo().start();
		} catch (RecibirServerSocketException e) {
			e.printStackTrace();
		}
	}
	
}
