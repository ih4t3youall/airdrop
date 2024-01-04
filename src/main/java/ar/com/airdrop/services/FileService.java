package ar.com.airdrop.services;


import ar.com.airdrop.domine.Message;

import java.io.File;

public class FileService {
	
	
	private File file;
	
	private String directorioSalvado="";
	

	public void archivoAEnviar(Message mensaje){
		
		this.file = file;
		
	}
	
	public File obtenerArchivoAEviar(){

		//originalmente Mensaje devolvia un file, ahora no, asi que lo creo con el payload que deberia ser un filename.
		return file;
		
	}
	
	public String obtenerNombreArchivo(){
		return file.getName();
	}

	public void archivoARecibir(File file) {
		this.file = file;
	}


	public void setDirectorioSalvado(String directorioSalvado){
		this.directorioSalvado = directorioSalvado;
	}
	
	public String getDirectorioSalvado(){
		return this.directorioSalvado;
	}
}
