package Task3;

import java.util.ArrayList;

public class Box<T> {

    private ArrayList<T> fruitList = new ArrayList<>();

    public ArrayList<T> getFruitList(){
        return fruitList;
    }

    public void addFruit(T fruit){
        fruitList.add(fruit);
    }

    public void addFruits(T fruit, int quantity){
        for (int i = 0; i < quantity; i++) {
            fruitList.add(fruit);
        }
    }

    public float getWeight(){
        if (getFruitList().size() != 0){
            if (getFruitList().get(0) instanceof Apple){
                return getFruitList().size() * new Apple().getWeight();
            } else if (getFruitList().get(0) instanceof Orange){
                return getFruitList().size() * new Orange().getWeight();
            }
        }
        return 0;
    }

    public void printWeight(){
        float weight = 0;
        if (getFruitList().size() != 0){
            if (fruitList.get(0) instanceof Apple){
                weight = fruitList.size() * new Apple().getWeight();
                System.out.println("Вес данной коробки: " + weight);
            } else if (fruitList.get(0) instanceof Orange){
                weight = fruitList.size() * new Orange().getWeight();
                System.out.println("Вес данной коробки: " + weight);
            }
        } else{
            System.out.println("Вес данной коробки: " + weight);
        }
    }

    public boolean compare(Box box){
        if(getWeight() == box.getWeight()){
             return true;
        }
        return false;
    }

    public void intersperseTo(Box<T> box){
        box.getFruitList().addAll(getFruitList());
        getFruitList().clear();
    }
}
