package ar.com.airdrop.domine;

public class Message {
    private Pc senderPc;
    private String command;
    private String targetIp;
    private String payload;
    private String payloadType;
    public Message(  Pc senderPc,   String command,   String targetIp, String payload, String payloadType) {
        this.senderPc = senderPc;
        this.command = command;
        this.targetIp = targetIp;
        this.payload = payload;
        this.payloadType = payloadType;
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

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public String getPayloadType() {
        return payloadType;
    }

    public void setPayloadType(String payloadType) {
        this.payloadType = payloadType;
    }
}
