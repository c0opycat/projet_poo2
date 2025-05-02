package view.viewEditor.viewHistory;

import java.util.Stack;

public class HistoryManager {
    private static HistoryManager instance;
    private final Stack<EditorAction> undoStack = new Stack<>();
    private final Stack<EditorAction> redoStack = new Stack<>();

    public HistoryManager() {}

    public static HistoryManager getInstance() {
        if (instance == null) {
            instance = new HistoryManager();
        }
        return instance;
    }

    public void recordAction(EditorAction action) {
        if (action == null) {
            System.out.println("Action is null!");
        } 
        else {
            System.out.println("Action recorded: " + action.getClass().getSimpleName());
            undoStack.push(action);
            redoStack.clear();
        }
        System.out.println("recordAction ok");
    }

    public void undo() {
        if (!undoStack.isEmpty()) {
            EditorAction action = undoStack.pop();
            action.undo();
            redoStack.push(action);
            System.out.println("undoStack not empty");
        }
        System.out.println("undo ok");
    }

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
