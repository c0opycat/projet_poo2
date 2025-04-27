package view.viewEditor.viewHistory;

import view.Cell;

public class CellAction implements EditorAction{
    private final Cell cell;
    private final String oldElem;
    private final String newElem;
    private final String oldImg;
    private final String newImg;

    public CellAction(Cell cell, String[] oldElement, String[] newElement) {
        this.cell = cell;
        this.oldImg = oldElement[0];
        this.newImg = newElement[0];
        this.oldElem = oldElement[1];
        this.newElem = newElement[1];
    }

    @Override
    public void apply() {
        cell.setElement(new String[] {newImg, newElem});
    }

    @Override
    public void undo() {
        cell.setElement(new String[] {oldImg, oldElem});
    }
    
}
