package view;

import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

/**
 * A custom BorderPane with additional functionalities for managing buttons, titles, and background.
 * @author L. Cooper
 * @author A. Bertrand-Bernard
 */
public class BorderWithButtons extends BorderPane {

  /**
   * Constructs a new BorderWithButtons instance.
   */
  public BorderWithButtons() {
    super();
  }

  /**
   * Retrieves the MainScene associated with this BorderWithButtons.
   *
   * @return the MainScene instance of this BorderWithButtons.
   */
  public MainScene getMainScene() {
    return (MainScene) this.getScene();
  }

  /**
   * Sets the content to be displayed at the center of the BorderPane.
   *
   * @param content the Node to be set as the center content.
   */
  public void setContent(Node content) {
    BorderPane.setMargin(content, new Insets(50, 0, 0, 0));
    this.setCenter(content);
  }

  /**
   * Retrieves the content currently displayed at the center of the BorderPane.
   *
   * @return the Node currently set as the center content.
   */
  public Node getContent() {
    return this.getCenter();
  }

  /**
   * Retrieves a specific button from the bottom HBox by its text.
   *
   * @param buttonText the text of the button to retrieve.
   * @return the Button with the specified text, or null if not found.
   */
  public Button getSpecButton(String buttonText) {
    Button res = null;

    ObservableList<Node> nodes = ((HBox) this.getBottom()).getChildren();
    for (Node node : nodes) {
      Button button = (Button) node;

      if (button.getText() == buttonText) {
        res = button;
        break;
      }
    }

    if (res == null) {
      System.err.println("Error getSpecButton : No button was found.");
    }

    return res;
  }

  /**
   * Adds a title to the top of the BorderPane.
   *
   * @param title the title text to be displayed.
   */
  public void addTitle(String title, boolean isGame) {
    Label titleLabel = new Label(title);

    titleLabel.getStyleClass().add("title");

    BorderPane.setAlignment(titleLabel, Pos.CENTER);
    BorderPane.setMargin(titleLabel, new Insets(isGame ? 10 : 75, 0, 0, 0));

    this.setTop(titleLabel);
  }

  /**
   * Adds a list of buttons to the bottom of the BorderPane.
   *
   * @param buttons the list of buttons to be added.
   */
  public void addButtons(ArrayList<? extends Button> buttons) {
    HBox buttonsBox = new HBox();

    //Adding each button of the list
    for (Button button : buttons) {
      buttonsBox.getChildren().add(button);
      HBox.setMargin(button, new Insets(5));
      button.setAlignment(Pos.CENTER_RIGHT);
    }

    buttonsBox.setAlignment(Pos.BOTTOM_RIGHT);
    this.getStylesheets().add("file:./resources/style.css");

    this.setBottom(buttonsBox);
  }

  /**
   * Sets a background image for the BorderPane.
   *
   * @param bckgd the filename of the background image located in the assets folder.
   */
  public void addBackground(String bckgd) {
    String nameBackground = "file:./resources/assets/" + bckgd;
    Image backgroundImage = new Image(nameBackground);
    //width, height, widthAsPercentage, heightAsPercentage, contain, cover
    BackgroundSize backgroundSize = new BackgroundSize(
      100,
      100,
      true,
      true,
      false,
      true
    );

    BackgroundImage background = new BackgroundImage(
      backgroundImage,
      BackgroundRepeat.NO_REPEAT,
      BackgroundRepeat.NO_REPEAT,
      BackgroundPosition.CENTER,
      backgroundSize
    );
    this.setBackground(new Background(background));
  }
}
