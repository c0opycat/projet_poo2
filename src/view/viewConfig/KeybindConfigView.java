package view.viewConfig;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
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

    private void addComp() {
        Label title = new Label("Keybind :");
        title.getStyleClass().add("under-title"); // Ajouter une classe CSS
    
        // Forward part
        HBox forward = new HBox();
        forward.getStyleClass().add("keybind-row"); // Ajouter une classe CSS
        Label forwardl = new Label("Forward : ");
        TextField forwardtf = new TextField();
        forward.getChildren().addAll(forwardl, forwardtf);
        forwardtf.setAlignment(Pos.CENTER);
        forwardtf.setMaxWidth(50);
        forward.setAlignment(Pos.CENTER);
    
        // Backward part
        HBox backward = new HBox();
        backward.getStyleClass().add("keybind-row"); // Ajouter une classe CSS
        Label backwardl = new Label("Backward : ");
        TextField backwardtf = new TextField();
        backward.getChildren().addAll(backwardl, backwardtf);
        backwardtf.setAlignment(Pos.CENTER);
        backwardtf.setMaxWidth(50);
        backward.setAlignment(Pos.CENTER);
    
        // Right part
        HBox right = new HBox();
        right.getStyleClass().add("keybind-row"); // Ajouter une classe CSS
        Label rightl = new Label("Right : ");
        TextField righttf = new TextField();
        right.getChildren().addAll(rightl, righttf);
        righttf.setAlignment(Pos.CENTER);
        righttf.setMaxWidth(50);
        right.setAlignment(Pos.CENTER);
    
        // Left part
        HBox left = new HBox();
        left.getStyleClass().add("keybind-row"); // Ajouter une classe CSS
        Label leftl = new Label("Left : ");
        TextField lefttf = new TextField();
        left.getChildren().addAll(leftl, lefttf);
        lefttf.setAlignment(Pos.CENTER);
        lefttf.setMaxWidth(50);
        left.setAlignment(Pos.CENTER);


        // Set the size of the hbox
        forward.setMaxSize(200, 50);
        backward.setMaxSize(200, 50);
        right.setMaxSize(200, 50);
        left.setMaxSize(200, 50);

        Button save = new Button("Save");
        save.getStyleClass().add("button"); // Ajouter une classe CSS
        save.setOnAction(e -> {
            getText();
        });

        this.getChildren().addAll(title, forward, backward, right, left, save);
        this.setAlignment(Pos.CENTER);
    }

    public void getText(){
        // Get the text from the text fields
        String forward = ((TextField)((HBox)this.getChildren().get(1)).getChildren().get(1)).getText();
        String backward = ((TextField)((HBox)this.getChildren().get(2)).getChildren().get(1)).getText();
        String right = ((TextField)((HBox)this.getChildren().get(3)).getChildren().get(1)).getText();
        String left = ((TextField)((HBox)this.getChildren().get(4)).getChildren().get(1)).getText();

        System.out.println("Forward : " + forward);
        System.out.println("Backward : " + backward);
        System.out.println("Right : " + right);
        System.out.println("Left : " + left);
    }

}
