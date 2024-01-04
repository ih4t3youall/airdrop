package ar.com.airdrop.listeners;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JFileChooser;

import ar.com.airdrop.context.Constants;
import ar.com.airdrop.domine.Message;
import ar.com.airdrop.domine.Pc;
import ar.com.airdrop.services.PcService;
import ar.com.airdrop.services.SendService;

public class ListenerSendFile implements ActionListener {

	
	private List list = null;
	private PcService pcService = null;

	private SendService sendService = new SendService();
	public ListenerSendFile(List list, PcService pcService){
		this.pcService = pcService;
		this.list = list;
	}

	public void actionPerformed(ActionEvent e) {

		try {
			Pc localPc = pcService.getLocalPc();
			int selectedIndex = list.getSelectedIndex();
			pcService.getExternalPcList().get(selectedIndex);
			//(  Pc senderPc,   String command,   String targetIp, String payload, String payloadType)
			Message message =  new Message(localPc, )
			Socket socket = new Socket(message.getTargetIp(), Constants.FILES_PORT);
			
			JFileChooser jfc = new JFileChooser();
			 jfc.showOpenDialog(null);

			sendService.sendFile(jfc.getSelectedFile().getAbsolutePath(),new ObjectOutputStream(socket.getOutputStream()));

		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
}
