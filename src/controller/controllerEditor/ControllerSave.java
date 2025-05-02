package controller.controllerEditor;

import java.util.ArrayList;

import model.modelEditor.CustomMapSaveModel;
import model.modelLocation.LocationModel;
import view.viewEditor.EditorPane;

public class ControllerSave {

    private final CustomMapSaveModel mapModel;
    private final EditorPane editorPane;

    public ControllerSave(EditorPane editorPane){
        this.editorPane = editorPane;
        this.mapModel = new CustomMapSaveModel();
    }

    public void saveLevel(EditorPane editor) {
        mapModel.saveToJson();

    }

    public String[] getLevelName() {
        ArrayList<LocationModel> listLocation = this.mapModel.loadFromJson();
        String[] names = new String[listLocation.size()];

        // Boucle pour remplir le tableau avec les noms des locations
        for (int i = 0; i < listLocation.size(); i++) {
            names[i] = listLocation.get(i).getName();
        }
        return names;
    }
}
