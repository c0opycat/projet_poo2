package view.viewEditor;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class EditorScene extends Application{

    @Override
    public void start (Stage stage) throws Exception {
        
        final EditorPane myPane = new EditorPane();

        // Scene dimensions
        double width = 1500.0, height = 900.0;

        // Scene = container for all content
        Scene scene = new Scene(myPane, width, height);
        stage.setScene(scene);
        stage.setTitle("Editeur de niveau");
        stage.show();
    }
    
    public static void main (String args []) {
		launch(args);
    }
}
