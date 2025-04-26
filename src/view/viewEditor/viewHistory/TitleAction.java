package view.viewEditor.viewHistory;

import javafx.scene.control.TextField;

public class TitleAction implements EditorAction{

    private final TextField textField;
    private final String previousText;
    private String currentText;

    public TitleAction(TextField textField, String previousText) {
        this.textField = textField;
        this.previousText = previousText;
        this.currentText = textField.getText();
    }

    @Override
    public void apply() {
        textField.setText(currentText);
    }

    @Override
    public void undo() {
        textField.setText(previousText);
    }
}
