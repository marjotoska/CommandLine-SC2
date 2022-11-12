public interface HiveMind {
    void setUnit(String newUnit);

    String getUnit();

    void setHP(int newHP);

    int getHP();

    void spawnUnits();

    int getUnitCount();

    String getInfo();

    void addWorker();

    void harvestMinerals() throws InterruptedException;

    void setGameState();

    void spendMinerals(int value);
}

class Hatchery implements HiveMind {
    private String unit;
    private int HP = 0;
    private static int unitCount = 0;
    private static int minerals = 50;
    private boolean gameState = true;
    private static int workerCount = 12;

    @Override
    public void setUnit(String newUnit) {
        this.unit = newUnit;
    }

    @Override
    public String getUnit() {
        return unit;
    }

    @Override
    public void setHP(int newHP) {
        this.HP = newHP;
    }

    @Override
    public int getHP() {
        return HP;
    }

    @Override
    public void spawnUnits() {
        unitCount++;
    }

    @Override
    public int getUnitCount() {
        return unitCount;
    }

    @Override
    public String getInfo() {
        return "There are " + getUnitCount() + " units in the side of zerg\n"
                + getUnit() + "'s hp is: " + getHP();
    }

    @Override
    public void addWorker() {
        workerCount++;
    }

    @Override
    public void harvestMinerals() throws InterruptedException {
        Thread.sleep(4000);
        System.out.println("Minerals: " + (minerals += (workerCount * 3)));
        while (gameState) {
            harvestMinerals();
        }
    }

    @Override
    public void setGameState() {
        gameState = false;
    }

    @Override
    public void spendMinerals(int value) {
        // prevent minerals going lower than 0
        if (minerals <= 0) {
            minerals = 0;
        }
        minerals -= value;
    }
}

// The concrete Decorator class
abstract class Player implements HiveMind {
    private final HiveMind p;

    public Player(HiveMind p) {
        this.p = p;
    }

    @Override
    public void setUnit(String newUnit) {
        p.setUnit(newUnit);
    }

    @Override
    public String getUnit() {
        return p.getUnit();
    }

    @Override
    public void setHP(int newHP) {
        p.setHP(newHP);
    }

    @Override
    public int getHP() {
        return p.getHP();
    }

    @Override
    public void spawnUnits() {
        p.spawnUnits();
    }

    @Override
    public int getUnitCount() {
        return p.getUnitCount();
    }

    @Override
    public String getInfo() {
        return p.getInfo();
    }

    @Override
    public void harvestMinerals() throws InterruptedException {
        p.harvestMinerals();
    }

    @Override
    public void spendMinerals(int value) {
        p.spendMinerals(value);
    }

    @Override
    public void addWorker() {
        p.addWorker();
    }

    @Override
    public void setGameState() {
        p.setGameState();
    }
}

class Drone extends Player {
    public Drone(HiveMind p) {
        super(p);
        setUnit("Drone");
        setHP(40);
        addWorker();
        spendMinerals(50);
        spawnUnits();
    }

    @Override
    public String getInfo() {
        return super.getInfo()
                + "\nBasic worker unit. Can harvest minerals and Vespene Gas. Can mutate into structures.";
    }
}

class Zergling extends Player {
    public Zergling(HiveMind p) {
        super(p);
        setUnit("Zergling");
        setHP(35);
        spendMinerals(50);
        spawnUnits();
        spawnUnits();
    }

    @Override
    public String getInfo() {
        return super.getInfo() + "\nFast melee creature. Can morph into a Baneling. Hatch in pairs.";
    }
}

class Baneling extends Player {
    public Baneling(HiveMind p) {
        super(p);
        setUnit("Baneling");
        setHP(35);
        spendMinerals(25);
        spawnUnits();
    }

    @Override
    public String getInfo() {
        return super.getInfo() + "\nKamikaze unit. Does damage over a small area.";
    }
}

class Roach extends Player {
    public Roach(HiveMind p) {
        super(p);
        setUnit("Roach");
        setHP(145);
        spendMinerals(75);
        spawnUnits();
    }

    @Override
    public String getInfo() {
        return super.getInfo() + "\nAssault unit. Regenerates health quickly when burrowed.";
    }
}
