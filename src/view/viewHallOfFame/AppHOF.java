package view.viewHallOfFame;

import Vue_editeur.EditeurPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppHOF extends Application{
    
    @Override
    public void start (Stage stage) throws Exception {
        
        final EditeurPane myPane = new EditeurPane();

        // Scene dimensions
        double width = 850.0, height = 600.0;

        // Scene = container for all content
        Scene scene = new Scene(myPane, width, height);
        stage.setScene(scene);
        stage.setTitle("Hall of Fame");
        stage.show();  
    }
    
    public static void main (String args []) {
		launch(args);
    }
}
