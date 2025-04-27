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
    KeybindConfigView use = new KeybindConfigView(
      "Use",
      keybinds.getSpecKeybind("use"),
      keybinds
    );
    KeybindConfigView backpack = new KeybindConfigView(
      "Backpack",
      keybinds.getSpecKeybind("backpack"),
      keybinds
    );

    // // Touches de déplacement
    // HBox forward = new HBox();
    // forward.getStyleClass().add("keybind-row");
    // Label forwardl = new Label("Forward : ");
    // TextField forwardtf = new TextField();
    // forwardtf.setTextFormatter(createSingleCharFormatter());
    // forward.getChildren().addAll(forwardl, forwardtf);
    // forwardtf.setAlignment(Pos.CENTER);
    // forwardtf.setMaxWidth(50);
    // forward.setAlignment(Pos.CENTER);

    // HBox backward = new HBox();
    // backward.getStyleClass().add("keybind-row");
    // Label backwardl = new Label("Backward : ");
    // TextField backwardtf = new TextField();
    // backwardtf.setTextFormatter(createSingleCharFormatter());
    // backward.getChildren().addAll(backwardl, backwardtf);
    // backwardtf.setAlignment(Pos.CENTER);
    // backwardtf.setMaxWidth(50);
    // backward.setAlignment(Pos.CENTER);

    // HBox right = new HBox();
    // right.getStyleClass().add("keybind-row");
    // Label rightl = new Label("Right : ");
    // TextField righttf = new TextField();
    // righttf.setTextFormatter(createSingleCharFormatter());
    // right.getChildren().addAll(rightl, righttf);
    // righttf.setAlignment(Pos.CENTER);
    // righttf.setMaxWidth(50);
    // right.setAlignment(Pos.CENTER);

    // HBox left = new HBox();
    // left.getStyleClass().add("keybind-row");
    // Label leftl = new Label("Left : ");
    // TextField lefttf = new TextField();
    // lefttf.setTextFormatter(createSingleCharFormatter());
    // left.getChildren().addAll(leftl, lefttf);
    // lefttf.setAlignment(Pos.CENTER);
    // lefttf.setMaxWidth(50);
    // left.setAlignment(Pos.CENTER);

    // // Touches d'attaque
    // HBox attack = new HBox();
    // attack.getStyleClass().add("keybind-row");
    // Label attackl = new Label("Attack : ");
    // TextField attacktf = new TextField();
    // attacktf.setTextFormatter(createSingleCharFormatter());
    // attack.getChildren().addAll(attackl, attacktf);
    // attacktf.setAlignment(Pos.CENTER);
    // attacktf.setMaxWidth(50);
    // attack.setAlignment(Pos.CENTER);

    // // Touche d'équipement
    // HBox equipment = new HBox();
    // equipment.getStyleClass().add("keybind-row"); // Ajouter une classe CSS
    // Label equipmentl = new Label("Equipment : ");
    // TextField equipmenttf = new TextField();
    // equipmenttf.setTextFormatter(createSingleCharFormatter());
    // equipment.getChildren().addAll(equipmentl, equipmenttf);
    // equipmenttf.setAlignment(Pos.CENTER);
    // equipmenttf.setMaxWidth(50);
    // equipment.setAlignment(Pos.CENTER);

    // // Touche prendre
    // HBox take = new HBox();
    // take.getStyleClass().add("keybind-row"); // Ajouter une classe CSS
    // Label takel = new Label("Take : ");
    // TextField taketf = new TextField();
    // taketf.setTextFormatter(createSingleCharFormatter());
    // take.getChildren().addAll(takel, taketf);
    // taketf.setAlignment(Pos.CENTER);
    // taketf.setMaxWidth(50);
    // take.setAlignment(Pos.CENTER);

    // // Touche utiliser
    // HBox use = new HBox();
    // use.getStyleClass().add("keybind-row"); // Ajouter une classe CSS
    // Label usel = new Label("Use : ");
    // TextField usetf = new TextField();
    // usetf.setTextFormatter(createSingleCharFormatter());
    // use.getChildren().addAll(usel, usetf);
    // usetf.setAlignment(Pos.CENTER);
    // usetf.setMaxWidth(50);
    // use.setAlignment(Pos.CENTER);

    // // Touche pour ouvrir le backpack
    // HBox backpack = new HBox();
    // backpack.getStyleClass().add("keybind-row"); // Ajouter une classe CSS
    // Label backpackl = new Label("BackpackModel : ");
    // TextField backpacktf = new TextField();
    // backpacktf.setTextFormatter(createSingleCharFormatter());
    // backpack.getChildren().addAll(backpackl, backpacktf);
    // backpacktf.setAlignment(Pos.CENTER);
    // backpacktf.setMaxWidth(50);
    // backpack.setAlignment(Pos.CENTER);

    // // Taille max des TextField
    // forward.setMaxSize(200, 50);
    // backward.setMaxSize(200, 50);
    // right.setMaxSize(200, 50);
    // left.setMaxSize(200, 50);
    // attack.setMaxSize(200, 50);
    // equipment.setMaxSize(200, 50);
    // take.setMaxSize(200, 50);
    // use.setMaxSize(200, 50);
    // backpack.setMaxSize(200, 50);

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
        attack,
        equip,
        take,
        use,
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
    String attack =
      ((KeybindConfigView) this.getChildren().get(5)).getkeybindButton()
        .getText();
    String equip =
      ((KeybindConfigView) this.getChildren().get(6)).getkeybindButton()
        .getText();
    String take =
      ((KeybindConfigView) this.getChildren().get(7)).getkeybindButton()
        .getText();
    String use =
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
      !keys.add(attack) ||
      !keys.add(equip) ||
      !keys.add(take) ||
      !keys.add(use) ||
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
    keybindsMap.put("attack", attack);
    keybindsMap.put("equip", equip);
    keybindsMap.put("take", take);
    keybindsMap.put("use", use);
    keybindsMap.put("backpack", backpack);

    this.getKeybinds().saveKeybinds(keybindsMap);
  }
}
