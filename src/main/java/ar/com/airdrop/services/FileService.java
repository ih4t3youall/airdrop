package ar.com.airdrop.services;


import ar.com.airdrop.domine.Message;

import java.io.File;

public class FileService {
	
	
	private File file;

	private String fileName;
	
	private String directorioSalvado="";
	

	public void archivoAEnviar(File file){
		
		this.file = file;
		
	}
	
	public File obtenerArchivoAEviar(){

		//originalmente Mensaje devolvia un file, ahora no, asi que lo creo con el payload que deberia ser un filename.
		return file;
		
	}
	
	public String obtenerNombreArchivo(){
		return this.fileName;
	}

	public void archivoARecibir(String fileName) {
		this.fileName = fileName;
	}


	public void setDirectorioSalvado(String directorioSalvado){
		this.directorioSalvado = directorioSalvado;
	}
	
	public String getDirectorioSalvado(){
		return this.directorioSalvado;
	}
}
