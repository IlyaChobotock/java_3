package lesson_3_5;

import java.util.concurrent.Semaphore;

public class Tunnel extends Stage {

    // Счетчик указывает нам, сколько потоков одновременно могут получать доступ
    private Semaphore semaphore;

    public Tunnel() {
        semaphore = new Semaphore(MainClass.CARS_COUNT / 2);
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
    }

    @Override
    public void go(Car c) {
        try {
            // По условию проверяем занято или нет
            if (!semaphore.tryAcquire()) {
                System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
                semaphore.acquire();
            }
            System.out.println(c.getName() + " начал этап: " + description);
            Thread.sleep(length / c.getSpeed() * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(c.getName() + " закончил этап: " + description);
            // освобождаем доступ
            semaphore.release();
        }
    }
}


