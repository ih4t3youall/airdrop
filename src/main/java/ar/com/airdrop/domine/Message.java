package ar.com.airdrop.domine;

public class Message {
    private Pc senderPc;
    private String command;
    private String targetIp;
    public Message(  Pc senderPc,   String command,   String targetIp) {
        this.senderPc = senderPc;
        this.command = command;
        this.targetIp = targetIp;
    }


    public Pc getSenderPc() {
        return senderPc;
    }

    public void setSenderPc(Pc senderPc) {
        this.senderPc = senderPc;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getTargetIp() {
        return targetIp;
    }

    public void setTargetIp(String targetIp) {
        this.targetIp = targetIp;
    }
}
