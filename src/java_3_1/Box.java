package java_3_1;

import java.util.ArrayList;

public class Box<T extends Fruit> {

    private ArrayList<T> box = new ArrayList<>();

    public float getWeight() {
        float weight = 0.0f;
        for (T o : box) {
            weight += o.getWeight();
        }
        return weight;
    }

    public boolean compare(Box anotherBox) {
        if (Math.abs(getWeight() - anotherBox.getWeight()) < 0.00001) {
            return true;
        }
        return false;
    }

    public void addFruit(T fruit, int quantity) {
        for (int i = 0; i < quantity; i++) {
            box.add(fruit);
        }
    }

    public void intersperse(Box<T> anotherBox) {
//        if (box.equals(anotherBox)){
            anotherBox.box.addAll(box);
            box.clear();
//        } else {
//            System.out.println("Выберите коробку с одними фруктами для пересыпания");
//        }

    }

}
