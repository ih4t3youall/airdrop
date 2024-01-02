package ar.com.airdrop.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ar.com.airdrop.vistas.ManualInsertIpView;

public class ListenerInsertIp implements ActionListener {

	public void actionPerformed(ActionEvent arg0) {
		new ManualInsertIpView();
	}
}
