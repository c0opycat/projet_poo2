package Vue_editeur;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;

public class EditeurPane extends HBox{
    public EditeurPane()
    {
        super();

        //Ajout des éléments à gauche
        VBox leftNodes = this.leftNodes();
        this.getChildren().add(leftNodes);
        leftNodes.setAlignment(Pos.CENTER);

        //Ajout d'un ressort pour faire de l'espace entre la partie gauche et celle de droite
        Region spring = new Region();
        this.getChildren().add(spring);

        //Ajout des onglets à droite
        VBox rightNodes = this.rightNodes();
        this.getChildren().add(rightNodes);

        HBox.setHgrow(leftNodes, Priority.ALWAYS);
        HBox.setMargin(leftNodes, new Insets(5, 5, 0, 5));
        HBox.setHgrow(rightNodes, Priority.ALWAYS);
        HBox.setMargin(rightNodes, new Insets(5,10, 0, 20));

    }

    private VBox leftNodes ()
    {
        //VBox pour ajouter la preview du niveau, 
        //les textfields de nom et de descriptions en deux langues
        VBox leftPane = new VBox();

        //Presentation de l'aperçu en français et anglais
        Label previewLabelfr = new Label("Aperçu du niveau");
        Label previewLabelen = new Label("Level preview");


        //Aperçu du jeu 
        //Attention pour le moment j'ai juste mis le code de board du tp2
        //taille à gérer? -> sujet demande deux tailles
        FrameGame preview = new FrameGame();

        //Ressort pour mettre entre l'aperçu et les zones de textes
        //J'ai l'impression que cela ne marche pas vraiment ...
        Region spring = new Region();

        //zone de texte nom, description en français et anglais
        //Textfield a parametrer 
        //(style de police, nombre de caractère autorisée, nombre de ligne, espace pris dans la fenêtre)
        TextField name = new TextField("Nom / Name");

        TextField descriptionFr = new TextField("Description du quartier");
        TextField descriptionEn = new TextField("Description of the neighborhood");


        leftPane.getChildren().addAll(previewLabelfr, previewLabelen, preview, spring, name, descriptionFr, descriptionEn);

        //Style
        VBox.setMargin(name, new Insets(10, 10, 20, 10));
        VBox.setMargin(descriptionFr, new Insets(10, 10, 10, 10));
        VBox.setMargin(descriptionEn, new Insets(10, 10, 10, 10));

        return leftPane;
    }
    
    private HBox width(){
        HBox width = new HBox();
        //Hauteur et longueur a parametrer pour avoir uniquement des doubles
        //Est ce qu'on peut avoir un texte descriptif en filigranne?
        TextField height = new TextField("Hauteur / Height");
        TextField lenght = new TextField("Longueur / Length");

        //Ressort pour mettre entre les deux TextFields
        Region spring = new Region();

        width.getChildren().addAll(height, spring, lenght);
        HBox.setMargin(height, new Insets(10, 5, 10, 0));
        HBox.setMargin(lenght, new Insets(10, 0, 10, 5));

        return width;
    }


    //transformer cette fonction pour qu'à partir d'une liste elle créé trois bouttons
    private HBox buttons3 ()
    {
        HBox button3Box = new HBox();

        //à changer par icone + lier une action
        Button reset = new Button("Reset");
        Button restore = new Button("Restore");
        Button init = new Button("Re-init");

        button3Box.getChildren().addAll(reset, restore, init);

        //mise en page / style à faire

        return button3Box;
    }



    private VBox rightNodes()
    {
        //VBox pour ajouter les choix d'éléments à ajouter 
        //ainsi que les commandes et paramètres
        VBox rightPane = new VBox();

        //Ajout des onglets d'ajout
        SelectElem selectElem = new SelectElem();

        //Ajout des commandes
        HBox widthTxtField = this.width();
        HBox buttonRetour = this.buttons3();
        HBox buttonStatus = this.buttons3();

        rightPane.getChildren().addAll(selectElem, widthTxtField, buttonRetour, buttonStatus);
        
        return rightPane;
    }
}
