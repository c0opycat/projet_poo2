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

/**
 * View class for the main game screen.
 * Manages the layout and interactions for the game, including the hero, location, commands, and game information.
 */
public class GameView extends BorderWithButtons {

  private final HeroView heroView;
  private final Keybinds keybinds;
  private final GameController gameController;
  private LocationView currentLocationView;
  private CommandsView commandsView;
  private Lang lang = new Lang();

  /**
   * Constructs a GameView with the specified hero name and job choice.
   * Initializes the game controller, hero view, and layout.
   *
   * @param name the name of the hero
   * @param jobChoice the job choice of the hero
   */
  public GameView(String name, String jobChoice) {
    super();
    this.keybinds = new Keybinds();
    this.keybinds.loadKeybinds();

    this.gameController = new GameController(this, name, jobChoice);

    this.addContent();

    this.heroView = new HeroView(this.getGameController().getHeroController());
    this.heroView.getHeroController().setGameView(this);
    this.heroView.getHeroController().updateDescription();

    if (lang.getCurr_lang().equals("EN")) {
      this.addTitle("Game");
    } else {
      this.addTitle("Jeu");
    }

    this.getGameController().start();

    this.commandsView = null;
  }

  /**
   * Sets up the buttons for the game view, including the quit button.
   */
  public void setButtons() {
    ArrayList<Button> buttons = new ArrayList<>();

    QuitView quit = new QuitView(this);

    buttons.add(quit);

    this.addButtons(buttons);
  }

  /**
   * Gets the HeroView instance associated with this game view.
   *
   * @return the HeroView instance
   */
  public HeroView getHeroView() {
    return this.heroView;
  }

  /**
   * Gets the Keybinds instance associated with this game view.
   *
   * @return the Keybinds instance
   */
  public Keybinds getKeybinds() {
    return this.keybinds;
  }

  /**
   * Gets the GameController instance associated with this game view.
   *
   * @return the GameController instance
   */
  public GameController getGameController() {
    return this.gameController;
  }

  /**
   * Gets the current LocationView instance.
   *
   * @return the current LocationView instance
   */
  public LocationView getCurrentLocationView() {
    return this.currentLocationView;
  }

  /**
   * Gets the CommandsView instance associated with this game view.
   *
   * @return the CommandsView instance
   */
  public CommandsView getCommandsView() {
    return this.commandsView;
  }

  /**
   * Gets the Lang instance for managing language settings.
   *
   * @return the Lang instance
   */
  public Lang getLang() {
    return this.lang;
  }

  /**
   * Sets the CommandsView instance for this game view.
   *
   * @param commandsView the CommandsView instance to set
   */
  public void setCommandsView(CommandsView commandsView) {
    this.commandsView = commandsView;
  }

  /**
   * Sets the current LocationView instance and updates its reference to this game view.
   *
   * @param currentLocation the current LocationView instance
   */
  public void setCurrentLocationView(LocationView currentLocation) {
    this.currentLocationView = currentLocation;
    this.getCurrentLocationView().setGameView(this);
  }

  /**
   * Updates the current location in the game by delegating to the game controller.
   */
  public void updateCurrentLocation() {
    this.getGameController().updateCurrentLocation();
  }

  /**
   * Gets the HBox containing the level layout.
   *
   * @return the HBox for the level layout
   */
  public HBox getLevelBox() {
    return (HBox) ((HBox) ((VBox) this.getCenter()).getChildren()
        .get(0)).getChildren()
      .get(0);
  }

  /**
   * Gets the VBox containing the container layout.
   *
   * @return the VBox for the container layout
   */
  public VBox getContainerBox() {
    return (VBox) ((HBox) ((VBox) this.getCenter()).getChildren()
        .get(0)).getChildren()
      .get(1);
  }

  /**
   * Gets the Label for the container.
   *
   * @return the Label for the container
   */
  public Label getContainerLabel() {
    return (Label) this.getContainerBox().getChildren().get(0);
  }

  /**
   * Gets the ContainerView instance for managing container content.
   *
   * @return the ContainerView instance
   */
  public ContainerView getContainerView() {
    return (ContainerView) this.getContainerBox().getChildren().get(1);
  }

  /**
   * Gets the Help button.
   *
   * @return the Help button
   */
  public Button getHelpButton() {
    return (Button) ((HBox) ((VBox) this.getCenter()).getChildren()
        .get(1)).getChildren()
      .get(0);
  }

  /**
   * Gets the Undo button.
   *
   * @return the Undo button
   */
  public Button getUndoButton() {
    return (Button) ((HBox) ((VBox) this.getCenter()).getChildren()
        .get(1)).getChildren()
      .get(1);
  }

  /**
   * Gets the Pause button.
   *
   * @return the Pause button
   */
  public Button getPauseButton() {
    return (Button) ((HBox) ((VBox) this.getCenter()).getChildren()
        .get(1)).getChildren()
      .get(2);
  }

  /**
   * Gets the TextArea for displaying game information.
   *
   * @return the TextArea for game information
   */
  public TextArea getGameInfos() {
    return (TextArea) ((HBox) ((VBox) this.getCenter()).getChildren()
        .get(2)).getChildren()
      .get(0);
  }

  /**
   * Gets the TextArea for displaying hero information.
   *
   * @return the TextArea for hero information
   */
  public TextArea getHeroInfos() {
    return (TextArea) ((HBox) ((VBox) this.getCenter()).getChildren()
        .get(2)).getChildren()
      .get(1);
  }

  /**
   * Adds event handlers for the game view, including movement and container interactions.
   *
   * @param scene the MainScene instance to add handlers to
   */
  public void addHandlers(MainScene scene) {
    this.setCommandsView(
        new CommandsView(currentLocationView, getContainerBox())
      );
    this.getCommandsView().addHandlers(scene);
  }

  /**
   * Removes event handlers from the game view.
   *
   * @param mainScene the MainScene instance to remove handlers from
   */
  public void removeHandlers(MainScene mainScene) {
    this.getCommandsView().removeHandlers(mainScene);
  }

  /**
   * Adds the main content layout for the game view, including the main frame, buttons, and text areas.
   */
  private void addContent() {
    VBox contentBox = new VBox();
    contentBox
      .getChildren()
      .addAll(initMainFrame(), initGameButtons(), initTextInfos());
    contentBox.setAlignment(Pos.CENTER);

    this.setContent(contentBox);
  }

  /**
   * Initializes the container view for managing container content.
   *
   * @return the initialized ContainerView instance
   */
  private ContainerView initContainer() {
    ContainerView container = new ContainerView(this);
    return container;
  }

  /**
   * Initializes the main frame layout, including the level and container sections.
   *
   * @return the initialized HBox for the main frame
   */
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

  /**
   * Initializes the game buttons, including help, undo, and pause buttons.
   *
   * @return the initialized HBox for game buttons
   */
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

  /**
   * Initializes the text areas for displaying game and hero information.
   *
   * @return the initialized HBox for text areas
   */
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
