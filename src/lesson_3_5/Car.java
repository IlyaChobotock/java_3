package lesson_3_5;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

public class Car implements Runnable {

    private static AtomicInteger atomicInteger;
    private static int CARS_COUNT;

    static {
        atomicInteger = new AtomicInteger(0);
    }

    private Race race;
    private int speed;
    private String name;
    CyclicBarrier barrier = new CyclicBarrier(CARS_COUNT + 1);

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public Car(Race race, int speed, CyclicBarrier barrier) {
        this.race = race;
        this.speed = speed;
        this.barrier = barrier;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }

    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int) (Math.random() * 800));
            System.out.println(this.name + " готов");

            barrier.await();
            barrier.await();

            for (int i = 0; i < race.getStages().size(); i++) {
                race.getStages().get(i).go(this);
            }

            // проверка по атомику для сокращения времени на проверку
            if (atomicInteger.incrementAndGet() == 1) {
                System.out.println(name + " - W I N");
            }

            barrier.await();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
