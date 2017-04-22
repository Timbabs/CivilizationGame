package runner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import javafx.scene.control.TextInputDialog;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;

import view.StartScreen;
import view.CivEnum;
import view.GridFX;
import view.GameScreen;
import controller.GameController;
import model.Civilization;
import model.RomanEmpire;
import model.QinDynasty;
import model.Aztec;
import model.America;
import model.China;
import model.Egypt;
import model.Map;
import model.Bandit;

/**
 * Launches and displays the javafx Game/Application.
 *
 * @author Timothy Baba
 */

public class CivilizationGame extends Application {
    private String townName;
    private Civilization civ;
    private static Stage primaryStage;
    private static int rowNum;
    private static int colNum;
    private int enemies;
    private static String file = "File:./src/main/java/view/sounds/";

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static int getRowNum() {
        return rowNum;
    }

    public static int getColNum() {
        return colNum;
    }

    public static String getFile() {
        return file;
    }



    /**
     * this method is called upon running/launching the application
     * this method should display a scene on the stage
     */

    public void start(Stage stage) {
        stage.setMinWidth(1255);
        stage.setMaxHeight(900);
        this.primaryStage = stage;
        stage.setScene(startGame());
        //primaryStage.setMaximized(true);
        stage.show();
    }
    /**
     * This is the main method that launches the javafx application
     */
    public static void main(String[] args) {
        launch(args);
    }
    /**
    * This method is responsible for setting the scene to the corresponding
    * layout.
    * and returning the scene.
    * @return Scene
    */

    public  Scene startGame() {

        StartScreen root = new StartScreen();
        root.setAlignment(Pos.BOTTOM_CENTER);
        root.setPadding(new Insets(25, 25, 25, 25));


        Button btn = root.getStartButton();
        ImageView imv = root.getImv();
        //imv.fitWidthProperty().bind(primaryStage.widthProperty());
        //imv.setPreserveRatio(true);
        imv.fitWidthProperty().bind(primaryStage.widthProperty());
        imv.fitHeightProperty().bind(primaryStage.heightProperty());

        btn.setOnAction(e -> {
                String selectedAnswer = null;

                TextInputDialog dialog = new TextInputDialog("Town Name");
                dialog.initOwner(primaryStage);
                dialog.setTitle("A new Settlement");
                dialog.setHeaderText("You have built a Settlement!");
                dialog.setContentText("Enter the Name of your first town:");
                Optional<String> result = dialog.showAndWait();
                if (result.isPresent()) {
                    townName = result.get();
                    if (StartScreen.getCivilizationSelected()
                        == CivEnum.ANCIENT_EGYPT) {
                        civ = new Egypt();
                    } else if (StartScreen.getCivilizationSelected()
                        == CivEnum.QIN_DYNASTY) {
                        civ = new QinDynasty();
                    } else if (StartScreen.getCivilizationSelected()
                        == CivEnum.ROMAN_EMPIRE) {
                        civ = new RomanEmpire();
                    } else if (StartScreen.getCivilizationSelected()
                        == CivEnum.Aztec) {
                        civ = new Aztec();
                    } else if (StartScreen.getCivilizationSelected()
                        == CivEnum.ANCIENT_CHINA) {
                        civ = new China();
                    } else if (StartScreen.getCivilizationSelected()
                        == CivEnum.America) {
                        civ = new America();
                    }

                }
                List<String> choices = new ArrayList<>();
                choices.add("Small");
                choices.add("Medium");
                choices.add("Large");

                ChoiceDialog<String> input
                    = new ChoiceDialog<>("select", choices);
                input.initOwner(primaryStage);
                input.setTitle("Map");
                input.setHeaderText("Create your map size");
                input.setContentText("Select the map size size "
                    + " you wish to play on:");
                Optional<String> answer = input.showAndWait();
                if (result.isPresent()) {
                    selectedAnswer = answer.get();
                }
                GameController.setCivilization(civ);
                //Random rand = new Random();
                if (selectedAnswer.equals("Small")) {
                    rowNum = 10;
                    colNum = 10;
                    enemies = 4;
                } else if (selectedAnswer.equals("Medium")) {
                    rowNum = 10;
                    colNum = 13;
                    enemies = 6;
                } else if (selectedAnswer.equals("Large")) {
                    rowNum = 10;
                    colNum = 16;
                    enemies = 8;
                }
                Random rand = new Random();

                GridFX grid = GridFX.getInstance();
                GridFX.update();
                GridFX.getMap()
                    .putSettlement(townName, civ, rowNum / 2, colNum / 2);

                grid.setPrefSize(300, 200);
                GameScreen gameRoot = new GameScreen();
                gameRoot.setCenter(grid);
                Scene scene = new Scene(gameRoot, 1255, 900);
                Map.addEnemies(new Bandit(), enemies);
                gameRoot.update();
                Boolean full = false;
                if (primaryStage.isFullScreen()) {
                    full = true;
                }
                primaryStage.setScene(scene);
                if (full) {
                    primaryStage.setFullScreen(true);
                }
            });

        Scene scene = new Scene(root, 1255, 900);
        return scene;

    }




}
