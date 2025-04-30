package controller.controllerGame.controllerCommand.controllerItemCommand;

import java.awt.Point;
import model.modelGame.modelCommand.modelItemCom.EquipModel;
import view.viewGame.GameView;
import view.viewGame.viewCommand.viewItemCommand.EquipView;

/**
 * The controller class for handling the equip command in the game.
 * Manages interactions between the EquipView and EquipModel.
 * @author L. Cooper
 * @author A. Bertrand-Bernard
 */
public class EquipController {

  /**
   * The view class for the equip button in the game.
   * Provides functionality to display equip options and manage interactions.
   */
  private final EquipView equipView;
  /**
   * The model class for the equip command.
   * Handles the logic and interactions related to equipping items.
   */
  private EquipModel equipModel;
  /**
   * The game view associated with this EquipController.
   * Provides access to the main game interface and other views.
   */
  private final GameView gameView;

  /**
   * Constructs a new EquipController instance.
   *
   * @param equipView the EquipView instance associated with this controller.
   * @param gameView the GameView instance associated with this controller.
   */
  public EquipController(EquipView equipView, GameView gameView) {
    this.equipView = equipView;
    this.equipModel = null;
    this.gameView = gameView;
  }

  /**
   * Retrieves the EquipView associated with this controller.
   *
   * @return the EquipView instance.
   */
  public EquipView getEquipView() {
    return this.equipView;
  }

  /**
   * Retrieves the EquipModel associated with this controller.
   *
   * @return the EquipModel instance.
   */
  public EquipModel getEquipModel() {
    return this.equipModel;
  }

  /**
   * Retrieves the GameView associated with this controller.
   *
   * @return the GameView instance.
   */
  public GameView getGameView() {
    return this.gameView;
  }

  /**
   * Sets the EquipModel with the specified zone index and item index.
   *
   * @param zoneInd the index of the zone where the item is located.
   * @param ind the index of the item to equip.
   */
  public void setEquipModel(int zoneInd, String ind) {
    System.out.println(zoneInd);
    this.equipModel = new EquipModel(
      new String[] { "EQUIP", ind, String.valueOf(zoneInd) },
      this.getGameView().getGameController().getGameModel()
    );
  }

  /**
   * Executes the equip command at the specified point.
   *
   * @param p the point where the command is executed.
   * @return true if the command was successfully executed, false otherwise.
   */
  public boolean execute(Point p) {
    return this.getEquipModel() == null
      ? false
      : this.getEquipModel().execute(p);
  }
}
