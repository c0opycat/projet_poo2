package view.viewEditor.viewHistory;

import view.viewEditor.TextView;

/**
 * Represents an undoable/redoable text change made in a TextView.
 * 
 * @author C. Besançon
 */
public class TextAction implements EditorAction {
    /** area where the text is */
    private final TextView textView;
    /** previous text */
    private final String previousText;
    /** current text */
    private String currentText;

    /**
     * Constructs a new TextAction.
     *
     * @param textView     the TextArea affected by the change
     * @param previousText the content of the field before the change
     */
    public TextAction(TextView text, String previousText) {
        this.textView = text;
        this.previousText = previousText;
        this.currentText = textView.getText();
    }

    /**
     * Reapplies the current text to the TextView
     * It use for redo
     */
    @Override
    public void apply() {
        textView.setText(currentText);
    }

    /**
     * Reapplies the previous text to the TextView
     */
    @Override
    public void undo() {
        textView.setText(previousText);
    }
}
