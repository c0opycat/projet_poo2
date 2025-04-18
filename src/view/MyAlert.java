package view;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class MyAlert {
    
    private final String TITLE;
    private final String HEADER;
    private final String CONTENT;

    public MyAlert(String title, String header, String content){

        this.TITLE = title;
        this.HEADER = header;
        this.CONTENT = content;

    }

    public void show(){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(TITLE);
        alert.setHeaderText(HEADER);
        alert.setContentText(CONTENT);
        alert.showAndWait();
    }

}
