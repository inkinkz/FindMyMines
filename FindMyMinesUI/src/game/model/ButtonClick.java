package game.model;

import java.io.Serializable;

import javafx.scene.input.MouseEvent;

public class ButtonClick implements Serializable {

	private static final long serialVersionUID = 1L;
	private MouseEvent event;

	public ButtonClick(MouseEvent event) {
		this.event = event;
	}
	
	public MouseEvent getEvent() {
		return event;
	}

}
