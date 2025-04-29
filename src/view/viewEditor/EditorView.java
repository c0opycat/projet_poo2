package view.viewEditor;

import java.util.ArrayList;

import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import view.BorderWithButtons;
import view.ButtonMenu;
import view.ButtonQuit;

/**
 * 
 * @author
 */
public class EditorView extends BorderWithButtons{
    
    public EditorView()
    {
        super();

        this.addComp();
    }

    private void addComp()
    {
        this.addTitle("Editeur");

        EditorPane ep = new EditorPane();
        ep.addHandlers();

        // Ajouter EditorPane dans un ScrollPane
        ScrollPane scrollPane = new ScrollPane(ep);
        scrollPane.setFitToWidth(true); // Ajuste la largeur du contenu au ScrollPane
        scrollPane.setFitToHeight(true); // Permet le défilement vertical uniquement
        scrollPane.setPrefSize(500, 500);
        scrollPane.setStyle(
            "-fx-background: transparent; -fx-background-color: transparent;"
        );

        this.setContent(scrollPane);
        this.addBackground("backgroundEditor.png");
    }

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
