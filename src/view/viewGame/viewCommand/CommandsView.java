package view.viewGame.viewCommand;

import controller.controllerGame.controllerCommand.controllerInterractCommand.AttackController;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
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
import view.viewCharacter.MonsterView;
import view.viewContainer.ContainerView;
import view.viewGame.GameView;
import view.viewGame.viewCommand.viewInteractCommand.TakeView;
import view.viewGame.viewCommand.viewItemCommand.EquipView;
import view.viewLocation.ExitView;
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
  private final KeyCode keybindGo;
  private final KeyCode keybindAttack;
  private LocationView locationView;
  private AttackController attackController;
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
    this.keybindGo = keybinds.getSpecKeyCode("go");
    this.keybindAttack = keybinds.getSpecKeyCode("attack");
    this.isBackpackOpen = false;

    this.locationView = locationView;
    this.gameView = gameView;
    this.attackController = new AttackController(this.getGameView());

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
        this.getLocationView().moveHero("East");
      } else if (kc == this.getKeybindRight()) {
        this.getLocationView().moveHero("West");
      } else if (kc == this.getKeybindForward()) {
        this.getLocationView().moveHero("North");
      } else if (kc == this.getKeybindBackward()) {
        this.getLocationView().moveHero("South");
      } else if (kc == this.getKeybindBackpack()) {
        this.toggleViewBackack(containerLabel, containerView);
      } else if (kc == this.getKeybindTake()) {
        this.takeAction();
      } else if (kc == this.getKeybindEquip()) {
        this.equipAction();
      } else if (kc == this.getKeybindGo()) {
        this.goAction();
      } else if (kc == this.getKeybindAttack()) {
        this.attackAction();
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

  public AttackController getAttackController() {
    return this.attackController;
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

  /**
   * getKeybindtakes is a method that returns the keybind for the take action.
   * @return KeyCode object
   */
  public KeyCode getKeybindTake() {
    return this.keybindTake;
  }

  /**
   * getKeybindEquip is a method that returns the keybind for the equip action.
   * @return KeyCode object
   */
  public KeyCode getKeybindEquip() {
    return this.keybindEquip;
  }

  /**
   * getKeybindGo is a method that returns the keybind for the go action.
   * @return KeyCode object
   */
  public KeyCode getKeybindGo() {
    return this.keybindGo;
  }

  /**
   * getKeybindAttack is a method that returns the keybind for the attack action.
   * @return KeyCode object
   */
  public KeyCode getKeybindAttack() {
    return this.keybindAttack;
  }

  /**
   * getKeybindBackpack is a method that returns the keybind to open the hero's backpack.
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

    ArrayList<String> items =
      this.getLocationView().getItemsBesidesHero(heroX, heroY);

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

      if (choice != null) {
        if (choice.equals("left") || choice.equals("gauche")) {
          x--;
        } else if (choice.equals("above") || choice.equals("au-dessus")) {
          y--;
        } else if (choice.equals("right") || choice.equals("droit")) {
          x++;
        } else if (choice.equals("below") || choice.equals("en dessous")) {
          y++;
        }

        Point toTake = new Point(x, y);

        TakeView takeView = new TakeView(gameView);
        takeView.getTakeController().setTakeModel();
        if (takeView.getTakeController().execute(toTake)) {
          getLocationView().removeItem(toTake);

          if (
            this.getLocationView().getMonsterView() != null &&
            this.getLocationView().getMonsterView().isInAttackRange()
          ) {
            this.getLocationView().getMonsterView().attack();
          }
        }
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

    ArrayList<String> items =
      this.getLocationView().getItemsBesidesHero(heroX, heroY);

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

      if (choice != null) {
        if (choice.equals("left") || choice.equals("gauche")) {
          x--;
        } else if (choice.equals("above") || choice.equals("au-dessus")) {
          y--;
        } else if (choice.equals("right") || choice.equals("droit")) {
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
          this.getLocationView().updateItems();

          if (
            this.getLocationView().getMonsterView() != null &&
            this.getLocationView().getMonsterView().isInAttackRange()
          ) {
            this.getLocationView().getMonsterView().attack();
          }
        }
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

  public void goAction() {
    String newLine = System.getProperty("line.separator");

    HeroView heroView = this.getGameView().getHeroView();
    int heroX = (int) heroView.getActualCoord().getX();
    int heroY = (int) heroView.getActualCoord().getY();

    LocationView locationView = this.getLocationView();
    HashMap<String, String> exits = locationView.getExitsBesidesHero(
      heroX,
      heroY
    );

    if (exits.size() != 0) {
      ArrayList<String> buttonTypesText = new ArrayList<>();

      String content = "";

      for (String exit : exits.keySet()) {
        content += exits.get(exit) + " : " + exit + newLine;
        buttonTypesText.add(exits.get(exit));
      }

      String title = curLang.equals("EN")
        ? "Choose the exit to take"
        : "Choisissez la sortie à prendre";
      String header = curLang.equals("EN") ? "The exit:" : "La sortie :";

      MyAlert choiceAlert = new MyAlert(title, header, content);

      String choice = choiceAlert.showChoiceAlert(
        choiceAlert.createButtonTypes(buttonTypesText)
      );

      int x = heroX;
      int y = heroY;

      if (choice != null) {
        if (choice.equals("left") || choice.equals("gauche")) {
          x--;
        } else if (choice.equals("above") || choice.equals("au-dessus")) {
          y--;
        } else if (choice.equals("right") || choice.equals("droite")) {
          x++;
        } else {
          y++;
        }

        ExitView exitView = (ExitView) locationView
          .getCell(x, y)
          .getImageView();
        exitView.setGameView(this.getGameView());
        if (exitView.getExitController().go()) {
          this.getGameView().updateCurrentLocation();
        }
      }
    } else {
      String title = curLang.equals("EN") ? "Error" : "Erreur";
      String content = curLang.equals("EN")
        ? "There is no exit to take besides you."
        : "Il n'y a aucune sortie à prendre à côté de vous.";
      MyAlert errorAlert = new MyAlert(title, null, content);
      errorAlert.showInformation();
    }
  }

  public void attackAction() {
    HeroView heroView = this.getGameView().getHeroView();

    MonsterView monsterView =
      this.getGameView().getCurrentLocationView().getMonsterView();

    if (monsterView != null) {
      if (heroView.isInAttackRange(monsterView)) {
        if (this.getAttackController().execute()) {
          if (monsterView.getMonsterController().isKo()) {
            this.getGameView().getMonsterInfos().clear();
            this.getLocationView().removeMonster();
          } else {
            monsterView.getMonsterController().updateMonsterDescription();
          }

          if (
            this.getLocationView().getMonsterView() != null &&
            this.getLocationView().getMonsterView().isInAttackRange()
          ) {
            this.getLocationView().getMonsterView().attack();
          }
        }
      }
    }
  }
}
