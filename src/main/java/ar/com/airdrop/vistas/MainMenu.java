package ar.com.airdrop.vistas;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.List;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import ar.com.airdrop.componentes.MenuBar;
import ar.com.airdrop.context.SpringContext;
import ar.com.airdrop.domine.Pc;
import ar.com.airdrop.listeners.*;
import ar.com.airdrop.services.PcService;
import ar.com.airdrop.services.ReceptionService;

public class MainMenu extends JFrame {

	private JButton scan, sendFiles, exit, insertIp, sendMessage,
			edit, sendCommand;
	private LinkedList<Pc> pcs = new LinkedList<Pc>();
	//es un helper del modelo que dibuja pero no es la actual lista de pc no se porque
	private List lista = new List();

	private static PcService pcService = (PcService) SpringContext.getContext()
			.getBean("pcService");
	private static ReceptionService receptionService = (ReceptionService) SpringContext.getContext()
			.getBean("receptionService");

	public MainMenu() {
		super("V2 Scanner : " + pcService.getLocalPcIp());

		this.setJMenuBar(new MenuBar(this));

		GridBagConstraints constraints = new GridBagConstraints();
		edit = new JButton("Edit");
		scan = new JButton("Scanner");
		sendFiles = new JButton("Send Files");
		exit = new JButton("Exit");
		insertIp = new JButton("Insert Ip");
		sendMessage = new JButton("Send Message");
		sendCommand = new JButton("Send Command");

		Dimension size = new Dimension(550, 200);

		setSize(size);
		setLocation(500, 500);
		setResizable(false);

		this.getContentPane().setLayout(new GridBagLayout());

		// lista
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 3;
		constraints.gridheight = 1;

		// constraints.fill = GridBagConstraints.BOTH;

		this.getContentPane().add(lista, constraints);
		constraints.weighty = 0;
		constraints.weightx = 0;
		// boton1
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		// constraints.fill = GridBagConstraints.BOTH;

		this.getContentPane().add(scan, constraints);
		constraints.weighty = 0.0;

		// boton 2

		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		// constraints.fill = GridBagConstraints.BOTH;

		this.getContentPane().add(sendFiles, constraints);
		constraints.weighty = 0.0;

		// ip a mano
		constraints.gridx = 2;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		// constraints.fill = GridBagConstraints.BOTH;

		this.getContentPane().add(insertIp, constraints);
		constraints.weighty = 0.0;

		// boton 3

		constraints.gridx = 3;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		// constraints.fill = GridBagConstraints.BOTH;

		this.getContentPane().add(exit, constraints);
		constraints.weighty = 0.0;

		// boton Editar
		constraints.gridx = 2;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		// constraints.fill = GridBagConstraints.BOTH;

		this.getContentPane().add(edit, constraints);
		constraints.weighty = 0.0;

		// boton enviarComando
		constraints.gridx = 4;
		constraints.gridy = 3;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		// constraints.fill = GridBagConstraints.BOTH;

		this.getContentPane().add(sendCommand, constraints);
		constraints.weighty = 0.0;

		this.scan.addActionListener(new ScannerListener(this));
		this.insertIp.addActionListener(new ListenerInsertIp());
		this.exit.addActionListener(new ListenerExitButton(this));
		this.sendMessage.addActionListener(new ListenerSendMessage(lista));
		this.sendFiles.addActionListener(new ListenerSendFile(lista, pcService));
		this.edit.addActionListener(new ListenerEdit(lista, this));
		this.sendCommand.addActionListener(new ListenerSendCommand(lista));

		receptionService.initReceivingSocket(this);

		this.cargarLista();

		// enviar mensaje
		// ip a mano
		constraints.gridx = 4;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		// constraints.fill = GridBagConstraints.BOTH;

		this.getContentPane().add(sendMessage, constraints);
		constraints.weighty = 0.0;

		// cerrar y terminar programa
		setDefaultCloseOperation(0); // que por defecto no haga nada
		addWindowListener(new WindowAdapter() {
			@Override
			// (anotacion) se sobreescribe el metodo windowClosing
			public void windowClosing(WindowEvent we) {
				int eleccion = JOptionPane.showConfirmDialog(null,
						"Desea salir?");
				if (eleccion == 0) {
					System.out.println("adios");
					System.exit(0);
				}
			}
		});
	}

	public LinkedList<Pc> damePcs() {

		return this.pcs;

	}


	public void cargarLista() {

		LinkedList<Pc> ListaOtrasPc = pcService.getExternalPcList();

		lista.removeAll();

		for (Pc pc : ListaOtrasPc) {
			String aux = "";
			if (pc.getPcName().length() > 14)
				aux = pc.getPcName().substring(0, 14);
			else
				aux = pc.getPcName();
			lista.add(aux);
		}
	}

	public static PcService getPcService() {
		return pcService;
	}

	public static void setPcService(PcService pcService) {
		MainMenu.pcService = pcService;
	}

	public void renovarNombre() {
		this.setTitle(pcService.getLocalPc().getIp());

	}

	public void eliminarElSeleccionado() {

		int selectedIndex = lista.getSelectedIndex();
		lista.remove(selectedIndex);

	}

	public List getLista() {

		return lista;
	}

}
