package ar.com.airdrop.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JFileChooser;

import ar.com.airdrop.context.Constants;
import ar.com.airdrop.domine.Message;
import ar.com.airdrop.services.SendService;

public class ListenerAcceptSend implements ActionListener {

	
	private Message message = null;
	
	private SendService sendService = new SendService();
	public ListenerAcceptSend(Message message){
		
		this.message = message;
		
	}
	
	public void actionPerformed(ActionEvent e) {

		try {
			Socket socket = new Socket(message.getTargetIp(), Constants.FILES_PORT);
			
			JFileChooser jfc = new JFileChooser();
			 jfc.showOpenDialog(null);

			sendService.sendFile(jfc.getSelectedFile().getAbsolutePath(),new ObjectOutputStream(socket.getOutputStream()));

		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
}
