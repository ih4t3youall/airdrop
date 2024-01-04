package ar.com.airdrop.services;

import java.util.LinkedList;

import ar.com.airdrop.domine.Pc;

public class PcService {

	private Pc pcLocal = new Pc("0");
	private LinkedList<Pc> expternalPc = new LinkedList<Pc>();


	public PcService(){
		//Persistence persistence = new Persistence();
		//se fija si hay configuraciones y las carga

		//try {
		//	//TODO not implemented
		//	persistence.recuperarGuardado(this);
		//} catch (FileNotFoundException e) {
		//	// TODO Auto-generated catch block
		//	e.printStackTrace();
		//}
	}


	public void setExpternalPc(LinkedList<Pc> expternalPc){
		this.expternalPc = expternalPc;
	}

	public Pc getLocalPc() {
		return pcLocal;
	}

	public void setLocalhostIp(String ip) {

		pcLocal.setIp(ip);

	}

	public void setLocalPcName(String name) {

		pcLocal.setPcName(name);

	}

	public String getLocalPcIp() {
		return this.pcLocal.getIp();
	}

	public String obtenerNombrePcLocal() {
		return this.pcLocal.getPcName();
	}

	public void addPcExterna(Pc pc) {

		boolean f = true;
		for (Pc iterPc : expternalPc) {

			if (iterPc.getIp().equals(pc.getIp())) {
				//para actualizar cuando cambia por archivo->Editar local, asi se actualiza la lista.
				iterPc.setPcName(pc.getPcName());
				f = false;
			}

		}

		if (f) {

			this.expternalPc.add(pc);
		}
	}

	public LinkedList<Pc> getExternalPcList() {
		return this.expternalPc;
	}

	public void limpiarLista() {
		expternalPc.clear();
	}

}
