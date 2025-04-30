package controller.controllerCharacter.controllerHeros;

import java.util.ArrayList;
import model.modelCharacter.modelHeros.HeroModel;
import model.modelItem.ItemModel;
import view.viewCharacter.HeroView;
import view.viewGame.GameView;

/**
 * Controller class for managing the Hero character.
 * It acts as an intermediary between the HeroModel and HeroView.
 */
public class HeroController {

  private final HeroModel heroModel;
  private final HeroView heroView;
  private GameView gameView;

  /**
   * Constructs a HeroController with the specified HeroModel.
   * Initializes the associated HeroView.
   *
   * @param heroModel the HeroModel instance to be managed by this controller
   */
  public HeroController(HeroModel heroModel) {
    this.heroModel = heroModel;
    this.heroView = new HeroView(this);
    this.gameView = null;
  }

  /**
   * Gets the HeroModel instance managed by this controller.
   *
   * @return the HeroModel instance
   */
  public HeroModel getHeroModel() {
    return this.heroModel;
  }

  /**
   * Gets the HeroView instance associated with this controller.
   *
   * @return the HeroView instance
   */
  public HeroView getHeroView() {
    return this.heroView;
  }

  /**
   * Gets the GameView instance associated with this controller.
   *
   * @return the GameView instance
   */
  public GameView getGameView() {
    return this.gameView;
  }

  /**
   * Sets the GameView instance for this controller.
   *
   * @param gameView the GameView instance to be associated with this controller
   */
  public void setGameView(GameView gameView) {
    this.gameView = gameView;
  }

  /**
   * Gets the name of the hero.
   *
   * @return the hero's name
   */
  public String getName() {
    return this.getHeroModel().getName();
  }

  /**
   * Gets the current health points (HP) of the hero.
   *
   * @return the hero's HP
   */
  public int getHP() {
    return this.getHeroModel().getHealth();
  }

  /**
   * Gets the weapon currently equipped by the hero.
   *
   * @return a string representation of the hero's weapon
   */
  public String getWeapon() {
    return this.getHeroModel().getWeapon() == null
      ? ""
      : this.getHeroModel().getWeapon().toString();
  }

  /**
   * Gets the protection (shield) currently equipped by the hero.
   *
   * @return a string representation of the hero's shield
   */
  public String getProtection() {
    return this.getHeroModel().getShield() == null
      ? ""
      : this.getHeroModel().getShield().toString();
  }

  /**
   * Gets the job or role of the hero.
   *
   * @return the name of the hero's job
   */
  public String getJob() {
    return this.getHeroModel().getJob().name();
  }

  /**
   * Gets the content of the hero's backpack.
   *
   * @return a list of items in the hero's backpack
   */
  public ArrayList<ItemModel> getBackpackContent() {
    return HeroModel.gBackpack().getItemList();
  }

  public String getDescription() {
    String newLine = System.getProperty("line.separator");

    return (
      this.getName() +
      " (" +
      this.getJob() +
      ") : " +
      newLine +
      this.getHP() +
      " HP" +
      newLine +
      this.getWeapon() +
      newLine +
      this.getProtection() +
      newLine
    );
  }

  /**
   * Updates the hero's description in the GameView.
   * Clears the current hero information and sets the updated description.
   */
  public void updateDescription() {
    this.getGameView().getHeroInfos().clear();
    this.getGameView().getHeroInfos().setText(getDescription());
  }
}
