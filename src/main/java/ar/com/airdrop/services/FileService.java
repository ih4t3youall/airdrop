package ar.com.airdrop.services;


import ar.com.airdrop.domine.Message;

public class FileService {
	
	
	private Message message;
	
	private String directorioSalvado="";
	

	public void archivoAEnviar(Message mensaje){
		
		this.message = mensaje;
		
	}
	
	public Message obtenerArchivoAEviar(){
		
		return message;
		
	}
	
	public String obtenerNombreArchivo(){

		//TODO(not implemented)
		//return message.getFile().getName();
		return null;
		
	}

	public void archivoARecibir(Message mensaje) {

		this.message = mensaje;
	}


	public void setDirectorioSalvado(String directorioSalvado){
		
		this.directorioSalvado = directorioSalvado;
		
	}
	
	public String getDirectorioSalvado(){
		
		return this.directorioSalvado;
		
	}
	
	

	

}
