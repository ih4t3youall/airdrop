package ar.com.airdrop.listeners;

import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import ar.com.airdrop.context.SpringContext;
import ar.com.airdrop.services.PcService;
import ar.com.airdrop.vistas.Edit;
import ar.com.airdrop.vistas.MainMenu;
import ar.com.commons.send.airdrop.Pc;

public class ListenerEdit implements ActionListener {

	
	
	private List lista;
	private PcService pcService =(PcService) SpringContext
			.getContext().getBean("pcService") ;
	private MainMenu mainMenu;
	
	public ListenerEdit(List lista, MainMenu mainMenu){
		this.mainMenu = mainMenu;
		this.lista = lista;
		
	}
	
	
	public void actionPerformed(ActionEvent arg0) {

		
		int selectedIndex = lista.getSelectedIndex();
		
		if (selectedIndex == -1){
		
			JOptionPane.showMessageDialog(null, "debe seleccionar una pc");
			
			
		}else {
		Pc pcExterna = pcService.obtenerListaPcExternas().get(selectedIndex);
		
		new Edit(pcExterna, mainMenu);
		
		}
		
		
	}

}
