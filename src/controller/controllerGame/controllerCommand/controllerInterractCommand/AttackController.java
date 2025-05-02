package controller.controllerGame.controllerCommand.controllerInterractCommand;

import model.modelGame.modelCommand.modelInterractCom.AttackModel;
import view.viewGame.GameView;

/**
 * Controller class for managing attack actions in the game.
 * This class acts as an intermediary between the AttackModel and the game view.
 * It handles the execution of attack commands initiated by the player against monsters.
 * @author L. Cooper
 */
public class AttackController {

  /** The model containing the attack action's logic and execution code. */
  private final AttackModel attackModel;

  /** Reference to the main game view for updating UI after attacks. */
  private final GameView gameView;

  /**
   * Constructs a new AttackController with the specified game view.
   * Initializes the controller with a reference to the game view and creates
   * a new AttackModel with the appropriate command name and game model reference.
   *
   * @param gameView the game view to associate with this attack controller
   */
  public AttackController(GameView gameView) {
    this.gameView = gameView;
    this.attackModel = new AttackModel(
      new String[] { "ATTACK" },
      this.getGameView().getGameController().getGameModel()
    );
  }

  /**
   * Gets the attack model associated with this controller.
   *
   * @return the AttackModel instance
   */
  public AttackModel getAttackModel() {
    return this.attackModel;
  }

  /**
   * Gets the game view associated with this controller.
   *
   * @return the GameView instance
   */
  public GameView getGameView() {
    return this.gameView;
  }

  /**
   * Executes an attack action.
   * Delegates to the attack model to perform the actual attack logic,
   * which typically includes damage calculation, checking if the target
   * is within range, and applying the attack effects.
   *
   * @return true if the attack was successful, false otherwise
   */
  public boolean execute() {
    return this.getAttackModel().execute();
  }
}
