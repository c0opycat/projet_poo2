package view.viewEditor;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import view.FrameGame;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;

//REMARQUE AJOUT DES COMMANDES A FAIRE SUR TOUTES L'INTERFACE RIEN N'EST FONCTIONNEL C'EST UNIQUEMENT LA VUE SANS INTERACTION POSSIBLE
//CODE A RECOMMANTER PROPREMENT et A ENCAPSULER/METTRE EN CLASSE
//CODER ESTHETIQUE A AJOUTER

public class EditorPane extends HBox{
    public EditorPane()
    {
        super();


        //Ajout des éléments à gauche
        VBox leftNodes = this.leftNodes();
        this.getChildren().add(leftNodes);
        leftNodes.setAlignment(Pos.CENTER);

        //Ajout d'un ressort pour faire de l'espace entre la partie gauche et celle de droite
        Region spring = new Region();
        HBox.setHgrow(spring, Priority.ALWAYS);
        this.getChildren().add(spring);

        //Ajout des onglets à droite
        VBox rightNodes = this.rightNodes();
        this.getChildren().add(rightNodes);
        
        super.setAlignment(Pos.CENTER);
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
        leftPane.setAlignment(Pos.CENTER);

        //Presentation de l'aperçu en français et anglais
        Label previewLabelfr = new Label("Aperçu du niveau");
        Label previewLabelen = new Label("Level preview");


        //Aperçu du jeu 
        //Attention pour le moment j'ai juste mis le code de board du tp2
        //taille à gérer? -> sujet demande deux tailles
        FrameGame preview = new FrameGame(16, 10);
        
        //Ressort pour mettre entre l'aperçu et les zones de textes
        //A ENCAPSULER
        Region spring1 = new Region();
        VBox.setVgrow(spring1, Priority.SOMETIMES);

        Region spring2 = new Region();
        VBox.setVgrow(spring2, Priority.SOMETIMES);

        Region spring3 = new Region();
        VBox.setVgrow(spring3, Priority.SOMETIMES);

        Region springS = new Region();
        VBox.setVgrow(springS, Priority.SOMETIMES);

        //zone de texte nom, description en français et anglais
        //Textfield a parametrer 
        //(style de police, nombre de caractère autorisée, nombre de ligne, espace pris dans la fenêtre)
        TextField name = new TextField("Nom / Name");
        
        TextArea descriptionFr = new TextArea("Description du quartier");
        TextArea descriptionEn = new TextArea("Description of the neighborhood");
        VBox.setVgrow(descriptionFr, Priority.ALWAYS);
        VBox.setVgrow(descriptionEn, Priority.ALWAYS);


        leftPane.getChildren().addAll(previewLabelfr, previewLabelen, springS, preview, spring1, name, spring2, descriptionFr, spring3, descriptionEn);

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
    private HBox buttonsNb (int nb, String[] names)
    {
        HBox buttonNbBox = new HBox();
        VBox.setVgrow(buttonNbBox, Priority.ALWAYS);

        for (int i = 0; i<nb; ++i)
        {
            //LIER A UNE ACTION
            String name = names[i];
            Button bt = new Button(name);

            Region spring = new Region();
            HBox.setHgrow(spring, Priority.ALWAYS);

            buttonNbBox.getChildren().addAll(bt, spring);
        }
        

        //mise en page / style à faire

        return buttonNbBox;
    }



    private VBox rightNodes()
    {
        //VBox pour ajouter les choix d'éléments à ajouter 
        //ainsi que les commandes et paramètres
        VBox rightPane = new VBox();

        String[] strTab = {"Hey", "Hoy"};
        //Ajout des onglets d'ajout
        SelectTypeElem selectTypeElem = new SelectTypeElem(strTab);

        Region spring1 = new Region();
        VBox.setVgrow(spring1, Priority.ALWAYS);

        Region spring2 = new Region();
        VBox.setVgrow(spring2, Priority.ALWAYS);

        Region spring3 = new Region();
        VBox.setVgrow(spring3, Priority.ALWAYS);

        Region springS = new Region();
        VBox.setVgrow(springS, Priority.ALWAYS);

        //Ajout des commandes
        HBox widthTxtField = this.width();
        
        String[] namesRtr = {"Reset", "Restore", "Re-init"};
        String[] namesSt = {"Save", "Menu", "Quit"};
        HBox buttonRetour = this.buttonsNb(3, namesRtr);
        HBox buttonStatus = this.buttonsNb(3, namesSt);

        rightPane.getChildren().addAll(selectTypeElem, spring1, widthTxtField, spring2, buttonRetour, spring3, buttonStatus, springS);
        
        return rightPane;
    }
}
