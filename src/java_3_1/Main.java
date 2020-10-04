package java_3_1;

/*
1. Написать метод, который меняет два элемента массива местами (массив может быть любого
ссылочного типа);
2. Написать метод, который преобразует массив в ArrayList;
3. Задача:
a. Даны классы Fruit, Apple extends Fruit, Orange extends Fruit;
b. Класс Box, в который можно складывать фрукты. Коробки условно сортируются по
типу фрукта, поэтому в одну коробку нельзя сложить и яблоки, и апельсины;
c. Для хранения фруктов внутри коробки можно использовать ArrayList;
d. Сделать метод getWeight(), который высчитывает вес коробки, зная вес одного фрукта
и их количество: вес яблока – 1.0f, апельсина – 1.5f (единицы измерения не важны);
e. Внутри класса Box сделать метод compare(), который позволяет сравнить текущую
коробку с той, которую подадут в compare() в качестве параметра. true – если их массы
равны, false в противоположном случае. Можно сравнивать коробки с яблоками и
апельсинами;
f. Написать метод, который позволяет пересыпать фрукты из текущей коробки в другую.
Помним про сортировку фруктов: нельзя яблоки высыпать в коробку с апельсинами.
Соответственно, в текущей коробке фруктов не остается, а в другую перекидываются
объекты, которые были в первой;
g. Не забываем про метод добавления фрукта в коробку.
 */

import com.sun.org.apache.xpath.internal.operations.Or;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        // Задание 1

        Integer[] arrInteger = {1, 2, 3, 4, 5};
        Float[] arrFloat = {1.1f, 2.2f, 3.3f, 4.4f, 5.5f};
        String[] arrString = {"a", "b", "c", "d", "e"};

        System.out.println("== Задание 1 ==\n");
        changePlace(arrInteger, 2, 3);
        changePlace(arrFloat, 1, 2);
        changePlace(arrString, 3, 4);

        // Задание 2

        toArrayList(arrString);

        // Задание 3

        // Создаем коробки
        Box<Apple> appleBox1 = new Box<>();
        Box<Apple> appleBox2 = new Box<>();
        Box<Apple> appleBox3 = new Box<>();
        Box<Orange> orangeBox1 = new Box<>();
        Box<Orange> orangeBox2 = new Box<>();
        Box<Orange> orangeBox3 = new Box<>();

        // Добавляем фрукты и их количество
        appleBox1.addFruit(new Apple(), 10);
        appleBox2.addFruit(new Apple(), 13);
        appleBox3.addFruit(new Apple(), 10);
        orangeBox1.addFruit(new Orange(), 13);
        orangeBox2.addFruit(new Orange(), 25);
        orangeBox3.addFruit(new Orange(), 10);

        // Смотрим результат добаления
        System.out.println("\n== Задание 3 ==\n");
        System.out.println("После добавления фруктов имеем следующее распределение по коробкам:\n");
        System.out.println("Коробка 1. Яблоки: " + appleBox1.getWeight() + " центнеров");
        System.out.println("Коробка 2. Яблоки: " + appleBox2.getWeight() + " центнеров");
        System.out.println("Коробка 3. Яблоки: " + appleBox3.getWeight() + " центнеров");
        System.out.println("Коробка 1. Апельсины: " + orangeBox1.getWeight() + " центнеров");
        System.out.println("Коробка 2. Апельсины: " + orangeBox2.getWeight() + " центнеров");
        System.out.println("Коробка 3. Апельсины: " + orangeBox3.getWeight() + " центнеров");

        // Проверяем на эквивалентность (часть коробок)
        System.out.println("\nСравнение коробок:\n");
        System.out.println("Коробка 1 с яблоками и Коробка 3 с яблоками: " + appleBox1.compare(appleBox3));
        System.out.println("Коробка 2 с апельсинами и Коробка 2 с яблоками: " + orangeBox2.compare(appleBox2));

        // Пересыпаем
        System.out.println("\nРезультат пересыпания:\n");
        appleBox1.intersperse(appleBox2);
        System.out.println("Пересыпали из 1 во 2 коробку с яблоками: во 2 коробке стало " + appleBox2.getWeight());
//        appleBox1.intersperse(orangeBox1);

    }

    public static void changePlace(Object[] arr, int i, int j) {
        System.out.println("Исходный массив: " + Arrays.toString(arr));
        Object iMemory = arr[i];
        arr[i] = arr[j];
        arr[j] = iMemory;
        System.out.println("Массив после смены мест " + i + " и " + j + " элементов: " + Arrays.toString(arr));
    }

    public static void toArrayList(Object[] arr) {
        System.out.println("\n== Задание 2 ==\n");
        Arrays.asList(arr);
        System.out.println(Arrays.toString(arr));
    }

}
