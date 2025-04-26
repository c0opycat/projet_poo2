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
        this.focusedProperty().addListener((obs, oldFocus, newFocus) -> {
            if (!newFocus) {
                history.recordAction(new TitleAction(this, this.getText()));
            }
        });
        this.setOnAction(e -> {
            history.recordAction(new TitleAction(this, this.getText()));
        });
    }
    
}
