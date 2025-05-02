package view.viewEditor;

import java.util.ArrayList;

import javafx.scene.control.Label;

/**
 * A specialized version of SelectElem that adds click listeners
 * to each draggable item, displaying French and English messages when clicked.
 * 
 * @author C. Besançon
 */
public class SelectElemItem extends SelectElem {
    /** msgF - list of french messages */
    private ArrayList<String> msgF;
    /** msgE - list of english messages */
    private ArrayList<String> msgE;

    /// Public///
    /**
     * Constructs a selection grid with associated messages for each item.
     * When an item is clicked, the corresponding French and English messages are
     * displayed in the provided labels.
     *
     * @param listItem  List of item names (used for display and image association)
     * @param col       Number of columns in the grid
     * @param msgFItem  List of French messages, one per item
     * @param msgEItem  List of English messages, one per item
     * @param msgFLabel Label to display the selected French message
     * @param msgELabel Label to display the selected English message
     */
    public SelectElemItem(ArrayList<String> listItem,
            int col, ArrayList<String> msgFItem,
            ArrayList<String> msgEItem,
            Label msgFLabel,
            Label msgELabel) {
        super(listItem, col);
        this.msgF = msgFItem;
        this.msgE = msgEItem;
        this.addsListenersOnClicked(msgFLabel, msgELabel);
    }

    /**
     * Attaches click listeners to each item in the grid.
     * When clicked, the item updates the given labels with its associated messages.
     *
     * @param msgFLabel Label to display the French message
     * @param msgELabel Label to display the English message
     */
    public void addsListenersOnClicked(Label msgFLabel, Label msgELabel) {
        int nbItems = this.msgF.size();

        for (int i = 0; i < nbItems; i++) {
            final String currentFMsg = this.msgF.get(i);
            final String currentEMsg = this.msgE.get(i);
            ViewImage img = ((ViewImage) this.getChildren().get(i));
            img.addListenerOnClicked(msgFLabel, currentFMsg, msgELabel, currentEMsg);
        }
    }
}