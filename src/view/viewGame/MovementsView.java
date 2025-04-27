package view.viewGame;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import view.Keybinds;
import view.MainScene;
import view.viewLocation.LocationView;

public class MovementsView {

  private final KeyCode keybindLeft;
  private final KeyCode keybindRight;
  private final KeyCode keybindForward;
  private final KeyCode keybindBackward;
  private final LocationView locationView;
  private final EventHandler<KeyEvent> moveHandler;

  public MovementsView(LocationView locationView) {
    Keybinds keybinds = new Keybinds();
    this.keybindLeft = keybinds.getSpecKeyCode("left");
    this.keybindRight = keybinds.getSpecKeyCode("right");
    this.keybindForward = keybinds.getSpecKeyCode("forward");
    this.keybindBackward = keybinds.getSpecKeyCode("backward");

    this.locationView = locationView;

    this.moveHandler = e -> {
      System.out.println("ds l'handler");

      KeyCode kc = e.getCode();

      if (kc == this.getKeybindLeft()) {
        System.out.println("LEFTTTTTTTT");
      } else if (kc == this.getKeybindRight()) {
        System.out.println("RIGHTTTTTTT");
      } else if (kc == this.getKeybindForward()) {
        System.out.println("FORWARDDDDD");
      } else if (kc == this.getKeybindBackward()) {
        System.out.println("BACKWARDDDD");
      }

      e.consume();
    };
  }

  public EventHandler<KeyEvent> getMoveHandler() {
    return this.moveHandler;
  }

  public LocationView getLocationView() {
    return this.locationView;
  }

  public KeyCode getKeybindLeft() {
    return this.keybindLeft;
  }

  public KeyCode getKeybindRight() {
    return this.keybindRight;
  }

  public KeyCode getKeybindForward() {
    return this.keybindForward;
  }

  public KeyCode getKeybindBackward() {
    return this.keybindBackward;
  }

  public void addHandlers(MainScene scene) {
    scene.addEventHandler(KeyEvent.KEY_PRESSED, this.getMoveHandler());
  }

  public void removeHandlers(MainScene scene) {
    scene.removeEventHandler(KeyEvent.KEY_PRESSED, this.getMoveHandler());
  }
}
