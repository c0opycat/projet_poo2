package view.viewMenu;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import view.BorderWithButtons;

public class Menu extends BorderWithButtons{
    
    public Menu()
    {
        super();

        this.addcomp();

    }

    private void addcomp()
    {
        VBox vb = new VBox();

        Button jouer = new Button("Play");
        Button cfg = new Button("Config");
        Button edit = new Button("Editor");
        Button hof = new Button("Hall of Fame");

        vb.getChildren().addAll(jouer, cfg, edit, hof);

        this.setCenter(vb);

    
    }

}
