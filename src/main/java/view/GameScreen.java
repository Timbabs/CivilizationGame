package view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import controller.GameController;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import runner.CivilizationGame;


/**
 * This class represents the GameScreen class
 *
 * @author Timothy Baba
 */
public class GameScreen extends BorderPane {
    private AudioClip plonkSound;
    private static VBox vbox = new VBox();
    private static ResourcesMenu resMenu;
    /**
     * Creates a new view into the game. this layout should include
     * the rescource bar, grid map, and action menus
     *
     */
    public GameScreen() {
        StartScreen.getPlonkSound2().stop();
        //goBack.getChildren().addAll(roundButton);
        Image image2 = new Image("File:./src/main/java/view/giphy.gif");
        String file = "File:./src/main/java/view/sounds/loop.wav";
        plonkSound = new AudioClip(file);
        plonkSound.setCycleCount(AudioClip.INDEFINITE);
        plonkSound.play();

        ImageView imv = new ImageView(image2);
        imv.fitWidthProperty().bind(CivilizationGame.getPrimaryStage()
            .widthProperty());
        imv.fitHeightProperty().bind(CivilizationGame.getPrimaryStage()
            .heightProperty());
        resMenu = new ResourcesMenu();
        this.getChildren().addAll(imv);
        vbox.getChildren().addAll(getAbstractMenu().getRootNode());
        this.setTop(resMenu.getRootNode());
        this.setLeft(vbox);
    }

    /**
     * This method should update the gridfx and the resouce bar
     */
    public void update() {
        GridFX.update();
        GameController.updateResourcesBar();

    }
    /**
    * this method should return the resource menu
    * @return reosuce menu
    */
    public static ResourcesMenu getResources() {
        //TODO
        return resMenu;
    }

    public AbstractMenu getAbstractMenu() {
        return new AbstractMenu();
    }


    /**
     * This method switches menus based on passed in game state.
     * Game.java calls this to selectively control which menus are displayed
     * @param state
     */
    public static void switchMenu(GameController.GameState state) {
        if (state == GameController.GameState.MILITARY) {
            vbox.getChildren().clear();
            vbox.getChildren().add(new MilitaryMenu().getRootNode());
        } else if (state == GameController.GameState.WORKER) {
            vbox.getChildren().clear();
            vbox.getChildren().add(new WorkerMenu().getRootNode());

        } else if (state == GameController.GameState.RECRUITING) {
            vbox.getChildren().clear();
            vbox.getChildren().add(new RecruitMenu().getRootNode());
        } else if (state == GameController.GameState.BUILDING) {
            vbox.getChildren().clear();
            vbox.getChildren().add(new BuildingMenu().getRootNode());
        } else if (state == GameController.GameState.NEUTRAL) {
            vbox.getChildren().clear();
            vbox.getChildren().add(new StatusMenu().getRootNode());
        }

    }
}
