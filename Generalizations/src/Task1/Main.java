package Task1;

/*
1. Написать метод, который меняет два элемента массива местами
(массив может быть любого ссылочного типа);
 */

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Integer [] mas = {1,2,3,4,5,6,7,8};
        displayBeforeAndAfterSwap(1, 3, mas);
        String [] strMas = {"Дерево", "Собака" , "Птица", "Карусель" };
        displayBeforeAndAfterSwap(1, 3, strMas);

    }

    public static void swap(int a, int b, Object [] mas){
        Object buff = mas[b];
        mas[b] = mas[a];
        mas[a] = buff;
    }

    private static void displayBeforeAndAfterSwap(int a, int b, Object [] mas ){
        System.out.println("\n" + Arrays.toString(mas) + "\n");
        swap(a, b, mas);
        System.out.println(Arrays.toString(mas));
    }

}
