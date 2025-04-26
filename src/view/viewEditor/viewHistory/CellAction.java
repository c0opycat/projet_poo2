package view.viewEditor.viewHistory;

import view.Cell;

public class CellAction implements EditorAction{
    private final Cell cell;
    private final String[] oldElement;
    private final String[] newElement;

    public CellAction(Cell cell, String[] oldElement, String[] newElement) {
        this.cell = cell;
        this.oldElement = oldElement;
        this.newElement = newElement;
    }

    @Override
    public void apply() {
        cell.setElement(newElement);
    }

    @Override
    public void undo() {
        cell.setElement(oldElement);
    }
    
}
