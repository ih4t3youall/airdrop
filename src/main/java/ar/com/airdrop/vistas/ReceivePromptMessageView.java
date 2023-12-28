package ar.com.airdrop.vistas;

import ar.com.airdrop.domine.Message;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class ReceivePromptMessageView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
	JTextField textfield = new JTextField("", 30);
	JButton portaPapeles = new JButton("Copy to clipboard");
	JButton aceptar = new JButton("Ok");
	StringSelection toCopy;

	public ReceivePromptMessageView(Message message) {

		String text = "";
		text = message.getPayload();

		toCopy = new StringSelection(text);

		Dimension d = new Dimension(400, 100);

		setSize(d);

		setLocation(200, 200);

		JPanel panel = new JPanel();

		panel.setLayout(new FlowLayout());
		textfield.setText(text);

		panel.add(textfield);
		panel.add(portaPapeles);
		panel.add(aceptar);
		add(panel);

		setVisible(true);
		this.setResizable(false);
		portaPapeles.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				cb.setContents(toCopy, null);

			}
		});

		aceptar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				setVisible(false);

			}
		});

	}

}
