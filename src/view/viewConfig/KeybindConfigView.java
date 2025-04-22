package view.viewConfig;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.json.JSONObject;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import view.MyAlert;

/**
 * Classe représentant la vue de configuration des touches (keybinds).
 * Elle permet à l'utilisateur de configurer les touches pour les actions du jeu.
 * @author A. Bertrand-Bernard
 */

public class KeybindConfigView extends VBox{
    
    /**
     * Constructeur de la classe KeybindConfigView.
     * Il initialise la vue avec les composants nécessaires pour configurer les keybinds.
     */
    public KeybindConfigView()
    {

        super(20);

        this.addComp();

    }

    /**
     * Méthode pour ajouter les composants de la vue.
     */
    private void addComp() {
        Label title = new Label("Keybind :");
        title.getStyleClass().add("under-title"); // Ajouter une classe CSS
    
        // Touches de déplacement
        HBox forward = new HBox();
        forward.getStyleClass().add("keybind-row");
        Label forwardl = new Label("Forward : ");
        TextField forwardtf = new TextField();
        forwardtf.setTextFormatter(createSingleCharFormatter());
        forward.getChildren().addAll(forwardl, forwardtf);
        forwardtf.setAlignment(Pos.CENTER);
        forwardtf.setMaxWidth(50);
        forward.setAlignment(Pos.CENTER);
    
        HBox backward = new HBox();
        backward.getStyleClass().add("keybind-row");
        Label backwardl = new Label("Backward : ");
        TextField backwardtf = new TextField();
        backwardtf.setTextFormatter(createSingleCharFormatter());
        backward.getChildren().addAll(backwardl, backwardtf);
        backwardtf.setAlignment(Pos.CENTER);
        backwardtf.setMaxWidth(50);
        backward.setAlignment(Pos.CENTER);

        HBox right = new HBox();
        right.getStyleClass().add("keybind-row");
        Label rightl = new Label("Right : ");
        TextField righttf = new TextField();
        righttf.setTextFormatter(createSingleCharFormatter());
        right.getChildren().addAll(rightl, righttf);
        righttf.setAlignment(Pos.CENTER);
        righttf.setMaxWidth(50);
        right.setAlignment(Pos.CENTER);
 
        HBox left = new HBox();
        left.getStyleClass().add("keybind-row");
        Label leftl = new Label("Left : ");
        TextField lefttf = new TextField();
        lefttf.setTextFormatter(createSingleCharFormatter());
        left.getChildren().addAll(leftl, lefttf);
        lefttf.setAlignment(Pos.CENTER);
        lefttf.setMaxWidth(50);
        left.setAlignment(Pos.CENTER);

        // Touches d'attaque
        HBox attack = new HBox();
        attack.getStyleClass().add("keybind-row");
        Label attackl = new Label("Attack : ");
        TextField attacktf = new TextField();
        attacktf.setTextFormatter(createSingleCharFormatter());
        attack.getChildren().addAll(attackl, attacktf);
        attacktf.setAlignment(Pos.CENTER);
        attacktf.setMaxWidth(50);
        attack.setAlignment(Pos.CENTER);

        // Touche d'équipement
        HBox equipment = new HBox();
        equipment.getStyleClass().add("keybind-row"); // Ajouter une classe CSS
        Label equipmentl = new Label("Equipment : ");
        TextField equipmenttf = new TextField();
        equipmenttf.setTextFormatter(createSingleCharFormatter());
        equipment.getChildren().addAll(equipmentl, equipmenttf);
        equipmenttf.setAlignment(Pos.CENTER);
        equipmenttf.setMaxWidth(50);
        equipment.setAlignment(Pos.CENTER);

        // Touche prendre
        HBox take = new HBox();
        take.getStyleClass().add("keybind-row"); // Ajouter une classe CSS
        Label takel = new Label("Take : ");
        TextField taketf = new TextField();
        taketf.setTextFormatter(createSingleCharFormatter());
        take.getChildren().addAll(takel, taketf);
        taketf.setAlignment(Pos.CENTER);
        taketf.setMaxWidth(50);
        take.setAlignment(Pos.CENTER);

        // Touche utiliser
        HBox use = new HBox();
        use.getStyleClass().add("keybind-row"); // Ajouter une classe CSS
        Label usel = new Label("Use : ");
        TextField usetf = new TextField();
        usetf.setTextFormatter(createSingleCharFormatter());
        use.getChildren().addAll(usel, usetf);
        usetf.setAlignment(Pos.CENTER);
        usetf.setMaxWidth(50);
        use.setAlignment(Pos.CENTER);

        // Taille max des TextField
        forward.setMaxSize(200, 50);
        backward.setMaxSize(200, 50);
        right.setMaxSize(200, 50);
        left.setMaxSize(200, 50);
        attack.setMaxSize(200, 50);
        equipment.setMaxSize(200, 50);
        take.setMaxSize(200, 50);
        use.setMaxSize(200, 50);


        Button save = new Button("Save");
        save.getStyleClass().add("button");

        save.setOnAction(e -> {
            saveKeys();
        });

        this.getChildren().addAll(title, forward, backward, right, left, attack, equipment, take, use, save);
        this.setAlignment(Pos.CENTER);
    }

    /**
     * Méthode pour sauvegarder les touches dans un fichier JSON.
     */
    public void saveKeys(){
        // Récupérer les touches des TextField
        String forward = ((TextField)((HBox)this.getChildren().get(1)).getChildren().get(1)).getText();
        String backward = ((TextField)((HBox)this.getChildren().get(2)).getChildren().get(1)).getText();
        String right = ((TextField)((HBox)this.getChildren().get(3)).getChildren().get(1)).getText();
        String left = ((TextField)((HBox)this.getChildren().get(4)).getChildren().get(1)).getText();
        String attack = ((TextField)((HBox)this.getChildren().get(5)).getChildren().get(1)).getText();
        String equipment = ((TextField)((HBox)this.getChildren().get(6)).getChildren().get(1)).getText();
        String take = ((TextField)((HBox)this.getChildren().get(7)).getChildren().get(1)).getText();
        String use = ((TextField)((HBox)this.getChildren().get(8)).getChildren().get(1)).getText();


        // Check si les touches sont vides
        if (forward.isEmpty() || 
            backward.isEmpty() || 
            right.isEmpty() || 
            left.isEmpty() ||
            attack.isEmpty() || 
            equipment.isEmpty() || 
            take.isEmpty() || 
            use.isEmpty()){
            MyAlert alert = new MyAlert("Keybinds", "Key empty", "Please fill all the keybinds");
            alert.show();
            return;
            }   
        
        // Check si les touches sont déjà utilisées
        Set<String> keys = new HashSet<>();
        if(!keys.add(forward) ||
           !keys.add(backward) ||
           !keys.add(right) ||
           !keys.add(left) ||
           !keys.add(attack) ||
           !keys.add(equipment) ||
           !keys.add(take) ||
           !keys.add(use)) {
            MyAlert alert = new MyAlert("Keybinds", "Key already used", "Please use different keys for each action");
            alert.show();
            return;
        }
        
        // Créer un objet JSON pour stocker les keybinds
        JSONObject keybinds = new JSONObject();
        keybinds.put("forward", forward);
        keybinds.put("backward", backward);
        keybinds.put("right", right);
        keybinds.put("left", left);
        keybinds.put("attack", attack);
        keybinds.put("equipment", equipment);
        keybinds.put("take", take);
        keybinds.put("use", use);

        // sauvegarder les keybinds dans un fichier JSON
        try(FileWriter file = new FileWriter("../save/keybinds.json")) {
            file.write(keybinds.toString(4));
            MyAlert alert = new MyAlert("Keybinds", "Keybinds saved", "Keybinds saved successfully");
            alert.show();
        } catch (IOException e){
            MyAlert alert = new MyAlert("Keybinds", "Error", "Error while saving keybinds");
            alert.show();
            e.printStackTrace();
        }

    }


    /**
     * Crée un TextFormatter qui n'accepte qu'un seul caractère.
     * @return Le TextFormatter configuré.
     */
    private TextFormatter<String> createSingleCharFormatter() {
        return new TextFormatter<>(change -> {
            if (change.getControlNewText().length() > 1) {
                return null;
            }
            return change;
        });
    }


}
