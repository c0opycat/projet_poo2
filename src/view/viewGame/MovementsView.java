package view.viewGame;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import view.Keybinds;
import view.MainScene;
import view.viewLocation.LocationView;

/**
 * MovementsView is a class that represents the view of the movements in the game.
 * It contains methods to manage the movements of the hero in the level.
 * @author L. Cooper
 * @author A. Bertrand-Bernard
 */
public class MovementsView {

  private final KeyCode keybindLeft;
  private final KeyCode keybindRight;
  private final KeyCode keybindForward;
  private final KeyCode keybindBackward;
  private final LocationView locationView;
  private final EventHandler<KeyEvent> moveHandler;

  /**
   * Constructor
   * initializes the handlers for the movements of the hero
   * @param locationView the LocationView object
   */
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
        locationView.moveHero("East");
      } else if (kc == this.getKeybindRight()) {
        System.out.println("RIGHTTTTTTT");
        locationView.moveHero("West");
      } else if (kc == this.getKeybindForward()) {
        System.out.println("FORWARDDDDD");
        locationView.moveHero("North");
      } else if (kc == this.getKeybindBackward()) {
        System.out.println("BACKWARDDDD");
        locationView.moveHero("South");
      }

      e.consume();
    };
  }

  /**
   * getMoveHandler is a method that returns the moveHandler object.
   * @return EventHandler<KeyEvent> object
   */
  public EventHandler<KeyEvent> getMoveHandler() {
    return this.moveHandler;
  }

  /**
   * getLocationView is a method that returns the LocationView object.
   * @return LocationView object
   */
  public LocationView getLocationView() {
    return this.locationView;
  }

  /**
   * getKeybindLeft is a method that returns the keybind for left movement.
   * @return KeyCode object
   */
  public KeyCode getKeybindLeft() {
    return this.keybindLeft;
  }

  /**
   * getKeybindRight is a method that returns the keybind for right movement.
   * @return KeyCode object
   */
  public KeyCode getKeybindRight() {
    return this.keybindRight;
  }

  /**
   * getKeybindForward is a method that returns the keybind for forward movement.
   * @return KeyCode object
   */
  public KeyCode getKeybindForward() {
    return this.keybindForward;
  }

  /**
   * getKeybindBackward is a method that returns the keybind for backward movement.
   * @return KeyCode object
   */
  public KeyCode getKeybindBackward() {
    return this.keybindBackward;
  }

  /**
   * addHandlers is a method that adds the handlers to the scene.
   * It is used to add the event handlers for the movements of the hero.
   * @param scene the MainScene object
   * @return void
   */
  public void addHandlers(MainScene scene) {
    scene.addEventHandler(KeyEvent.KEY_PRESSED, this.getMoveHandler());
  }

  /**
   * removeHandlers is a method that removes the handlers from the scene.
   * It is used to remove the event handlers for the movements of the hero.
   * @param scene the MainScene object
   * @return void
   */
  public void removeHandlers(MainScene scene) {
    scene.removeEventHandler(KeyEvent.KEY_PRESSED, this.getMoveHandler());
  }
}
