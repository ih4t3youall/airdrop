package ar.com.airdrop.Escaner;

import java.util.LinkedList;
import java.util.StringTokenizer;

import ar.com.airdrop.context.SpringContext;
import ar.com.airdrop.exceptions.SendThroughtSocketException;
import ar.com.airdrop.services.SendService;
import ar.com.airdrop.services.PcService;
import ar.com.commons.send.airdrop.Mensaje;
import ar.com.commons.send.airdrop.Pc;

public class Scanner {

	private static LinkedList<Pc> pcs = new LinkedList<Pc>();
	
	private PcService pcService = (PcService) SpringContext.getContext()
			.getBean("pcService");
	private SendService sendService = (SendService) SpringContext
			.getContext().getBean("sendService");

	public void inicioEscanner() throws InterruptedException {

		String ipAEscanear = limpiarIp(pcService.obtenerIpLocal());

		for (int i = 0; i < 255; i++) {

			String serverHostName = ipAEscanear + i;
			ThreadEscanner threadTestIp = new ThreadEscanner(serverHostName,
					pcs);
			threadTestIp.start();

		}

		Thread.sleep(7000);

		for (Pc pc : pcs) {
			if (!pc.getIp().equals(pcService.obtenerIpLocal())) {

				Mensaje mensaje = new Mensaje(pcService.getPcLocal());
				mensaje.setIpDestino(pc.getIp());
				mensaje.setComando("who");

				try {
					sendService.sendMessage(mensaje);
				} catch (SendThroughtSocketException e) {
					e.printStackTrace();
				}
			} else {

				System.out.println("omitiendo localhost...");

			}

		}

	}

	public String limpiarIp(String ip) {

		String ipLimpia = "";
		StringTokenizer token = new StringTokenizer(ip, ".");

		for (int i = 0; i < 3; i++) {

			ipLimpia += token.nextToken() + ".";

		}

		return ipLimpia;

	}

	public PcService getPcService() {
		return pcService;
	}

	public void setPcService(PcService pcService) {
		this.pcService = pcService;
	}

	public SendService getSendService() {
		return sendService;
	}

	public void setSendService(SendService sendService) {
		this.sendService = sendService;
	}

}
