package model;

/**
 * Represents the Egypt Civilization.
 *
 * @author Timothy Baba
 */
public class Egypt extends Civilization {
    private Desert desert = new Desert();

    /**
     * Public constructor.
     */
    public Egypt() {
        super("Egypt");
    }

    @Override
    public String explore() {
        int gold = desert.findTreasure();
        getTreasury().earn(gold);
        return "You explore the desert and find " + gold + " gold!";
    }

    /**
     * @return this civilization's Desert.
     */
    public Desert getDesert() {
        return desert;
    }

    @Override
    public RangedUnit getRangedUnit() {
        return new WarChariotUnit(this);
    }

    @Override
    public Landmark getLandmark() {
        return new Pyramid(this);
    }
}
