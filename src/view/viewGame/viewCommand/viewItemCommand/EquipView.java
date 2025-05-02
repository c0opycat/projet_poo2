package view.viewGame.viewCommand.viewItemCommand;

import controller.controllerGame.controllerCommand.controllerItemCommand.EquipController;
import java.awt.Point;
import javafx.scene.control.Button;
import view.Lang;
import view.viewGame.GameView;

/**
 * The view class for handling the equip command in the game.
 * Provides functionality to create equip buttons and manage interactions.
 * @author L. Cooper
 * @author A. Bertrand-Bernard
 */
public class EquipView {

  /**
   * The language manager for this view.
   * Used to set the text of buttons based on the current language.
   */
  private final Lang lang = new Lang();

  /**
   * The controller for handling equip commands.
   * Manages the logic and interactions related to equipping items.
   */
  private final EquipController equipController;

  /**
   * The game view associated with this EquipView.
   * Provides access to the main game interface and other views.
   */
  private final GameView gameView;

  /**
   * Constructs a new EquipView instance.
   *
   * @param gameView the GameView instance associated with this EquipView.
   */
  public EquipView(GameView gameView) {
    this.gameView = gameView;
    this.equipController = new EquipController(this, gameView);
  }

  /**
   * Retrieves the language manager for this view.
   *
   * @return the Lang instance.
   */
  public Lang getLang() {
    return this.lang;
  }

  /**
   * Retrieves the equip controller for this view.
   *
   * @return the EquipController instance.
   */
  public EquipController getEquipController() {
    return this.equipController;
  }

  /**
   * Retrieves the game view associated with this EquipView.
   *
   * @return the GameView instance.
   */
  public GameView getGameView() {
    return this.gameView;
  }

  /**
   * Creates an equip button for equipping items.
   *
   * @param isBackpack true if the item is in the backpack, false otherwise.
   * @param elem the element to equip.
   * @param ind the index of the item to equip.
   * @return the created Button instance.
   */
  public Button equipButton(boolean isBackpack, String elem, int ind) {
    Button equipButton = new Button();
    this.getLang().setButtonLang(equipButton, "Equiper", "Equip");
    equipButton.getStyleClass().add("button-Commande");

    int containerX = this.getGameView().getContainerView().getX();
    int containerY = this.getGameView().getContainerView().getY();
    Point containerPoint = new Point(containerX, containerY);

    equipButton.setOnAction(e -> {
      if (isBackpack) {
        this.getEquipController().setEquipModel(1, String.valueOf(ind));
        if (this.getEquipController().execute(containerPoint)) {
          this.getGameView().getContainerView().updateContainerView(true);
          this.getGameView().getCurrentLocationView().updateItems();
          this.getGameView()
            .getHeroView()
            .getHeroController()
            .updateDescription();
        }
      } else {
        this.getEquipController().setEquipModel(3, String.valueOf(ind));
        if (this.getEquipController().execute(containerPoint)) {
          this.getGameView().getContainerView().updateContainerView(false);
          this.getGameView().getCurrentLocationView().updateItems();
          this.getGameView()
            .getHeroView()
            .getHeroController()
            .updateDescription();
        }
      }
    });

    return equipButton;
  }
}
