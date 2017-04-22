package view;

import controller.GameController;
import javafx.scene.control.Button;

/**
 * @author Timothy Baba
 */

public class MilitaryMenu extends AbstractMenu {
    /**
    * Implement the buttons and actions associated with
    * the buttons for the military menu
    */
    public MilitaryMenu() {
        Button attack = new Button("Attack");
        attack.setOnAction(e-> {
                GameController.attacking();
                GameController.updateResourcesBar();
            });
        Button move = new Button("Move");
        move.setOnAction(e-> {
                GameController.moving();
                GameController.updateResourcesBar();
            });
        this.addMenuItem(attack);
        this.addMenuItem(move);
    }

}
