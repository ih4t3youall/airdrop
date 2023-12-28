package ar.com.airdrop.services;

import java.io.FileInputStream;
import java.io.ObjectOutputStream;

import ar.com.airdrop.domine.Message;
import ar.com.airdrop.domine.TakeFileMessage;
import ar.com.airdrop.exceptions.SendThroughtSocketException;
import ar.com.airdrop.threads.SendMessage;

public class SendService{

	public void sendMessage(Message message) throws SendThroughtSocketException {
		SendMessage sendMessage = new SendMessage(message);
		sendMessage.start();
	}

	public void sendFile(String fichero, ObjectOutputStream oos)
	{
		try
		{
			boolean enviadoUltimo=false;
			FileInputStream fis = new FileInputStream(fichero);
			TakeFileMessage mensaje = new TakeFileMessage();
			mensaje.nombreFichero = fichero;

			// Se leen los primeros bytes del fichero en un campo del mensaje
			int leidos = fis.read(mensaje.contenidoFichero);

			// Bucle mientras se vayan leyendo datos del fichero
			while (leidos > -1)
			{

				// Se rellena el n�mero de bytes leidos
				mensaje.bytesValidos = leidos;

				// Si no se han leido el m�ximo de bytes, es porque el fichero
				// se ha acabado y este es el �ltimo mensaje
				if (leidos < TakeFileMessage.LONGITUD_MAXIMA)
				{
					mensaje.ultimoMensaje = true;
					enviadoUltimo=true;
				}
				else
					mensaje.ultimoMensaje = false;

				// Se env�a por el socket
				oos.writeObject(mensaje);

				// Si es el �ltimo mensaje, salimos del bucle.
				if (mensaje.ultimoMensaje)
					break;

				// Se crea un nuevo mensaje
				mensaje = new TakeFileMessage();
				mensaje.nombreFichero = fichero;

				// y se leen sus bytes.
				leidos = fis.read(mensaje.contenidoFichero);
			}

			if (enviadoUltimo==false)
			{
				mensaje.ultimoMensaje=true;
				mensaje.bytesValidos=0;
				oos.writeObject(mensaje);
			}
			// Se cierra el ObjectOutputStream
			oos.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
