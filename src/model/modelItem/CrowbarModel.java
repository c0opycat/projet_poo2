package model.modelItem;

import java.util.Scanner;
import model.modelGame.MessageModel;
import model.modelGame.modelCommand.CommandModel;
import model.modelItem.modelContainer.BackpackModel;

/**
 * Represents a Crowbar item that can be used to open certain containers such as crates.
 * <p>
 * A Crowbar has a predefined weight and can be consumed when used.
 * </p>
 */
public class CrowbarModel extends ItemModel {

  /**
   * Constructs a CrowbarModel with a predefined weight of 7.
   */
  public CrowbarModel() {
    super(7);
  }

  /**
   * Asks the player for confirmation before using the Crowbar.
   * <p>
   * If confirmed, the crowbar is removed from the backpack and the method returns true.
   * If declined, nothing happens and the method returns false.
   * </p>
   * @param b the {@link BackpackModel} from which the crowbar should be removed
   * @param scan the {@link Scanner} used to read the player's confirmation input
   * @return true if the player confirmed and the crowbar was used, false otherwise
   */
  public boolean useConfirmation(BackpackModel b, Scanner scan) {
    System.out.println(MessageModel.useItem(this));
    boolean ans = CommandModel.confirmation(scan);
    if (ans) {
      b.removeItem(this);
      return true;
    } else {
      return false;
    }
  }

  /**
   * Uses the crowbar directly without asking for confirmation.
   * <p>
   * The crowbar is removed from the backpack and the method returns true.
   * </p>
   * @param b the {@link BackpackModel} from which the crowbar should be removed
   * @return true when the crowbar has been used
   */
  public boolean use(BackpackModel b) {
    b.removeItem(this);
    return true;
  }
}
