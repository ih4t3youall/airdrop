package ar.com.airdrop.componentes;


	
	

import java.util.LinkedList;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import ar.com.airdrop.dominio.Pc;
import ar.com.airdrop.listeners.ListnerMenuBarEnviarComando;
import ar.com.airdrop.vistas.PantallaIngresarComando;


public class BarraDeMenuEnviarComando extends JMenuBar{

	private static String[] labels = {"Comandos"};
	private static String[] labelsItems = {"Abrir Torrent","Cerrar Torrent"};
	private LinkedList<JMenu> itemsMenu = new LinkedList<JMenu>();
	 private PantallaIngresarComando contextoPadre;
	
	public BarraDeMenuEnviarComando(PantallaIngresarComando contexto,Pc pcExterna){
//		contextoPadre = menuPrincipal;
		for (int i = 0 ; i<labels.length;i++){
			
			JMenu menu = new JMenu(labels[i]);
			itemsMenu.add(menu);
			this.add(menu);
		}
		
		for (JMenu menu : itemsMenu) {
			
			for (int i = 0 ; i<labelsItems.length;i++){
				
				JMenuItem menuItem = new JMenuItem(labelsItems[i]);
				menuItem.addActionListener(new ListnerMenuBarEnviarComando(contexto,pcExterna));
				menu.add(menuItem);
			}
		}
		
		
	}
	
	
}

	
