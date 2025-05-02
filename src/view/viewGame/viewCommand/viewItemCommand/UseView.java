package view.viewGame.viewCommand.viewItemCommand;

import controller.controllerGame.controllerCommand.controllerItemCommand.UseController;
import java.awt.Point;
import java.util.ArrayList;
import javafx.scene.control.Button;
import view.Lang;
import view.MyAlert;
import view.viewCharacter.HeroView;
import view.viewCharacter.MonsterView;
import view.viewGame.GameView;
import view.viewLocation.LocationView;

/**
 * View class for the "Use" command button.
 * This class handles the graphical representation and interaction logic for using items.
 * @author L. Cooper
 * @author A. Bertrand-Bernard
 */
public class UseView extends Button {

  private Lang lang = new Lang();
  private final UseController useController;

  /**
   * Constructs a UseView button with the specified parameters.
   * Sets up the button's appearance and action logic based on the item type.
   *
   * @param gameView the GameView instance associated with this button
   * @param elem the name of the item to be used
   * @param ind the index of the item in the inventory
   */
  public UseView(GameView gameView, String elem, int ind) {
    super();
    this.useController = new UseController(gameView, this);
    this.lang.setButtonLang(this, "Utiliser", "Use");
    this.getStyleClass().add("button-Commande");

    if (
      elem.equals("Medicine") ||
      elem.equals("Fruit") ||
      elem.equals("Cake") ||
      elem.equals("Meat")
    ) {
      this.setOnAction(e -> {
          this.getUseController().setUseModel(String.valueOf(ind));
          if (this.getUseController().execute(null)) {
            gameView.getContainerView().updateContainerView(true);
          }
        });
    } else {
      this.setOnAction(e -> {
          String curLang = lang.getCurr_lang();

          HeroView heroView = gameView.getHeroView();
          int heroX = (int) heroView.getActualCoord().getX();
          int heroY = (int) heroView.getActualCoord().getY();
          LocationView locationView = gameView.getCurrentLocationView();

          ArrayList<String> items = locationView.getItemsBesidesHero(
            heroX,
            heroY
          );

          if (items.size() != 0) {
            ArrayList<String> buttonTypesText = new ArrayList<>();

            for (String item : items) {
              buttonTypesText.add(item);
            }

            String title = curLang.equals("EN")
              ? "Choose the item to perform this action on"
              : "Choisissez l'objet sur lequel faire cette action";
            String content = curLang.equals("EN") ? "The item:" : "L'objet :";
            MyAlert choiceAlert = new MyAlert(title, null, content);

            String choice = choiceAlert.showChoiceAlert(
              choiceAlert.createButtonTypes(buttonTypesText)
            );

            int x = heroX;
            int y = heroY;

            if (choice != null) {
              if (choice.equals("left") || choice.equals("gauche")) {
                x--;
              } else if (choice.equals("above") || choice.equals("au-dessus")) {
                y--;
              } else if (choice.equals("right") || choice.equals("droit")) {
                x++;
              } else {
                y++;
              }
            }

            Point toOpen = new Point(x, y);

            this.getUseController().setUseModel(String.valueOf(ind));
            if (this.getUseController().execute(toOpen)) {
              gameView.getContainerView().updateContainerView(true);

              MonsterView monsterView = gameView
                .getCurrentLocationView()
                .getMonsterView();
              if (monsterView != null && monsterView.isInAttackRange()) {
                monsterView.attack();
              }
            }
          } else {
            String title = curLang.equals("EN") ? "Error" : "Erreur";
            String content = curLang.equals("EN")
              ? "You need to be next to other objects to perform this action."
              : "Vous devez vous situer à côté de d'autres objets pour faire cette action.";
            MyAlert errorAlert = new MyAlert(title, null, content);
            errorAlert.showInformation();
          }
        });
    }
  }

  /**
   * Gets the UseController instance associated with this button.
   *
   * @return the UseController instance
   */
  public UseController getUseController() {
    return this.useController;
  }
}
