package model.item;

import java.util.Scanner;
import model.game.Message;
import model.item.container.Backpack;
import model.game.commands.Command;

public class Crowbar extends Item
{
    public Crowbar()
    {
        super(7);
    }

    public boolean use(Backpack b, Scanner scan)
    {
        System.out.println(Message.useItem(this));
        boolean ans;
        ans = Command.confirmation(scan);
        if (ans){
            b.removeItem(this);
            return true;
        }
        else return false;
    }
}
