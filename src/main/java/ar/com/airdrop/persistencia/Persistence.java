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


	public void Guardar(PcService pcService) {
		Object obj = pcService;

		File arch = new File("airdropdata");
		arch.delete();

		FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream("airdropdata");

			ObjectOutputStream obj_out = new ObjectOutputStream(fileOut);

			obj_out.writeObject(obj);

		} catch (java.io.FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void recuperarGuardado(PcService pcService) throws FileNotFoundException {

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
			
			
			throw new FileNotFoundException("el archivo no existe");
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
