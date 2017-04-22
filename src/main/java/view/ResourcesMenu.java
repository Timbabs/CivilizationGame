package view;

import controller.GameController;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

/**
 * Represents civlization's resources
 *
 * @author Timothy Baba
 */


public class ResourcesMenu {
    //private HBox ResMenu = new HBox();
    private Label strategyLevel = new Label();
    private Label resources = new Label();
    private Label settlements = new Label();
    private Label money = new Label();
    private Label food = new Label();
    private Label happiness = new Label();

    private HBox resourceBar;
    /**
    * creates a resource bar and display the current state of
    * your civilization's resouces
    */
    public ResourcesMenu() {
        //TODO
        resourceBar = new HBox();
        strategyLevel.setStyle("-fx-font-weight: bold");
        strategyLevel.setTextFill(Color.WHITE);
        resources.setStyle("-fx-font-weight: bold");
        resources.setTextFill(Color.WHITE);
        settlements.setStyle("-fx-font-weight: bold");
        settlements.setTextFill(Color.WHITE);
        money.setStyle("-fx-font-weight: bold");
        money.setTextFill(Color.WHITE);
        food.setStyle("-fx-font-weight: bold");
        food.setTextFill(Color.WHITE);
        happiness.setStyle("-fx-font-weight: bold");
        happiness.setTextFill(Color.WHITE);

        update();

    }
    /**
    * should update all the resouce values to the current
    * state of your resource values
    */
    public void update() {
        strategyLevel.setText("Strat Level: " + GameController.getCivilization()
            .getStrategy().getStrategyLevel());
        resources.setText("\tResources: " + GameController.getCivilization()
            .getResources());
        settlements.setText("\tSettlement: " + GameController.getCivilization()
            .getNumSettlements());
        money.setText("\tMoney: " + GameController.getCivilization()
            .getTreasury().getCoins());
        food.setText("\tFood: " + GameController.getCivilization().getFood());
        happiness.setText("\tHappniess: " + GameController.getCivilization()
            .getHappiness());
        resourceBar.getChildren().clear();
        resourceBar.getChildren()
            .addAll(strategyLevel, resources, settlements, money,
                food, happiness);

    }
    /**
    * updates the resource bar and returns the resource bar
    * @return a hbox representation of the resource bar
    */
    public HBox getRootNode() {
        //TODO
        update();

        return resourceBar;
    }
}