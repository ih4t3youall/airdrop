package ar.com.airdrop.listeners;

import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import ar.com.airdrop.context.SpringContext;
import ar.com.airdrop.domine.Pc;
import ar.com.airdrop.services.PcService;
import ar.com.airdrop.vistas.InsertCommandView;

public class ListenerSendCommand implements ActionListener {

	
	private List lista;
	private PcService pcService =(PcService) SpringContext.getContext().getBean("pcService");
	
	public ListenerSendCommand(List lista){
		this.lista = lista;
	}
	
	
	public void actionPerformed(ActionEvent arg0) {
	
		
int selectedIndex = this.lista.getSelectedIndex();
		
		if (selectedIndex == -1){
		
			JOptionPane.showMessageDialog(null, "debe seleccionar una pc");
			
			
		}else {
		Pc pcExterna = pcService.getExternalPcList().get(selectedIndex);
		
		
		new InsertCommandView(pcExterna);
		}
		
		

	}

}
