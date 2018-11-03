package game.model;

import java.io.Serializable;

public class ButtonClick implements Serializable {

    protected static final long serialVersionUID = 10L;
    public static final int WHOISIN = 0, CLICK = 1, LOGOUT = 2, READY = 4, NOTREADY = 5;
    private int type;
    private String message;

    public ButtonClick(int type, String message) {
        this.type = type;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public int getType() {
        return type;
    }

}
