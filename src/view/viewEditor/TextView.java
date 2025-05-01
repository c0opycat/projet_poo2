package view.viewEditor;

import view.viewEditor.viewHistory.HistoryManager;
import view.viewEditor.viewHistory.TextAction;
import javafx.geometry.Insets;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class TextView extends TextArea{

    public TextView(String desc, HistoryManager history){
        super();
        this.setText(desc);

        VBox.setVgrow(this, Priority.ALWAYS);
        VBox.setMargin(this, new Insets(10, 10, 10, 10));
        
        historyText(history);
    }

    public void historyText(HistoryManager history){
        final String[] oldText = {this.getText()};

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

    private void changeText(String[] oldText, HistoryManager history){
        if (!oldText[0].equals(this.getText())) {
            history.recordAction(new TextAction(this, oldText[0])); 
            oldText[0] = this.getText();
        }
    }
    
    public String getDesc(){
        return this.getText();
    }
}
