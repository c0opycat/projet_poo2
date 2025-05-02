package view.viewConfig;

import java.io.FileWriter;
import java.io.IOException;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.json.JSONObject;
import view.Lang;
import view.MyAlert;

/**
 * The LanguageConfigView class provides an interface for changing the game's language.
 * This view displays language options (English and French) as buttons that the player
 * can select to change the game's interface language. The selected language preference
 * is saved to a configuration file for persistence between game sessions.
 * @author A. Bertrand-Bernard
 */
public class LanguageConfigView extends VBox {

  /** Language handler for internationalization support. */
  private Lang lang = new Lang();

  /**
   * Constructs a new LanguageConfigView.
   * Initializes the view with vertical spacing of 20 pixels between elements
   * and adds the language configuration components to the view.
   */
  public LanguageConfigView() {
    super(20);
    this.addComp();
  }

  /**
   * Adds the language configuration components to the view.
   * Creates and configures a title label and buttons for each supported language
   * (English and French). Sets up action handlers for the buttons to save the
   * selected language when clicked.
   */
  private void addComp() {
    Label title = new Label();
    lang.setLabelLang(title, "Langue", "Language");
    title.getStyleClass().add("under-title");

    double buttonWidth = 150;
    double buttonHeight = 35;

    Button englishButton = new Button();
    lang.setButtonLang(englishButton, "Anglais", "English");
    englishButton.setOnAction(e -> {
      saveLanguage("EN");
    });

    Button frenchButton = new Button();
    lang.setButtonLang(frenchButton, "Français", "French");
    frenchButton.setOnAction(e -> {
      saveLanguage("FR");
    });

    englishButton.setPrefSize(buttonWidth, buttonHeight);
    frenchButton.setPrefSize(buttonWidth, buttonHeight);

    this.getChildren().addAll(title, englishButton, frenchButton);
    this.setAlignment(Pos.TOP_CENTER);
  }

  /**
   * Saves the selected language preference to a configuration file.
   * Creates a JSON object with the language code and writes it to the
   * language configuration file. Displays a confirmation message if successful
   * or an error message if the save operation fails.
   *
   * @param language the language code to save ("EN" for English, "FR" for French)
   */
  public void saveLanguage(String language) {
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("language", language);
    try (FileWriter fileWriter = new FileWriter("./save/language.json")) {
      fileWriter.write(jsonObject.toString());

      String title = this.lang.getCurr_lang().equals("EN")
        ? "Language"
        : "Langue";
      String header = this.lang.getCurr_lang().equals("EN")
        ? "Language changed to "
        : "Langue changée à ";
      String content = this.lang.getCurr_lang().equals("EN")
        ? "The language has been changed to "
        : "La langue a été changée à ";
      MyAlert alert = new MyAlert(title, header + language, content + language);
      alert.showInformation();
    } catch (IOException e) {
      MyAlert alert = new MyAlert(
        "Language",
        "Error",
        "Error while saving language"
      );
      alert.showInformation();
      e.printStackTrace();
    }
  }
}
