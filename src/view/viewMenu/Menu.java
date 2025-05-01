package view.viewMenu;

import java.util.ArrayList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import view.BorderWithButtons;
import view.ButtonQuit;
import view.Lang;
import view.MainScene;
import view.viewConfig.ConfigView;
import view.viewEditor.EditorView;
import view.viewGame.CharacterChoiceView;
import view.viewHallOfFame.HOFView;

/**
 * The main menu of the application.
 * Extends BorderWithButtons and provides buttons for navigation to different views.
 */
public class Menu extends BorderWithButtons {

  private Lang lang;

  /**
   * Constructs a new Menu instance.
   * Initializes the menu with a title, buttons, and components.
   */
  public Menu() {
    super();
    this.lang = new Lang();

    this.addTitle("Menu", false);
    this.setButtons();
    this.addcomp();
  }

  /**
   * Adds a quit button to the menu.
   */
  private void setButtons() {
    ArrayList<Button> buttons = new ArrayList<>();

    ButtonQuit quit = new ButtonQuit();
    buttons.add(quit);

    this.addButtons(buttons);
  }

  /**
   * Adds the main components (buttons) to the menu.
   * Configures their layout, size, and language settings.
   */
  private void addcomp() {
    VBox vb = new VBox(20);

    Button jouer = new Button();
    lang.setButtonLang(jouer, "Jouer", "Play");
    Button cfg = new Button();
    lang.setButtonLang(cfg, "Configuration", "Config");
    Button edit = new Button();
    lang.setButtonLang(edit, "Editeur", "Editor");
    Button hof = new Button();
    lang.setButtonLang(hof, "HOF", "HOF");

    //style du menu

    this.addBackground("background.png");

    //Met tous les button a la meme taille
    double buttonWidth = 150;
    double buttonHeight = 35;
    jouer.setPrefSize(buttonWidth, buttonHeight);
    cfg.setPrefSize(buttonWidth, buttonHeight);
    edit.setPrefSize(buttonWidth, buttonHeight);
    hof.setPrefSize(buttonWidth, buttonHeight);

    vb.setMaxHeight(350);
    vb.setMaxWidth(450);

    vb.getChildren().addAll(jouer, cfg, edit, hof);

    vb.setAlignment(Pos.CENTER);

    this.setCenter(vb);
  }

  /**
   * Retrieves the "Play" button from the menu.
   *
   * @return the "Play" button.
   */
  private Button getPlay() {
    return (Button) ((VBox) this.getCenter()).getChildren().get(0);
  }

  /**
   * Retrieves the "Config" button from the menu.
   *
   * @return the "Config" button.
   */
  private Button getConfig() {
    return (Button) ((VBox) this.getCenter()).getChildren().get(1);
  }

  /**
   * Retrieves the "Editor" button from the menu.
   *
   * @return the "Editor" button.
   */
  private Button getEditor() {
    return (Button) ((VBox) this.getCenter()).getChildren().get(2);
  }

  /**
   * Retrieves the "HOF" (Hall of Fame) button from the menu.
   *
   * @return the "HOF" button.
   */
  private Button getHOF() {
    return (Button) ((VBox) this.getCenter()).getChildren().get(3);
  }

  /**
   * Adds event handlers to the menu buttons for navigation to different views.
   */
  public void addHandlers() {
    MainScene scene = this.getMainScene();

    getPlay()
      .setOnAction(e -> {
        CharacterChoiceView characterChoiceView = new CharacterChoiceView();
        scene.setContent(characterChoiceView);
        characterChoiceView.setButtons();
      });

    getConfig()
      .setOnAction(e -> {
        ConfigView configView = new ConfigView();
        scene.setContent(configView);
        configView.setButtons();
      });

    getEditor()
      .setOnAction(e -> {
        EditorView editorView = new EditorView();
        scene.setContent(editorView);
        editorView.setButtons();
      });

    getHOF()
      .setOnAction(e -> {
        HOFView hofView = new HOFView();
        scene.setContent(hofView);
        hofView.setButtons();
      });
  }
}
