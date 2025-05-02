package view.viewConfig;

import java.util.ArrayList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import view.BorderWithButtons;
import view.ButtonMenu;
import view.ButtonQuit;

/**
 * The ConfigView class provides a comprehensive configuration screen for the game.
 * This view integrates all configuration components including keyboard bindings,
 * screen resolution settings, and language options. It extends BorderWithButtons
 * to maintain consistent layout and navigation functionality with other game screens.
 * @author A. Bertrand-Bernard
 */
public class ConfigView extends BorderWithButtons {

  /**
   * Constructs a new ConfigView.
   * Initializes the view with a title, creates the configuration components,
   * and sets up the layout. The configuration screen includes keybind settings,
   * resolution options, and language selection.
   */
  public ConfigView() {
    super();
    this.addComp();
  }

  /**
   * Adds the configuration components to the view.
   * Creates and arranges the three main configuration panels:
   * Keyboard bindings (in a scrollable container),
   * Screen resolution options,
   * Language selection
   * These panels are arranged horizontally and centered in the view.
   */
  private void addComp() {
    this.addTitle("Configuration", false);

    HBox Hb = new HBox(60);
    Hb.setAlignment(Pos.CENTER);

    AllKeybindsConfigView kb = new AllKeybindsConfigView();
    ResolutionConfigView res = new ResolutionConfigView();
    LanguageConfigView lang = new LanguageConfigView();

    ScrollPane scrollPane = new ScrollPane(kb);
    scrollPane.setFitToWidth(true);
    scrollPane.setFitToHeight(false);
    scrollPane.setPrefHeight(500);
    scrollPane.setPrefWidth(300);
    scrollPane.setStyle(
      "-fx-background: transparent; -fx-background-color: transparent;"
    );

    Hb.getChildren().addAll(scrollPane, res, lang);

    this.setContent(Hb);
    this.addBackground("background.png");
  }

  /**
   * Sets up the navigation buttons for the configuration screen.
   * Adds a menu button to return to the main menu and a quit button
   * to exit the application. These buttons are added to the button bar
   * at the bottom of the view.
   */
  public void setButtons() {
    ArrayList<Button> buttons = new ArrayList<>();

    ButtonMenu menu = new ButtonMenu(this.getMainScene());
    ButtonQuit quit = new ButtonQuit();

    buttons.add(menu);
    buttons.add(quit);

    this.addButtons(buttons);
  }
}
