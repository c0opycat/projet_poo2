package model.item;

import java.util.Scanner;
import model.game.MessageM;
import model.item.container.Backpack;
import model.game.commandM.Command;

public class Crowbar extends Item
{
    public Crowbar()
    {
        super(7);
    }

    public boolean useConfirmation(Backpack b, Scanner scan)
    {
        System.out.println(MessageM.useItem(this));
        boolean ans;
        ans = Command.confirmation(scan);
        if (ans){
            b.removeItem(this);
            return true;
        }
        else return false;
    }

    public boolean use(Backpack b)
    {
            b.removeItem(this);
            return true;
    }
}
