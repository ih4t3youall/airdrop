package ar.com.airdrop.listeners;

import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import ar.com.airdrop.context.SpringContext;
import ar.com.airdrop.dominio.Pc;
import ar.com.airdrop.services.PcService;
import ar.com.airdrop.vistas.MenuPrincipal;
import ar.com.airdrop.vistas.PantallaIngresarComando;

public class ListnerEnviarComando implements ActionListener {

	
	private List lista;
	private PcService pcService =(PcService) SpringContext.getContext().getBean("pcService");
	
	public ListnerEnviarComando(List lista){
		this.lista = lista;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
	
		
int selectedIndex = this.lista.getSelectedIndex();
		
		if (selectedIndex == -1){
		
			JOptionPane.showMessageDialog(null, "debe seleccionar una pc");
			
			
		}else {
		Pc pcExterna = pcService.obtenerListaPcExternas().get(selectedIndex);
		
		
		new PantallaIngresarComando(pcExterna);
		}
		
		

	}

}
