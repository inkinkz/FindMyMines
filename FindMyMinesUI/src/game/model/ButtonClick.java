package game.model;

import java.io.Serializable;

public class ButtonClick implements Serializable {

    protected static final long serialVersionUID = 10L;
    // Use READDY instead of READY to distinguish it from NOTREADY when using contains("NOTREADY")
    public static final int WHOISIN = 0, CLICK = 1, LOGOUT = 2, READDY = 4, NOTREADY = 5, TRIGGER_END = 6;
    private int type;
    private String message;


    private String currentLeadingPlayer;

    public ButtonClick(int type) {
        this.type = type;
    }

    public ButtonClick(int type, String message) {
        this.type = type;
        this.message = message;
    }

    public ButtonClick(int type, String message, String currentLeadingPlayer) {
        this.type = type;
        this.message = message;
        this.currentLeadingPlayer = currentLeadingPlayer;
    }

    public String getMessage() {
        return message;
    }

    public int getType() {
        return type;
    }

    public String getCurrentLeadingPlayer() {
        return currentLeadingPlayer;
    }

    public void setCurrentLeadingPlayer(String currentLeadingPlayer) {
        this.currentLeadingPlayer = currentLeadingPlayer;
    }
}
