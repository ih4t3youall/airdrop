package ar.com.airdrop.services;


import ar.com.airdrop.domine.Message;

import java.io.File;

public class FileService {
	
	
	private Message message;
	
	private String directorioSalvado="";
	

	public void archivoAEnviar(Message mensaje){
		
		this.message = mensaje;
		
	}
	
	public File obtenerArchivoAEviar(){

		//originalmente Mensaje devolvia un file, ahora no, asi que lo creo con el payload que deberia ser un filename.
		return new File(message.getPayload());
		
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
