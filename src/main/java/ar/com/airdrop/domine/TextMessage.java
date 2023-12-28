package ar.com.airdrop.domine;

public class TextMessage extends Message{
    private String textMessage;

    public TextMessage(Pc senderPc, String command, String targetIp, String textMessage) {
        super(senderPc, command, targetIp);
        this.textMessage = textMessage;
    }

    public String getTextMessage() {
        return textMessage;
    }

    public void setTextMessage(String textMessage) {
        this.textMessage = textMessage;
    }
}
