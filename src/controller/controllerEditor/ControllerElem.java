package controller.controllerEditor;

import java.util.ArrayList;

import model.modelGame.GameMapModel;
import model.modelLocation.LocationModel;
import view.viewEditor.SelectTypeElem;

public class ControllerElem {
    // ArrayList<LocationModel>
    private final GameMapModel mapModel;
    private final SelectTypeElem selectTypeElem;

    public ControllerElem(SelectTypeElem selectTypeElem) {
        this.selectTypeElem = selectTypeElem;
        this.mapModel = new GameMapModel();
    }

    public String[] getLevelName() {
        ArrayList<LocationModel> listLocation = this.mapModel.getLocations();
        String[] names = new String[listLocation.size()];

        // Boucle pour remplir le tableau avec les noms des locations
        for (int i = 0; i < listLocation.size(); i++) {
            names[i] = listLocation.get(i).getName();
        }
        return names;
    }

}
