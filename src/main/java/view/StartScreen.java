package view;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.layout.StackPane;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import javafx.scene.media.AudioClip;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.layout.HBox;

/**
 * This class represents the Start Screen for the Civ applicatios. This is the
 * layout that should be displayed upon running the Civ application.
 *
 * @author Timothy Baba
 */
public class StartScreen extends StackPane {
    private static CivEnum  civilizationSelected;
    private Button btn;
    private ImageView imv;
    private static AudioClip plonkSound2;
    private static final String STANDARD_BUTTON_STYLE
        = "-fx-background-color: linear-gradient(#61a2b1, #2A5058)";
    private static  final String HOVERED_BUTTON_STYLE
        = "-fx-background-color: linear-gradient(#2A5058, #61a2b1)";

    /**
    * constuctor of the start screen sets the background
    * image and displays a list of civilizations and a start button
    */
    public StartScreen() {
        Image image2
            = new Image("File:./src/main/java/view/civ_background.png");
        imv = new ImageView(image2);
        String file = "File:./src/main/java/view/sounds/intro.mp3";
        plonkSound2 = new AudioClip(file);
        plonkSound2.play();

        btn = new Button("START");
        btn.setTextFill(Color.WHITE);
        btn.setFont(Font.font("Arial Narrow"));
        btn.setStyle("-fx-font-weight: bold");

        changeBackgroundOnHoverUsingBinding(btn);
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.CENTER);
        hbBtn.getChildren().add(btn);


        GridPane grid = new GridPane();
        grid.setAlignment(Pos.BOTTOM_CENTER);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("Select a Civilization to Begin");
        scenetitle.setFont(Font.font("Arial Narrow", 13));
        scenetitle.setFill(Color.WHITE);
        HBox hey = new HBox(scenetitle);
        hey.setAlignment(Pos.CENTER);
        hey.setStyle("-fx-background-color: linear-gradient(#2A5058, #61a2b1)");

        Image image = new Image("File:./src/main/java/view/fireWorks.gif");
        ImageView imv2 = new ImageView(image);
        imv2.setFitHeight(200);
        imv2.setFitWidth(500);
        this.setAlignment(Pos.BASELINE_CENTER);
        grid.add(hey, 1, 1);
        grid.add(getCivList(), 1, 3);
        grid.add(hbBtn, 1, 4);
        this.getChildren().addAll(imv, imv2, grid);
    }

    public static AudioClip getPlonkSound2() {
        return plonkSound2;
    }

    private void changeBackgroundOnHoverUsingBinding(Node node) {
        node.styleProperty().bind(
                Bindings
                        .when(node.hoverProperty())
                        .then(
                                new SimpleStringProperty(HOVERED_BUTTON_STYLE)
                        )
                        .otherwise(
                                new SimpleStringProperty(STANDARD_BUTTON_STYLE)
                        )
        );
    }

    /**
    * gets the start button
    * @return the start button
    */
    public Button getStartButton() {
        return btn;
    }
    /**
    * return a ListView of CivEnums representing the list of
    * available civilizations to choose from
    * @return listview of CivEnum
    */
    public ListView<CivEnum> getCivList() {
        ObservableList<CivEnum> data = FXCollections.observableArrayList();
        for (CivEnum civ: CivEnum.values()) {
            data.add(civ);
        }
        ListView<CivEnum> listView = new ListView<>(data);
        listView.setPrefSize(200, 100);

        listView.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends CivEnum> ov, CivEnum oldVal,
                 CivEnum newVal) -> {
                civilizationSelected = newVal;

            }
        );
        return listView;

    }
    public static CivEnum getCivilizationSelected() {
        return civilizationSelected;
    }

    public ImageView getImv() {
        return imv;
    }
}