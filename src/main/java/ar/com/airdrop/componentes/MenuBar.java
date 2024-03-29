package ar.com.airdrop.componentes;

import java.util.LinkedList;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import ar.com.airdrop.listeners.ListnerMenuBar;
import ar.com.airdrop.vistas.MainMenu;


public class MenuBar extends JMenuBar{

	private static String[] labels = {"File"};
	private static String[] labelsItems = {"Edit Local","Save","Load"};
	private LinkedList<JMenu> itemsMenu = new LinkedList<JMenu>();
	 private MainMenu contextoPadre;
	
	public MenuBar(MainMenu mainMenu){
		contextoPadre = mainMenu;
		for (int i = 0 ; i<labels.length;i++){
			
			JMenu menu = new JMenu(labels[i]);
			itemsMenu.add(menu);
			this.add(menu);
		}
		
		for (JMenu menu : itemsMenu) {
			
			for (int i = 0 ; i<labelsItems.length;i++){
				
				JMenuItem menuItem = new JMenuItem(labelsItems[i]);
				menuItem.addActionListener(new ListnerMenuBar(contextoPadre));
				menu.add(menuItem);
			}
		}
		
		
	}
	
	
}

	
