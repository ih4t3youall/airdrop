package ar.com.airdrop.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JFileChooser;

import ar.com.airdrop.services.SendService;
import ar.com.commons.send.airdrop.Constantes;
import ar.com.commons.send.airdrop.Mensaje;

public class ListenerAcceptSend implements ActionListener {

	
	private Mensaje mensaje = null;
	
	private SendService sendService = new SendService();
	public ListenerAcceptSend(Mensaje mensaje){
		
		this.mensaje = mensaje;
		
	}
	
	public void actionPerformed(ActionEvent e) {
	
		
		try {
			Socket socket = new Socket(mensaje.getIpDestino(), Constantes.PUERTO_ARCHIVOS);
			
			JFileChooser jfc = new JFileChooser();
			 jfc.showOpenDialog(null);

			sendService.sendFile(jfc.getSelectedFile().getAbsolutePath(),new ObjectOutputStream(socket.getOutputStream()));
			
			
			
			
			
		} catch (Exception e1) {

			e1.printStackTrace();
		}
		
		
		
		
		

	}

}
