package view;

import java.io.FileReader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.json.JSONObject;
import org.json.JSONTokener;

public class Lang {

  private String curr_lang;

  public Lang() {
    this.curr_lang = loadCurr_lang();
  }

  public String getCurr_lang() {
    return curr_lang;
  }

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

  public void setButtonLang(Button button, String fr, String en) {
    if (curr_lang.equals("EN")) {
      button.setText(en);
    } else {
      button.setText(fr);
    }
  }

  public void setLabelLang(Label label, String fr, String en) {
    if (curr_lang.equals("EN")) {
      label.setText(en);
    } else {
      label.setText(fr);
    }
  }
}
