package view.viewEditor;

import javafx.scene.control.TextField;
import view.viewEditor.viewHistory.HistoryManager;
import view.viewEditor.viewHistory.TitleAction;

public class TitleView extends TextField {

    public TitleView(String desc, HistoryManager history){
        super();
        this.setText(desc);

        historyText(history);
    }

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

    private void changeTitle(String[] oldText, HistoryManager history){
        if (!oldText[0].equals(this.getText())) {
            history.recordAction(new TitleAction(this, oldText[0])); 
            oldText[0] = this.getText();
        }
    }
    
}
