package view.viewEditor.viewHistory;

import javafx.scene.control.TextField;

/**
 * Represents an undoable/redoable text change made in a TextField
 * 
 * @author C. Besançon
 */
public class TitleAction implements EditorAction {
    /** field where the text is */
    private final TextField textField;
    /** previous text */
    private final String previousText;
    /** current text */
    private String currentText;

    /**
     * Constructs a new TextAction.
     *
     * @param textField    the TextField affected by the change
     * @param previousText the content of the field before the change
     */
    public TitleAction(TextField textField, String previousText) {
        this.textField = textField;
        this.previousText = previousText;
        this.currentText = textField.getText();
    }

    /**
     * Reapplies the current text to the TextView
     * It use for redo
     */
    @Override
    public void apply() {
        textField.setText(currentText);
    }

    /**
     * Reapplies the previous text to the TextView
     */
    @Override
    public void undo() {
        textField.setText(previousText);
    }
}
