package view.viewEditor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import controller.controllerEditor.ControllerSave;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import view.viewEditor.viewHistory.HistoryManager;

//REMARQUE AJOUT DES COMMANDES A FAIRE SUR TOUTES L'INTERFACE RIEN N'EST FONCTIONNEL C'EST UNIQUEMENT LA VUE SANS INTERACTION POSSIBLE
//CODE A RECOMMANTER PROPREMENT et A ENCAPSULER/METTRE EN CLASSE
//CODER ESTHETIQUE A AJOUTER

/**
 * HBox containing the modelEditor interface.
 * The left section is dedicated to level information - modelGame board, level name, and description.
 * The right section is dedicated to customization tools
 * - tabs for modelItem selection, board size, undo, redo, reset, save, and level editing.
 * @author C. Besançon
 */
public class EditorPane extends HBox {

  /**Spinner to choose the number of columns */
  private Spinner<Integer> nbColSpinner = new Spinner<>(2, 20, 10);
  /**Spinner to choose the number of rows */
  private Spinner<Integer> nbRowSpinner = new Spinner<>(2, 20, 10);
  /**Frame with the level to edit */
  private FrameGame frame;
  /**Name of the level*/
  private String name;
  /**French description of the level */
  private String descFr;
  /**English description of the level */
  private String descEn;
  /**Array of Name of the different object tabs*/
  private String[] nomsType = {
    "Décors / Sets",
    "Portes / Doors",
    "Objets / Items",
    "Personnages / Characters",
  };
  /**List of list of information about object that can be put on the board. One list for each tabs*/
  private ArrayList<ArrayList<String>> nomsItems = new ArrayList<>(
    List.of(
      new ArrayList<>(
        List.of(
          "cathedrale",
          "immeuble",
          "immeubles",
          "maison abandonnée",
          "palais"
        )
      ),
      new ArrayList<>(List.of("noDoor", "door0", "door1", "door2", "door3")),
      new ArrayList<>(
        List.of(
          "sac à dos/backpack ; capacité : 5/capacity: 5",
          "coffre/chest ; capacité : 5/capacity: 5",
          "caisse1/crate1 ; capacité : 5/capacity: 5; fermé/close",
          "caisse2/crate2 ; capacité : 5/capacity: 5; fermé/close",
          "pied de biche/crowbar ; poids : 1 / weight:1",
          "pistolet/gun ; poids : 1 / weight:1 ; dégâts : 3/damage: 3 ; type de dégât : perçant / damage type: piercing ",
          "fusil/shotgun; poids : 2 / weight:2 ; dégâts : 3/damage: 3 ; type de dégât : perçant / damage type: piercing ",
          "épée/sword ; poids : 2 / weight:2 ; dégâts : 3/damage: 3 ; type de dégât : perçant / damage type: piercing ",
          "batte/baseball Bat; poids : 2 / weight:2 ; dégâts : 2/damage: 2 ; type de dégât : assommant / damage type: stunning ",
          "armure/armor ; reduction des dommages : 2 /damage reduction: 2"
        )
      ),
      new ArrayList<>(
        List.of(
          "sac à dos/backpack ; capacité : 5/capacity: 5",
          "coffre/chest ; capacité : 5/capacity: 5",
          "caisse1/crate1 ; capacité : 5/capacity: 5; fermé/close",
          "caisse2/crate2 ; capacité : 5/capacity: 5; fermé/close",
          "pied de biche/crowbar ; poids : 1 / weight:1",
          "pistolet/gun ; poids : 1 / weight:1 ; dégâts : 3/damage: 3 ; type de dégât : perçant / damage type: piercing ",
          "fusil/shotgun; poids : 2 / weight:2 ; dégâts : 3/damage: 3 ; type de dégât : perçant / damage type: piercing ",
          "épée/sword ; poids : 2 / weight:2 ; dégâts : 3/damage: 3 ; type de dégât : perçant / damage type: piercing ",
          "batte/baseball Bat; poids : 2 / weight:2 ; dégâts : 2/damage: 2 ; type de dégât : assommant / damage type: stunning ",
          "armure/armor ; reduction des dommages : 2 /damage reduction: 2"
        )
      )
    )
  );
  private HistoryManager history = new HistoryManager();

  /// Public ///
  /**
   * Constructor
   */
  public EditorPane() {
    super();
    //Ajout des onglets à droite
    //Attention bien créer rightNodes avant LeftNodes car on créé dedans les spinners avec leurs valeurs par défauts
    VBox rightNodes = this.rightNodes();
    rightNodes.setAlignment(Pos.CENTER);

    //Ajout des éléments à gauche
    VBox leftNodes = this.leftNodes();
    leftNodes.setAlignment(Pos.CENTER);

    //Ajout d'un ressort pour faire de l'espace entre la partie gauche et celle de droite
    Region spring = new Region();
    HBox.setHgrow(spring, Priority.ALWAYS);

    this.getChildren().addAll(leftNodes, spring, rightNodes);

    super.setAlignment(Pos.CENTER);
    HBox.setHgrow(leftNodes, Priority.ALWAYS);
    HBox.setMargin(leftNodes, new Insets(5, 5, 0, 5));
    HBox.setHgrow(rightNodes, Priority.ALWAYS);
    HBox.setMargin(rightNodes, new Insets(5, 10, 0, 20));
  }

  /**
   * Recover the levels saved using the control.
   * This function currently hard-codes the level names so that you can test them while waiting for the final model.
   * @return an array with all the level names (and data?)
   */
  public String[] getLevels() {
    //A RECUPERER DEPUIS LE CONTROLLER
    String[] levels = {
      "new",
      "Place Lepetit",
      "Beaulieu",
      "Notre-Dame",
      "Blossac",
    };
    return levels;
  }

  /**
   * Get the spinner for the number of column
   * @return spinner for number of column
   */
  public Spinner<Integer> getNbColSpinner() {
    return this.nbColSpinner;
  }

  /**
   * Get the spinner for the number of row
   * @return spinner for number of row
   */
  public Spinner<Integer> getNbRowSpinner() {
    return this.nbRowSpinner;
  }

  /**
   * Add action for each button
   */
  public void addHandlers() {
    getUndo()
      .setOnAction(e -> {
        history.undo();
      });

    getRedo()
      .setOnAction(e -> {
        history.redo();
      });

    getReinit()
      .setOnAction(e -> {
        initFrameGame(getRow(), getCol());
      });

    getSave().setOnAction(e -> {
      ControllerSave contSave = new ControllerSave();
      contSave.saveLevel(this);
    });
  }

  /// Private ///

  /**
   * Creation of the left part of the interface. Contains the modelGame board, a text box for the level title,
   * a text box for the description needed for the level in French, and another box for the English description.
   * @return VBox left part of the modelEditor
   */
  private VBox leftNodes() {
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
    this.getNbColSpinner()
      .valueProperty()
      .addListener((obs, oldValue, newValue) -> {
        initFrameGame(getRow(), getCol());
      });

    this.getNbRowSpinner()
      .valueProperty()
      .addListener((obs, oldValue, newValue) -> {
        initFrameGame(getRow(), getCol());
      });

    //zone de texte nom, description en français et anglais
    //Textfield a parametrer
    //(style de police, nombre de caractère autorisée, nombre de ligne, espace pris dans la fenêtre)
    TitleView name = new TitleView("Nom / Name", history);

    TextView descriptionFr = new TextView("Description du quartier", history);
    TextView descriptionEn = new TextView(
      "Description of the neighborhood",
      history
    );

    leftPane
      .getChildren()
      .addAll(
        previewLabelfr,
        previewLabelen,
        springV(),
        preview,
        springV(),
        name,
        springV(),
        descriptionFr,
        springV(),
        descriptionEn
      );

    //Style
    VBox.setMargin(name, new Insets(10, 10, 20, 10));

    return leftPane;
  }

  /**
   * Spring to put between the zones in VBox set on sometimes
   * @return region -> spring
   */
  private Region springV() {
    Region spring = new Region();
    VBox.setVgrow(spring, Priority.SOMETIMES);
    return spring;
  }

  /**
   * Spring to put between the zones in HBox set on always
   * @return region -> spring
   */
  private Region springH() {
    Region spring = new Region();
    HBox.setHgrow(spring, Priority.ALWAYS);
    return spring;
  }

  /**
   * Updates the size of the frameGame by resetting everything
   * @param newRow new number of rows
   * @param newCol new number of columns
   * @return FrameGame -> board modelGame
   */
  private FrameGame initFrameGame(int newRow, int newCol) {
    FrameGame preview = new FrameGame(newCol, newRow);
    preview.setCellsDraggableInFrame();
    if (!this.getChildren().isEmpty()) {
      VBox leftBox = getLeftPane();
      FrameGame oldFrame = getFrame();
      leftBox.getChildren().remove(oldFrame);
      leftBox.getChildren().add(3, preview);
    }
    return preview;
  }

  /**
   * Added an HBox containing two VBoxes, one for height and the other for width.
   * Each VBox contains a spinner and an HBox with a French label and an English label.
   * @return HBox with element to choose the new size of the frameGame
   */
  private HBox size() {
    HBox size = new HBox();

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

    size
      .getChildren()
      .addAll(heightBox, heightSpinner, spring, lenghtBox, lenghtSpinner);
    HBox.setMargin(heightSpinner, new Insets(10, 5, 10, 0));
    HBox.setMargin(lenghtSpinner, new Insets(10, 0, 10, 5));

    // Ajouter les écouteurs sur les valeurs des spinners
    nbColSpinner
      .valueProperty()
      .addListener((observable, oldValue, newValue) -> {});

    nbRowSpinner
      .valueProperty()
      .addListener((observable, oldValue, newValue) -> {});

    return size;
  }

  /**
   * Create a HBox with an alternance of spring button spring button spring ...
   * @param nb number of button to create
   * @param names array with the name of each button to create
   * @return Hbox with buttons and regions
   */
  private HBox buttonsNb(int nb, String[] names) {
    HBox buttonNbBox = new HBox();
    VBox.setVgrow(buttonNbBox, Priority.ALWAYS);

    buttonNbBox.getChildren().add(springH());

    for (int i = 0; i < nb; ++i) {
      String name = names[i];
      Button bt = new Button(name);

      buttonNbBox.getChildren().addAll(bt, springH());
    }

    return buttonNbBox;
  }

  /**
   * Create a HBox with an HBox to select the level to edit and a button to save the edit.
   * @return HBox
   */
  private HBox saveLevel() {
    HBox sl = selectLevel();

    Button btSave = new Button("Save");

    sl.getChildren().addAll(springH(), btSave, springH());

    return sl;
  }

  /**
   * HBox with two Labels one in french the other in english
   * and a comboBox with the name of all the Levels already edit and newOne
   * @return HBox
   */
  private HBox selectLevel() {
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

    wayBox.getChildren().addAll(labelBox, choiceLevel);
    wayBox.setAlignment(Pos.CENTER);

    //Ajout dans la box principal
    return wayBox;
  }

  /**
   * Creation of the right part of the interface. Contains a tab to select different type of object
   * a HBox with two Spinner to choose the number of column and row for the modelGame board,
   * a HBox with three button undo redo reinit and another HBox with a comboBox to choose a level to edit,
   * and a button save
   * @return VBox -> right part of the modelEditor
   */
  private VBox rightNodes() {
    //VBox pour ajouter les choix d'éléments à ajouter
    //ainsi que les commandes et paramètres
    VBox rightPane = new VBox();

    String[] strTab = this.nomsType;
    ArrayList<ArrayList<String>> itemTab = this.nomsItems;

    int nbTypes = strTab.length;
    int nbItems = itemTab.size();

    String strTab2[];
    ArrayList<ArrayList<String>> itemTab2;

    if (nbTypes < nbItems) {
      strTab2 = strTab;
      itemTab2 = new ArrayList<>(itemTab.subList(0, nbTypes));
    } else {
      strTab2 = Arrays.copyOfRange(strTab, 0, nbItems);
      itemTab2 = this.nomsItems;
    }

    //Ajout des onglets d'ajout
    SelectTypeElem selectTypeElem = new SelectTypeElem(strTab2, itemTab2);
    selectTypeElem.setMinWidth(600);
    selectTypeElem.setMaxWidth(600);
    selectTypeElem.setPrefWidth(600);

    selectTypeElem.setMaxHeight(300);
    selectTypeElem.setMinHeight(300);
    selectTypeElem.setPrefHeight(300);



    //Ajout des commandes
    HBox widthField = this.size();

    String[] namesRtr = { "Undo", "Redo", "Re-init" };
    HBox buttonRetour = this.buttonsNb(3, namesRtr);
    HBox saveLevel = saveLevel();

    rightPane
      .getChildren()
      .addAll(
        selectTypeElem,
        springV(),
        widthField,
        springV(),
        buttonRetour,
        springV(),
        saveLevel,
        springV()
      );

    return rightPane;
  }

  // Getter pour le nombre de colonnes  et de lignes
  /**
   * get number of column
   * @return int number of columns
   */
  public int getCol() {
    return getNbColSpinner().getValue();
  }

  /**
   * get number of row
   * @return int number of row
   */
  public int getRow() {
    return getNbRowSpinner().getValue();
  }

  //Getter pour les boutons
  /**
   * get button undo
   * @return button undo
   */
  private Button getUndo() {
    return (Button) ((HBox) ((VBox) this.getChildren().get(2)).getChildren()
        .get(4)).getChildren()
      .get(1);
  }

  /**
   * get button redo
   * @return button redo
   */
  private Button getRedo() {
    return (Button) ((HBox) ((VBox) this.getChildren().get(2)).getChildren()
        .get(4)).getChildren()
      .get(3);
  }

  /**
   * get button reinit
   * @return button Reinit
   */
  private Button getReinit() {
    return (Button) ((HBox) ((VBox) this.getChildren().get(2)).getChildren()
        .get(4)).getChildren()
      .get(5);
  }

  /**
   * get button save
   * @return button save
   */
  private Button getSave() {
    return (Button) ((HBox) ((VBox) this.getChildren().get(2)).getChildren()
        .get(6)).getChildren()
      .get(3);
  }

  /**
   * get frameGame
   * @return frameGame
   */
  public FrameGame getFrame(){
    return (FrameGame)this.getLeftPane().getChildren().get(3);
  }

  /**
   * get the name of the level
   * @return String name level
   */
  public String getName(){
    TitleView name = (TitleView)getLeftPane().getChildren().get(5);
    return name.getTitle();
  }

  /**
   * get the french description
   * @return String french description
   */
  public String getDescFr(){
    return getDesc(7);
  }

    /**
   * get the french description
   * @return String french description
   */
  public String getDescEn(){
    return getDesc(9);
  }

  /**
   * get the text from a textField with is position in the pane
   * @param i index of the position in the pane
   * @return String text
   */
  private String getDesc(int i){
    TextView desc = (TextView)getLeftPane().getChildren().get(i);
    return desc.getDesc();
  }

  /**
   * get Left Pane
   * @return VBox Left Pane
   */
  private VBox getLeftPane(){
    return (VBox)this.getChildren().getFirst();
  }
}
