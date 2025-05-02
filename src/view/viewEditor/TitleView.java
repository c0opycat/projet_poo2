package view.viewEditor;

import javafx.scene.control.TextField;
import view.viewEditor.viewHistory.HistoryManager;
import view.viewEditor.viewHistory.TitleAction;

/**
 * A custom TextField used for editing titles, with integrated history tracking.
 * 
 * Changes are recorded either when the field loses focus or when the user presses Enter.
 * 
 * @author C. Besançon
 */
public class TitleView extends TextField {
    ///Public///
    /**
     * Constructs a new TitleView with the given initial title text and associated history manager.
     *
     * @param desc    The initial title text.
     * @param history The history manager used to track title modifications.
     */
    public TitleView(String desc, HistoryManager history){
        super();
        this.setText(desc);

        historyText(history);
    }

    /**
     * Sets up history tracking by listening to focus loss and Enter key actions.
     * Records changes both when the field loses focus and when the user presses Enter.
     *
     * @param history The history manager that records title actions.
     */
    public void historyText(HistoryManager history){
        final String[] oldText = {this.getText()};

        this.focusedProperty().addListener((obs, oldFocus, newFocus) -> {
            if (!newFocus) {
                history.recordAction(new TitleAction(this, this.getText()));
                changeTitle(oldText, history);
            }
        });
        this.setOnAction(e -> {
            history.recordAction(new TitleAction(this, this.getText()));
        });
    }

    /**
     * Returns the current title in the text field.
     *
     * @return The current title string.
     */
    public String getTitle(){
        return this.getText();
    }

    ///Private///
    /**
     * Compares the current text with the previous value and records the change if necessary.
     *
     * @param oldText The previously stored title.
     * @param history The history manager to which the change is recorded.
     */
    private void changeTitle(String[] oldText, HistoryManager history){
        if (!oldText[0].equals(this.getText())) {
            history.recordAction(new TitleAction(this, oldText[0])); 
            oldText[0] = this.getText();
        }
    }
    
}
