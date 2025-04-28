package view.viewGame.viewCommand;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import view.Keybinds;
import view.MainScene;
import view.viewContainer.ContainerView;
import view.viewLocation.LocationView;

/**
 * MovementsView is a class that represents the view of the movements in the game.
 * It contains methods to manage the movements of the hero in the level.
 * @author L. Cooper
 * @author A. Bertrand-Bernard
 */
public class CommandsView {

  private final KeyCode keybindLeft;
  private final KeyCode keybindRight;
  private final KeyCode keybindForward;
  private final KeyCode keybindBackward;
  private final KeyCode keybindBackpack;
  private LocationView locationView;
  private final EventHandler<KeyEvent> moveHandler;

  /**
   * Constructor
   * initializes the handlers for the movements of the hero
   * @param locationView the LocationView object
   */
  public CommandsView(LocationView locationView, ContainerView containerView) {
    System.out.println(locationView);
    Keybinds keybinds = new Keybinds();
    this.keybindLeft = keybinds.getSpecKeyCode("left");
    this.keybindRight = keybinds.getSpecKeyCode("right");
    this.keybindForward = keybinds.getSpecKeyCode("forward");
    this.keybindBackward = keybinds.getSpecKeyCode("backward");
    this.keybindBackpack = keybinds.getSpecKeyCode("backpack");

    this.locationView = locationView;

    this.moveHandler = e -> {
      KeyCode kc = e.getCode();

      if (kc == this.getKeybindLeft()) {
        locationView.moveHero("East");
      } else if (kc == this.getKeybindRight()) {
        locationView.moveHero("West");
      } else if (kc == this.getKeybindForward()) {
        locationView.moveHero("North");
      } else if (kc == this.getKeybindBackward()) {
        locationView.moveHero("South");
      } else if (kc == this.getKeybindBackpack()) {
        System.out.println("Backpack");
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
   * getKeybindBackpack is a method that returns the keybind for backward movement.
   * @return KeyCode object
   */
  public KeyCode getKeybindBackpack() {
    return this.keybindBackpack;
  }

  /**
   * setLocationView is a method that returns the LocationView object.
   * @param locationView the LocationView object
   */
  public void setLocationView(LocationView locationView) {
    this.locationView = locationView;
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
