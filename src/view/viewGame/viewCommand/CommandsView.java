package view.viewGame.viewCommand;

import java.awt.Point;
import java.util.ArrayList;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import view.Keybinds;
import view.Lang;
import view.MainScene;
import view.MyAlert;
import view.viewCharacter.HeroView;
import view.viewContainer.ContainerView;
import view.viewGame.GameView;
import view.viewGame.viewCommand.viewInteractCommand.TakeView;
import view.viewGame.viewCommand.viewItemCommand.EquipView;
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
  private final KeyCode keybindTake;
  private final KeyCode keybindEquip;
  private LocationView locationView;
  private GameView gameView;
  private final EventHandler<KeyEvent> actionsHandler;
  private boolean isBackpackOpen;
  private String curLang;

  /**
   * Constructs a CommandsView instance and initializes the handlers for the hero's movements.
   * Sets up key bindings for movement and backpack interaction.
   *
   * @param locationView the LocationView instance associated with this CommandsView
   * @param containerBox the VBox containing the container label and container view
   */
  public CommandsView(
    GameView gameView,
    LocationView locationView,
    VBox containerBox
  ) {
    Keybinds keybinds = new Keybinds();
    this.keybindLeft = keybinds.getSpecKeyCode("left");
    this.keybindRight = keybinds.getSpecKeyCode("right");
    this.keybindForward = keybinds.getSpecKeyCode("forward");
    this.keybindBackward = keybinds.getSpecKeyCode("backward");
    this.keybindBackpack = keybinds.getSpecKeyCode("backpack");
    this.keybindTake = keybinds.getSpecKeyCode("take");
    this.keybindEquip = keybinds.getSpecKeyCode("equip");
    this.isBackpackOpen = false;

    this.locationView = locationView;
    this.gameView = gameView;

    this.getLocationView().setCommandsView(this);

    Lang lang = new Lang();
    this.curLang = lang.getCurr_lang();

    this.actionsHandler = e -> {
      Label containerLabel = (Label) containerBox.getChildren().get(0);
      ContainerView containerView = (ContainerView) containerBox
        .getChildren()
        .get(1);

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
        this.toggleViewBackack(containerLabel, containerView);
      } else if (kc == this.getKeybindTake()) {
        this.takeAction();
      } else if (kc == this.getKeybindEquip()) {
        this.equipAction();
      } else {
        return;
      }

      e.consume();
    };
  }

  /**
   * getMoveHandler is a method that returns the moveHandler object.
   * @return EventHandler<KeyEvent> object
   */
  public EventHandler<KeyEvent> getActionsHandler() {
    return this.actionsHandler;
  }

  /**
   * getLocationView is a method that returns the LocationView object.
   * @return LocationView object
   */
  public LocationView getLocationView() {
    return this.locationView;
  }

  public GameView getGameView() {
    return this.gameView;
  }

  public String getLang() {
    return this.curLang;
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

  public KeyCode getKeybindTake() {
    return this.keybindTake;
  }

  public KeyCode getKeybindEquip() {
    return this.keybindEquip;
  }

  /**
   * getKeybindBackpack is a method that returns the keybind for backward movement.
   * @return KeyCode object
   */
  public KeyCode getKeybindBackpack() {
    return this.keybindBackpack;
  }

  /**
   * getIsContainerOpen is a method that returns true if the content of a container is being displayed, false otherwise
   * @return boolean the value of isContainerOpen
   */
  public boolean getIsBackpackOpen() {
    return this.isBackpackOpen;
  }

  /**
   * setIsContainerView is a method that set the isContainerView variable.
   * @param boolean the value of isContainerView
   */
  public void setIsBackpackOpen(boolean b) {
    this.isBackpackOpen = b;
  }

  /**
   * setLocationView is a method that set the LocationView object.
   * @param locationView the LocationView object
   */
  public void setLocationView(LocationView locationView) {
    this.locationView = locationView;
    this.setIsBackpackOpen(false);
  }

  /**
   * addHandlers is a method that adds the handlers to the scene.
   * It is used to add the event handlers for the movements of the hero.
   * @param scene the MainScene object
   * @return void
   */
  public void addHandlers(MainScene scene) {
    scene.addEventHandler(KeyEvent.KEY_PRESSED, this.getActionsHandler());
  }

  /**
   * removeHandlers is a method that removes the handlers from the scene.
   * It is used to remove the event handlers for the movements of the hero.
   * @param scene the MainScene object
   * @return void
   */
  public void removeHandlers(MainScene scene) {
    scene.removeEventHandler(KeyEvent.KEY_PRESSED, this.getActionsHandler());
  }

  public void toggleViewBackack(
    Label containerLabel,
    ContainerView containerView
  ) {
    if (!this.getIsBackpackOpen()) {
      if (!this.getLocationView().getIsContainerOpen()) {
        containerView.getContainerController().setContainerModel(null);
        this.setIsBackpackOpen(true);
        this.getLocationView().setIsContainerOpen(true);
        containerView.getChildren().clear();
        containerView.addItemList(
          true,
          containerView.getContainerController().getBackPackContent()
        );
        containerLabel.setText(
          this.getLang().equals("EN") ? "Your backpack" : "Votre sac à dos"
        );
      }
    } else {
      containerView.getContainerController().setContainerModel(null);
      this.setIsBackpackOpen(false);
      this.getLocationView().setIsContainerOpen(false);
      containerView.getChildren().clear();
      containerLabel.setText(null);
    }
  }

  public void takeAction() {
    HeroView heroView = this.getGameView().getHeroView();
    int heroX = (int) heroView.getActualCoord().getX();
    int heroY = (int) heroView.getActualCoord().getY();

    ArrayList<String> items = locationView.getItemsBesidesHero(heroX, heroY);

    if (items.size() != 0) {
      ArrayList<String> buttonTypesText = new ArrayList<>();

      for (String item : items) {
        buttonTypesText.add(item);
      }

      String title = curLang.equals("EN")
        ? "Choose the item to take"
        : "Choisissez l'object à prendre";
      String content = curLang.equals("EN") ? "The item:" : "L'objet :";
      MyAlert choiceAlert = new MyAlert(title, null, content);

      String choice = choiceAlert.showChoiceAlert(
        choiceAlert.createButtonTypes(buttonTypesText)
      );

      int x = heroX;
      int y = heroY;

      if (choice.equals("left") || choice.equals("gauche")) {
        x--;
      } else if (choice.equals("above") || choice.equals("au-dessus")) {
        y--;
      } else if (choice.equals("right") || choice.equals("droite")) {
        x++;
      } else {
        y++;
      }

      Point toTake = new Point(x, y);

      TakeView takeView = new TakeView(gameView);
      takeView.getTakeController().setTakeModel();
      if (takeView.getTakeController().execute(toTake)) {
        getLocationView().removeItem(toTake);
      }
    } else {
      String title = curLang.equals("EN") ? "Error" : "Erreur";
      String content = curLang.equals("EN")
        ? "There is no item to take besides you."
        : "Il n'y a aucun item à prendre à côté de vous.";
      MyAlert errorAlert = new MyAlert(title, null, content);
      errorAlert.showInformation();
    }
  }

  public void equipAction() {
    HeroView heroView = this.getGameView().getHeroView();
    int heroX = (int) heroView.getActualCoord().getX();
    int heroY = (int) heroView.getActualCoord().getY();

    ArrayList<String> items = locationView.getItemsBesidesHero(heroX, heroY);

    if (items.size() != 0) {
      ArrayList<String> buttonTypesText = new ArrayList<>();

      for (String item : items) {
        buttonTypesText.add(item);
      }

      String title = curLang.equals("EN")
        ? "Choose the item to equip"
        : "Choisissez l'object à équiper";
      String content = curLang.equals("EN") ? "The item:" : "L'objet :";
      MyAlert choiceAlert = new MyAlert(title, null, content);

      String choice = choiceAlert.showChoiceAlert(
        choiceAlert.createButtonTypes(buttonTypesText)
      );

      int x = heroX;
      int y = heroY;

      if (choice.equals("left") || choice.equals("gauche")) {
        x--;
      } else if (choice.equals("above") || choice.equals("au-dessus")) {
        y--;
      } else if (choice.equals("right") || choice.equals("droite")) {
        x++;
      } else {
        y++;
      }

      Point toEquip = new Point(x, y);

      EquipView equipView = new EquipView(gameView);
      equipView.getEquipController().setEquipModel(2, "7");
      if (equipView.getEquipController().execute(toEquip)) {
        getLocationView().removeItem(toEquip);
        this.getGameView()
          .getHeroView()
          .getHeroController()
          .updateDescription();
      }
    } else {
      String title = curLang.equals("EN") ? "Error" : "Erreur";
      String content = curLang.equals("EN")
        ? "There is no item to equip besides you."
        : "Il n'y a aucun item à équiper à côté de vous.";
      MyAlert errorAlert = new MyAlert(title, null, content);
      errorAlert.showInformation();
    }
  }
}
