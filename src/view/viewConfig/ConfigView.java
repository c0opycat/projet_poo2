package view.viewConfig;

import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import view.BorderWithButtons;
import view.ButtonMenu;
import view.ButtonQuit;


/**
 * Classe représentant la vue de configuration.
 * Elle permet à l'utilisateur de configurer les paramètres du jeu.
 * @author A. Bertrand-Bernard
 */

public class ConfigView extends BorderWithButtons{
    
    public ConfigView()
    {
        super();

        this.addComp();
    }

    private void addComp() {
        this.addTitle("Configuration");

        // Créer un VBox pour contenir les composants
        VBox vb = new VBox();
        vb.setAlignment(Pos.CENTER);


        // Créer une instance de KeybindConfigView
        KeybindConfigView kb = new KeybindConfigView();

        // Ajouter KeybindConfigView dans un ScrollPane
        ScrollPane scrollPane = new ScrollPane(kb);
        scrollPane.setFitToWidth(true); // Ajuste la largeur du contenu au ScrollPane
        scrollPane.setFitToHeight(false); // Permet le défilement vertical uniquement
        scrollPane.setPrefHeight(500); // Hauteur préférée du ScrollPane (modifiable selon vos besoins)
        scrollPane.setPrefWidth(300);
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");

        // Ajouter le ScrollPane au VBox
        vb.getChildren().add(scrollPane);

        // Ajouter le VBox au contenu principal
        this.setContent(vb);
        this.addBackground("background.jpeg");
    }

    /**
     * Méthode pour ajouter les différents boutons au conteneur principal.
     */
    public void setButtons()
    {
        ArrayList<Button> buttons = new ArrayList<>();

        ButtonMenu menu = new ButtonMenu(this.getMainScene());
        ButtonQuit quit = new ButtonQuit();

        buttons.add(menu);
        buttons.add(quit);

        this.addButtons(buttons);
    }
}
