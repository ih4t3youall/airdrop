package ar.com.airdrop.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ar.com.airdrop.Escaner.Scanner;
import ar.com.airdrop.vistas.MainMenu;

public class ScannerListener implements ActionListener {

	
	private MainMenu mainMenu;
	public ScannerListener(MainMenu mainMenu){
		
		
		this.mainMenu = mainMenu;
	}
	
	
	public void actionPerformed(ActionEvent arg0) {
	
		try {
			
			 new Scanner().inicioEscanner();
			
		} catch (InterruptedException e) {
			System.out.println("Fallo el scanner.");
		}

		
		 
		
		
		
		
		
		
	}

}
