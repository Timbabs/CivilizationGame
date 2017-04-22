package view;

import javafx.scene.control.ButtonType;
import controller.GameController;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.media.AudioClip;
import model.Unit;
import javafx.scene.control.Button;
import runner.CivilizationGame;

import java.util.Optional;

/**
 * @author Timothy Baba
 */
public class RecruitMenu extends AbstractMenu {
    private String selected = null;

    public RecruitMenu() {

        ObservableList<String> data = FXCollections.observableArrayList();
        data.addAll("Melee Unit", "Ranged Unit", "Hybrid Unit", "Siege Unit",
            "Settlers", "Farmers", "Coal Miners", "Anglers", "Master Builders");
        ListView<String> listView = new ListView<>(data);
        listView.setPrefSize(65, 270);

        listView.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends String> ov, String oldVal,
                 String newVal) -> {
                selected = newVal;
            }
        );
        Button select = new Button("Select");
        select.setOnAction(e -> {
                TerrainTileFX tileSelected = GameController.getLastClicked();
                Boolean recruited = false;
                Boolean canceled = false;
                if (selected.equals("Melee Unit")) {
                    Unit unit = GameController.getCivilization().getMeleeUnit();
                    if (unit.isAffordable()) {
                        tileSelected.getTile().setOccupant(unit);
                        recruited = true;
                    }
                } else if (selected.equals("Ranged Unit")) {
                    Unit unit = GameController.getCivilization()
                        .getRangedUnit();
                    if (unit.isAffordable()) {
                        tileSelected.getTile().setOccupant(unit);
                        recruited = true;
                    }
                } else if (selected.equals("Hybrid Unit")) {
                    Unit unit = GameController.getCivilization()
                        .getHybridUnit();
                    if (unit.isAffordable()) {
                        tileSelected.getTile().setOccupant(unit);
                        recruited = true;
                    }
                } else if (selected.equals("Siege Unit")) {
                    Unit unit = GameController.getCivilization()
                        .getSiegeUnit();
                    if (unit.isAffordable()) {
                        tileSelected.getTile().setOccupant(unit);
                        recruited = true;
                    }
                } else if  (selected.equals("Settlers")) {
                    TextInputDialog dialog
                        = new TextInputDialog("Settlement Name");
                    dialog.initOwner(CivilizationGame.getPrimaryStage());
                    dialog.setTitle("Settler Unit");
                    dialog
                        .setHeaderText("You have selected to recruit settlers");
                    dialog
                        .setContentText("Enter the Name of your settler unit:");
                    Optional result = dialog.showAndWait();

                    if (result.isPresent() && result.get() instanceof String) {
                        Unit unit = GameController.getCivilization()
                            .getSettlerUnit(((String) result.get()));
                        if (unit.isAffordable()) {
                            tileSelected.getTile().setOccupant(unit);
                            recruited = true;
                        }
                    } else if (result.get() instanceof ButtonType) {
                        if (result.get() == ButtonType.CANCEL) {
                            canceled = true;
                        }
                    }
                } else if (selected.equals("Farmers")) {
                    Unit unit = GameController.getCivilization()
                        .getFarmerUnit();
                    if (unit.isAffordable()) {
                        tileSelected.getTile().setOccupant(unit);
                        recruited = true;
                    }
                } else if (selected.equals("Coal Miners")) {
                    Unit unit = GameController.getCivilization()
                        .getCoalMinerUnit();
                    if (unit.isAffordable()) {
                        tileSelected.getTile().setOccupant(unit);
                        recruited = true;
                    }
                } else if (selected.equals("Angler")) {
                    Unit unit = GameController.getCivilization()
                        .getAnglerUnit();
                    if (unit.isAffordable()) {
                        tileSelected.getTile().setOccupant(unit);
                        recruited = true;
                    }
                } else if (selected.equals("Master Builders")) {
                    Unit unit = GameController.getCivilization()
                        .getMasterBuilderUnit();
                    if (unit.isAffordable()) {
                        tileSelected.getTile().setOccupant(unit);
                        recruited = true;
                    }
                }
                if (!recruited && !canceled) {
                    Alert newAlert = new Alert(Alert.AlertType.ERROR);
                    newAlert.initOwner(CivilizationGame.getPrimaryStage());
                    newAlert.setTitle("Error Message");
                    newAlert.setHeaderText("You cannot afford this unit");
                    AudioClip plonkSound = new AudioClip(CivilizationGame
                        .getFile() + "wave.mp3");
                    plonkSound.play();
                    newAlert.showAndWait();
                } else {
                    AudioClip plonkSound = new AudioClip(CivilizationGame
                        .getFile() + "win.mp3");
                    plonkSound.play();
                }
                GameController.getLastClicked().updateTileView();
                GameController.updateResourcesBar();
            });

        addMenuItem(listView);
        addMenuItem(select);
    }
}
