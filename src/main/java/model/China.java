package model;

/**
 * Created by timothybaba on 11/29/16.
 */
public class China extends Civilization {
    private Hills hills = new Hills();

    public China() {
        super("China");
    }

    public String explore() {
        int resources = hills.mineCoal();
        produceResources(resources);
        return "You go mining and get " + resources + " resources!";
    }

    public Hills getHills() {
        return hills;
    }

    @Override
    public RangedUnit getRangedUnit() {
        return new WarChariotUnit(this);
    }

    @Override
    public Landmark getLandmark() {
        return new GreatWall(this);
    }
}
