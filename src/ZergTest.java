import java.util.Scanner;

public class ZergTest extends Thread {
    public static void main(String[] args) throws InterruptedException {

        // testing
        // Hatchery h = new Hatchery();
        // h.setUnit("baneling");
        // System.out.println(h.getInfo());
        // h.spawnUnits();
        // h.spawnUnits();
        // h.spawnUnits();
        // System.out.println(h.getInfo());

        // HiveMind h = new Hatchery();
        // h.spawnUnits();
        // System.out.println(h.getInfo());
        //
        // h = new Zergling();
        // h.spawnUnits();
        // System.out.println(h.getInfo());

        // declares the threads
        Thread t1 = new Thread() {
            public void run() {
                HiveMind h = new Hatchery();
                try {
                    h.harvestMinerals();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread t2 = new Thread() {
            public void run() {
                HiveMind h;
                System.out.println("********************************\n" +
                        "Welcome to Command Line StarCraft 2!\n" +
                        "Enter the following commands to play:\n" +
                        "'drone' to spawn drones,\n" +
                        "'zergling' to spawn zerglings,\n" +
                        "'baneling' to spawn banelings,\n" +
                        "'roach' to spawn roaches,\n" +
                        "'quit' to quit the game");

                Scanner sc = new Scanner(System.in);

                while (sc.hasNextLine()) {
                    String s = sc.nextLine();
                    try {
                        switch (s) {
                            case "drone":
                                h = new Drone(new Hatchery());
                                // prints unit info
                                // System.out.println(h.getInfo());
                                break;
                            case "zergling":
                                h = new Zergling(new Hatchery());
                                // prints unit info
                                // System.out.println(h.getInfo());
                                break;
                            case "baneling":
                                h = new Baneling(new Hatchery());
                                // prints unit info
                                // System.out.println(h.getInfo());
                                break;
                            case "roach":
                                h = new Roach(new Hatchery());
                                // prints unit info
                                // System.out.println(h.getInfo());
                                break;
                            case "quit":
                                System.err.println("GAME OVER!");
                                sc.close();
                                t1.interrupt();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                // if(s.equals("quit"))sc.close();
            }
        };

        // run the threads concurrently
        t1.start();
        t2.start();
    }
}
