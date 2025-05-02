import javafx.application.Application;
import javafx.stage.Stage;
import view.*;

/**
 * Main application class that initializes and launches the game.
 * This class extends JavaFX's Application class to provide the entry point for
 * the game. It handles the initial setup of the primary stage and scene.
 * @author C. Besançon
 * @author L. Cooper
 * @author A. Bertrand-Bernard
 */
public class Main extends Application {

  /**
   * Initializes the JavaFX stage with the main scene.
   * This method is called automatically by the JavaFX runtime after the
   * Application has been instantiated. It creates the main scene,
   * sets it on the primary stage, configures the window title,
   * and displays the window.
   *
   * @param stage the primary stage for this application
   * @throws Exception if an error occurs during initialization
   */
  @Override
  public void start(Stage stage) throws Exception {
    //Scene
    final MainScene scene = new MainScene();

    stage.setScene(scene);
    stage.setTitle("Ashes of Humanity");
    stage.show();
  }

  /**
   * The main entry point for the application.
   * This method launches the JavaFX application by calling the
   * static launch method inherited from the Application class.
   *
   * @param args command line arguments passed to the application
   */
  public static void main(String[] args) {
    launch(args);
  }
}
