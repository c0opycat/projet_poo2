package view.viewGame.viewCommand.viewMenuCommand;

import javafx.scene.control.Button;
import view.viewGame.GameView;
import controller.controllerGame.controllerCommand.HelpController;

public class HelpView extends Button{
    private final HelpController helpController;

    public HelpView(GameView gameView)
    {
        super("Help");

        this.helpController = new HelpController(this, gameView);

        this.setOnAction(e -> {
            this.getHelpController().help();

            e.consume();
        });
    }

    public HelpController getHelpController()
    {
        return this.helpController;
    }
}
