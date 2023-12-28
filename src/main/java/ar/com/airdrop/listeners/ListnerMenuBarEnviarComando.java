package ar.com.airdrop.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import ar.com.airdrop.context.SpringContext;
import ar.com.airdrop.domine.Pc;
import ar.com.airdrop.exceptions.SendThroughtSocketException;
import ar.com.airdrop.services.SendService;
import ar.com.airdrop.vistas.InsertCommandView;

public class ListnerMenuBarEnviarComando implements ActionListener {


	private InsertCommandView contexto;
	private Pc pcExterna;
	private SendService sendService = (SendService) SpringContext
			.getContext().getBean("sendService");
	
	public ListnerMenuBarEnviarComando(InsertCommandView contexto,
									   Pc pcExterna) {
		this.pcExterna = pcExterna;
		this.contexto = contexto;
	}

	public void actionPerformed(ActionEvent arg0) {
	
		JMenuItem boton = (JMenuItem) arg0.getSource();
		

		//TODO no se que onda con esto
		//if (boton.getText().equals("Abrir Torrent")){
		//
		//	Mensaje mensaje = new Mensaje(pcExterna);
		//	mensaje.setIpDestino(pcExterna.getIp());
		//	mensaje.setComando("bash");
		//	mensaje.setMensaje("tixati");
		//	mensaje.setRespuesta(false);
		//	try {
		//		sendService.sendMessage(mensaje);
		//	} catch (SendThroughtSocketException e1) {
		//		e1.printStackTrace();
		//	} finally {
		//		contexto.dispose();
		//	}
		//}
		
		//if (boton.getText().equals("Cerrar Torrent")){
		//	Mensaje mensaje = new Mensaje(pcExterna);
		//	mensaje.setIpDestino(pcExterna.getIp());
		//	mensaje.setComando("bash");
		//	mensaje.setMensaje("killall tixati");
		//	mensaje.setRespuesta(false);
		//	try {
		//		sendService.sendMessage(mensaje);
		//	} catch (SendThroughtSocketException e1) {
		//		e1.printStackTrace();
		//	} finally {

		//		contexto.dispose();

		//	}
		//
		//}
		
	}

}
