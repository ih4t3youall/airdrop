package ar.com.airdrop.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ar.com.airdrop.vistas.MainMenu;

public class ListenerExitButton implements ActionListener {

	
	private MainMenu mainMenu;
	
	public ListenerExitButton(MainMenu mainMenu){
		
		this.mainMenu = mainMenu;
		
	}
	
	public void actionPerformed(ActionEvent arg0) {
		
		System.exit(0);
		
		

	}

}
