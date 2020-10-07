package lesson_3_4;

/**
 Создать три потока, каждый из которых выводит определенную букву (A, B и C) 5 раз (порядок
 – ABСABСABС). Используйте wait/notify/notifyAll.
**/

public class Main {

    static Object monitor = new Object();
    static volatile char currentLetter = 'A';       // запрет кэширования. Как понял, устанавливаем флаг, который далее используем как условие в while()
    private static int q = 5;

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                for (int i = 0; i < q; i++) {
                    synchronized (monitor) {
                        while (currentLetter != 'A') {    // для исключения ложной активизации по рекомендации Oracle вызывем метод wait() в цикле, проверяющем условие, по которму поток ожидает возобновление
                            monitor.wait();         // переход в режим ожидания, уступает монитор
                        }
                        System.out.print("A");
                        currentLetter = 'B';        // меняем флаг, чтобы следующий поток сработал по условию
                        monitor.notifyAll();        // возобновление исполнения потоков
                    }

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

// ниже прописаны аналогичные потоки с замененными флагами и необходимыми для печати буквами

        new Thread(() -> {
            try {
                for (int i = 0; i < q; i++) {
                    synchronized (monitor) {
                        while (currentLetter != 'B') {
                            monitor.wait();
                        }
                        System.out.print("B");
                        currentLetter = 'C';
                        monitor.notifyAll();
                    }

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                for (int i = 0; i < q; i++) {
                    synchronized (monitor) {
                        while (currentLetter != 'C') {
                            monitor.wait();
                        }
                        System.out.print("C");
                        currentLetter = 'A';
                        monitor.notifyAll();
                    }

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

    }

}
