package Task3;

public class Main {

    public static void main(String[] args) {
        Box<Apple> appleBox = new Box<>();

        Box<Orange> orangeBox = new Box<>();

        appleBox.addFruit(new Apple());
        orangeBox.addFruit(new Orange());

        System.out.println(appleBox.getWeight());
        System.out.println(orangeBox.getWeight());

        appleBox.printWeight();
        orangeBox.printWeight();

        System.out.println(appleBox.compare(orangeBox));


        Box<Apple> appleBox2 = new Box<>();
        Box<Orange> orangeBox2 = new Box<>();

        System.out.println(appleBox2.getWeight());
        System.out.println(orangeBox2.getWeight());

        appleBox2.printWeight();
        orangeBox2.printWeight();

        System.out.println( "\n" + "Задание с перекладыванием: " + "\n");

        Box<Apple> appleBox3 = new Box<>();
        Box<Apple> appleBox4 = new Box<>();

        Box<Orange> orangeBox3 = new Box<>();
        Box<Orange> orangeBox4 = new Box<>();

        appleBox3.addFruits(new Apple(), 10);
        orangeBox3.addFruits(new Orange(), 10);

        appleBox3.printWeight();
        orangeBox3.printWeight();

        appleBox3.intersperseTo(appleBox4);
        orangeBox3.intersperseTo(orangeBox4);

        System.out.println("После перекладывания: ");

        appleBox3.printWeight();
        orangeBox3.printWeight();

        System.out.println("Коробки, в которые переложили: ");

        appleBox4.printWeight();
        orangeBox4.printWeight();

    }

}
