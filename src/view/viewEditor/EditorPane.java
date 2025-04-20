package view.viewEditor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import view.FrameGame;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;

//REMARQUE AJOUT DES COMMANDES A FAIRE SUR TOUTES L'INTERFACE RIEN N'EST FONCTIONNEL C'EST UNIQUEMENT LA VUE SANS INTERACTION POSSIBLE
//CODE A RECOMMANTER PROPREMENT et A ENCAPSULER/METTRE EN CLASSE
//CODER ESTHETIQUE A AJOUTER


public class EditorPane extends HBox{
    private Spinner<Integer> nbColSpinner = new Spinner<>(2, 20, 10);
    private Spinner<Integer> nbRowSpinner = new Spinner<>(2, 20, 10);
    private String[] nomsType = {"Décors / Sets", "Portes / Doors", "Objets / Items"};
    private ArrayList<ArrayList<String>> nomsItems = new ArrayList<>(List.of(
                new ArrayList<>(List.of("cathedrale", "immeuble", "immeubles", "maison abandonnée", "palais")),
                new ArrayList<>(List.of("noDoor", "door0", "door1", "door2", "door3")), 
                new ArrayList<>(List.of("sac à dos/backpack ; capacité : 5/capacity: 5", 
                    "coffre/chest ; capacité : 5/capacity: 5", 
                    "caisse1/crate1 ; capacité : 5/capacity: 5; fermé/close", 
                    "caisse2/crate2 ; capacité : 5/capacity: 5; fermé/close", 
                    "pied de biche/crowbar ; poids : 1 / weight:1", 
                    "pistolet/gun ; poids : 1 / weight:1 ; dégâts : 3/damage: 3 ; type de dégât : perçant / damage type: piercing ", 
                    "fusil/shotgun; poids : 2 / weight:2 ; dégâts : 3/damage: 3 ; type de dégât : perçant / damage type: piercing ", 
                    "épée/sword ; poids : 2 / weight:2 ; dégâts : 3/damage: 3 ; type de dégât : perçant / damage type: piercing ", 
                    "batte/baseball Bat; poids : 2 / weight:2 ; dégâts : 2/damage: 2 ; type de dégât : assommant / damage type: stunning ", 
                    "armure/armor ; reduction des dommages : 2 /damage reduction: 2" ))
                ));

    public EditorPane()
    {
        super();

        //Ajout des onglets à droite
        //Attention bien créer rightNodes avant LeftNodes car on créé dedans les spinners avec leurs valeurs par défauts
        VBox rightNodes = this.rightNodes();

        //Ajout des éléments à gauche
        VBox leftNodes = this.leftNodes();

        //Ajout d'un ressort pour faire de l'espace entre la partie gauche et celle de droite
        Region spring = new Region();
        HBox.setHgrow(spring, Priority.ALWAYS);

        this.getChildren().addAll(leftNodes, spring, rightNodes);
        
        super.setAlignment(Pos.CENTER);
        HBox.setHgrow(leftNodes, Priority.ALWAYS);
        HBox.setMargin(leftNodes, new Insets(5, 5, 0, 5));
        HBox.setHgrow(rightNodes, Priority.ALWAYS);
        HBox.setMargin(rightNodes, new Insets(5,10, 0, 20));

    }

    private VBox leftNodes ()
    {
        int col = getCol();
        int row = getRow();
        //VBox pour ajouter la preview du niveau, 
        //les textfields de nom et de descriptions en deux langues
        VBox leftPane = new VBox();

        //Presentation de l'aperçu en français et anglais
        Label previewLabelfr = new Label("Aperçu du niveau");
        Label previewLabelen = new Label("Level preview");


        //Aperçu du jeu 
        FrameGame preview = initFrameGame(col, row);
        
        // Lier les Spinners aux tailles du GridPane
        this.getNbColSpinner().valueProperty().addListener((obs, oldValue, newValue) -> {
            initFrameGame(getRow(), getCol());
        });

        this.getNbRowSpinner().valueProperty().addListener((obs, oldValue, newValue) -> {
            initFrameGame(getRow(), getCol());
        });
        
        //zone de texte nom, description en français et anglais
        //Textfield a parametrer 
        //(style de police, nombre de caractère autorisée, nombre de ligne, espace pris dans la fenêtre)
        TextField name = new TextField("Nom / Name");
        
        TextArea descriptionFr = new TextArea("Description du quartier");
        TextArea descriptionEn = new TextArea("Description of the neighborhood");
        VBox.setVgrow(descriptionFr, Priority.ALWAYS);
        VBox.setVgrow(descriptionEn, Priority.ALWAYS);


        leftPane.getChildren().addAll(previewLabelfr, previewLabelen, springS(), preview, springS(), name, springS(), descriptionFr, springS(), descriptionEn);

        //Style
        VBox.setMargin(name, new Insets(10, 10, 20, 10));
        VBox.setMargin(descriptionFr, new Insets(10, 10, 10, 10));
        VBox.setMargin(descriptionEn, new Insets(10, 10, 10, 10));

        return leftPane;
    }

    //Ressort pour mettre entre les zones sometimes VBox
    private Region springS(){
        Region spring = new Region();
        VBox.setVgrow(spring, Priority.SOMETIMES);
        return spring;
    }

    //Ressort pour mettre entre les zones always VBox
    private Region springA(){
        Region spring = new Region();
        VBox.setVgrow(spring, Priority.ALWAYS);
        return spring;
    }

    private Region springH(){
        Region spring = new Region();
        HBox.setHgrow(spring, Priority.ALWAYS);
        return spring;
    }

    // Met à jour la taille du frameGame en reinitialisant tout
    private FrameGame initFrameGame(int newRow, int newCol) {
        FrameGame preview = new FrameGame(newCol, newRow);
        preview.addCellsToFrame();
        if (!this.getChildren().isEmpty())
        {
            VBox leftBox = ((VBox)this.getChildren().getFirst());
            FrameGame oldFrame = ((FrameGame)leftBox.getChildren().get(3));
            leftBox.getChildren().remove(oldFrame);
            leftBox.getChildren().add(3, preview);
        }
        return preview;
    }
    
    private HBox width(){
        HBox width = new HBox();

        VBox heightBox = new VBox();
        VBox lenghtBox = new VBox();
        
        Label hauteurLabel = new Label("Hauteur");
        Label heightLabel = new Label("Height");
        Label largeurLabel = new Label("Largeur");
        Label lenghtLabel = new Label("Lenght");

        heightBox.getChildren().addAll(hauteurLabel, heightLabel);
        lenghtBox.getChildren().addAll(largeurLabel, lenghtLabel);

        Spinner<Integer> heightSpinner = getNbRowSpinner();
        Spinner<Integer> lenghtSpinner = getNbColSpinner();

        heightSpinner.setEditable(true); // Permet la saisie manuelle

        lenghtSpinner.setEditable(true); 

        //Ressort pour mettre entre les deux Spinner
        Region spring = new Region();
        HBox.setHgrow(spring, Priority.ALWAYS);

        width.getChildren().addAll(heightBox, heightSpinner, spring, lenghtBox, lenghtSpinner);
        HBox.setMargin(heightSpinner, new Insets(10, 5, 10, 0));
        HBox.setMargin(lenghtSpinner, new Insets(10, 0, 10, 5));

        // Ajouter les écouteurs sur les valeurs des spinners
        nbColSpinner.valueProperty().addListener((observable, oldValue, newValue) -> {

        });

        nbRowSpinner.valueProperty().addListener((observable, oldValue, newValue) -> {
        });

        return width;
    }

    //transformer cette fonction pour qu'à partir d'une liste elle créé trois bouttons
    private HBox buttonsNb (int nb, String[] names)
    {
        HBox buttonNbBox = new HBox();
        VBox.setVgrow(buttonNbBox, Priority.ALWAYS);

        buttonNbBox.getChildren().add(springH());

        for (int i = 0; i<nb; ++i)
        {
            String name = names[i];
            Button bt = new Button(name);


            buttonNbBox.getChildren().addAll(bt, springH());
        }

        return buttonNbBox;
    }

    private HBox saveLevel(){
        HBox sl = selectLevel();

        Button btSave = new Button("Save");

        sl.getChildren().addAll(springH(), btSave, springH());

        return sl;
    }

    public String[] getLevels(){
        //A RECUPERER DEPUIS LE CONTROLLER
        String[] levels = {"new", "Place Lepetit", "Beaulieu", "Notre-Dame", "Blossac"};
        return levels;
    }

    private HBox selectLevel(){
      String[] listLevel = getLevels();

      //Creation de la boîte choix
      ComboBox<String> choiceLevel = new ComboBox<>();
      choiceLevel.getItems().addAll(listLevel);
      choiceLevel.setValue("new"); // Valeur par défaut = rien

      //Creation et ajout des labels Vers:
      Label selectionner = new Label("Selectionner :");
      Label select = new Label("Select:");

      VBox labelBox = new VBox();
      labelBox.getChildren().addAll(selectionner, select);
      
      //Ajout du choix de la direction
      HBox wayBox = new HBox();
      VBox.setVgrow(wayBox, Priority.ALWAYS);

      wayBox.getChildren().addAll(springH(), labelBox, choiceLevel);
      wayBox.setAlignment(Pos.CENTER);
      
      
      //Ajout dans la box principal
      return wayBox;
   }


    private VBox rightNodes()
    {
        //VBox pour ajouter les choix d'éléments à ajouter 
        //ainsi que les commandes et paramètres
        VBox rightPane = new VBox();

        String[] strTab = this.nomsType;
        ArrayList<ArrayList<String>> itemTab = this.nomsItems;

        int nbTypes = strTab.length;
        int nbItems = itemTab.size();

        String strTab2[];
        ArrayList<ArrayList<String>> itemTab2;

        if (nbTypes < nbItems){
            strTab2 = strTab;
            itemTab2 = new ArrayList<>(itemTab.subList( 0, nbTypes));
        }
        else{
            strTab2 = Arrays.copyOfRange(strTab, 0, nbItems);
            itemTab2 = this.nomsItems;
        }

        //Ajout des onglets d'ajout
        SelectTypeElem selectTypeElem = new SelectTypeElem(strTab2, itemTab2);
        VBox.setVgrow(selectTypeElem, Priority.ALWAYS);

        //Ajout des commandes
        HBox widthField = this.width();
        
        String[] namesRtr = {"Back", "Foward", "Re-init"};
        HBox buttonRetour = this.buttonsNb(3, namesRtr);
        HBox saveLevel = saveLevel();

        rightPane.getChildren().addAll(selectTypeElem, springA(), widthField, springA(), buttonRetour, springA(), saveLevel, springA());
        
        return rightPane;
    }

    // Getter pour le nombre de colonnes  et de lignes
    private int getCol(){
        return getNbColSpinner().getValue();
    }

    private int getRow(){
        return getNbRowSpinner().getValue();
    }

    // Getter pour les Spinners
    public Spinner<Integer> getNbColSpinner() {
        return this.nbColSpinner;
    }
    
    public Spinner<Integer> getNbRowSpinner() {
        return this.nbRowSpinner;
    }


    //Getter pour les boutons
    private Button getBack(){
        return (Button) ((HBox)((VBox)this.getChildren().getLast()).getChildren().get(4)).getChildren().getFirst();
    }

    private Button getFoward(){
        return (Button) ((HBox)((VBox)this.getChildren().getLast()).getChildren().get(4)).getChildren().get(1);
    }

    private Button getReinit(){
        return (Button) ((HBox)((VBox)this.getChildren().getLast()).getChildren().get(4)).getChildren().getLast();
    }

    //Action des boutons
    public void addHandlers()
    {

        getBack().setOnAction(e -> {
            
        });

        getFoward().setOnAction(e -> {
            
        });

        getReinit().setOnAction(e -> {
            initFrameGame(getRow(), getCol());
        });
    }
}
