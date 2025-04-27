package controller.controllerCharacter.controllerHeros;

import model.modelCharacter.modelHeros.HeroModel;
import view.viewCharacter.HeroView;

public class HeroController {

  private final HeroModel heroModel;
  private final HeroView heroView;

  public HeroController(HeroModel heroModel) {
    this.heroModel = heroModel;
    this.heroView = new HeroView(this);
  }

  public HeroModel getHeroModel() {
    return this.heroModel;
  }

  public HeroView getHeroView() {
    return this.heroView;
  }

  public String getName() {
    return this.getHeroModel().getName();
  }
}
