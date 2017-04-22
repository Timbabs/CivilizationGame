package model;

/**
 * Represnets an American Civilization
 *
 * @author Timothy Baba
 */
public class America extends Civilization {
    private Hills hills = new Hills();
    public America() {

        super("America");
    }

    public String explore() {
        int food = hills.hunt().getHealth();
        hills.replenishGame();
        makeFood(food);
        return "You go hunting and get " + food + " food!";
    }

    public Hills getHills() {
        return hills;
    }

    @Override
    public SiegeUnit getSiegeUnit() {
        return new BlackPowderUnit(this);
    }

    @Override
    public Landmark getLandmark() {
        return new Coliseum(this);
    }
}
