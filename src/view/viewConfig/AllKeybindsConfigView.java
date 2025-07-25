package view.viewConfig;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import view.Keybinds;
import view.Lang;
import view.MyAlert;

/**
 * AllKeybindsConfigView is a class that represents the view for configuring all keybinds in the game.
 * It extends VBox and contains methods to manage the display of keybinds.
 * It is responsible for displaying the keybinds and allowing the user to change them.
 * It also manages the saving of keybinds.
 * @author L. Cooper
 * @author A. Bertrand-Bernard
 */

public class AllKeybindsConfigView extends VBox {

  /** Keybinds object */
  private final Keybinds keybinds;
  /** Lang object */
  private Lang lang = new Lang();

  /**
   * Constructor
   */
  public AllKeybindsConfigView() {
    super(20);
    this.keybinds = new Keybinds();
    this.addComp();
  }

  /**
   * getKeybinds is a method that returns the Keybinds object.
   * @return Keybinds object
   */
  public Keybinds getKeybinds() {
    return this.keybinds;
  }

  /**
   * Method to add components to the view
   * It creates a title and a list of keybinds with buttons to change them.
   * It also creates a save button to save the changes made to the keybinds.
   * @return void
   */
  private void addComp() {
    Label title = new Label();
    lang.setLabelLang(title, "Touches", "Keybinds");
    title.getStyleClass().add("under-title"); // Ajouter une classe CSS

    Keybinds keybinds = this.getKeybinds();

    KeybindConfigView forward = new KeybindConfigView(
      this.lang.getCurr_lang().equals("EN") ? "Forward" : "Avancer",
      keybinds.getSpecKeybind("forward"),
      keybinds
    );
    KeybindConfigView backward = new KeybindConfigView(
      this.lang.getCurr_lang().equals("EN") ? "Backward" : "Reculer",
      keybinds.getSpecKeybind("backward"),
      keybinds
    );
    KeybindConfigView right = new KeybindConfigView(
      this.lang.getCurr_lang().equals("EN") ? "Right" : "Droite",
      keybinds.getSpecKeybind("right"),
      keybinds
    );
    KeybindConfigView left = new KeybindConfigView(
      this.lang.getCurr_lang().equals("EN") ? "Left" : "Gauche",
      keybinds.getSpecKeybind("left"),
      keybinds
    );
    KeybindConfigView go = new KeybindConfigView(
      this.lang.getCurr_lang().equals("EN") ? "Go" : "Aller",
      keybinds.getSpecKeybind("go"),
      keybinds
    );
    KeybindConfigView attack = new KeybindConfigView(
      this.lang.getCurr_lang().equals("EN") ? "Attack" : "Attaquer",
      keybinds.getSpecKeybind("attack"),
      keybinds
    );
    KeybindConfigView equip = new KeybindConfigView(
      this.lang.getCurr_lang().equals("EN") ? "Equip" : "Equiper",
      keybinds.getSpecKeybind("equip"),
      keybinds
    );
    KeybindConfigView take = new KeybindConfigView(
      this.lang.getCurr_lang().equals("EN") ? "Take" : "Prendre",
      keybinds.getSpecKeybind("take"),
      keybinds
    );
    KeybindConfigView backpack = new KeybindConfigView(
      this.lang.getCurr_lang().equals("EN") ? "Backpack" : "Sac à dos",
      keybinds.getSpecKeybind("backpack"),
      keybinds
    );

    Button save = new Button();
    lang.setButtonLang(save, "Sauvegarder", "Save");
    save.getStyleClass().add("button");

    save.setOnAction(e -> {
      saveKeys();
    });

    this.getChildren()
      .addAll(
        title,
        forward,
        backward,
        right,
        left,
        go,
        attack,
        equip,
        take,
        backpack,
        save
      );
    this.setAlignment(Pos.CENTER);
  }

  /**
   * Method to save the keybinds
   * It retrieves the keybinds from the view and saves them using the Keybinds object.
   * It checks if the keybinds are already used and displays an alert if they are.
   * It also updates the keybinds in the Keybinds object.
   * @return void
   */
  public void saveKeys() {
    // Get the keybinds from the view
    String forward =
      ((KeybindConfigView) this.getChildren().get(1)).getkeybindButton()
        .getText();
    String backward =
      ((KeybindConfigView) this.getChildren().get(2)).getkeybindButton()
        .getText();
    String right =
      ((KeybindConfigView) this.getChildren().get(3)).getkeybindButton()
        .getText();
    String left =
      ((KeybindConfigView) this.getChildren().get(4)).getkeybindButton()
        .getText();
    String go =
      ((KeybindConfigView) this.getChildren().get(5)).getkeybindButton()
        .getText();
    String attack =
      ((KeybindConfigView) this.getChildren().get(6)).getkeybindButton()
        .getText();
    String equip =
      ((KeybindConfigView) this.getChildren().get(7)).getkeybindButton()
        .getText();
    String take =
      ((KeybindConfigView) this.getChildren().get(8)).getkeybindButton()
        .getText();
    String backpack =
      ((KeybindConfigView) this.getChildren().get(9)).getkeybindButton()
        .getText();

    // Check if the keys are already used
    Set<String> keys = new HashSet<>();
    if (
      !keys.add(forward) ||
      !keys.add(backward) ||
      !keys.add(right) ||
      !keys.add(left) ||
      !keys.add(go) ||
      !keys.add(attack) ||
      !keys.add(equip) ||
      !keys.add(take) ||
      !keys.add(backpack)
    ) {
      String title = this.lang.getCurr_lang().equals("EN")
        ? "Keybinds Configuration"
        : "Configuration des touches";
      String header = this.lang.getCurr_lang().equals("EN")
        ? "Key already used"
        : "Touche déjà utilisée";
      String content = this.lang.getCurr_lang().equals("EN")
        ? "Please use different keys for each action"
        : "Veuillez utiliser des touches différentes pour chaque action";
      MyAlert alert = new MyAlert(title, header, content);
      alert.showInformation();
      return;
    }

    HashMap<String, String> keybindsMap = new HashMap<>();
    keybindsMap.put("forward", forward);
    keybindsMap.put("backward", backward);
    keybindsMap.put("right", right);
    keybindsMap.put("left", left);
    keybindsMap.put("go", go);
    keybindsMap.put("attack", attack);
    keybindsMap.put("equip", equip);
    keybindsMap.put("take", take);
    keybindsMap.put("backpack", backpack);

    this.getKeybinds().saveKeybinds(keybindsMap);
  }
}
