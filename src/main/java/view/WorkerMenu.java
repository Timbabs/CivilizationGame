package view;

import controller.GameController;
import controller.TileType;
import javafx.scene.control.Alert;
import javafx.scene.media.AudioClip;
import model.Convertable;
import model.MapObject;

import javafx.scene.control.Button;
import runner.CivilizationGame;

/**
 * @author Timothy Baba
 */
public class WorkerMenu extends AbstractMenu {
    private static Button moveButton;
    /**
    * There should be a convert and move button
    * as well as the functions associated with those
    * buttons
    */
    public WorkerMenu() {
        //TODO
        moveButton = new Button("Move");
        moveButton.setOnAction(e-> {
                GameController.moving();
                GameController.updateResourcesBar();
            });

        Button convert =  new Button("Convert");
        convert.setOnAction(e-> {
                MapObject answer = GameController.getLastClicked().getTile()
                    .getOccupant();
                TileType type = GameController.getLastClicked().getTile()
                    .getType();

                if (answer.isWorker() && ((Convertable) answer)
                        .canConvert(type)) {
                    MapObject converted = ((Convertable) answer).convert();
                    GameController.getLastClicked().getTile()
                        .setOccupant(converted);
                    GameController.getLastClicked().updateTileView();
                    GameController.updateResourcesBar();
                    AudioClip plonkSound = new AudioClip(CivilizationGame
                        .getFile() + "convert.mp3");
                    plonkSound.play();
                } else {
                    Alert newAlert = new Alert(Alert.AlertType.ERROR);
                    newAlert.initOwner(CivilizationGame.getPrimaryStage());
                    newAlert.setTitle("Error Message");
                    newAlert
                        .setHeaderText("The tile selected cannot be "
                            + "converted!");
                    AudioClip plonkSound = new AudioClip(CivilizationGame
                        .getFile() + "wave.mp3");
                    plonkSound.play();
                    newAlert.showAndWait();
                }
            });

        addMenuItem(moveButton);
        addMenuItem(convert);
    }
}

