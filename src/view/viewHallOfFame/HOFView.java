package view.viewHallOfFame;

import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import view.BorderWithButtons;
import view.ButtonMenu;
import view.ButtonQuit;

public class HOFView extends BorderWithButtons {

  public HOFView() {
    super();
    this.addTitle("Hall of Fame", false);

    ScoreSaveView scores = new ScoreSaveView();
    this.setCenter(scores);

    BorderPane.setMargin(scores, new Insets(2));
  }

  public void setButtons() {
    ArrayList<Button> buttons = new ArrayList<>();

    ButtonMenu menu = new ButtonMenu(this.getMainScene());
    ButtonQuit quit = new ButtonQuit();

    buttons.add(menu);
    buttons.add(quit);

    this.addButtons(buttons);
  }
}
