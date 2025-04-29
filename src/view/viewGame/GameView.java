package view.viewGame;

import controller.controllerGame.GameController;
import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import view.BorderWithButtons;
import view.Keybinds;
import view.Lang;
import view.MainScene;
import view.viewCharacter.HeroView;
import view.viewContainer.ContainerView;
import view.viewGame.viewCommand.CommandsView;
import view.viewGame.viewCommand.viewMenuCommand.HelpView;
import view.viewGame.viewCommand.viewMenuCommand.QuitView;
import view.viewLocation.LocationView;

public class GameView extends BorderWithButtons {

  private final HeroView heroView;
  private final Keybinds keybinds;
  private final GameController gameController;
  private LocationView currentLocationView;
  private CommandsView commandsView;
  private Lang lang = new Lang();

  public GameView(String name, String jobChoice) {
    super();
    this.keybinds = new Keybinds();
    this.keybinds.loadKeybinds();

    this.gameController = new GameController(this, name, jobChoice);

    this.addContent();

    this.heroView = new HeroView(this.getGameController().getHeroController());

    if (lang.getCurr_lang().equals("EN")) {
      this.addTitle("Game");
    } else {
      this.addTitle("Jeu");
    }

    this.getGameController().start();

    this.commandsView = null;
  }

  //Set the bottom of the pane with buttons
  public void setButtons() {
    ArrayList<Button> buttons = new ArrayList<>();

    QuitView quit = new QuitView(this);

    buttons.add(quit);

    this.addButtons(buttons);
  }

  public HeroView getHeroView() {
    return this.heroView;
  }

  public Keybinds getKeybinds() {
    return this.getKeybinds();
  }

  public GameController getGameController() {
    return this.gameController;
  }

  public LocationView getCurrentLocationView() {
    return this.currentLocationView;
  }

  public CommandsView getCommandsView() {
    return this.commandsView;
  }

  public void setCommandsView(CommandsView commandsView) {
    this.commandsView = commandsView;
  }

  public void setCurrentLocationView(LocationView currentLocation) {
    this.currentLocationView = currentLocation;
    this.getCurrentLocationView().setGameView(this);
  }

  public void updateCurrentLocation() {
    this.getGameController().updateCurrentLocation();
  }

  public HBox getLevelBox() {
    return (HBox) ((HBox) ((VBox) this.getCenter()).getChildren()
        .get(0)).getChildren()
      .get(0);
  }

  public VBox getContainerBox() {
    return (VBox) ((HBox) ((VBox) this.getCenter()).getChildren()
        .get(0)).getChildren()
      .get(1);
  }

  public Label getContainerLabel() {
    return (Label) this.getContainerBox().getChildren().get(0);
  }

  public ContainerView getContainerView() {
    return (ContainerView) this.getContainerBox().getChildren().get(1);
  }

  public Button getHelpButton() {
    return (Button) ((HBox) ((VBox) this.getCenter()).getChildren()
        .get(1)).getChildren()
      .get(0);
  }

  public Button getUndoButton() {
    return (Button) ((HBox) ((VBox) this.getCenter()).getChildren()
        .get(1)).getChildren()
      .get(1);
  }

  public Button getPauseButton() {
    return (Button) ((HBox) ((VBox) this.getCenter()).getChildren()
        .get(1)).getChildren()
      .get(2);
  }

  public TextArea getGameInfos() {
    return (TextArea) ((HBox) ((VBox) this.getCenter()).getChildren()
        .get(2)).getChildren()
      .get(0);
  }

  public TextArea getHeroInfos() {
    return (TextArea) ((HBox) ((VBox) this.getCenter()).getChildren()
        .get(2)).getChildren()
      .get(1);
  }

  /**
   * addHandlers is a method that adds the handlers to the MovementsView object.
   * its use for the manage the movements of the hero in the level.
   * @param scene the MainScene object
   */
  public void addHandlers(MainScene scene) {
    this.setCommandsView(
        new CommandsView(currentLocationView, getContainerBox())
      );
    this.getCommandsView().addHandlers(scene);
  }

  /**
   * removeHandlers is a method that removes the handlers from the MovementsView object.
   * its use for remove the handlers at the end of the game.
   * @param mainScene the MainScene object
   */
  public void removeHandlers(MainScene mainScene) {
    this.getCommandsView().removeHandlers(mainScene);
  }

  //Add every informations about the modelGame at the center of the pane
  private void addContent() {
    VBox contentBox = new VBox();
    contentBox
      .getChildren()
      .addAll(initMainFrame(), initGameButtons(), initTextInfos());
    contentBox.setAlignment(Pos.CENTER);

    this.setContent(contentBox);
  }

  private ContainerView initContainer() {
    ContainerView container = new ContainerView(this);
    return container;
  }

  //Returns the view of the level and the one that will display the containers' content
  private HBox initMainFrame() {
    HBox gridPanesBox = new HBox(20);

    HBox levelBox = new HBox();

    VBox containerBox = new VBox(10);

    Label containerLabel = new Label();
    ContainerView containersContent = initContainer();

    containerBox.getChildren().addAll(containerLabel, containersContent);

    gridPanesBox.getChildren().addAll(levelBox, containerBox);

    gridPanesBox.setAlignment(Pos.CENTER);
    gridPanesBox.setPadding(new Insets(10));

    return gridPanesBox;
  }

  //Returns the "help", "undo" and "pause" buttons
  private HBox initGameButtons() {
    HBox gameButtonsBox = new HBox(20);

    HelpView helpButton = new HelpView(this);
    Button undoButton = new Button();
    lang.setButtonLang(undoButton, "Retour", "Undo");
    Button pause = new Button("Pause");

    Region spring = new Region();

    gameButtonsBox.getChildren().addAll(helpButton, undoButton, spring, pause);

    HBox.setHgrow(spring, Priority.ALWAYS);
    gameButtonsBox.setAlignment(Pos.CENTER);
    gameButtonsBox.setPadding(new Insets(10));

    return gameButtonsBox;
  }

  //Returns the textual informations of the modelGame and the hero's characteristics
  private HBox initTextInfos() {
    HBox textInfosBox = new HBox(10);

    TextArea gameInfos = new TextArea();
    gameInfos.setEditable(false);
    gameInfos.setMinWidth(768);
    gameInfos.setWrapText(true);

    TextArea heroInfos = new TextArea();
    heroInfos.setEditable(false);
    heroInfos.setWrapText(true);

    textInfosBox.getChildren().addAll(gameInfos, heroInfos);

    textInfosBox.setAlignment(Pos.CENTER);
    textInfosBox.setPadding(new Insets(10));

    return textInfosBox;
  }
}
