package Task2;
/*
2. Написать метод, который преобразует массив в ArrayList;
 */

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> list;
        Integer [] a = {1,2,3,4,5,6,7,8,9};
        System.out.println(Arrays.toString(a));
        list = new ConvertClass<Integer>().convertMassiveToArrayList(a);
        System.out.println(list.toString());
    }
}
