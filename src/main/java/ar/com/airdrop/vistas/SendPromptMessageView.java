package ar.com.airdrop.vistas;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ar.com.airdrop.context.SpringContext;
import ar.com.airdrop.domine.Message;
import ar.com.airdrop.domine.Pc;
import ar.com.airdrop.exceptions.SendThroughtSocketException;
import ar.com.airdrop.services.SendService;

public class SendPromptMessageView extends JFrame {

	JTextField textfield = new JTextField("", 30);
	JButton aceptar, cancelar;
	private Pc pc;

	private SendService sendService = (SendService) SpringContext
			.getContext().getBean("sendService");

	public SendPromptMessageView(Pc pc1) {
		this.pc = pc1;
		this.aceptar = new JButton("Ok");
		this.cancelar = new JButton("Cancel");

		Dimension d = new Dimension(400, 100);

		setSize(d);

		setLocation(200, 200);

		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());

		panel.add(textfield);
		panel.add(aceptar);
		panel.add(cancelar);

		textfield.requestFocus();
		textfield.requestFocusInWindow();
		
		
		add(panel);
		setVisible(true);
		this.setResizable(false);
		
		cancelar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
			dispose();
				
			}
		});

		aceptar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Message mensaje = new Message(pc,"mensajePrompt",textfield.getText());

				try {
					sendService.sendMessage(mensaje);
				} catch (SendThroughtSocketException e1) {
					e1.printStackTrace();
				} finally {

					setVisible(false);

				}

			}
		});

	}

	public SendService getSendService() {
		return sendService;
	}

	public void setSendService(SendService sendService) {
		this.sendService = sendService;
	}

}
