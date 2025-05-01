package controller.controllerCharacter;

import model.modelCharacter.modelMonster.MonsterModel;
import view.viewCharacter.MonsterView;
import view.viewGame.GameView;
import view.viewLocation.LocationView;

public class MonsterController {

  private final MonsterView monsterView;
  private final MonsterModel monsterModel;
  private GameView gameView;

  public MonsterController(MonsterView monsterView, LocationView locationView) {
    this.monsterView = monsterView;
    this.monsterModel = locationView
      .getLocationController()
      .getLocationModel()
      .getMonster();
    this.gameView = null;
  }

  public MonsterView getMonsterView() {
    return this.monsterView;
  }

  public MonsterModel getMonsterModel() {
    return this.monsterModel;
  }

  public GameView getGameView() {
    return this.gameView;
  }

  public void setGameView(GameView gameView) {
    this.gameView = gameView;
  }

  public boolean hasMonsterModel() {
    return this.monsterView != null;
  }

  public String getType() {
    String className = this.getMonsterModel().getClass().getSimpleName();
    return className.substring(0, className.length() - 5);
  }

  public String getHP() {
    return String.valueOf(this.getMonsterModel().getHealth());
  }

  public String getWeapon() {
    return this.getMonsterModel().getWeapon() == null
      ? ""
      : this.getMonsterModel().getWeapon().toString();
  }

  public String getProtection() {
    return this.getMonsterModel().getShield() == null
      ? ""
      : this.getMonsterModel().getShield().toString();
  }

  public String getDescription() {
    String newLine = System.getProperty("line.separator");

    return (
      this.getType() +
      " : " +
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

  public void updateMonsterDescription() {
    if (this.getGameView() != null) {
      this.getGameView().getMonsterInfos().clear();
      this.getGameView().getMonsterInfos().setText(getDescription());
    }
  }
}
