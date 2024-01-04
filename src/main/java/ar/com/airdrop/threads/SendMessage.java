package ar.com.airdrop.threads;

import ar.com.airdrop.context.Constants;
import ar.com.airdrop.domine.Message;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;



public class SendMessage extends Thread{

    public Message message;
    private final int PORT = Constants.PORT;

    public SendMessage(Message mensaje){
        this.message = mensaje;
    }

    public void run(){



        Socket socket = null;
        ObjectOutputStream buffer = null;

        try {
            socket = new Socket(message.getTargetIp(), PORT);
            buffer = new ObjectOutputStream(socket.getOutputStream());
            String json =  new Gson().toJson(message);
            buffer.writeObject(json);

        } catch (Exception e) {

            String error = "Error con el socket al acceder al puerto : ";
            e.printStackTrace();

        } finally {

            if (socket != null) {

                try {
                    socket.close();
                } catch (IOException e) {
                    String error = "Error al cerrar el socket : ";
                    e.printStackTrace();
                }

            }

        }
    }

}





