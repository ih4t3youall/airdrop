package ar.com.airdrop.persistencia;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import ar.com.airdrop.exceptions.FileNotFoundException;
import ar.com.airdrop.services.PcService;

public class Persistence {


	public void Guardar(PcService pcService){
		//TODO(not implemented)
	}

	public void recuperarGuardado(PcService pcService) throws FileNotFoundException {
		//TODO(not implemented)
	}

	public static String getDownloadDirectory(){
		String userHome = System.getProperty("user.home");
		String airdropDownloads = "/Downloads";
		if(directoryExist(userHome+airdropDownloads)){
			return userHome + airdropDownloads;
		}else{
		File file = new File(userHome+airdropDownloads);
		file.mkdir();
		return file.getAbsolutePath();
		}
	}
	private static boolean directoryExist(String directory){
		return new File(directory).exists();
	}
}
