package ar.com.airdrop.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import ar.com.airdrop.context.SpringContext;
import ar.com.airdrop.exceptions.FileNotFoundException;
import ar.com.airdrop.persistencia.Persistence;
import ar.com.airdrop.services.PcService;
import ar.com.airdrop.vistas.Edit;
import ar.com.airdrop.vistas.MainMenu;

public class ListnerMenuBar implements ActionListener {

	private PcService pcService = (PcService) SpringContext
			.getContext().getBean("pcService");
	private MainMenu contextoPadre;
		
	

	public ListnerMenuBar(MainMenu contextoPadre2) {
		this.contextoPadre = contextoPadre2;
	}

	public void actionPerformed(ActionEvent event) {

		JMenuItem menuItem = (JMenuItem) event.getSource();

		if (menuItem.getText().equals("Edit Local")) {

			new Edit(pcService.getLocalPc(),contextoPadre);

		}
		if (menuItem.getText().equals("Save")) {
			
			 
				 Persistence persistence = new Persistence();
				 persistence.Guardar(pcService);
			
		}
		if (menuItem.getText().equals("Load")) {
			
				Persistence persistence = new Persistence();
				try {
					persistence.recuperarGuardado(pcService);
					this.contextoPadre .cargarLista();
					this.contextoPadre .renovarNombre();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}

	}

}
