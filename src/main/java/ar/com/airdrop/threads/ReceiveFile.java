package ar.com.airdrop.threads;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JOptionPane;

import ar.com.airdrop.context.SpringContext;
import ar.com.airdrop.dominio.GiveMeFileMessage;
import ar.com.airdrop.dominio.TakeFileMessage;
import ar.com.airdrop.services.FileService;
import ar.com.commons.send.airdrop.Constantes;

public class ReceiveFile extends Thread {

	private FileService fileService = (FileService) SpringContext
			.getContext().getBean("archivoService");

	private ServerSocket socket;
	
	public void run() {

		
		try {
			socket = new ServerSocket(
					Constantes.PUERTO_ARCHIVOS);
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null, "Error con el socket");
			e1.printStackTrace();
		}
		
		while (true) {

			FileOutputStream fos;
			ObjectInputStream ois;
			Socket accept;

			try {

				// Se abre el socket.

				accept = socket.accept();

				System.out.println("recibi un archivo de la ip"
						+ accept.getInetAddress());
				String fichero = fileService.obtenerNombreArchivo();
				// TODO

				// Se envia un mensaje de peticion de fichero.
				ObjectOutputStream oos = new ObjectOutputStream(
						accept.getOutputStream());
				GiveMeFileMessage mensaje = new GiveMeFileMessage();
				mensaje.nombreFichero = fichero;
				oos.writeObject(mensaje);

				// Se abre un fichero para empezar a copiar lo que se reciba.
				fos = new FileOutputStream(fileService.getDirectorioSalvado()+"/"
						+ mensaje.nombreFichero);

				// Se crea un ObjectInputStream del socket para leer los
				// mensajes
				// que contienen el fichero.
				ois = new ObjectInputStream(accept.getInputStream());
				TakeFileMessage mensajeRecibido;
				Object mensajeAux;
				do {
					// Se lee el mensaje en una variabla auxiliar
					mensajeAux = ois.readObject();

					// Si es del tipo esperado, se trata
					if (mensajeAux instanceof TakeFileMessage) {
						mensajeRecibido = (TakeFileMessage) mensajeAux;
						// Se escribe en pantalla y en el fichero
						System.out.print(new String(
								mensajeRecibido.contenidoFichero, 0,
								mensajeRecibido.bytesValidos));
						fos.write(mensajeRecibido.contenidoFichero, 0,
								mensajeRecibido.bytesValidos);
					} else {
						// Si no es del tipo esperado, se marca error y se
						// termina
						// el bucle
						System.err.println("Mensaje no esperado "
								+ mensajeAux.getClass().getName());
						break;
					}
				} while (!mensajeRecibido.ultimoMensaje);

				// Se cierra socket y fichero

				fos.close();
				ois.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
				
				
				
			}
		}
	}


