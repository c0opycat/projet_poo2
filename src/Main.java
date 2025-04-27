import javafx.application.Application;
import javafx.stage.Stage;
import view.*;

public class Main extends Application {

  @Override
  public void start(Stage stage) throws Exception {
    //Scene
    final MainScene scene = new MainScene();

    stage.setScene(scene);
    stage.setTitle("OOP 2 Project");
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
