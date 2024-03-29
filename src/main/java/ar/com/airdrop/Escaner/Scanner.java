package ar.com.airdrop.Escaner;

import java.util.LinkedList;
import java.util.StringTokenizer;

import ar.com.airdrop.context.Commands;
import ar.com.airdrop.context.SpringContext;
import ar.com.airdrop.domine.Message;
import ar.com.airdrop.domine.Pc;
import ar.com.airdrop.exceptions.SendThroughtSocketException;
import ar.com.airdrop.services.SendService;
import ar.com.airdrop.services.PcService;

public class Scanner {

	private static LinkedList<Pc> pcs = new LinkedList<Pc>();
	
	private PcService pcService = (PcService) SpringContext.getContext()
			.getBean("pcService");
	private SendService sendService = (SendService) SpringContext
			.getContext().getBean("sendService");

	public void inicioEscanner() throws InterruptedException {

		String ipAEscanear = limpiarIp(pcService.getLocalPcIp());

		for (int i = 0; i < 255; i++) {

			String serverHostName = ipAEscanear + i;
			ThreadEscanner threadTestIp = new ThreadEscanner(serverHostName,
					pcs);
			threadTestIp.start();

		}

		Thread.sleep(7000);

		for (Pc pc : pcs) {
			if (!pc.getIp().equals(pcService.getLocalPcIp())) {

				Message message = new Message(pcService.getLocalPc(), Commands.WHO,pc.getIp(),null,null);

				try {
					sendService.sendMessage(message);
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
