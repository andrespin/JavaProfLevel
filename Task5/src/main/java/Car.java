import java.util.concurrent.CountDownLatch;

public class Car implements Runnable {
    private static int CARS_COUNT;

    static {
        CARS_COUNT = 0;
    }

    private Race race;
    private int speed;
    private String name;
    private RaceStatus raceStatus;
    private CountDownLatch cdl;
    private CountDownLatch cdl2;

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public Car(Race race, int speed, CountDownLatch cdl, CountDownLatch cdl2 , RaceStatus raceStatus) {
        this.race = race;
        this.speed = speed;
        this.cdl = cdl;
        this.cdl2 = cdl2;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
        this.raceStatus = raceStatus;
    }

    @Override
    public void run() {
        raceStatus.printAboutRacePreparation();
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int)(Math.random() * 800));
            cdl.countDown();
            System.out.println(this.name + " готов");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            cdl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        raceStatus.printAboutRaceStart();
        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }
        raceStatus.printWinner(this);

        cdl2.countDown();

        try {
            cdl2.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        raceStatus.printAboutRaceEnd();

    }

}