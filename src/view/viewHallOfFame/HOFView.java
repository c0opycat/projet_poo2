package view.viewHallOfFame;

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.paint.Color;
import view.BorderWithButtons;

public class HOFView extends BorderWithButtons{

    public HOFView()
    {
        super(createButtons());

        //Adding the title at the top of the pane.
        Label title = new Label("HALL OF FAME");
        
        this.setTop(title);

        //Adding the scores in the center of the pane.
        ScoresView scores = new ScoresView();
        this.setCenter(scores);


        //Style
        title.setStyle("-fx-font-weight: bold; -fx-font-size: 20px");
        title.setPadding(new Insets(10));
        title.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, null)));
        BorderPane.setAlignment(title, Pos.CENTER);
        scores.setAlignment(Pos.CENTER);
        BorderPane.setMargin(title, new Insets(10));
        BorderPane.setMargin(scores, new Insets(2));
    }

    private static ArrayList<Button> createButtons()
    {
        ArrayList<Button> buttons = new ArrayList<>();

        Button menu = new Button("Menu");
        buttons.add(menu);

        return buttons;
    }
}
