package ar.com.airdrop.threads;

import ar.com.airdrop.context.Constants;
import ar.com.airdrop.context.SpringContext;
import ar.com.airdrop.domine.Message;
import ar.com.airdrop.domine.Pc;
import ar.com.airdrop.exceptions.ServerSocketReceivingException;
import ar.com.airdrop.services.FileService;
import ar.com.airdrop.services.PcService;
import ar.com.airdrop.services.SendService;
import ar.com.airdrop.vistas.MainMenu;
import ar.com.airdrop.vistas.ReceivePromptMessageView;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ReceiveMessage extends Thread {

    private PcService pcService = (PcService) SpringContext.getContext()
            .getBean("pcService");
    private SendService sendService = (SendService) SpringContext
            .getContext().getBean("sendService");
    private FileService fileService = (FileService) SpringContext
            .getContext().getBean("archivoService");

    private static int PUERTO = Constants.PORT;
    private MainMenu mainMenu;

    public ReceiveMessage(MainMenu mainMenu)
            throws ServerSocketReceivingException {
        this.mainMenu = mainMenu;

    }

    public void run() {

        while (true) {
            ServerSocket socket = null;

            try {
                socket = new ServerSocket(PUERTO);
            } catch (IOException e) {
                String error = "Error al cear el socket";
                System.out.println(error);
            }
            System.out.println("Esperando envio....");

            try {
                Socket cliente = socket.accept();

                String ipOtroCliente = cliente.getInetAddress()
                        .getHostAddress();
                System.out.println("Conectado con cliente de " + ipOtroCliente);

                cliente.setSoLinger(true, 10);

                ObjectInputStream buffer = new ObjectInputStream(
                        cliente.getInputStream());

                String json = (String) buffer.readObject();
                Message messageReceived = new Gson().fromJson(json,Message.class);
                if (messageReceived.getCommand().equals("who")) {

                    //TODO pc service no funciona todavia
                    Pc pcResponse = pcService.getLocalPc();

                    pcService.addPcExterna(messageReceived.getSenderPc());
                    //TODO la pc esta nula
                    Message mensajeRespuesta = new Message(pcResponse,"autenticar",ipOtroCliente,null,null);
                    sendService.sendMessage(mensajeRespuesta);

                }
                if (messageReceived.getCommand().equals("autenticar")) {

                    System.out.println("Recibi una pc de la ip : "
                            + ipOtroCliente);

                    Pc pc = messageReceived.getSenderPc();
                    pcService.addPcExterna(pc);

                }

                if (messageReceived.getCommand().equals("mensajePrompt")) {

                    new ReceivePromptMessageView((Message)messageReceived);

                }

                //if (mensaje.getComando().equals("archivo")) {

                //    JFileChooser jfc = new JFileChooser();
                //    jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                //    if (jfc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
                //        fileService.archivoARecibir(mensaje);
                //        fileService.setDirectorioSalvado(jfc.getSelectedFile().getAbsolutePath());


                //        Mensaje mensajeRespuesta = new Mensaje(
                //                pcService.getPcLocal());
                //        mensajeRespuesta.setIpDestino(ipOtroCliente);
                //        mensajeRespuesta.setComando("okArchivo");
                //        mensajeRespuesta.setNombreArchivo(mensaje
                //                .getNombreArchivo());
                //        sendService.sendMessage(mensajeRespuesta);
                //    }

                //}
                //if (mensaje.getComando().equals("bash")){

                //    Process p = Runtime.getRuntime().exec(mensaje.getMensaje());

                //    if (mensaje.isRespuesta()){
                //        BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
                //        BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
                //        String s = null;
                //        String respuestaComando = "";
                //        // Leemos la salida del comando y la desplegamos en el jTextArea
                //        while ((s = stdInput.readLine()) != null ) {
                //            System.out.println(s);
                //            respuestaComando +=s+"\n";
                //        }
                //        stdInput.close();
                //        stdError.close();

                //        Mensaje mensajeRespuesta = new Mensaje(
                //                pcService.getPcLocal());
                //        mensajeRespuesta.setIpDestino(ipOtroCliente);
                //        mensajeRespuesta.setComando("respuestaComando");
                //        mensajeRespuesta.setMensaje(respuestaComando);
                //        sendService.sendMessage(mensajeRespuesta);
                //    }

                //}


                //if (mensaje.getComando().equals("respuestaComando")){

                //    new CommandResponseView(mensaje);

                //}


                //if (mensaje.getComando().equals("okArchivo")) {

                //    Mensaje archivoAEviar = fileService
                //            .obtenerArchivoAEviar();

                //    Socket socketEnviarArch = new Socket(
                //            archivoAEviar.getIpDestino(),
                //            Constantes.PUERTO_ARCHIVOS);

                //    sendService.sendFile(archivoAEviar.getFile()
                //            .getAbsolutePath(), new ObjectOutputStream(
                //            socketEnviarArch.getOutputStream()));



                //}


                this.mainMenu.cargarLista();

            } catch (Exception e) {

                String error = "Error con el serversocket en el puerto : "
                        + PUERTO;
                System.out.println(error);

            } finally {

                try {
                    if (socket != null) {
                        socket.close();
                    }
                } catch (IOException e) {
                    System.err.println("Error al cerrar el serversocket.");

                }

            }
        }

    }

    public PcService getPcService() {
        return pcService;
    }

    public void setPcService(PcService pcService) {
        this.pcService = pcService;
    }

    public SendService getSendService() {
        return sendService;
    }

    public void setSendService(SendService sendService) {
        this.sendService = sendService;
    }

}