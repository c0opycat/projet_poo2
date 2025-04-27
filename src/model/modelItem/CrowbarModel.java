package model.modelItem;

import java.util.Scanner;
import model.modelGame.MessageEnModel;
import model.modelItem.modelContainer.BackpackModel;
import model.modelGame.commandM.CommandModel;

public class CrowbarModel extends ItemModel
{
    public CrowbarModel()
    {
        super(7);
    }

    public boolean useConfirmation(BackpackModel b, Scanner scan)
    {
        System.out.println(MessageEnModel.useItem(this));
        boolean ans;
        ans = CommandModel.confirmation(scan);
        if (ans){
            b.removeItem(this);
            return true;
        }
        else return false;
    }

    public boolean use(BackpackModel b)
    {
            b.removeItem(this);
            return true;
    }
}
