import Vue_editeur.EditeurPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MyApp extends Application{
    
    @Override
    public void start (Stage stage) throws Exception {
        
        final EditeurPane myPane = new EditeurPane();

        // Scene dimensions
        double width = 200.0, height = 200.0;

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
