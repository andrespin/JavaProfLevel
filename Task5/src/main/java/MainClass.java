
/*
Все участники должны стартовать одновременно, несмотря на то,
что на подготовку у каждого их них уходит разное время.
В тоннель не может заехать одновременно больше половины участников (условность).
Попробуйте все это синхронизировать.
Только после того, как все завершат гонку, нужно выдать объявление об окончании.
Можете корректировать классы (в т.ч. конструктор машин) и добавлять объекты классов
 из пакета util.concurrent.
 */


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class MainClass {

    private static final RaceStatus raceStatus = new RaceStatus();

    public static final int CARS_COUNT = 4;

    public static final int HALF_CARS_COUNT = CARS_COUNT/2;

    private static CountDownLatch cdl = new CountDownLatch(CARS_COUNT);

    private static CountDownLatch cdl2 = new CountDownLatch(CARS_COUNT);

    private static Semaphore smp = new Semaphore(HALF_CARS_COUNT);

    public static void main(String[] args) {

        Race race = new Race(new Road(60), new Tunnel(smp), new Road(40));

        Car[] cars = new Car[CARS_COUNT];

        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10), cdl, cdl2 , raceStatus);
        }

        for (int i = 0; i < cars.length; i++) {
             new Thread(cars[i]).start();
        }

    }
}