package ar.com.airdrop.vistas;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JTextField;

import ar.com.airdrop.componentes.SendCommandMenuBar;
import ar.com.airdrop.context.Commands;
import ar.com.airdrop.context.PayloadType;
import ar.com.airdrop.context.SpringContext;
import ar.com.airdrop.domine.BashCommand;
import ar.com.airdrop.domine.Message;
import ar.com.airdrop.domine.Pc;
import ar.com.airdrop.exceptions.SendThroughtSocketException;
import ar.com.airdrop.services.SendService;
import ar.com.airdrop.services.PcService;
import com.google.gson.Gson;

public class InsertCommandView extends JFrame {

	public JButton aceptar,cancelar;
	public JTextField textoComando;
	private PcService pcService =(PcService) SpringContext.getContext().getBean("pcService");
	private SendService sendService = (SendService) SpringContext.getContext().getBean("sendService");
	private JCheckBox check;
	private Gson gson =  new Gson();

	public InsertCommandView(final Pc pcExterna){

		setLayout(new FlowLayout());
		aceptar = new JButton("Aceptar");
		cancelar = new JButton("Cancelar");
		textoComando = new JTextField("",20);
		check = new JCheckBox("con respuesta",false);

		check.isEnabled();

		this.setJMenuBar(new SendCommandMenuBar(this,pcExterna));

		setSize(570,120);
		setLocation(400,400);
		add(textoComando);
		add(aceptar);
		add(cancelar);
		add(check);

		setVisible(true);
		this.setResizable(false);
		aceptar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				//Mensaje mensaje = new Mensaje(pcExterna);
				//	mensaje.setIpDestino(pcExterna.getIp());
				//	mensaje.setComando("bash");
				//	mensaje.setMensaje(textoComando.getText());
				//	mensaje.setRespuesta(check.isSelected());
				//TODO ipservice not implemented el primer parametro deberia ser la pc local
				Pc pc = new Pc("192.168.1.1");

				BashCommand bashCommand = new BashCommand(textoComando.getText(),check.isEnabled());
				Message textMessage = new Message(pc, Commands.BASH, pcExterna.getIp(), gson.toJson(bashCommand), PayloadType.BASH_COMMAND);
				try {
					sendService.sendMessage(textMessage);
				} catch (SendThroughtSocketException e1) {
					e1.printStackTrace();
				} finally {

					setVisible(false);

				}

			}
		});

		cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
	}
}
