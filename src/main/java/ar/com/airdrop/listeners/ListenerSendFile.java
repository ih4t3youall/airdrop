package ar.com.airdrop.listeners;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JFileChooser;

import ar.com.airdrop.context.Commands;
import ar.com.airdrop.context.Constants;
import ar.com.airdrop.context.SpringContext;
import ar.com.airdrop.domine.Message;
import ar.com.airdrop.domine.Pc;
import ar.com.airdrop.services.FileService;
import ar.com.airdrop.services.PcService;
import ar.com.airdrop.services.SendService;

public class ListenerSendFile implements ActionListener {

	
	private List list = null;
	private PcService pcService = (PcService) SpringContext.getContext()
			.getBean("pcService");
	private FileService fileService = (FileService) SpringContext.getContext().getBean("archivoService");
	private SendService sendService = new SendService();
	public ListenerSendFile(List list, PcService pcService){
		this.pcService = pcService;
		this.list = list;
	}

	public void actionPerformed(ActionEvent e) {

		try {
			int selectedIndex = list.getSelectedIndex();
			Pc targetPc = pcService.getExternalPcList().get(selectedIndex);

			Socket socket = new Socket(targetPc.getIp(), Constants.FILES_PORT);
			
			JFileChooser jfc = new JFileChooser();
			jfc.showOpenDialog(null);

			Pc lcoalPc = pcService.getLocalPc();

			Message message = new Message(lcoalPc, Commands.FILE, targetPc.getIp(), jfc.getName(),"file");
				sendService.sendMessage(message);
				fileService.archivoAEnviar(jfc.getSelectedFile());

		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
}
