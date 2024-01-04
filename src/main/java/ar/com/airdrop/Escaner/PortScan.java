package ar.com.airdrop.Escaner;


import ar.com.airdrop.context.SpringContext;
import ar.com.airdrop.domine.Pc;
import ar.com.airdrop.services.PcService;

import java.util.Iterator;
import java.util.LinkedList;


public class PortScan {

	
	private PcService pcService = (PcService)SpringContext.getContext().getBean("pcService");
	

	
	public void inicioEscanner(){

		Iterator<Pc> iterator = pcService.getExternalPcList().iterator();
		LinkedList<Pc> filtradas = new LinkedList<Pc>();
		
		while(iterator.hasNext()){
			Pc pc = iterator.next();
			
		ThreadScanPorts thread =new ThreadScanPorts(pc,filtradas);
		thread.start();
		}
		
		try {
			Thread.sleep(7000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		pcService.setExpternalPc(filtradas);
		
		
	}

	public PcService getPcService() {
		return pcService;
	}

	public void setPcService(PcService pcService) {
		this.pcService = pcService;
	}

}
