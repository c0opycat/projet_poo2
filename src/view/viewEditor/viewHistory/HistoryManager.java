package view.viewEditor.viewHistory;

import java.util.Stack;

/**
 * Manages the history of actions performed in the editor to support undo and
 * redo functionality.
 * 
 * This class follows the singleton design pattern: only one instance of
 * {@code HistoryManager}
 * can exist
 * 
 * Actions are stored in two stacks: one for undo operations and one for redo
 * operations.
 * 
 * @author C. Besançon
 */
public class HistoryManager {
    /** instance - singleton instance of HistoryManager */
    private static HistoryManager instance;
    /** Stack storing actions that can be undone */
    private final Stack<EditorAction> undoStack = new Stack<>();
    /** Stack storing actions that can be redone */
    private final Stack<EditorAction> redoStack = new Stack<>();

    /**
     * Private constructor to prevent direct instantiation.
     * Use {@link #getInstance()} to retrieve the singleton instance.
     */
    public HistoryManager() {
    }

    /**
     * Returns HistoryManager, creating it if necessary.
     *
     * @return instance of HistoryManager
     */
    public static HistoryManager getInstance() {
        if (instance == null) {
            instance = new HistoryManager();
        }
        return instance;
    }

    /**
     * Records a new action to the undo stack and clears the redo stack.
     *
     * @param action the {@code EditorAction} to record.
     */
    public void recordAction(EditorAction action) {
        if (action == null) {
            System.out.println("Action is null!");
        } else {
            System.out.println("Action recorded: " + action.getClass().getSimpleName());
            undoStack.push(action);
            redoStack.clear();
        }
        System.out.println("recordAction ok");
    }

    /**
     * Undoes the last action, if available, and pushes it onto the redo stack.
     */
    public void undo() {
        if (!undoStack.isEmpty()) {
            EditorAction action = undoStack.pop();
            action.undo();
            redoStack.push(action);
            System.out.println("undoStack not empty");
        }
        System.out.println("undo ok");
    }

    /**
     * Redoes the last undone action, if available, and pushes it back onto the undo
     * stack.
     */
    public void redo() {
        if (!redoStack.isEmpty()) {
            EditorAction action = redoStack.pop();
            action.apply();
            undoStack.push(action);
            System.out.println("redoStack not empty");
        }
        System.out.println("redo ok");
    }

}
