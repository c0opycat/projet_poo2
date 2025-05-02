package view.viewHallOfFame;

import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import view.BorderWithButtons;
import view.ButtonMenu;
import view.ButtonQuit;

/**
 * The HOFView class represents the Hall of Fame display screen.
 * This view displays the top player scores in a nicely formatted leaderboard.
 * It extends BorderWithButtons to provide a consistent layout with navigation buttons
 * and includes a title, score display, and background image.
 * @author L. Cooper
 */
public class HOFView extends BorderWithButtons {

  /**
   * Constructs a new HOFView.
   * * Initializes the view with a "Hall of Fame" title, creates and adds the
   * score display component to the center area, adds a background image,
   * and applies appropriate margins to the content.
   */
  public HOFView() {
    super();
    this.addTitle("Hall of Fame", false);

    ScoreSaveView scores = new ScoreSaveView();
    this.setCenter(scores);
    this.addBackground("background.png");

    BorderPane.setMargin(scores, new Insets(2));
  }

  /**
   * Sets up the navigation buttons for the Hall of Fame screen.
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
