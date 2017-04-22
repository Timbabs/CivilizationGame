package model;

/**
 * Created by timothybaba on 11/29/16.
 */
public class Aztec extends Civilization {
    private Desert desert = new Desert();

    public Aztec() {
        super("Aztec");
    }

    public String explore() {
        int gold = desert.findTreasure();
        getTreasury().earn(gold);
        return "You explore the desert and find " + gold + " gold!";
    }

    public Desert getDesert() {
        return desert;
    };

    @Override
    public MeleeUnit getMeleeUnit() {
        return new LegionUnit(this);
    }

    @Override
    public Landmark getLandmark() {
        return new Pyramid(this);
    }
}
