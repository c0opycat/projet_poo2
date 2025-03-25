package view.viewConfig;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class KeybindConfigView extends VBox{
    
    public KeybindConfigView()
    {

        super(20);

        this.addComp();

    }

    private void addComp()
    {
        Label title = new Label("Keybind :");

        //Forward part
        HBox forward = new HBox();

        Label forwardl = new Label("Forward");
        TextField forwardtf = new TextField();

        forward.getChildren().addAll(forwardl, forwardtf);
        forward.setAlignment(Pos.CENTER);

        //backward part
        HBox backward = new HBox();

        Label backwardl = new Label("Forward");
        TextField backwardtf = new TextField();

        backward.getChildren().addAll(backwardl, backwardtf);
        backward.setAlignment(Pos.CENTER);


        //right part
        HBox right = new HBox();

        Label rightl = new Label("Forward");
        TextField righttf = new TextField();

        right.getChildren().addAll(rightl, righttf);
        right.setAlignment(Pos.CENTER);


        
        //right part
        HBox left = new HBox();

        Label leftl = new Label("Forward");
        TextField lefttf = new TextField();

        left.getChildren().addAll(leftl, lefttf);
        left.setAlignment(Pos.CENTER);


        this.getChildren().addAll(title, forward, backward, right, left);
        this.setAlignment(Pos.CENTER);
    }

}
