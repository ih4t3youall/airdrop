package ar.com.airdrop.persistencia;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import ar.com.airdrop.context.SpringContext;
import ar.com.airdrop.exceptions.ArchivoNoExisteException;
import ar.com.airdrop.services.PcService;
import ar.com.airdrop.vistas.MenuPrincipal;
import ar.com.commons.send.services.IpService;
import org.springframework.beans.factory.annotation.Autowired;

public class Persistencia {


	public void Guardar(PcService pcService) {
		Object obj = pcService;

		File arch = new File("airdropdata");
		arch.delete();

		FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream("airdropdata");

			ObjectOutputStream obj_out = new ObjectOutputStream(fileOut);

			obj_out.writeObject(obj);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void recuperarGuardado(PcService pcService) throws ArchivoNoExisteException {

		File f = new File("airdropdata");
		if(f.exists()){
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(new FileInputStream("airdropdata"));

			Object aux = ois.readObject();

			PcService service = (PcService) aux;
			pcService.setPcExternas(service.obtenerListaPcExternas());

			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		} finally {
			try {
				if (ois != null)
					ois.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		}else {
			
			
			throw new ArchivoNoExisteException("el archivo no existe");
		}

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
