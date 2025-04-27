package view.viewConfig;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import view.Keybinds;
import view.MyAlert;

/**
 * Classe représentant la vue de configuration des touches (keybinds).
 * Elle permet à l'utilisateur de configurer les touches pour les actions du jeu.
 * @author A. Bertrand-Bernard
 */

public class AllKeybindsConfigView extends VBox {

  private final Keybinds keybinds;

  /**
   * Constructeur de la classe KeybindConfigView.
   * Il initialise la vue avec les composants nécessaires pour configurer les keybinds.
   */
  public AllKeybindsConfigView() {
    super(20);
    this.keybinds = new Keybinds();
    this.addComp();
  }

  public Keybinds getKeybinds() {
    return this.keybinds;
  }

  /**
   * Méthode pour ajouter les composants de la vue.
   */
  private void addComp() {
    Label title = new Label("Keybind :");
    title.getStyleClass().add("under-title"); // Ajouter une classe CSS

    Keybinds keybinds = this.getKeybinds();

    KeybindConfigView forward = new KeybindConfigView(
      "Forward",
      keybinds.getSpecKeybind("forward"),
      keybinds
    );
    KeybindConfigView backward = new KeybindConfigView(
      "Backward",
      keybinds.getSpecKeybind("backward"),
      keybinds
    );
    KeybindConfigView right = new KeybindConfigView(
      "Right",
      keybinds.getSpecKeybind("right"),
      keybinds
    );
    KeybindConfigView left = new KeybindConfigView(
      "Left",
      keybinds.getSpecKeybind("left"),
      keybinds
    );
    KeybindConfigView go = new KeybindConfigView(
      "Go",
      keybinds.getSpecKeybind("go"),
      keybinds
    );
    KeybindConfigView attack = new KeybindConfigView(
      "Attack",
      keybinds.getSpecKeybind("attack"),
      keybinds
    );
    KeybindConfigView equip = new KeybindConfigView(
      "Equip",
      keybinds.getSpecKeybind("equip"),
      keybinds
    );
    KeybindConfigView take = new KeybindConfigView(
      "Take",
      keybinds.getSpecKeybind("take"),
      keybinds
    );
    KeybindConfigView backpack = new KeybindConfigView(
      "Backpack",
      keybinds.getSpecKeybind("backpack"),
      keybinds
    );

    Button save = new Button("Save");
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
   * Méthode pour sauvegarder les touches
   */
  public void saveKeys() {
    // Récupérer les touches
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

    // Check si les touches sont déjà utilisées
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
      MyAlert alert = new MyAlert(
        "Keybinds configuration",
        "Key already used",
        "Please use different keys for each action"
      );
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
