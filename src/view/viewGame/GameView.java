package view.viewGame;

import controller.controllerGame.GameController;
import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import model.item.Crowbar;
import model.item.Item;
import model.item.Protection;
import model.item.consumable.Medicine;
import view.BorderWithButtons;
import view.ButtonMenu;
import view.viewContainer.ContainerView;
import view.viewGame.viewCommand.viewMenuCommand.HelpView;
import view.viewGame.viewCommand.viewMenuCommand.QuitView;
import view.viewLocation.LocationView;

public class GameView extends BorderWithButtons {

  private final GameController gameController;

  public GameView() {
    super();
    this.gameController = new GameController(this);

    this.addTitle("Game");

    this.addContent();

    this.getGameController().start();
  }

  //Set the bottom of the pane with buttons
  public void setButtons() {
    ArrayList<Button> buttons = new ArrayList<>();

    ButtonMenu menu = new ButtonMenu(this.getMainScene());
    //SaveView save = new SaveView(); //??? Ce n'est précisé nul part qu'il faut pouvoir save/load une game.
    QuitView quit = new QuitView(this);

    buttons.add(menu);
    //buttons.add(save);
    buttons.add(quit);

    this.addButtons(buttons);
  }

  public GameController getGameController() {
    return this.gameController;
  }

  public LocationView getLocationView() {
    return (LocationView) ((HBox) ((VBox) this.getCenter()).getChildren()
        .get(0)).getChildren()
      .get(0);
  }

  public GridPane getContainersContent() {
    return (GridPane) ((HBox) ((VBox) this.getCenter()).getChildren()
        .get(0)).getChildren()
      .get(1);
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

  //Add every informations about the game at the center of the pane
  private void addContent() {
    VBox contentBox = new VBox();

    HBox mainGame = new HBox(20);
    mainGame.getChildren().addAll(initGridPanes(), initContainer());
    mainGame.setAlignment(Pos.CENTER);

    contentBox
      .getChildren()
      .addAll(mainGame, initGameButtons(), initTextInfos());
    contentBox.setAlignment(Pos.CENTER);

    this.setContent(contentBox);
  }

  private VBox initContainer() {
    ContainerView container = new ContainerView();

    ArrayList<Item> test = new ArrayList<>();

    test.add(new Crowbar());
    test.add(new Protection());
    test.add(new Medicine());

    container.addItemList(test);

    return container;
  }

  //Returns the view of the level and the one that will display the containers' content
  private HBox initGridPanes() {
    HBox gridPanesBox = new HBox(10);

    LocationView level = new LocationView();
    GridPane containersContent = new GridPane();

    //a supp
    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 6; j++) {
        containersContent.add(new Label("(" + i + "," + j + ")"), i, j);
      }
    }

    gridPanesBox.getChildren().addAll(level, containersContent);

    gridPanesBox.setAlignment(Pos.CENTER);
    gridPanesBox.setPadding(new Insets(10));

    return gridPanesBox;
  }

  //Returns the "help", "undo" and "pause" buttons
  private HBox initGameButtons() {
    HBox gameButtonsBox = new HBox(20);

    HelpView helpButton = new HelpView(this);
    Button undoButton = new Button("Undo");
    Button pause = new Button("Pause");

    Region spring = new Region();

    gameButtonsBox.getChildren().addAll(helpButton, undoButton, spring, pause);

    HBox.setHgrow(spring, Priority.ALWAYS);
    gameButtonsBox.setAlignment(Pos.CENTER);
    gameButtonsBox.setPadding(new Insets(10));

    return gameButtonsBox;
  }

  //Returns the textual informations of the game and the hero's characteristics
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
