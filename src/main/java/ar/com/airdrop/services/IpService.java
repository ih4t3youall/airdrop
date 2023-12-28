package ar.com.airdrop.services;


import ar.com.airdrop.domine.Pc;
import ar.com.airdrop.exceptions.ServiceException;

import java.net.InetAddress;
import java.net.UnknownHostException;



public class IpService {

    private String hostAddress ;
    private String hostName ;

    public Pc getIp() throws ServiceException {
        if (hostAddress == null){
            InetAddress localHost = null;
            try {
                localHost = InetAddress.getLocalHost();
            } catch (UnknownHostException e1) {
                throw new ServiceException("Error al obtener la ip" ,e1);
            }
            hostAddress = localHost.getHostAddress();
            hostName = localHost.getHostName();
        }
        Pc pc = new Pc(hostAddress);
        pc.setPcName(hostName);
        return pc;

    }

    public String obtenerNombrePc() throws ServiceException{
        return hostName;
    }
}

