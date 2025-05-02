package view.viewEditor;

import view.viewEditor.viewHistory.HistoryManager;
import view.viewEditor.viewHistory.TextAction;
import javafx.geometry.Insets;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 * A custom TextArea that integrates with a HistoryManager to track text
 * changes.
 * 
 * This component allows for recording changes to its content, enabling
 * undo/redo
 * or history-based features. It triggers history recording on Enter key press
 * or when
 * the component loses focus.
 * 
 * @author C. Besançon
 */
public class TextView extends TextArea {
    ///Public///
    /**
     * Constructs a new TextView initialized with the given description text
     * and connected to a history manager for tracking modifications.
     *
     * @param desc    The initial text to be displayed.
     * @param history The history manager responsible for recording text changes.
     */
    public TextView(String desc, HistoryManager history) {
        super();
        this.setText(desc);

        VBox.setVgrow(this, Priority.ALWAYS);
        VBox.setMargin(this, new Insets(10, 10, 10, 10));

        historyText(history);
    }

    /**
     * Sets up listeners to record changes in text via the provided history manager.
     * <p>
     * Text changes are recorded on Enter key press or when focus is lost.
     * </p>
     *
     * @param history The history manager to which text actions will be recorded.
     */
    public void historyText(HistoryManager history) {
        final String[] oldText = { this.getText() };

        this.focusedProperty().addListener((obs, oldFocus, newFocus) -> {
            if (!newFocus) {
                changeText(oldText, history);
            }
        });
        this.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER)
                changeText(oldText, history);
            history.recordAction(new TextAction(this, this.getText()));
        });
    }

    /**
     * Returns the current content of the TextView.
     *
     * @return The current text in the TextArea.
     */
    public String getDesc() {
        return this.getText();
    }

    /// Private///
    /**
     * Checks if the text has changed and, if so, records the change in the history
     * manager.
     *
     * @param oldText The previously recorded text.
     * @param history The history manager to update.
     */
    private void changeText(String[] oldText, HistoryManager history) {
        if (!oldText[0].equals(this.getText())) {
            history.recordAction(new TextAction(this, oldText[0]));
            oldText[0] = this.getText();
        }
    }
}
