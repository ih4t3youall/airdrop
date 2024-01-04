package ar.com.airdrop.domine;

public class BashCommand {

    public String command;
    public boolean withResponse;

    public BashCommand(String command, boolean withResponse) {
        this.command = command;
        this.withResponse = withResponse;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public boolean withResponse() {
        return withResponse;
    }
}
