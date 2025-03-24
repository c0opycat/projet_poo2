package view.viewMonster;

import javafx.scene.control.Label;

//Label pour l'instant pour ne pas à gérer les images maintenant
public class MonsterView extends Label{
    private Label hp;
    private Label weapon;

    public MonsterView(String hp, String weapon)
    {
        super();

        this.hp = new Label(hp);
        this.weapon = new Label(weapon);
    }
}
