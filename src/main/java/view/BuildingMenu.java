package view;

import controller.GameController;
import javafx.scene.control.Alert;
import javafx.scene.media.AudioClip;
import model.Building;
import model.MapObject;
import model.Settlement;
import javafx.scene.control.Button;
import runner.CivilizationGame;

/**
 * This class should represents the bulding menu
 *
 * @author Timothy Baba
 */
public class BuildingMenu extends AbstractMenu {
    /**
    * there should be an invest and demolish button for this menu
    * as well as functions associated with the buttons
    */
    public BuildingMenu() {
        //TODO
        Button invest = new Button("Invest");
        invest.setOnAction(e-> {
                MapObject occupant = GameController.getLastClicked().getTile()
                    .getOccupant();
                if (occupant.isBuilding()) {
                    if (occupant.getOwner().getTreasury().getCoins() >= 25) {
                        ((Building) occupant).invest();
                        occupant.getOwner().getTreasury().spend(25);
                        GameController.updateResourcesBar();
                        AudioClip plonkSound
                            = new AudioClip(CivilizationGame.getFile()
                                + "invest.mp3");
                        plonkSound.play();
                    } else {
                        Alert newAlert = new Alert(Alert.AlertType.ERROR);
                        newAlert.initOwner(CivilizationGame.getPrimaryStage());
                        newAlert.setTitle("Error Message");
                        newAlert
                            .setHeaderText("This action cannot be performed!");
                        newAlert.setContentText("You have insufficent gold!");
                        AudioClip plonkSound
                            = new AudioClip(CivilizationGame.getFile()
                                + "wave.mp3");
                        plonkSound.play();
                        newAlert.showAndWait();
                    }
                }

            });

        Button demolish = new Button("Demolish");
        demolish.setOnAction(e-> {
                MapObject occupant = GameController.getLastClicked().getTile()
                    .getOccupant();
                if (occupant.isBuilding()) {
                    if (occupant instanceof Settlement) {
                        if (GameController.getCivilization()
                            .getNumSettlements() > 1) {
                            ((Settlement) occupant).demolish();
                            GameController.getLastClicked().getTile()
                            .setOccupant(null);
                            occupant.getOwner().decrementNumSettlements();
                            AudioClip plonkSound
                                = new AudioClip("File:./src/main/java/view/"
                                    + "sounds/move.mp3");
                            plonkSound.play();
                        } else {
                            Alert newAlert = new Alert(Alert.AlertType.ERROR);
                            newAlert.initOwner(CivilizationGame
                                .getPrimaryStage());
                            newAlert.setTitle("Error Message");
                            newAlert.setHeaderText("You cannot demolish your "
                                + "only settlement");
                            AudioClip plonkSound
                                = new AudioClip(CivilizationGame.getFile()
                                    + "wave.mp3");
                            plonkSound.play();
                            newAlert.showAndWait();
                        }
                    } else {
                        ((Building) occupant).demolish();
                        GameController.getLastClicked().getTile()
                        .setOccupant(null);
                        AudioClip plonkSound
                            = new AudioClip(CivilizationGame.getFile()
                                + "move.mp3");
                        plonkSound.play();
                    }

                }
                GameController.getLastClicked().updateTileView();
                GameController.updateResourcesBar();
            });

        addMenuItem(invest);
        addMenuItem(demolish);
    }
}
