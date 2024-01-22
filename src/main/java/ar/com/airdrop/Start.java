package ar.com.airdrop;

import javax.swing.JOptionPane;

import ar.com.airdrop.context.SpringContext;
import ar.com.airdrop.domine.Pc;
import ar.com.airdrop.exceptions.ServiceException;
import ar.com.airdrop.services.FileService;
import ar.com.airdrop.services.IpService;
import ar.com.airdrop.services.PcService;
import ar.com.airdrop.threads.ReceiveFile;
import ar.com.airdrop.vistas.MainMenu;

public class Start {

	private static IpService ipService = (IpService) SpringContext.getContext()
			.getBean("ipService");
	private FileService fileService = (FileService) SpringContext
			.getContext().getBean("archivoService");
	private PcService pcService = (PcService) SpringContext
			.getContext().getBean("pcService");



	public Start() {


		ReceiveFile rs = new ReceiveFile();
		rs.start();
		Pc pc = null;
		try {
			pc = ipService.getIp();
			if (pcService.getLocalPcIp().equals("0")){
				pcService.setLocalhostIp(pc.getIp());
			}
			pcService.setLocalPcName(pc.getPcName());
		} catch (Exception | ServiceException e) {
			JOptionPane
					.showMessageDialog(null,
							"Error al obtener la ip local, verifique su conexion a internet");
			System.exit(0);
		}

		MainMenu mainMenu = new MainMenu();
		mainMenu.setVisible(true);
	}

	public IpService getIpService() {
		return ipService;
	}

	public void setIpService(IpService ipService) {
		this.ipService = ipService;
	}

	public FileService getRecepcionService() {
		return fileService;
	}

	public void setRecepcionService(FileService fileService) {
		this.fileService = fileService;
	}

}
