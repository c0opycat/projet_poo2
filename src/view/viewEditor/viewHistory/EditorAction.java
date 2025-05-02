package view.viewEditor.viewHistory;

/**
 * Represents a generic action in the editor that can be applied and undone.
 * 
 * This interface is intended to be implemented by all actions that should
 * support undo and redo functionality via the {@link HistoryManager}
 * 
 * @author C. Besançon
 */
public interface EditorAction {
    /**
     * Applies the action -> for redo
     */
    void apply();

    /**
     * Undoes the action
     */
    void undo();
}
