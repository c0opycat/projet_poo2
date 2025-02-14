package Vue_editeur;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Scene_editeur extends Application{
    @Override
    public void start (Stage stage) throws Exception {
        final EditeurPane myPane = new EditeurPane()

        // Scene dimensions
        double width = 750.0, height = 600.0;

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
