package view.viewEditor.viewHistory;

import view.viewEditor.TextView;

public class TextAction implements EditorAction {
    private final TextView textView;
    private final String previousText;
    private String currentText;

    public TextAction(TextView text, String previousText) {
        this.textView = text;
        this.previousText = previousText;
        this.currentText = textView.getText();
    }

    @Override
    public void apply() {
        textView.setText(currentText);
    }

    @Override
    public void undo() {
        textView.setText(previousText);
    }
}
