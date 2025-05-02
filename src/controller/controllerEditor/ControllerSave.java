package controller.controllerEditor;

import java.util.ArrayList;
import java.util.HashMap;

import model.modelEditor.CustomMapSaveModel;
import model.modelLocation.ExitModel;
import model.modelLocation.LocationModel;
import view.viewEditor.EditorPane;

public class ControllerSave {

    private final CustomMapSaveModel mapModel;
    private LocationModel loc;
    private EditorPane editorPane;

    public ControllerSave(EditorPane editorPane) {
        this.editorPane = editorPane;
        this.mapModel = new CustomMapSaveModel();
    }

    public void saveLevel() {
        
        this.loc = new LocationModel(
                (int) this.editorPane.getWidth(),
                (int) this.editorPane.getHeight(),
                this.editorPane.getName(),
                new HashMap<>(),
                new HashMap<>(),
                new ArrayList<>(),
                (this.editorPane.getDescFr() + "/" + this.editorPane.getDescEn()));
        mapModel.saveToJson(loc);

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
