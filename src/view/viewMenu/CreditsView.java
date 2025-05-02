package view.viewMenu;

import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import view.BorderWithButtons;
import view.ButtonMenu;
import view.ButtonQuit;
import view.Lang;

/**
 * The CreditsView class displays the credits screen for the game.
 * This view shows information about the developers and the project.
 * It extends BorderWithButtons to provide a consistent layout with menu navigation.
 * @author L. Cooper
 */
public class CreditsView extends BorderWithButtons {

  /** Language handler for internationalization. */
  private Lang lang = new Lang();

  /**
   * Constructs a new CreditsView.
   * Initializes the view with a title, content area displaying developers' names,
   * navigation buttons, and a background image.
   */
  public CreditsView() {
    super();
    this.addTitle(
        getLang().getCurr_lang().equals("EN") ? "Credits" : "Crédits",
        false
      );

    this.addContent();
    this.addBackground("background.png");
  }

  /**
   * Gets the language handler for this view.
   *
   * @return The Lang object used for text internationalization
   */
  public Lang getLang() {
    return this.lang;
  }

  /**
   * Sets up the navigation buttons for the credits screen.
   * Adds a menu button to return to the main menu and a quit button
   * to exit the application.
   */
  public void setButtons() {
    ArrayList<Button> buttons = new ArrayList<>();

    ButtonMenu menu = new ButtonMenu(this.getMainScene());
    ButtonQuit quit = new ButtonQuit();

    buttons.add(menu);
    buttons.add(quit);

    this.addButtons(buttons);
  }

  /**
   * Creates and adds the main content of the credits view.
   * Displays the developers' names, project information, and university affiliation
   * in a vertically arranged layout with proper spacing and styling.
   */
  public void addContent() {
    VBox creditsBox = new VBox(20);
    creditsBox.getStyleClass().add("credits-box");
    creditsBox.setPadding(new Insets(75, 0, 20, 0));
    creditsBox.setMaxWidth(600);

    Label devLabel = new Label();
    this.getLang().setLabelLang(devLabel, "Développeurs :", "Developers:");
    devLabel.getStyleClass().add("credits-header");

    Label arthurLabel = new Label("Arthur Bertrand-Bernard");
    Label claireLabel = new Label("Claire Besançon");
    Label leilaLabel = new Label("Leïla Cooper");
    Label gabrielLabel = new Label("Gabriel Jardin");

    arthurLabel.getStyleClass().add("credits-name");
    claireLabel.getStyleClass().add("credits-name");
    leilaLabel.getStyleClass().add("credits-name");
    gabrielLabel.getStyleClass().add("credits-name");

    Region spring = new Region();
    spring.setMaxHeight(350);

    Label projectLabel = new Label();
    this.getLang()
      .setLabelLang(
        projectLabel,
        "Projet de troisième année de licence informatique - 2024/2025",
        "Third-year computer science degree project - 2024/2025"
      );
    Label univPoitersLabel = new Label();
    this.getLang()
      .setLabelLang(
        univPoitersLabel,
        "Université de Poitiers",
        "University of Poitiers"
      );

    projectLabel.getStyleClass().add("credits-footer");
    univPoitersLabel.getStyleClass().add("credits-footer");

    creditsBox
      .getChildren()
      .addAll(
        devLabel,
        arthurLabel,
        claireLabel,
        leilaLabel,
        gabrielLabel,
        spring,
        projectLabel,
        univPoitersLabel
      );

    creditsBox.setAlignment(Pos.CENTER);
    VBox.setVgrow(spring, Priority.ALWAYS);

    this.setCenter(creditsBox);
  }
}
