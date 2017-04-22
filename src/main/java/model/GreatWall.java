package model;

import javafx.scene.image.Image;

/**
 * Represnts a Great Wall that can increase strategy.
 *
 * @author Timothy Baba
 */
class GreatWall extends Landmark {

    /**
     * Public constructor.
     *
     * @param owner the Civilization that owns this Building.
     */
    public GreatWall(Civilization owner) {
        super(owner);
    }

    @Override
    public void invest() {
        super.invest();
        getOwner().getStrategy().battle();
    }

    @Override
    public String toString() {
        return "Great Wall " + super.toString();
    }

    @Override
    public Image getImage() {
        return new Image("File:./src/main/java/view/Civ_Icon/"
                + "great_wall_icon.PNG");
    }
}
