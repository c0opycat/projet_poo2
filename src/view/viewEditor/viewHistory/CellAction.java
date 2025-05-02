package view.viewEditor.viewHistory;

import view.Cell;

/**
 * Represents an undoable/redoable cell change made in a TextView.
 * 
 * @author C. Besançon
 */
public class CellAction implements EditorAction {
    /** Cell that we want to change */
    private final Cell cell;
    /** Previous element name */
    private final String oldElem;
    /** New element name */
    private final String newElem;
    /** Previous image name */
    private final String oldImg;
    /** New image name */
    private final String newImg;

    /**
     * Constructs a new CellAction
     *
     * @param cell       the cemm modified
     * @param oldElement a String array of [imageName, elementName] before the
     *                   change
     * @param newElement a String array of [imageName, elementName] after the change
     */
    public CellAction(Cell cell, String[] oldElement, String[] newElement) {
        this.cell = cell;
        this.oldImg = oldElement[0];
        this.newImg = newElement[0];
        this.oldElem = oldElement[1];
        this.newElem = newElement[1];
    }

    /**
     * Reapplies the new element and image to the Cell
     * It use for redo
     */
    @Override
    public void apply() {
        cell.setElement(new String[] { newImg, newElem });
    }

    /**
     * Reapplies the previous element and image to the Cell
     */
    @Override
    public void undo() {
        cell.setElement(new String[] { oldImg, oldElem });
    }

}
