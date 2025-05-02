package view;

import java.io.FileReader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * A class to manage language settings for the application.
 * Provides functionality to load the current language and set text for UI components.
 * @author A. Bertrand-Bernard
 */
public class Lang {

  /**
   * The current language of the application.
   */
  private String curr_lang;

  /**
   * Constructs a new Lang instance and loads the current language from a file.
   */
  public Lang() {
    this.curr_lang = loadCurr_lang();
  }

  /**
   * Retrieves the current language.
   *
   * @return the current language as a String.
   */
  public String getCurr_lang() {
    return curr_lang;
  }

  /**
   * Loads the current language from a JSON file.
   *
   * @return the current language as a String, or null if the file is not found.
   */
  public String loadCurr_lang() {
    String language = null;
    try {
      FileReader reader = new FileReader("./save/language.json");
      JSONObject jsonObject = new JSONObject(new JSONTokener(reader));
      language = (String) jsonObject.get("language");
    } catch (Exception e) {
      System.err.println("File 'language.json' not found.");
    }
    return language;
  }

  /**
   * Sets the text of a Button based on the current language.
   *
   * @param button the Button to set the text for.
   * @param fr the text to display if the language is French.
   * @param en the text to display if the language is English.
   */
  public void setButtonLang(Button button, String fr, String en) {
    if (curr_lang.equals("EN")) {
      button.setText(en);
    } else {
      button.setText(fr);
    }
  }

  /**
   * Sets the text of a Label based on the current language.
   *
   * @param label the Label to set the text for.
   * @param fr the text to display if the language is French.
   * @param en the text to display if the language is English.
   */
  public void setLabelLang(Label label, String fr, String en) {
    if (curr_lang.equals("EN")) {
      label.setText(en);
    } else {
      label.setText(fr);
    }
  }
}
